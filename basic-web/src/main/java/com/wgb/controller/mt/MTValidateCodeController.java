package com.wgb.controller.mt;

import com.wgb.exception.ServiceException;
import com.wgb.rocketmq.StringUtil;
import com.wgb.util.VerifyCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * Created by Administrator on 2017/11/28/028.
 */
@Controller
@RequestMapping("")
public final class MTValidateCodeController extends MTBaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/entry/getValidateCode")
    public void getValidateCode(HttpServletRequest request, HttpServletResponse response) {

        try {

            // 1.保存验证码到session
            VerifyCode vc = new VerifyCode();
            BufferedImage bufferedImage = vc.getImage();
            request.getSession().setAttribute("yzcode", vc.getText());

            // 2.输出图片
            ServletOutputStream sos = response.getOutputStream();
            ImageIO.write(bufferedImage, "JPEG", sos);

            // 3.禁止图像缓存
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("image/jpeg");
            // 4.关闭sos
            sos.close();
        }catch(Exception e){
            throw new ServiceException("获取图片验证码失败，请稍后重试！");
        }
    }
}
