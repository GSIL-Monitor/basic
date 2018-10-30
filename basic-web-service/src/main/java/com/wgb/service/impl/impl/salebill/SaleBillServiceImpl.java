package com.wgb.service.impl.impl.salebill;

import com.wgb.rocketmq.annotation.MQWired;
import com.wgb.service.impl.send.SaveSaleBills2DCMS;
import com.wgb.service.mt.salebill.SaleBillService;
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
public class SaleBillServiceImpl implements SaleBillService {

    private SaveSaleBills2DCMS saveSaleBills2DCMS;

    @MQWired
    public void setSaveSaleBills2DCMS(SaveSaleBills2DCMS saveSaleBills2DCMS) {
        this.saveSaleBills2DCMS = saveSaleBills2DCMS;
    }

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public  void  batchPageSizeSaveSaleBills(List<Map<String, Object>> dataList){
        /**
         * 处理报文分页
         * dataList:待分页数据列表
         */
        //记录消息列表
        List<Map<String, Object>> msgList = new ArrayList<Map<String, Object>>();

        //单页长度
        int pageSize = 10;

        for (int index = 1; index <= dataList.size(); index++) {
            msgList.add(dataList.get(index - 1));
            if (index % pageSize == 0 || index == dataList.size()) {
                //报文列表：msgList
                Map<String, Object> syncParams = new LinkedHashMap<String, Object>();
                Map<String, Object> items = new LinkedHashMap<String, Object>();

                List<Map<String, Object>> item = new ArrayList<Map<String, Object>>();
                for(Map<String, Object> msgItem : msgList){
                    item.add(msgItem);
                }
                items.put("item", item);
                syncParams.put("items", items);

                try {
                    String message = RscUtil.objectToMessage(syncParams);
                    // 保持销售记录指令到dcms系统
                    saveSaleBills2DCMS.send(message);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    throw new RuntimeException(e);
                }
                msgList.clear();
            }
        }
    }

}
