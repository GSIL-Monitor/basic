package com.wgb.service.admin;

import java.util.List;
import java.util.Map;

public interface AdminMenuService {
    List<Map<String,Object>> getMenuByUserId(String userId);
}
