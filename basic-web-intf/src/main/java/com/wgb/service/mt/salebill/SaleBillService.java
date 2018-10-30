package com.wgb.service.mt.salebill;

import java.util.List;
import java.util.Map;

/**
 * Created by yjw on 2016/8/23.
 */
public interface SaleBillService {

    void  batchPageSizeSaveSaleBills(List<Map<String, Object>> saleBills);

}
