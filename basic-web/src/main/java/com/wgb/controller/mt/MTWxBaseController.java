package com.wgb.controller.mt;

import java.util.HashMap;
import java.util.Map;

public abstract class MTWxBaseController {

    protected Map<String, Object> getXcxParams() {
        return new HashMap<>();
    }

    protected Map<String, Object> getPubParams() {
        return new HashMap<>();
    }

    protected Map<String, Object> getBranchParams() {
        return new HashMap<>();
    }
}
