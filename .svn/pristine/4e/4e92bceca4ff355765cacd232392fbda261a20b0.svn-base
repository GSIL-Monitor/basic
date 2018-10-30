package com.wgb.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 导出字段
 *
 * @author fxs
 * @create 2018-06-07 17:17
 **/
public class ExportFiledContants {

    private static final String DEFAULT_PARENT = "yy-MM-dd";
    private static final String DEFAULT_SEPARATOR = "_";
    public static final String FILE_NAME_PARENT = "yy-MM-dd＿hh-mm-ss";

    // 客户预存款字段
    public static final Map<String ,String> CUSTOMER_RECEIPT_FILED = new LinkedHashMap<>();
    //客户结算
    public static final Map<String ,String> CUSTOMER_SETTLE_FILED = new LinkedHashMap<>();
    //客户历历史账单/客户到期账单
    public static final Map<String ,String> CUSTOMER_HISTORY_BILL_FILED = new LinkedHashMap<>();
    // 客户预存款明细
    public static final Map<String ,String> CUSTOMER_RECEIPT_DETAIL_FILED = new LinkedHashMap<>();
    // 应收账款汇总
    public static final Map<String ,String> CUSTOMER_RECEIVABLES_FILED = new LinkedHashMap<>();
    // 加盟店对账单
    public static final Map<String ,String> FRANCHISEE_STATEMENT_FILED = new LinkedHashMap<>();
    // 加盟店结算单
    public static final Map<String ,String> FRANCHISEE_SETTLE_FILED = new LinkedHashMap<>();
    // 联营账单
    public static final Map<String ,String> JOINT_OPERATION_FILED = new LinkedHashMap<>();
    // 扣率代销账单
    public static final Map<String ,String> CONSIGNMENT_BILL_FILED = new LinkedHashMap<>();
    // 供应商对账单
    public static final Map<String ,String> SUPPLIER_BILL_FILED = new LinkedHashMap<>();
    // 供应商结算单
    public static final Map<String ,String> SUPPLIER_SETTLE_FILED = new LinkedHashMap<>();




    static {
        setCustomerReceiptFiled();
        setCustomerSettleFiled();
        setCustomerHistoryBillFiled();
        setCustomerReceiptDetailFiled();
        setCustomerReceivablesFiled();
        setFranchiseeStatementFiled();
        setFranchiseeSettleFiled();
        setJointOperationFiled();
        setConsignmentBillFiled();
        setSupplierBillFiled();
        setSupplierSettleFiled();

    }


    private static void setCustomerReceiptFiled(){
        CUSTOMER_RECEIPT_FILED.put("billcode" ,"业务单号");
        CUSTOMER_RECEIPT_FILED.put("customername" ,"客户名称");
        CUSTOMER_RECEIPT_FILED.put("branchname" ,"所属门店");
        CUSTOMER_RECEIPT_FILED.put("totalamount" ,"单据金额");
        CUSTOMER_RECEIPT_FILED.put("auditstatus" ,"审核状态");
        CUSTOMER_RECEIPT_FILED.put("originatorname" ,"制单人");
        CUSTOMER_RECEIPT_FILED.put("createtime" ,"制单日期");
    }


    private static void setCustomerSettleFiled(){
        CUSTOMER_SETTLE_FILED.put("billcode" ,"业务单号");
        CUSTOMER_SETTLE_FILED.put("customername" ,"客户名称");
        CUSTOMER_SETTLE_FILED.put("branchname" ,"所属门店");
        CUSTOMER_SETTLE_FILED.put("totalamount" ,"单据金额");
        CUSTOMER_SETTLE_FILED.put("auditstatus" ,"审核状态");
        CUSTOMER_SETTLE_FILED.put("originatorname" ,"制单人");
        CUSTOMER_SETTLE_FILED.put("createtime" ,"制单日期");
        CUSTOMER_SETTLE_FILED.put("auditusername" ,"审核人");
    }

    private static void setCustomerHistoryBillFiled(){
        CUSTOMER_HISTORY_BILL_FILED.put("customercode" ,"客户编号");
        CUSTOMER_HISTORY_BILL_FILED.put("customername" ,"客户名称");
        CUSTOMER_HISTORY_BILL_FILED.put("branchname" ,"所属门店");
        CUSTOMER_HISTORY_BILL_FILED.put("billcode" ,"业务单号");
        CUSTOMER_HISTORY_BILL_FILED.put("billtype" ,"单据类型");
        CUSTOMER_HISTORY_BILL_FILED.put("shouldamount" ,"应收金额");
        CUSTOMER_HISTORY_BILL_FILED.put("alreadyamount" ,"已收金额");
        CUSTOMER_HISTORY_BILL_FILED.put("discountamount" ,"优惠金额");
        CUSTOMER_HISTORY_BILL_FILED.put("nopayamount" ,"未收金额");
        CUSTOMER_HISTORY_BILL_FILED.put("effectivedate" ,"付款期限");
        CUSTOMER_HISTORY_BILL_FILED.put("createtime" ,"制单日期");
    }


    private static void setCustomerReceiptDetailFiled(){
        CUSTOMER_RECEIPT_DETAIL_FILED.put("customercode" ,"客户编号");
        CUSTOMER_RECEIPT_DETAIL_FILED.put("customername" ,"客户名称");
        CUSTOMER_RECEIPT_DETAIL_FILED.put("branchname" ,"所属门店");
        CUSTOMER_RECEIPT_DETAIL_FILED.put("billcode" ,"业务单号");
        CUSTOMER_RECEIPT_DETAIL_FILED.put("billtype" ,"单据类型");
        CUSTOMER_RECEIPT_DETAIL_FILED.put("totalamount" ,"已收金额");
        CUSTOMER_RECEIPT_DETAIL_FILED.put("useamount" ,"已用金额");
        CUSTOMER_RECEIPT_DETAIL_FILED.put("balanceamount" ,"未用金额");
        CUSTOMER_RECEIPT_DETAIL_FILED.put("paytime" ,"付款日期");
    }

    private static void setCustomerReceivablesFiled(){
        CUSTOMER_RECEIVABLES_FILED.put("customercode" ,"客户编号");
        CUSTOMER_RECEIVABLES_FILED.put("customername" ,"客户名称");
        CUSTOMER_RECEIVABLES_FILED.put("branchname" ,"所属门店");
        CUSTOMER_RECEIVABLES_FILED.put("shouldamount" ,"应收金额");
        CUSTOMER_RECEIVABLES_FILED.put("alreadyamount" ,"已收金额");
        CUSTOMER_RECEIVABLES_FILED.put("discountamount" ,"优惠金额");
        CUSTOMER_RECEIVABLES_FILED.put("nopayamount" ,"未收金额");
    }

    //对账单号	门店名称	单据金额(元)	操作人员	操作时间	审核人员	审核日期	操作状态
    private static void setFranchiseeStatementFiled(){
        FRANCHISEE_STATEMENT_FILED.put("billcode" ,"对账单号");
        FRANCHISEE_STATEMENT_FILED.put("recbranchname" ,"门店名称");
        FRANCHISEE_STATEMENT_FILED.put("totalprice" ,"单据金额(元)");
        FRANCHISEE_STATEMENT_FILED.put("createusername" ,"操作人员");
        FRANCHISEE_STATEMENT_FILED.put("createtime" ,"操作时间");
        FRANCHISEE_STATEMENT_FILED.put("examineusername" ,"审核人员");
        FRANCHISEE_STATEMENT_FILED.put("examinetime" ,"审核日期");
        FRANCHISEE_STATEMENT_FILED.put("examineflag" ,"操作状态");
    }

    //结算单号	门店名称	单据金额	已结金额	操作人员	操作日期	结算人员	结算日期	结算状态
    private static void setFranchiseeSettleFiled(){
        FRANCHISEE_SETTLE_FILED.put("billcode" ,"结算单号");
        FRANCHISEE_SETTLE_FILED.put("setbranchname" ,"门店名称");
        FRANCHISEE_SETTLE_FILED.put("amount" ,"单据金额(元)");
        FRANCHISEE_SETTLE_FILED.put("alreadyamount" ,"已结金额");
        FRANCHISEE_SETTLE_FILED.put("createusername" ,"操作人员");
        FRANCHISEE_SETTLE_FILED.put("createtime" ,"操作日期");
        FRANCHISEE_SETTLE_FILED.put("examineusername" ,"结算人员");
        FRANCHISEE_SETTLE_FILED.put("examinetime" ,"结算日期");
        FRANCHISEE_SETTLE_FILED.put("examineflag" ,"结算状态");
    }

    //对账单号	供应商名称	门店名称	销售金额	审核状态	操作人员	操作日期	审核人员	审核日期
    private static void setJointOperationFiled(){
        JOINT_OPERATION_FILED.put("billcode" ,"对账单号");
        JOINT_OPERATION_FILED.put("suppliername" ,"供应商名称");
        JOINT_OPERATION_FILED.put("lybranchname" ,"门店名称");
        JOINT_OPERATION_FILED.put("paytotal" ,"销售金额");
        JOINT_OPERATION_FILED.put("examineflag" ,"审核状态");
        JOINT_OPERATION_FILED.put("createusername" ,"操作人员");
        JOINT_OPERATION_FILED.put("createtime" ,"操作日期");
        JOINT_OPERATION_FILED.put("examineusername" ,"审核人员");
        JOINT_OPERATION_FILED.put("examinetime" ,"审核日期");
    }

    //对账单号	供应商名称	门店名称	销售金额	审核状态	操作人员	操作日期	审核人员	审核日期
    private static void setConsignmentBillFiled(){
        CONSIGNMENT_BILL_FILED.put("billcode" ,"对账单号");
        CONSIGNMENT_BILL_FILED.put("suppliername" ,"供应商名称");
        CONSIGNMENT_BILL_FILED.put("dxbranchname" ,"门店名称");
        CONSIGNMENT_BILL_FILED.put("paytotal" ,"销售金额");
        CONSIGNMENT_BILL_FILED.put("examineflag" ,"审核状态");
        CONSIGNMENT_BILL_FILED.put("createusername" ,"操作人员");
        CONSIGNMENT_BILL_FILED.put("createtime" ,"操作日期");
        CONSIGNMENT_BILL_FILED.put("examineusername" ,"审核人员");
        CONSIGNMENT_BILL_FILED.put("examinetime" ,"审核日期");
    }

    //对账单号	审核状态	供应商名称	门店名称	单据类型	单据金额(元)	操作人员	操作日期	审核人员	审核日期
    private static void setSupplierBillFiled(){
        SUPPLIER_BILL_FILED.put("billcode" ,"对账单号");
        SUPPLIER_BILL_FILED.put("examineflag" ,"审核状态");
        SUPPLIER_BILL_FILED.put("suppliername" ,"供应商名称");
        SUPPLIER_BILL_FILED.put("recbranchname" ,"门店名称");
        SUPPLIER_BILL_FILED.put("billtype" ,"单据类型");
        SUPPLIER_BILL_FILED.put("shouldamount" ,"单据金额(元)");
        SUPPLIER_BILL_FILED.put("createusername" ,"操作人员");
        SUPPLIER_BILL_FILED.put("createtime" ,"操作日期");
        SUPPLIER_BILL_FILED.put("examineusername" ,"审核人员");
        SUPPLIER_BILL_FILED.put("examinetime" ,"审核日期");
    }

    //结算单号	供应商名称	门店名称	单据金额	已结金额	操作人员	操作日期	结算人员	结算日期	结算状态	备注
    private static void setSupplierSettleFiled(){
        SUPPLIER_SETTLE_FILED.put("billcode" ,"结算单号");
        SUPPLIER_SETTLE_FILED.put("suppliername" ,"供应商名称");
        SUPPLIER_SETTLE_FILED.put("setbranchname" ,"门店名称");
        SUPPLIER_SETTLE_FILED.put("shouldamount" ,"单据金额");
        SUPPLIER_SETTLE_FILED.put("alreadyamount" ,"已结金额");
        SUPPLIER_SETTLE_FILED.put("createusername" ,"操作人员");
        SUPPLIER_SETTLE_FILED.put("createtime" ,"操作日期");
        SUPPLIER_SETTLE_FILED.put("examineusername" ,"结算人员");
        SUPPLIER_SETTLE_FILED.put("examinetime" ,"结算日期");
        SUPPLIER_SETTLE_FILED.put("examineflag" ,"结算状态");
    }



    /**
     * 名称 + 分隔符 + 时间
     * @param name  文件名称
     * @param parent 时间格式
     * @param separator 分隔符
     * @return
     */
    public static String getExportFileName(String name ,String parent ,String separator){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parent);
        return name.concat(separator).concat(simpleDateFormat.format(new Date()));
    }

    /**
     * 名称_yy_MM_dd
     * @param name  文件名称
     * @return
     */
    public static String getExportFileName(String name){
        return getExportFileName(name ,DEFAULT_PARENT ,DEFAULT_SEPARATOR);
    }

    /**
     * 名称_格式化时间
     * @param name  文件名称
     * @param parent 时间格式
     * @return
     */
    public static String getExportFileName(String name ,String parent){
        return getExportFileName(name ,parent ,DEFAULT_SEPARATOR);
    }
}
