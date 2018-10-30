package com.wgb.service.mt.act;

import java.util.Map;

/**
 * Created by 11609 on 2017/10/26.
 */
public interface ActService {

    void syncActPlayDetail(Map<String, Object> params);

    void syncDeviceStatus(Map<String, Object> params);
}
