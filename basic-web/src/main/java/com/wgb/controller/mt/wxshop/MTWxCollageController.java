package com.wgb.controller.mt.wxshop;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.wgb.bean.ZLResult;
import com.wgb.controller.mt.MTBaseController;
import com.wgb.dubbo.ZLRpcResult;
import com.wgb.exception.ServiceException;
import com.wgb.service.dubbo.wxms.web.ApitWxCollageService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by 10057 on 2018/6/21.
 */
@Controller
@RequestMapping("/wxshop/collage")
public class MTWxCollageController extends MTBaseController {

    @Autowired
    private ApitWxCollageService apitWxCollageService;

    /**
     * 查询拼团活动
     *
     * @param request
     * @return
     */
    @RequestMapping("/queryPageList")
    @ResponseBody
    public ZLResult queryPageList(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxCollageService.queryCollage(params);
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
     * 添加拼团活动
     *
     * @param request
     * @return
     */
    @RequestMapping("/addCollage")
    @ResponseBody
    public ZLResult addCollage(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
        params.put("url", basePath);
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxCollageService.addCollage(params);
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
     * 修改拼团活动
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateCollage")
    @ResponseBody
    public ZLResult updateCollage(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxCollageService.updateCollage(params);
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
     * 发布关闭拼团活动
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateStatusCollage")
    @ResponseBody
    public ZLResult updateStatusCollage(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxCollageService.updateStatusCollage(params);
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
     * 删除拼团活动
     *
     * @param request
     * @return
     */
    @RequestMapping("/delCollage")
    @ResponseBody
    public ZLResult delCollage(HttpServletRequest request) {
        Map<String, Object> params = getParams();
        ZLRpcResult zlRpcResult = new ZLRpcResult();
        try {
            // 系统调用
            zlRpcResult = apitWxCollageService.delCollage(params);
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

    @RequestMapping("/downloadQRCodeImage")
    @ResponseBody
    public ZLResult createQRCodeImage(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = getParams();
        int titleFontSize = 70;
        int subTitleFontSize = 50;

        Font titleFont = new Font("Microsoft YaHei", Font.PLAIN, titleFontSize);
        Font subTitleFont = new Font("Microsoft YaHei", Font.PLAIN, subTitleFontSize);
        int baseWidth = 740;
        int baseHeight = 840;
        int qrWidth = 550;
        int qrHeight = 550;
        int qrYOffset = 145;
        Integer onColor = 0xFF000000;
        Integer offColor = 0xFFFFFFFF; //背景色
        String content = MapUtils.getString(params, "hyperlink");
        BufferedImage baseImg;
        try {
            //生成二维码
            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
            // 指定纠错等级
            hints.put(EncodeHintType.ERROR_CORRECTION, "L");
            // 指定编码格式
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 0);   //设置白边

            //构建 qr
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrWidth, qrHeight, hints);
            MatrixToImageConfig config = new MatrixToImageConfig(onColor, offColor);
            BufferedImage qrImg = MatrixToImageWriter.toBufferedImage(bitMatrix, config);

            //构建底图
            baseImg = new BufferedImage(baseWidth, baseHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D baseImgGraphics = baseImg.createGraphics();
            Color c = baseImgGraphics.getColor();
            baseImgGraphics.setColor(Color.WHITE);
            baseImgGraphics.fillRect(0, 0, baseWidth, baseHeight);
            baseImgGraphics.setColor(c);
            //画
            baseImgGraphics.drawImage(qrImg, (baseWidth - qrWidth) / 2, baseHeight - qrHeight - qrYOffset, qrWidth, qrHeight, null);
            baseImgGraphics.dispose();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(baseImg, "png", out);
            byte[] b = out.toByteArray();
            response.setHeader("Content-Type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=QRCodeImage.png");
            response.getOutputStream().write(b);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ZLResult.Error("生成失败！");
    }

}
