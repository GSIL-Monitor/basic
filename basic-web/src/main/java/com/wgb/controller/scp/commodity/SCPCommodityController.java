package com.wgb.controller.scp.commodity;

import com.alibaba.fastjson.JSON;
import com.wgb.bean.ZLResult;
import com.wgb.controller.scp.SCPBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.dcms.web.ApitSCPCommodityFromDcmsService;
import com.wgb.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/scpcommodity/commodity")
public class SCPCommodityController extends SCPBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApitSCPCommodityFromDcmsService apitSCPCommodityFromDcmsService;

    /**
     * 商品资料(档案)页面(不存在调价,没有门店,供应商级数据)
     *
     * @return
     * @paramrequest
     */
    @RequestMapping("/queryCommoditys")
    @ResponseBody
    public ZLResult queryCommoditys(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPCommodityFromDcmsService.querySCPCommoditys(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 修改供应商商品-查询
     *
     * @param request
     * @return
     */
    @RequestMapping("/getScpCommodityById")
    @ResponseBody
    public ZLResult getScpCommodityById(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPCommodityFromDcmsService.getScpCommodityById(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getMap());
    }

    /**
     * 输条形码，查出商品库商品信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryCommodityLibraryForSCP")
    @ResponseBody
    public ZLResult queryBarLibInfoByBarcode(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPCommodityFromDcmsService.queryCommodityLibraryForSCP(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getMap());
    }

    /**
     * 新增修改商品
     */
    @RequestMapping("/saveOrUpdateForSCP")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPCommodityFromDcmsService.saveOrUpdateForSCP(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 删除商品
     *
     * @param
     * @return
     */
    @RequestMapping("/deleteSCPCommodity")
    @ResponseBody
    public ZLResult delCommodity(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPCommodityFromDcmsService.deleteSCPCommodity(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    /**
     * 修改售价
     *
     * @param
     * @return
     */
    @RequestMapping("/updateSCPCommoditySaleprice")
    @ResponseBody
    public ZLResult updateSCPCommoditySaleprice(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPCommodityFromDcmsService.updateSCPCommoditySaleprice(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    /**
     * 修改商品状态
     *
     * @param
     * @return
     */
    @RequestMapping("/updateSCPCommoditystatus")
    @ResponseBody
    public ZLResult updateSCPCommoditystatus(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPCommodityFromDcmsService.updateSCPCommoditystatus(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success();
    }

    /**
     * 商品档案导出
     *
     * @Param request, response
     */
    @RequestMapping("/exportCommodity")
    @ResponseBody
    public void exportCommodity(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getParams();
        exportMethod(params, request, response);
    }

    //处理商品列表 - 导出到excel
    private void exportMethod(Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitSCPCommodityFromDcmsService.exportSCPCommodity(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        List<Map<String, Object>> commodityList = zlRpcResult.getList();

        String[] columns = {"commoditycode", "commodityname", "barcodes", "saleprice", "spec", "unitname", "categoryname",
                "brandname"};

        // 列名数组
        String[] columnNames = {"货号", "商品名称", "商品条码", "售价", "商品规格", "商品单位", "商品分类",
                "商品品牌"};
        String fileName = "商品资料";
        ExcelUtil.exportExcel(commodityList, columns, columnNames, fileName, request, response);
    }

    //商品导入
    @RequestMapping("/importCommodity")
    @ResponseBody
    public void importCommodity(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        Map<String, Object> result = null;
        try {

            //读取导入数据
            List<Map<String, Object>> dataList = commodityRead(file, params, request);

            //导入限制大小
            if (dataList != null && dataList.size() > 2000) {
                throw new ServiceException("一次只能导入2000条商品数据!");
            }
            params.put("dataList", dataList);

            //dubbo调用
            zlRpcResult = apitSCPCommodityFromDcmsService.importSCPCommodity(params);

            //处理调用结果
            if (zlRpcResult.success()) {
                result = zlRpcResult.getMap();
                String url = saveHandleResult(result);
                result.put("url", url);
                result.remove("errorList");
            } else {
                throw new ServiceException("导入异常," + zlRpcResult.getErrorMsg());
            }
        } catch (ServiceException e) {
            result = new HashMap<String, Object>();
            result.put("success", "0");
            result.put("errorMsg", e.getMessage());
            logger.error("EXCEL处理异常0" + e.getMessage());
        } catch (Exception e) {
            result = new HashMap<String, Object>();
            result.put("success", "0");
            result.put("errorMsg", "导入异常");
            logger.error("EXCEL处理异常1" + e.toString());
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, muste-rvalidate");
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException e) {
            this.logger.error("与客户端通讯异常：" + e.getMessage(), e);
            e.printStackTrace();
        }
    }


    private String saveHandleResult(Map<String, Object> result) {
        List<Map<String, Object>> errorList = (List<Map<String, Object>>) result.get("errorList");
        if (CollectionUtils.isNotEmpty(errorList)) {
            // 字段名数组
            String[] columns = {"commoditycode", "commodityname", "barcodes", "saleprice", "spec", "unitname", "categoryname",
                    "brandname", "errorMsg"};
            // 模板标题
            String[] columnNames = {"货号", "商品名称", "商品条码", "售价", "商品规格", "商品单位", "商品分类",
                    "商品品牌", "错误信息"};
            //文件名
            String fileName = "商品资料导入错误报表";
            //上传
            return upLoadNewExcel(errorList, columns, columnNames, fileName);
        }
        return "";
    }

    private List<Map<String, Object>> commodityRead(MultipartFile file, Map<String, Object> params, HttpServletRequest request) throws IOException {
        // 字段名数组
        String[] columns = {"commoditycode", "commodityname", "barcodes", "saleprice", "spec", "unitname", "categoryname",
                "brandname"};

        // 模板标题
        String[] columnNames = {"货号", "商品名称", "商品条码", "售价", "商品规格", "商品单位", "商品分类",
                "商品品牌"};


        //把表格中数据按行读取，每一行放到一个Map中，组合一个List集合
        List<Map<String, Object>> dataList = ExcelReader.read(file.getInputStream(), columns, columnNames, 1);
        return dataList;
    }

    private Map<String, Object> getStorageMap(List<Map<String, Object>> storageList, String commoditycode) {
        if (CollectionUtils.isNotEmpty(storageList)) {
            for (Map<String, Object> storageMap : storageList) {
                String _commoditycode = MapUtils.getString(storageMap, "commoditycode");
                if (_commoditycode.equals(commoditycode)) {
                    return storageMap;
                }
            }
        }
        return null;
    }

    private void setCommodityStorage(List<Map<String, Object>> dataList, List<Map<String, Object>> storageList) {
        for (Map<String, Object> dataItem : dataList) {
            String commoditycode = MapUtils.getString(dataItem, "commoditycode");
            Map<String, Object> storageMap = getStorageMap(storageList, commoditycode);
            if (MapUtils.isEmpty(storageMap)) {
                dataItem.put("storage", 0);
            } else {
                dataItem.put("storage", MapUtils.getIntValue(storageMap, "storage", 0));
            }
        }
    }

    //直接生成excel对象上传
    public String upLoadNewExcel(List<Map<String, Object>> list, String[] columns, String[] columnNames, String fileName) {
        //无数据直接返回""
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }
        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        try {
            HSSFWorkbook e = new HSSFWorkbook();
            Sheet sheet = e.createSheet("sheet0");
            Row row = null;
            Cell cell = null;
            Map temp = null;
            row = sheet.createRow(0);
            int[] rowMaxBytes = new int[columnNames.length];

            int size;
            for (size = 0; size < columnNames.length; ++size) {
                cell = row.createCell(size);
                cell.setCellValue(columnNames[size]);
                rowMaxBytes[size] = columnNames[size].getBytes().length * 2 * 256;
            }

            size = list.size();
            int userAgent;
            for (userAgent = 0; userAgent < size; ++userAgent) {
                temp = (Map) list.get(userAgent);
                row = sheet.createRow(userAgent + 1);
                int j = 0;
                String[] arr$ = columns;
                int len$ = columns.length;

                for (int i$ = 0; i$ < len$; ++i$) {
                    String column = arr$[i$];
                    cell = row.createCell(j);
                    cell.setCellValue(MapUtils.getString(temp, column, ""));
                    int var45 = MapUtils.getString(temp, column, "").getBytes().length * 2 * 256;
                    if (var45 >= rowMaxBytes[j]) {
                        rowMaxBytes[j] = var45;
                    }
                    ++j;
                }
            }

            for (userAgent = 0; userAgent < columnNames.length; ++userAgent) {
                int columnWidth = rowMaxBytes[userAgent];
                sheet.setColumnWidth(userAgent, columnWidth < 255 * 256 ? columnWidth : 255 * 256);
            }
            //生成文件名
            fileName = fileName + UUID.randomUUID().toString() + ".xls";
            out = new ByteArrayOutputStream();
            //写入输出流
            e.write(out);
            //转换为输入流
            in = new ByteArrayInputStream(out.toByteArray());
            //文件上传存放路径
            String relativeFilePath = "attachment/scpcommodityexcel/" + CommonUtil.getCurrentYMDStr() + "/" + fileName;
            //上传
            OssUtil.uploadFile2OSS(in, relativeFilePath);
            //返回路径
            return relativeFilePath;
        } catch (UnsupportedEncodingException var41) {
            logger.error("不支持的字符编码");
        } catch (FileNotFoundException var42) {
            logger.error("模板文件找不到");
        } catch (IOException var43) {
            logger.error("IO异常");
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException var40) {
                    logger.error("上传错误信息失败!");
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException var39) {
                    logger.error("上传错误信息失败!");
                }
            }
        }
        return "";
    }

    /**
     * 供应商后台-查询商品类别信息
     * @param request
     * @return
     */
    @RequestMapping("/getCommodityCategoryList")
    @ResponseBody
    public ZLResult getCommodityCategoryList(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPCommodityFromDcmsService.getCommodityCategory(params);
        }catch (Exception ex){
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
}
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }
        return   ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 1.出入库查询供应商商品page
     *
     * @param request
     * @return
     */
    @RequestMapping("/getScpCommodityPageForOtherSys")
    @ResponseBody
    public ZLResult getScpCommodityPageForOtherSys(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            //系统调用
            zlRpcResult = apitSCPCommodityFromDcmsService.getScpCommodityForOtherSys(params);
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ex.getMessage());
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }


}
