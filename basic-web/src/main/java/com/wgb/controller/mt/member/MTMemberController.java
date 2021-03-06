package com.wgb.controller.mt.member;

import com.alibaba.fastjson.JSON;
import com.wgb.bean.ZLResult;
import com.wgb.cache.RedisFactory;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dao.Page;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.CacheService;
import com.wgb.service.dubbo.mbms.admin.ApiMemberService;
import com.wgb.service.dubbo.mbms.web.ApitMemberService;
import com.wgb.util.Contants;
import com.wgb.util.ExcelReader;
import com.wgb.util.ExcelUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
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
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/member/member")
public class MTMemberController extends MTBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MTMemberController.class);

    @Autowired
    ApitMemberService apitMemberService;

    @Autowired
    private ApiMemberService apiMemberService;

    /**
     * 零售 - 上传单条销售单
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryMemberInfo")
    @ResponseBody
    public ZLResult queryMemberInfo(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String keyword = MapUtils.getString(params, "keyword");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        if (StringUtils.isEmpty(keyword)) {
            return ZLResult.Success();
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMemberService.queryMemberInfo(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 会员 - 修改会员
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateMember")
    @ResponseBody
    public ZLResult updateMember(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String memberid = MapUtils.getString(params, "memberid");
        String membermodel = MapUtils.getString(params, "membermodel");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(memberid)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(membermodel)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMemberService.updateMember(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 会员 -添加会员
     *
     * @param request
     * @return
     */
    @RequestMapping("/addMember")
    @ResponseBody
    public ZLResult addMember(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String telephone = MapUtils.getString(params, "telephone");
        String payauthflag = MapUtils.getString(params, "payauthflag");
        String membermodel = MapUtils.getString(params, "membermodel");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(telephone)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(payauthflag)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(membermodel)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        //该会员支持无卡消费
        if (payauthflag.equals("1")) {

            //进行验证码验证
            String yzm = MapUtils.getString(params, "yzm");

            if (StringUtils.isEmpty(yzm)) {
                throw new ServiceException(ServiceException.PARAM_MISSING);
            }

            //从缓存中取出对应的验证码
            // String _yzm = cacheService.getYzm(shopcode + "_" + telephone, Contants.SMS_MEMBER_PAY_AUTH_TYPE);

            //从缓存中取出对应的验证码
            String mobileKey = Contants.SMS_MEMBER_PAY_AUTH_TYPE + telephone;
            String _yzm = RedisFactory.getDefaultClient().get(mobileKey);

            //验证码过期或者错误
            if (StringUtils.isEmpty(_yzm) || !_yzm.equals(yzm)) {
                throw new ServiceException(ServiceException.CODE_20707);
            }
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMemberService.addMember(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if(!zlRpcResult.success()){
            String errorMsg = zlRpcResult.getErrorMsg();
            int errorCode = zlRpcResult.getErrorCode();
            if (StringUtils.isEmpty(errorMsg)) {
                return ZLResult.Error(errorCode);
            }
            return ZLResult.Error(errorMsg);
        }

        return ZLResult.Success(zlRpcResult.getData());
    }


    /**
     * 会员 -添加会员
     *
     * @param request
     * @return
     */
    @RequestMapping("/addMemberForCardModel")
    @ResponseBody
    public ZLResult addMemberForCardModel(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String telephone = MapUtils.getString(params, "telephone");
        String payauthflag = MapUtils.getString(params, "payauthflag");
        String membermodel = MapUtils.getString(params, "membermodel");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(telephone)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(payauthflag)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(membermodel)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        //进行验证码验证
        String yzm = MapUtils.getString(params, "yzm");

        if (StringUtils.isEmpty(yzm)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        //从缓存中取出对应的验证码
        // String _yzm = cacheService.getYzm(shopcode + "_" + telephone, Contants.SMS_MEMBER_PAY_AUTH_TYPE);

        //从缓存中取出对应的验证码
        String mobileKey = null;
        if (payauthflag.equals("1")) {
            mobileKey = Contants.SMS_MEMBER_PAY_AUTH_TYPE + telephone;
        } else {
            mobileKey = Contants.SMS_MEMBER_CARDPAY_AUTH_TYPE + telephone;
        }

        String _yzm = RedisFactory.getDefaultClient().get(mobileKey);

        //验证码过期或者错误
        if (StringUtils.isEmpty(_yzm) || !_yzm.equals(yzm)) {
            throw new ServiceException(ServiceException.CODE_20707);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMemberService.addMember(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            String errorMsg = zlRpcResult.getErrorMsg();
            int errorCode = zlRpcResult.getErrorCode();
            if (StringUtils.isEmpty(errorMsg)) {
                return ZLResult.Error(errorCode);
            }
            return ZLResult.Error(errorMsg);
        }
        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 会员-修改会员卡号
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateCardNo")
    @ResponseBody
    public ZLResult updateCardNo(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String memberid = MapUtils.getString(params, "memberid");
        String cardno = MapUtils.getString(params, "cardno");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(memberid)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMemberService.updateCardNo(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }

        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 会员-修改会员卡号
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateMemberPayAuth")
    @ResponseBody
    public ZLResult updateMemberPayAuth(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String memberid = MapUtils.getString(params, "memberid");
        String telephone = MapUtils.getString(params, "telephone");
        String payauthflag = MapUtils.getString(params, "payauthflag");
        String membermodel = MapUtils.getString(params, "membermodel");

        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(memberid)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(telephone)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(payauthflag)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(membermodel)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        //该会员支持无卡消费
        if (payauthflag.equals("1")) {

            //进行验证码验证
            String yzm = MapUtils.getString(params, "yzm");

            if (StringUtils.isEmpty(yzm)) {
                throw new ServiceException(ServiceException.PARAM_MISSING);
            }

            //从缓存中取出对应的验证码
            String mobileKey = Contants.SMS_MEMBER_PAY_AUTH_TYPE + telephone;
            String _yzm = RedisFactory.getDefaultClient().get(mobileKey);
            //验证码过期或者错误
            if (StringUtils.isEmpty(_yzm) || !_yzm.equals(yzm)) {
                throw new ServiceException(ServiceException.CODE_20708);
            }
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMemberService.updateMemberPayAuth(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 会员-修改会员卡号
     *
     * @param request
     * @return
     */
    @RequestMapping("/changeMemberPayWay")
    @ResponseBody
    public ZLResult changeMemberPayWay(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();

        String shopcode = MapUtils.getString(params, "shopcode");
        String memberid = MapUtils.getString(params, "memberid");
        String telephone = MapUtils.getString(params, "telephone");
        String payauthflag = MapUtils.getString(params, "payauthflag");
        String membermodel = MapUtils.getString(params, "loginusermembermodel", "0");
        params.put("membermodel", membermodel);
        if (StringUtils.isEmpty(shopcode)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(memberid)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(telephone)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(payauthflag)) {
            throw new ServiceException(ServiceException.PARAM_MISSING);
        }

        ZLRpcResult zlRpcResult = null;
        try {
            zlRpcResult = apiMemberService.updateMemberPayAuth(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    @RequestMapping("/query")
    @ResponseBody
    public ZLResult query(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberService.queryPageList(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        Page<?> pageInfo = (Page<?>) zlRpcResult.getData();

        return ZLResult.Success(pageInfo);
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ZLResult saveOrUpdate(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberService.saveOrUpdate(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ZLResult delete(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberService.deleteObject(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/manageExport")
    public ZLResult manageExport(HttpServletRequest request, HttpServletResponse response) {

        //获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberService.manageExport(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }
        List<Map<String, Object>> manageExportList = zlRpcResult.getList();
        // 字段名数组
        String[] members = {"memberno", "name", "telephone", "birthday",
                "sex", "createbranchname", "createtime", "useable"};
        // 字段名数组
        String[] memberNames = {"会员号", "会员名称", "手机号", "会员生日",
                "性别", "开卡门店", "开卡时间", "状态"};
        String menberName = "会员资料表";
        ExcelUtil.exportExcel(manageExportList, members, memberNames, menberName, request, response);

        return ZLResult.Success();
    }

    @RequestMapping("/updateSms")
    @ResponseBody
    public ZLResult updateSms(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberService.updateSms(params);
        } catch (Exception ex) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success();
    }

    @RequestMapping("/getObject")
    @ResponseBody
    public ZLResult getObject(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params = getParams();
        // 定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            zlRpcResult = apitMemberService.getObject(params);
        } catch (Exception e) {
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            return ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());
    }

    /**
     * 会员资料-批量导入
     *
     * @param file
     * @param request
     * @param response
     */
    @RequestMapping("/manageImport")
    @ResponseBody
    public ZLResult manageImport(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult = new ZLRpcResult();

        try {
            // 字段名数组
            String[] columns = {"name", "telephone", "birthday", "levelname", "smsnotifiyflag", "sex", "credit"};
            // 模板标题
            String[] columnNames = {"会员姓名", "手机号", "会员生日", "会员等级", "短信通知", "性别", "会员积分"};
            List<Map<String, Object>> dataList = ExcelReader.read(file.getInputStream(), columns, columnNames, 2);
            params.put("fileDateList", dataList);
            //系统调用
            zlRpcResult = apitMemberService.manageImport(params);

        } catch (ServiceException sx) {
            //服务型异常
            throw sx;
        } catch (Exception ex) {
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if (!zlRpcResult.success()) {
            ZLResult.Error(zlRpcResult.getErrorMsg());
        }

        return ZLResult.Success(zlRpcResult.getData());

    }

}
