package com.wgb.util;

import com.wgb.dao.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.*;

public class CommonUtil {

    public static List<Map<String, Object>> copyArrayList(List<Map<String, Object>> list) {

        if (list == null || list.size() == 0) {
            return new ArrayList<Map<String, Object>>();
        }
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> item : list) {
            result.add(item);
        }
        return result;
    }

    /**
     * 功能描述: <br>
     * 〈获取客户端的IP〉
     *
     * @param request
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {

        if (request.getHeader("X-Requested-With") == null) {
            return false;
        }

        return request.getHeader("X-Requested-With").equals("XMLHttpRequest");
    }

    /**
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Map<String, String> getAliParams(HttpServletRequest request) throws UnsupportedEncodingException {
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //   valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return params;
    }

    /**
     * 获取树形结构的数据
     *
     * @param dataList
     * @param parentId
     * @return
     */
    public static List<Map<String, Object>> getTreeList(List<Map<String, Object>> dataList, String parentId, String name) {
        List<Map<String, Object>> childMenu = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> data : dataList) {
            String menuId = MapUtils.getString(data, "id");
            String pid = MapUtils.getString(data, "pid");
            String _name = MapUtils.getString(data, name);
            data.put("name", _name);
            if (parentId.equals(pid)) {
                List<Map<String, Object>> c_node = getTreeList(dataList, menuId, name);
                data.put("children", c_node);
                childMenu.add(data);
            }
        }
        return childMenu;
    }

    /**
     * @return
     */
    public static String getCurrentYMDStr() {
        String result = DateUtils.formatDate2Str(new Date(), DateUtils.C_DATE_PATTON_DEFAULT1);
        return result;
    }

    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
    }

    public static boolean isUseOss(String flag) {
        if (StringUtils.isEmpty(flag)) {
            return false;
        }

        return flag.equals("1");
    }

    public static boolean isStrExistInStrArr(String str, String[] strArr) {

        if (strArr == null || strArr.length == 0) {
            return false;
        }

        for (String item : strArr) {
            if (item.equals(str)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断字符串是存在于List<Map<String, Object>>中
     *
     * @param str
     * @param list
     * @param key
     * @return
     */
    public static boolean exists(List<Map<String, Object>> list, String key, String str) {

        if (CollectionUtils.isEmpty(list) || StringUtils.isEmpty(key) || StringUtils.isEmpty(str)) {
            return false;
        }

        for (Map<String, Object> item : list) {
            String _str = MapUtils.getString(item, key);
            if (_str.equals(str)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 列表中获取数据
     *
     * @param list
     * @param key
     * @return
     */
    public static List<String> getListStringFromListMap(List<Map<String, Object>> list, String key) {

        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<String>();
        }

        List<String> result = new ArrayList<String>();
        for (Map<String, Object> item : list) {
            result.add(MapUtils.getString(item, key));
        }

        return result;
    }

    /**
     * @param str
     * @return
     */
    public static List<String> getListStringFromSplit(String str) {
        List<String> result = new ArrayList<String>();
        if (StringUtils.isEmpty(str)) {
            return result;
        }

        String[] arr = str.split(",");
        for (String item : arr) {
            result.add(item);
        }
        return result;
    }

    /**
     * 列表中获取数据
     *
     * @param list
     * @param key
     * @return
     */
    public static String getStringFromListMap(List<Map<String, Object>> list, String key) {

        if (CollectionUtils.isEmpty(list)) {
            return "";
        }

        StringBuilder sb = new StringBuilder("");
        for (Map<String, Object> item : list) {

            String value = MapUtils.getString(item, key);

            if (StringUtils.isEmpty(value)) {
                continue;
            }

            if (StringUtils.isNotEmpty(sb.toString())) {
                sb.append(",");
            }

            sb.append(value);
        }

        return sb.toString();
    }

    public static String getFileType(String fileName) {

        if (StringUtils.isEmpty(fileName)) {
            return "";
        }

        String fileNameSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        String[] fileSuffixArr = Contants.IMAGE_SUFFIX.split(",");
        if (fileSuffixArr.length > 0) {
            for (String fileSuffix : fileSuffixArr) {
                if (StringUtils.isNotEmpty(fileSuffix) && fileSuffix.equals(fileNameSuffix)) {
                    return Contants.FILE_TYPE_IMAGE;
                }
            }
        }

        fileSuffixArr = Contants.VIDEO_SUFFIX.split(",");
        if (fileSuffixArr.length > 0) {
            for (String fileSuffix : fileSuffixArr) {
                if (StringUtils.isNotEmpty(fileSuffix) && fileSuffix.equals(fileNameSuffix)) {
                    return Contants.FILE_TYPE_VIDEO;
                }
            }
        }

        return "";
    }

    /**
     * 创建指定数量的随机字符串
     *
     * @param numberFlag 是否是数字
     * @param length
     * @return
     */
    public static String createRandom(boolean numberFlag, int length) {
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr;
    }

    /**
     * 根据值，从列表中遍历对应字段的Map
     *
     * @param list
     * @param column
     * @param value
     * @return
     */
    public static Map<String, Object> getMapFromList(List<Map<String, Object>> list, String column, String value) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        for (Map<String, Object> item : list) {
            if (MapUtils.getString(item, column, "").equals(value)) {
                return item;
            }
        }
        return null;
    }

    /**
     * 根据值，从列表中遍历对应字段的Map
     *
     * @param list
     * @param column
     * @param value
     * @return
     */
    public static boolean KVExistInList(List<Map<String, Object>> list, String column, String value) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        for (Map<String, Object> item : list) {
            if (MapUtils.getString(item, column, "").equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串连接，采用逗号分隔
     *
     * @param list
     * @return
     */
    public static String stringJoin(List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder("");
        for (String str : list) {
            if (StringUtils.isEmpty(str)) {
                continue;
            }
            if (StringUtils.isNotEmpty(sb.toString())) {
                sb.append(",");
            }
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 从字符串List删除str
     *
     * @param list
     * @param str
     * @return
     */
    public static List<String> removeItem(List<String> list, String str) {
        if (CollectionUtils.isNotEmpty(list)) {
            List<String> result = new ArrayList<String>();
            for (String item : list) {
                if (StringUtils.isNotEmpty(item) && item.equals(str)) {
                    continue;
                }
                result.add(item);
            }
            return result;
        }
        return list;
    }

    public static String generateCode(int count) {
        return generateCode(count, 3);
    }

    public static String generateCode(int count, int min) {
        int num = count + 1;
        String code = String.valueOf(num);
        StringBuilder sb = new StringBuilder();
        for (int i = code.length(); i < min; i++) {
            sb.append("0");
        }
        sb.append(code);
        return sb.toString();
    }

    public static boolean pageIsEmpty(Page<Map<String, Object>> pageInfo) {
        if (pageInfo == null || CollectionUtils.isEmpty(pageInfo.getList())) {
            return true;
        }
        return false;
    }

    /**
     * 从登录参数中获取shopcode
     *
     * @param params
     * @return
     */
    public static String getShopCodeFromParams(Map<String, Object> params) {
        return MapUtils.getString(params, Contants.LOGIN_USER_SHOP_CODE);
    }

    /**
     * 从登录参数中获取loginuserbranchcode
     *
     * @param params
     * @return
     */
    public static String getBranchIdFromParams(Map<String, Object> params) {
        return MapUtils.getString(params, Contants.LOGIN_USER_BRANCH_ID);
    }

    /**
     * 将字符串数据组转成ArrayList
     *
     * @param array
     * @return
     */
    public static List<String> asArrayList(String[] array) {
        List<String> result = new ArrayList<String>();
        if (array != null && array.length > 0) {
            for (String item : array) {
                if (StringUtils.isEmpty(item)) {
                    continue;
                }
                result.add(item);
            }
        }
        return result;
    }

    /**
     * 获取树形结构的数据
     *
     * @param dataList
     * @param parentId
     * @return
     */
    public static List<Map<String, Object>> getTreeList(List<Map<String, Object>> dataList, String parentId) {
        List<Map<String, Object>> childMenu = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> data : dataList) {
            String menuId = MapUtils.getString(data, "id");
            String pid = MapUtils.getString(data, "pid");
            if (parentId.equals(pid)) {
                List<Map<String, Object>> c_node = getTreeList(dataList, menuId);
                data.put("childList", c_node);
                childMenu.add(data);
            }
        }
        return childMenu;
    }


    public static boolean isStrExistInList(String str, List<Map<String, Object>> list, String key) {

        if (CollectionUtils.isEmpty(list)) {
            return false;
        }

        for (Map<String, Object> item : list) {
            String _str = MapUtils.getString(item, key, "");
            if (_str.equals(str)) {
                return true;
            }
        }

        return false;

    }

    public static boolean isApiCallSuccess(Map<String, Object> result) {
        return MapUtils.isNotEmpty(result) && MapUtils.getString(result, "success", "").equals("1");
    }

    public static boolean isSmsType(String type) {

        /*if (StringUtils.isEmpty(type) || (type.equals(Contants.SMS_REG_TYPE) == false && type.equals(Contants.SMS_RESET_TYPE) == false && type.equals(Contants.SMS_LOGIN_TYPE) && type.equals(Contants.SMS_DIST_TYPE))) {
            return false;
        }*/

        if (StringUtils.isEmpty(type) || type.equals(Contants.SMS_REG_TYPE) == false) {
            return false;
        }

        return true;
    }

    /**
     * 获取完整的请求路径
     *
     * @param request
     * @return
     */
    public static String getRedirectUrl(HttpServletRequest request) {

        String httpFullUrl = getHttpFullUrl(request, request.getRequestURI());
        StringBuilder sb1 = new StringBuilder(httpFullUrl);
        Map<String, Object> params = ParamsUtil.handleServletParameter(request);
        StringBuilder sb2 = new StringBuilder("");

        if (params.size() > 0) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (StringUtils.isNotEmpty(sb2.toString())) {
                    sb2.append("&");
                }
                sb2.append(entry.getKey() + "=" + entry.getValue());
            }

            if (sb1.indexOf("?") != -1) {
                sb1.append("&");
            } else {
                sb1.append("?");
            }
            sb1.append(sb2);
        }
        return sb1.toString();
    }

    /**
     * @param request
     * @param url
     * @return
     */
    public static String getHttpFullUrl(HttpServletRequest request, String url) {
        String portString = "";
        int port = request.getServerPort();
        if (port != 80 && port != 443) {
            portString = ":" + port;
        }
        return new StringBuilder("").append(request.getScheme()).append("://").append(request.getServerName()).append(portString).append(url).toString();
    }

    /**
     * 获取文件完整URL
     *
     * @param url
     * @return
     */
    public static String getFileUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            return "";
        }
        return CommonConfig.OSS_URL + "/" + url;
    }

    //获取request中的xml
     public static String getXML(HttpServletRequest request) throws IOException{
         InputStream inputStream;

         StringBuffer sb = new StringBuffer();

         inputStream = request.getInputStream();

         String s;

         BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
         String xml = null;
         while ((s = in.readLine()) != null) {

             sb.append(s);
              xml = sb.toString();
         }

         in.close();
         inputStream.close();
         return  xml;
     }

    //XML格式的数据转化成Map类型
    public static Map<String, Object> getMapFromXml(String strXML) throws Exception {
        try {
            Map<String, Object> data = new HashMap<>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            try {
                stream.close();
            } catch (Exception ex) {
                // do nothing
            }
            return data;
        } catch (Exception ex) {
//            WXPayUtil.getLogger().warn("Invalid XML, can not convert to map. Error message: {}. XML content: {}", ex.getMessage(), strXML);
            throw ex;
        }

    }

}
