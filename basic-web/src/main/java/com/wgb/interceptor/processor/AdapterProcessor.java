package com.wgb.interceptor.processor;

import com.wgb.util.HttpRequestConstant;
import com.wgb.util.ParamsUtil;
import com.wgb.util.SystemConfig;
import com.wgb.util.SystemStartEnv;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by wgb on 2018/8/9 0009.
 */
public abstract class AdapterProcessor implements AuthProcessor {

    protected String path;
    protected String sign;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public boolean adapter(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uri = request.getRequestURI();
        return uri.startsWith(this.path);
    }

    @Override
    public boolean adapterSign(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> params = ParamsUtil.handleServletParameter(request);
        String sign = MapUtils.getString(params, HttpRequestConstant.ZL_REQUEST_SIGN, "");

        if (StringUtils.isNotEmpty(sign) && !SystemConfig.SYSTEM_START_ENV.equalsIgnoreCase(SystemStartEnv.prod.toString())) {
            return sign.equals(this.sign);
        } else {
            String serverName = request.getServerName();
            return serverName.startsWith(this.sign);
        }
    }
}
