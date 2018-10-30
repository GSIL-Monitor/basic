package com.wgb.service.impl.impl.act;


import com.wgb.rocketmq.annotation.MQWired;
import com.wgb.service.impl.send.SyncActDevcieStatus2ACTS;
import com.wgb.service.impl.send.SyncActPlayDetail2ACTS;
import com.wgb.service.mt.act.ActService;
import com.wgb.util.RscUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yjw on 2016/8/27.
 */
@Service
public class ActServiceImpl implements ActService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private SyncActPlayDetail2ACTS syncActPlayDetail2ACTS;

    private SyncActDevcieStatus2ACTS syncActDevcieStatus2ACTS;

    @MQWired
    public void setSyncActPlayDetail2ACTS(SyncActPlayDetail2ACTS syncActPlayDetail2ACTS) {
        this.syncActPlayDetail2ACTS = syncActPlayDetail2ACTS;
    }

    @MQWired
    public void setSyncActDevcieStatus2ACTS(SyncActDevcieStatus2ACTS syncActDevcieStatus2ACTS) {
        this.syncActDevcieStatus2ACTS = syncActDevcieStatus2ACTS;
    }

    /**
     * 保存广告播放记录到acts系统
     *
     * @param params
     */
    @Override
    public void syncActPlayDetail(Map<String, Object> params) {

        Map<String, Object> syncParams = new LinkedHashMap<String, Object>();
        Map<String, Object> items = new LinkedHashMap<String, Object>();
        List<Map<String, Object>> item = new ArrayList<Map<String, Object>>();

        item.add(params);
        items.put("item", item);
        syncParams.put("items", items);

        try {
            String message = RscUtil.objectToMessage(syncParams);
            // 保存广告播放记录到acts系统
            syncActPlayDetail2ACTS.send(message);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }


    }

    @Override
    public void syncDeviceStatus(Map<String, Object> params) {

        Map<String, Object> syncParams = new LinkedHashMap<String, Object>();
        Map<String, Object> items = new LinkedHashMap<String, Object>();
        List<Map<String, Object>> item = new ArrayList<Map<String, Object>>();

        item.add(params);

        items.put("item", item);
        syncParams.put("items", items);

        try {
            String message = RscUtil.objectToMessage(syncParams);
            // 更新广告设备在线状态到acts系统
            syncActDevcieStatus2ACTS.send(message);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
