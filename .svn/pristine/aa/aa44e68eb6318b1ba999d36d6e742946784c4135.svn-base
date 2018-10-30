package com.wgb.controller.lm.img;

import com.wgb.bean.ZLResult;
import com.wgb.controller.lm.LMXCXBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.lms.web.ApitImgService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("entry/img")
public class ImgController extends LMXCXBaseController {

    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(ImgController.class);

    @Autowired
    private ApitImgService apitImgService;

    @RequestMapping("query")
    @ResponseBody
    public ZLResult queryHomeImg(HttpServletRequest request) {

        //获取入参数据
        Map<String, Object> params =  getParams();

        //定义返回对象
        ZLRpcResult zlRpcResult  = new ZLRpcResult();
        //校验参数
        try {
            //系统调用
            zlRpcResult = apitImgService.selectImg(params);
        }catch (Exception ex){
            ex.printStackTrace();
            //系统级别异常
            throw new ServiceException(ServiceException.SYS_ERROR);
        }
        //判断返回结果
        if(!zlRpcResult.success()){
            throw  new ServiceException(zlRpcResult.getErrorMsg());
        }

        return   ZLResult.Success(zlRpcResult.getData());
    }

}
