package com.wgb.service.portal;

import java.util.List;
import java.util.Map;

public interface PortalMenuService {
    List<Map<String,Object>> getMenuByUserId(String userId);
}
