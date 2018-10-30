package com.wgb.controller.mb.wxms;

import com.wgb.bean.ZLResult;
import com.wgb.controller.mb.MBBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitCsHomeService;
import com.wgb.service.dubbo.wxms.web.ApitWxShopMemberService;
import com.wgb.util.HttpRequestConstant;
import com.wgb.util.SystemConfig;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wgy on 2018/4/19.
 */
@Controller
@RequestMapping("/wxcs/home")
public class MBCsHomeController extends MBBaseController {

    @Autowired
    private ApitCsHomeService apitCsHomeService;
    @Autowired
    private ApitWxShopMemberService apitWxShopMemberService;
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 用户登录接口
     */
    @RequestMapping("/home")
    @ResponseBody
    public ZLResult getHomeDate(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        Map<String,Object> resultmap=new HashMap<>();
        String membercode=MapUtils.getString(params,"loginmembercode");
        params.put("membercode",membercode);
        try {
            String shopcode = MapUtils.getString(params, "shopcode", "");
            String shopname = MapUtils.getString(params, "shopname", "");
            String branchcode = MapUtils.getString(params, "branchcode", "");
            //请求过来就判断是否有带branchcode进来
            if(StringUtils.isEmpty(branchcode) || "".equals(branchcode)){
                //请求里没有branchcode去cookie中查看一下有没有branchcode
                Map<String,Object> map=getCookies(request);
                //   String _branchcode=MapUtils.getString(map,"branchcode_10000001");
               // String _branchcode=MapUtils.getString(map,shopcode+"_branchcod");
                 String _branchcode=MapUtils.getString(map, HttpRequestConstant.MB_WX_BRANCHCODE_PREFIX+shopcode);
                if(StringUtils.isEmpty(_branchcode) || "".equals(_branchcode)){
                    //说明此用户之前一个门店都没选择过则跳转到门店选择页面
                    //获取门店列表
                   // Page<?> branchList= branchService.getBranchListfForWXForSlave(params);
                       zlRpcResult=apitCsHomeService.getHomeBranchList(params);
//                    request.setAttribute("branchList", branchList.getList());
//                    request.setAttribute("shopname", shopname);
                    resultmap.put("branchList",zlRpcResult.getList());
                    resultmap.put("shopname",shopname);

                }else{
                    //说明之前有登录过门店的痕迹
                    //直接跳转到对应的门店主页
                    //获取门店首页
                      params.put("branchcode",_branchcode);
//                    Map<String, Object> homeData = homeService.getHomeData(params);
//                    HttpSession session = request.getSession();
//                    session.setAttribute("loginbranchinfo",MapUtils.getMap(homeData,"branch"));
//                    homeData.put("branchcode",_branchcode);
                      zlRpcResult=apitCsHomeService.getHomeDate(params);
                      //查询一下用户信息给前端
                      ZLRpcResult rpcResult = apitWxShopMemberService.getLoginMemberInfo(params);
                      Map<String,Object> memberinfo=rpcResult.getMap();

                      Map<String,Object> homeData=new HashMap<>();
                      homeData=zlRpcResult.getMap();
                      homeData.put("branchcode",_branchcode);
                      resultmap.put("homeData",homeData);
                      resultmap.put("memberinfo",memberinfo);
                }
            }else{
                //说明请求带了branchcode则先把此branchcode覆盖一下原来的cookie
                //将门店编码保存到cookie中
                logger.info("门店登录准备塞cooike");
                Cookie cookie = new Cookie(HttpRequestConstant.MB_WX_BRANCHCODE_PREFIX+shopcode,branchcode);

                //设置生命周期为1年，秒为单位
                cookie.setMaxAge(24*60*60*30*12);
                cookie.setPath("/");
                response.addCookie(cookie);
                
                logger.info("打印一下登录后已经塞过的cookie:"+cookie);
                //再跳转到对应的门店主页
//                Map<String, Object> homeData = homeService.getHomeData(params);
//                HttpSession session = request.getSession();
//                session.setAttribute("loginbranchinfo",MapUtils.getMap(homeData,"branch"));
//                homeData.put("branchcode",branchcode);
            /*logger.info("获取传的参数params:" + params);
            logger.info("获取微信主页获取的数据为:" + homeData);*/
                zlRpcResult=apitCsHomeService.getHomeDate(params);
                Map<String,Object> homeData=new HashMap<>();
                homeData=zlRpcResult.getMap();

                //查询一下用户信息给前端
                ZLRpcResult rpcResult = apitWxShopMemberService.getLoginMemberInfo(params);
                Map<String,Object> memberinfo=rpcResult.getMap();

                homeData.put("branchcode",branchcode);
                resultmap.put("homeData",homeData);
                resultmap.put("memberinfo",memberinfo);
            }

        } catch (Exception ex) {
            // 系统级别异常throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        String paysdomian = SystemConfig.ZL_WEB_PAYS_DOMAIN;
        resultmap.put("paysdomain", paysdomian);
        return ZLResult.Success(resultmap);
    }

    @RequestMapping("/choseBranchAgain")
    @ResponseBody
    public ZLResult choseBranchAgain(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitCsHomeService.choseBranchAgain(params);
        } catch (Exception ex) {
            // 系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        // 判断返回结果
        if (!zlRpcResult.success()) {
            throw new ServiceException(zlRpcResult.getErrorMsg());
        }
        return ZLResult.Success(zlRpcResult.getData());
    }



    /**
     * \
     * @param //获取浏览器的cookie判断用户是否登录过
     * @return
     */
    private Map<String, Object> getCookies(HttpServletRequest request) {
        HashMap result = new HashMap();
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            Cookie[] arr$ = cookies;
            int len$ = cookies.length;
            for(int i$ = 0; i$ < len$; ++i$) {
                Cookie cookie = arr$[i$];
                result.put(cookie.getName(), cookie.getValue());
            }
        }

        return result;
    }

}
