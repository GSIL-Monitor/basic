package com.wgb.util;

/**
 * Created by wgb on 2018/7/12 0012.
 */
public class HttpRequestConstant {

    /**
     * 中仑内部应用默认Http来源判定
     */
    public static final String ZL_REQUEST_SOURCE = "zl-request-source";

    /**
     * 中仑内部应用测试账号
     */
    public static final String ZL_REQUEST_TEST = "zl-request-test";

    /**
     * 中仑内部应用系统标志
     */
    public static final String ZL_REQUEST_SIGN = "zl-request-sign";


    /**
     * 商户请求web端URL前缀
     */
    public static final String MT_URL_PREFIX = "/mt/";

    /**
     * 商户请求web端URL前缀
     */
    public static final String MB_WX_URL_PREFIX = "/mb/wx/";

    /**
     * 商户请求web端URL前缀
     */
    public static final String MB_XCX_URL_PREFIX = "/mb/xcx/";

    /**
     * 商户请求web端URL前缀
     */
    public static final String MT_WEB_URL_PREFIX = "/mt/web/";

    /**
     * 商户请求pos端URL前缀/收银端使用
     */
    public static final String MT_POS_URL_PREFIX = "/mt/pos/";

    /**
     * 供应商请求pc端URL前缀/
     */
    public static final String SCP_WEB_URL_PREFIX = "/scp/web/";

    /**
     * 供应商请求xcx端URL前缀/
     */
    public static final String SCP_XCX_URL_PREFIX = "/scp/xcx/";

     // 商户请求老板助手URL前缀/老板助手使用

    public static final String MT_BOSS_URL_PREFIX = "/mt/boss/";

    /**
     * 商户请求wx端URL前缀
     */
    public static final String MT_WX_URL_PREFIX = "/mt/wx/";



    /**
     * 商户登录 TOKEN标识
     */
    public static final String MT_TOKEN_ID = "mt_id";

    /**
     * 会员登录 TOKEN标识
     */
    public static final String MB_TOKEN_ID = "mb_id";

    /**
     * 商户登录 redis存储前缀
     */
    public static final String MT_PC_ACCOUNT_PREFIX = "mt_pc_account_";

    /**
     * 会员登录 redis存储前缀
     */
    public static final String MB_WX_ACCOUNT_PREFIX = "mb_wx_account_";

    /**
     * 会员登录 redis存储前缀
     */
    public static final String MB_WX_USR_PREFIX = "mb_wx_usr_";

    /**
     * 会员登录 redis存储前缀
     */
    public static final String MB_XCX_USR_PREFIX = "mb_xcx_usr_";

    /**
     * 会员登录 redis存储前缀
     */
    public static final String MB_WX_BRANCH_PREFIX = "mb_wx_branch_";

    /**
     * 会员登录 cookie存储门店编码
     */
    public static final String MB_WX_BRANCHCODE_PREFIX = "mb_wx_branchcode_";

    /**
     * 会员登录 redis存储前缀
     */
    public static final String MB_USER_PREFIX = "mb_user_";

    /**
     * POS商户登录redis存储前缀
     */
    public static final String MT_POS_ACCOUNT_PREFIX = "mt_pos_account_";

    /**
     * BOSS商户登录redis存储前缀
     */
    public static final String MT_BOSS_ACCOUNT_PREFIX = "mt_boss_account_";

    /**
     * 商户助手用户登录redis存储前缀
     */
    public static final String MT_GOODS_HELP_ACCOUNT_PREFIX = "mt_goods_help_account_";


    /**
     * 供应链登录 TOKEN标识
     */
    public static final String SCP_TOKEN_ID = "scp_id";


    /**
     * 供应链登录 redis存储前缀
     */
    public static final String SCP_PC_ACCOUNT_PREFIX = "scp_pc_account_";

    /**
     * 服务商登录 TOKEN标识
     */
    public static final String SP_TOKEN_ID = "sp_id";

    /**
     * 服务商登录 redis存储前缀
     */
    public static final String SP_PC_ACCOUNT_PREFIX = "sp_pc_account_";


    /**
     * 当前请求处理对应的用户账号
     */
    public static final String CONST_LOGIN_PARAMS = "_const_login_params_";

    // 当前请求处理对应的用户账号
    public static final String CONST_LOGIN_ACCOUNT = "_const_login_account_";

}
