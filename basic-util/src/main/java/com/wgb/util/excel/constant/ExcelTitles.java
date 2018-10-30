package com.wgb.util.excel.constant;

import com.wgb.util.excel.model.ExcelColumn;

import java.util.ArrayList;
import java.util.List;

/**
 * 导出列表的表头定义
 *      最大设置单元格为  255
 */
public class ExcelTitles {

	private static final List<ExcelColumn> saleCommodityCollectTitle = new ArrayList<ExcelColumn>(0);
	private static final List<ExcelColumn> saleBillCollectTitle = new ArrayList<ExcelColumn>(0);
	private static final List<ExcelColumn> saleDetailCollectTitle = new ArrayList<ExcelColumn>(0);
	private static final List<ExcelColumn> purchaseStatisticsCollectTitle = new ArrayList<>(0);
    private static final List<ExcelColumn> purchaseStatisticsClothCollectTitle = new ArrayList<>(0);
    private static final List<ExcelColumn> purchaseDetailsClothCollectTitle = new ArrayList<>(0);
    private static final List<ExcelColumn> purchaseDetailsCollectTitle = new ArrayList<>(0);
    //收银流水
    private static final List<ExcelColumn> salesManageDatailCollectTitle = new ArrayList<>(0);
    //商品销售流水
    private static final List<ExcelColumn> commoditySaleWaterForReportCollectTitle = new ArrayList<>(0);
    //服装行业的零售统计
    private static final List<ExcelColumn> salesRetailStatisticsIndustryCollectTitle = new ArrayList<>(0);
    //商品销售统计 按照商品导出
    private static final List<ExcelColumn> salesRetailStatisticsCollectTitle = new ArrayList<>(0);
    //商品销售统计 按照品类导出
    private static final List<ExcelColumn> salesRetailStatistics1CollectTitle = new ArrayList<>(0);
    //商品销售统计 按照品牌导出
    private static final List<ExcelColumn> salesRetailStatistics2CollectTitle = new ArrayList<>(0);
    //门店统计
    private static final List<ExcelColumn> salesStoresSumCollectTitle = new ArrayList<>(0);
    //门店统计 按支付方式
    private static final List<ExcelColumn> salesStoresSumPayCollectTitle = new ArrayList<>(0);

    private static final List<ExcelColumn> storageStatisticsCollectTitle = new ArrayList<>(0);
    private static final List<ExcelColumn> storageStatisticsClothCollectTitle = new ArrayList<>(0);
    private static final List<ExcelColumn> storageCategoryStatisticsCollectTitle = new ArrayList<>(0);
    private static final List<ExcelColumn> storageBrandStatisticsCollectTitle = new ArrayList<>(0);
    private static final List<ExcelColumn> storageInventoryCollectTitle = new ArrayList<>(0);
    private static final List<ExcelColumn> storageInventoryClothCollectTitle = new ArrayList<>(0);
    //商品详情导出--服装行业1.0
    private static final List<ExcelColumn> commodityDetailByIndustryCollectTitle = new ArrayList<>(0);
    //商品详情导出--服装行业 自营店2.0
    private static final List<ExcelColumn> commodityDetailByIndustry2CollectTitle = new ArrayList<>(0);

    //商品档案导出--服装行业
    private static final List<ExcelColumn> commodityByIndustryCollectTitle = new ArrayList<>(0);

    //商品详情导出1.0
    private static final List<ExcelColumn> commodityDetailByCollectTitle = new ArrayList<>(0);
    //商品详情导出2.0
    private static final List<ExcelColumn> commodityDetailBy2CollectTitle = new ArrayList<>(0);

    //销售报表，统计分析，商品明细
    private static final List<ExcelColumn> queryTotalDetailListCollectTitle = new ArrayList<>(0);
    private static final List<ExcelColumn> storageDetailCollectTitle = new ArrayList<>(0);

    /**
     *  最大设置单元格为  255
     */
	static {
        /******************************销售商品汇总导出Title start******************************/
        saleCommodityCollectTitle.add(new ExcelColumn("货号" ,"commoditycode" ,12));
        saleCommodityCollectTitle.add(new ExcelColumn("商品名称" ,"commodityname" ,24));
        saleCommodityCollectTitle.add(new ExcelColumn("商品分类" ,"categoryname" ,12));
        saleCommodityCollectTitle.add(new ExcelColumn("商品规格" ,"spec" ,12));
        saleCommodityCollectTitle.add(new ExcelColumn("商品单位" ,"unitname" ,12));
        saleCommodityCollectTitle.add(new ExcelColumn("销售数量" ,"salenums" ,12));
        saleCommodityCollectTitle.add(new ExcelColumn("销售金额" ,"salesubtotal" ,12));
        saleCommodityCollectTitle.add(new ExcelColumn("退货数量" ,"returnnums" ,12));
        saleCommodityCollectTitle.add(new ExcelColumn("退货金额" ,"returnsubtotal" ,12));
        saleCommodityCollectTitle.add(new ExcelColumn("实销数量" ,"realnums" ,12));
        saleCommodityCollectTitle.add(new ExcelColumn("实销金额" ,"realsubtotal" ,12));
        saleCommodityCollectTitle.add(new ExcelColumn("赠送数量" ,"realgivenums" ,12));
        saleCommodityCollectTitle.add(new ExcelColumn("赠送金额" ,"salegivesubtotal" ,12));
        /******************************销售商品汇总导出Title end******************************/

        /******************************销售单据汇总导出Title start******************************/
        saleBillCollectTitle.add(new ExcelColumn("订单编号" ,"billcode" ,12));
        saleBillCollectTitle.add(new ExcelColumn("单据类型" ,"billtypename" ,24));
        saleBillCollectTitle.add(new ExcelColumn("客户ID" ,"account" ,12));
        saleBillCollectTitle.add(new ExcelColumn("客户名称" ,"nickname" ,12));
        saleBillCollectTitle.add(new ExcelColumn("销售数量" ,"salenums" ,12));
        saleBillCollectTitle.add(new ExcelColumn("销售金额" ,"salesubtotal" ,12));
        saleBillCollectTitle.add(new ExcelColumn("退货数量" ,"returnnums" ,12));
        saleBillCollectTitle.add(new ExcelColumn("退货金额" ,"returnsubtotal" ,12));
        saleBillCollectTitle.add(new ExcelColumn("实销数量" ,"realnums" ,12));
        saleBillCollectTitle.add(new ExcelColumn("实销金额" ,"realsubtotal" ,12));
        saleBillCollectTitle.add(new ExcelColumn("赠送数量" ,"realgivenums" ,12));
        saleBillCollectTitle.add(new ExcelColumn("赠送金额" ,"realgivesubtotal" ,12));
        saleBillCollectTitle.add(new ExcelColumn("制单日期" ,"createtime" ,12));
        /******************************销售单据汇总导出Title end******************************/

        /******************************销售明细汇总导出Title start******************************/
        saleDetailCollectTitle.add(new ExcelColumn("订单编号" ,"billcode" ,12));
        saleDetailCollectTitle.add(new ExcelColumn("单据类型" ,"billtypename" ,24));
        saleDetailCollectTitle.add(new ExcelColumn("客户ID" ,"account" ,12));
        saleDetailCollectTitle.add(new ExcelColumn("客户名称" ,"nickname" ,12));
        saleDetailCollectTitle.add(new ExcelColumn("货号" ,"commoditycode" ,12));
        saleDetailCollectTitle.add(new ExcelColumn("商品名称" ,"commodityname" ,12));
        saleDetailCollectTitle.add(new ExcelColumn("商品类别" ,"categoryname" ,12));
        saleDetailCollectTitle.add(new ExcelColumn("规格" ,"spec" ,12));
        saleDetailCollectTitle.add(new ExcelColumn("单位" ,"unitname" ,12));
        saleDetailCollectTitle.add(new ExcelColumn("数量" ,"nums" ,12));
        saleDetailCollectTitle.add(new ExcelColumn("售价" ,"price" ,12));
        saleDetailCollectTitle.add(new ExcelColumn("小计" ,"subtotal" ,12));
        saleDetailCollectTitle.add(new ExcelColumn("赠送数量" ,"givenums" ,12));
        saleDetailCollectTitle.add(new ExcelColumn("赠送金额" ,"givesubtotal" ,12));
        /******************************销售明细汇总导出Title end******************************/

        /******************************采购报表统计(服装)导出Title start******************************/
        ExcelColumn zPurchaseStatisticsClothTitle = new ExcelColumn("采购报表统计", "", 204);

        List<ExcelColumn> secPurchaseStatisticsClothExcelList = new ArrayList<>();
        secPurchaseStatisticsClothExcelList.add(new ExcelColumn("门店" ,"purbranchname",17));
        secPurchaseStatisticsClothExcelList.add(new ExcelColumn("日期" ,"createtime",17));

        ExcelColumn commodityInfoPurchaseClothStatistics = new ExcelColumn("商品信息" ,"" ,119);
        List<ExcelColumn> commodityChildPurchaseClothStatistics = new ArrayList<>();
        commodityChildPurchaseClothStatistics.add(new ExcelColumn("货号","spucode" ,17));
        commodityChildPurchaseClothStatistics.add(new ExcelColumn("条形码","barcode" ,17));
        commodityChildPurchaseClothStatistics.add(new ExcelColumn("品名","commodityname" ,17));
        commodityChildPurchaseClothStatistics.add(new ExcelColumn("颜色/尺码","specvalue" ,17));
        commodityChildPurchaseClothStatistics.add(new ExcelColumn("品类","categoryname" ,17));
        commodityChildPurchaseClothStatistics.add(new ExcelColumn("品牌","brandname" ,17));
        commodityChildPurchaseClothStatistics.add(new ExcelColumn("单位","unitname" ,17));
        commodityInfoPurchaseClothStatistics.setChildren(commodityChildPurchaseClothStatistics);
        secPurchaseStatisticsClothExcelList.add(commodityInfoPurchaseClothStatistics);

        ExcelColumn purchaseInfoPurchaseClothStatistics = new ExcelColumn("采购信息" ,"" ,51);
        List<ExcelColumn> purchaseInfoChildPurchaseClothStatistics = new ArrayList<>();
        purchaseInfoChildPurchaseClothStatistics.add(new ExcelColumn("采购金额","subtotal" ,17));
        purchaseInfoChildPurchaseClothStatistics.add(new ExcelColumn("采购数量","nums" ,17));
        purchaseInfoChildPurchaseClothStatistics.add(new ExcelColumn("赠送数量","givenums" ,17));
        purchaseInfoPurchaseClothStatistics.setChildren(purchaseInfoChildPurchaseClothStatistics);
        secPurchaseStatisticsClothExcelList.add(purchaseInfoPurchaseClothStatistics);

        zPurchaseStatisticsClothTitle.setChildren(secPurchaseStatisticsClothExcelList);
        purchaseStatisticsClothCollectTitle.add(zPurchaseStatisticsClothTitle);
        /******************************采购报表统计(服装)导出Title end******************************/

        /******************************采购报表统计导出Title start******************************/
        ExcelColumn zPurchaseStatisticsTitle = new ExcelColumn("采购报表统计", "", 170);

        List<ExcelColumn> secPurchaseStatisticsExcelList = new ArrayList<>();
        secPurchaseStatisticsExcelList.add(new ExcelColumn("门店" ,"purbranchname",17));
        secPurchaseStatisticsExcelList.add(new ExcelColumn("日期" ,"createtime",17));
        ExcelColumn commodityInfoPurchaseStatistics = new ExcelColumn("商品信息" ,"" ,85);
        List<ExcelColumn> commodityChildPurchaseStatistics = new ArrayList<>();
        commodityChildPurchaseStatistics.add(new ExcelColumn("货号","commoditycode" ,17));
        commodityChildPurchaseStatistics.add(new ExcelColumn("品名","commodityname" ,17));
        commodityChildPurchaseStatistics.add(new ExcelColumn("品类","categoryname" ,17));
        commodityChildPurchaseStatistics.add(new ExcelColumn("品牌","brandname" ,17));
        commodityChildPurchaseStatistics.add(new ExcelColumn("单位","unitname" ,17));
        commodityInfoPurchaseStatistics.setChildren(commodityChildPurchaseStatistics);
        secPurchaseStatisticsExcelList.add(commodityInfoPurchaseStatistics);
        ExcelColumn purchaseInfoPurchaseStatistics = new ExcelColumn("采购信息" ,"" ,51);
        List<ExcelColumn> purchaseInfoChildPurchaseStatistics = new ArrayList<>();
        purchaseInfoChildPurchaseStatistics.add(new ExcelColumn("采购金额","subtotal" ,17));
        purchaseInfoChildPurchaseStatistics.add(new ExcelColumn("采购数量","nums" ,17));
        purchaseInfoChildPurchaseStatistics.add(new ExcelColumn("赠送数量","givenums" ,17));
        purchaseInfoPurchaseStatistics.setChildren(purchaseInfoChildPurchaseStatistics);
        secPurchaseStatisticsExcelList.add(purchaseInfoPurchaseStatistics);
        zPurchaseStatisticsTitle.setChildren(secPurchaseStatisticsExcelList);
        purchaseStatisticsCollectTitle.add(zPurchaseStatisticsTitle);
        /******************************采购报表统计导出Title end******************************/

        /******************************采购报表明细导出Title start******************************/
        ExcelColumn zPurchaseDetailsTitle = new ExcelColumn("采购报表明细", "", 238);

        List<ExcelColumn> secPurchaseDetailsExcelList = new ArrayList<>();
        secPurchaseDetailsExcelList.add(new ExcelColumn("门店" ,"purbranchname",17));

        ExcelColumn commodityInfoPurchaseDetails = new ExcelColumn("商品信息" ,"" ,85);
        List<ExcelColumn> commodityChildPurchaseDetails = new ArrayList<>();
        commodityChildPurchaseDetails.add(new ExcelColumn("货号","commoditycode" ,17));
        commodityChildPurchaseDetails.add(new ExcelColumn("品名","commodityname" ,17));
        commodityChildPurchaseDetails.add(new ExcelColumn("品类","categoryname" ,17));
        commodityChildPurchaseDetails.add(new ExcelColumn("规格","spec" ,17));
        commodityChildPurchaseDetails.add(new ExcelColumn("单位","unitname" ,17));
        commodityInfoPurchaseDetails.setChildren(commodityChildPurchaseDetails);
        secPurchaseDetailsExcelList.add(commodityInfoPurchaseDetails);

        ExcelColumn purchaseInfoPurchaseDetails = new ExcelColumn("采购信息" ,"" ,136);
        List<ExcelColumn> purchaseInfoChildPurchaseDetails = new ArrayList<>();
        purchaseInfoChildPurchaseDetails.add(new ExcelColumn("时间","createtime" ,25));
        purchaseInfoChildPurchaseDetails.add(new ExcelColumn("采购单号","billcode" ,25));
        purchaseInfoChildPurchaseDetails.add(new ExcelColumn("单据类型","billtype" ,17));
        purchaseInfoChildPurchaseDetails.add(new ExcelColumn("供应商","suppliername" ,17));
        purchaseInfoChildPurchaseDetails.add(new ExcelColumn("采购金额","subtotal" ,17));
        purchaseInfoChildPurchaseDetails.add(new ExcelColumn("采购数量","nums" ,17));
        purchaseInfoChildPurchaseDetails.add(new ExcelColumn("赠送数量","givenums" ,17));
        purchaseInfoChildPurchaseDetails.add(new ExcelColumn("单价","price" ,17));
        purchaseInfoPurchaseDetails.setChildren(purchaseInfoChildPurchaseDetails);
        secPurchaseDetailsExcelList.add(purchaseInfoPurchaseDetails);

        zPurchaseDetailsTitle.setChildren(secPurchaseDetailsExcelList);
        purchaseDetailsCollectTitle.add(zPurchaseDetailsTitle);
        /******************************采购报表明细导出Title end******************************/

        /******************************采购报表明细(服装)导出Title start******************************/
        ExcelColumn zPurchaseDetailsClothTitle = new ExcelColumn("采购报表明细", "", 220);

        List<ExcelColumn> secPurchaseDetailsClothExcelList = new ArrayList<>();
        secPurchaseDetailsClothExcelList.add(new ExcelColumn("门店" ,"purbranchname",17));

        ExcelColumn commodityInfoPurchaseClothDetails = new ExcelColumn("商品信息" ,"" ,67);
        List<ExcelColumn> commodityChildPurchaseClothDetails = new ArrayList<>();
        commodityChildPurchaseClothDetails.add(new ExcelColumn("货号","spucode" ,10));
        commodityChildPurchaseClothDetails.add(new ExcelColumn("条形码","barcode" ,10));
        commodityChildPurchaseClothDetails.add(new ExcelColumn("品名","commodityname" ,17));
        commodityChildPurchaseClothDetails.add(new ExcelColumn("颜色/尺码","specvalue" ,10));
        commodityChildPurchaseClothDetails.add(new ExcelColumn("品类","categoryname" ,10));
        commodityChildPurchaseClothDetails.add(new ExcelColumn("单位","unitname" ,10));
        commodityInfoPurchaseClothDetails.setChildren(commodityChildPurchaseClothDetails);
        secPurchaseDetailsClothExcelList.add(commodityInfoPurchaseClothDetails);

        ExcelColumn purchaseInfoPurchaseClothDetails = new ExcelColumn("采购信息" ,"" ,136);
        List<ExcelColumn> purchaseInfoChildPurchaseClothDetails = new ArrayList<>();
        purchaseInfoChildPurchaseClothDetails.add(new ExcelColumn("时间","createtime" ,25));
        purchaseInfoChildPurchaseClothDetails.add(new ExcelColumn("采购单号","billcode" ,25));
        purchaseInfoChildPurchaseClothDetails.add(new ExcelColumn("单据类型","billtype" ,17));
        purchaseInfoChildPurchaseClothDetails.add(new ExcelColumn("供应商","suppliername" ,17));
        purchaseInfoChildPurchaseClothDetails.add(new ExcelColumn("采购金额","subtotal" ,17));
        purchaseInfoChildPurchaseClothDetails.add(new ExcelColumn("采购数量","nums" ,17));
        purchaseInfoChildPurchaseClothDetails.add(new ExcelColumn("赠送数量","givenums" ,17));
        purchaseInfoChildPurchaseClothDetails.add(new ExcelColumn("单价","price" ,17));
        purchaseInfoPurchaseClothDetails.setChildren(purchaseInfoChildPurchaseClothDetails);
        secPurchaseDetailsClothExcelList.add(purchaseInfoPurchaseClothDetails);

        zPurchaseDetailsClothTitle.setChildren(secPurchaseDetailsClothExcelList);
        purchaseDetailsClothCollectTitle.add(zPurchaseDetailsClothTitle);
        /******************************采购报表明细(服装)导出Title end******************************/



        /******************************销售报表导出******************************/
        /******************************销售报表-收银流水******************************/
        ExcelColumn  salesManageDatailCollect = new ExcelColumn("收银流水" ,"" ,0);
        salesManageDatailCollectTitle.add(salesManageDatailCollect);

        // zibiaotiao
        List<ExcelColumn> salesManageDatailCollectTitleChildren = new ArrayList<>();
        salesManageDatailCollect.setChildren(salesManageDatailCollectTitleChildren);
        //salesManageDatailCollectTitle.add(new ExcelColumn("序号" ,"billcode" ,12));
        salesManageDatailCollectTitleChildren.add(new ExcelColumn("门店" ,"branchname" ,32));
        salesManageDatailCollectTitleChildren.add(new ExcelColumn("收银时间" ,"paytime" ,12));
        salesManageDatailCollectTitleChildren.add(new ExcelColumn("订单号" ,"ticketcode" ,24));
        salesManageDatailCollectTitleChildren.add(new ExcelColumn("订单金额" ,"total" ,12));
        salesManageDatailCollectTitleChildren.add(new ExcelColumn("优惠金额" ,"trueamount" ,12));
        salesManageDatailCollectTitleChildren.add(new ExcelColumn("应收金额" ,"paytotal" ,12));
        salesManageDatailCollectTitleChildren.add(new ExcelColumn("会员号" ,"memberno" ,12));
        salesManageDatailCollectTitleChildren.add(new ExcelColumn("付款方式" ,"payname" ,24));
        salesManageDatailCollectTitleChildren.add(new ExcelColumn("付款金额" ,"payamount" ,12));
        salesManageDatailCollectTitleChildren.add(new ExcelColumn("交易类型" ,"tradename" ,24));
        salesManageDatailCollectTitleChildren.add(new ExcelColumn("退货原单号" ,"returncode" ,32));
        salesManageDatailCollectTitleChildren.add(new ExcelColumn("收银员" ,"cashiername" ,32));
        /******************************销售报表-商品销售流水******************************/
        ExcelColumn  commoditySaleWaterForReport = new ExcelColumn("商品销售流水" ,"" ,0);
        commoditySaleWaterForReportCollectTitle.add(commoditySaleWaterForReport);

        // zibiaotiao
        List<ExcelColumn> commoditySaleWaterForReportTitleChildren = new ArrayList<>();
        commoditySaleWaterForReport.setChildren(commoditySaleWaterForReportTitleChildren);
        commoditySaleWaterForReportTitleChildren.add(new ExcelColumn("门店" ,"branchname" ,32));
        commoditySaleWaterForReportTitleChildren.add(new ExcelColumn("销售时间" ,"saletime" ,12));
        commoditySaleWaterForReportTitleChildren.add(new ExcelColumn("货号" ,"commoditycode" ,24));
        commoditySaleWaterForReportTitleChildren.add(new ExcelColumn("品名" ,"commodityname" ,12));
        commoditySaleWaterForReportTitleChildren.add(new ExcelColumn("售价" ,"saleprice" ,32));
        commoditySaleWaterForReportTitleChildren.add(new ExcelColumn("优惠金额" ,"trueamount" ,12));
        commoditySaleWaterForReportTitleChildren.add(new ExcelColumn("应收金额" ,"paytotal" ,12));
        commoditySaleWaterForReportTitleChildren.add(new ExcelColumn("订单号" ,"ticketcode" ,24));;
        commoditySaleWaterForReportTitleChildren.add(new ExcelColumn("退货原单号" ,"returncode" ,32));

        /******************************销售报表-零售统计******************************/
        /******************************销售报表-零售统计******************************/
        ExcelColumn salesRetailStatisticsTitle = new ExcelColumn("零售统计" ,"" ,0);
        salesRetailStatisticsCollectTitle.add(salesRetailStatisticsTitle);

        // 子表头
        List<ExcelColumn> salesRetailStatisticsChildren= new ArrayList<>();
        salesRetailStatisticsTitle.setChildren(salesRetailStatisticsChildren);

        salesRetailStatisticsChildren.add(new ExcelColumn("门店" ,"branchname" ,32));
        salesRetailStatisticsChildren.add(new ExcelColumn("日期" ,"saletime" ,12));
        salesRetailStatisticsChildren.add(new ExcelColumn("品名" ,"commodityname" ,32));
        salesRetailStatisticsChildren.add(new ExcelColumn("品类" ,"categoryname" ,32));
        salesRetailStatisticsChildren.add(new ExcelColumn("品牌" ,"brandname" ,32));

        ExcelColumn retailStatisticsDetails = new ExcelColumn("数量" ,"" ,12);
        List <ExcelColumn> retailStatisticsDetailsChildren=new ArrayList<>();
        retailStatisticsDetailsChildren.add(new ExcelColumn("销售数量" ,"salenums" ,12));
        retailStatisticsDetailsChildren.add(new ExcelColumn("退货数量" ,"canreturnnums" ,12));
        retailStatisticsDetails.setChildren(retailStatisticsDetailsChildren);
        salesRetailStatisticsChildren.add(retailStatisticsDetails);

        salesRetailStatisticsChildren.add(new ExcelColumn("销售额" ,"paysubtotal" ,12));
        salesRetailStatisticsChildren.add(new ExcelColumn("销售成本" ,"costsaleamount" ,12));
        salesRetailStatisticsChildren.add(new ExcelColumn("毛利率" ,"grossprofitrate" ,12));

        /******************************销售报表-零售统计******************************/
/******************************销售报表-按照品类导出******************************/
        ExcelColumn salesRetailStatistics1Title = new ExcelColumn("零售统计" ,"" ,0);
        salesRetailStatistics1CollectTitle.add(salesRetailStatistics1Title);

        // 子表头
        List<ExcelColumn> salesRetailStatistics1Children= new ArrayList<>();
        salesRetailStatistics1Title.setChildren(salesRetailStatistics1Children);

        salesRetailStatistics1Children.add(new ExcelColumn("门店" ,"branchname" ,32));
        salesRetailStatistics1Children.add(new ExcelColumn("日期" ,"saletime" ,12));;
        salesRetailStatistics1Children.add(new ExcelColumn("品类" ,"categoryname" ,32));

        ExcelColumn retailStatistics1Details = new ExcelColumn("数量" ,"" ,12);
        List <ExcelColumn> retailStatisticsDetails1Children=new ArrayList<>();
        retailStatisticsDetails1Children.add(new ExcelColumn("销售数量" ,"salenums" ,12));
        retailStatisticsDetails1Children.add(new ExcelColumn("退货数量" ,"canreturnnums" ,12));
        retailStatistics1Details.setChildren(retailStatisticsDetails1Children);
        salesRetailStatistics1Children.add(retailStatistics1Details);

        salesRetailStatistics1Children.add(new ExcelColumn("销售额" ,"paysubtotal" ,12));
        salesRetailStatistics1Children.add(new ExcelColumn("销售成本" ,"costsaleamount" ,12));
        salesRetailStatistics1Children.add(new ExcelColumn("毛利率" ,"grossprofitrate" ,12));

        /******************************销售报表-零售统计******************************/
        /*******************************销售报表-按照品牌导出******************************/
        ExcelColumn salesRetailStatistics2Title = new ExcelColumn("零售统计" ,"" ,0);
        salesRetailStatistics2CollectTitle.add(salesRetailStatistics2Title);

        // 子表头
        List<ExcelColumn> salesRetailStatistics2Children= new ArrayList<>();
        salesRetailStatistics2Title.setChildren(salesRetailStatistics2Children);

        salesRetailStatistics2Children.add(new ExcelColumn("门店" ,"branchname" ,32));
        salesRetailStatistics2Children.add(new ExcelColumn("日期" ,"saletime" ,12));;
        salesRetailStatistics2Children.add(new ExcelColumn("品牌" ,"brandname" ,32));

        ExcelColumn retailStatistics2Details = new ExcelColumn("数量" ,"" ,12);
        List <ExcelColumn> retailStatisticsDetails2Children=new ArrayList<>();
        retailStatisticsDetails2Children.add(new ExcelColumn("销售数量" ,"salenums" ,12));
        retailStatisticsDetails2Children.add(new ExcelColumn("退货数量" ,"canreturnnums" ,12));
        retailStatistics2Details.setChildren(retailStatisticsDetails2Children);
        salesRetailStatistics2Children.add(retailStatistics2Details);

        salesRetailStatistics2Children.add(new ExcelColumn("销售额" ,"paysubtotal" ,12));
        salesRetailStatistics2Children.add(new ExcelColumn("销售成本" ,"costsaleamount" ,12));
        salesRetailStatistics2Children.add(new ExcelColumn("毛利率" ,"grossprofitrate" ,12));

        /******************************销售报表-零售统计******************************/
        /******************************销售报表-零售统计 服装行业******************************/
        ExcelColumn salesRetailStatisticsIndustryTitle = new ExcelColumn("零售统计" ,"" ,0);
        salesRetailStatisticsIndustryCollectTitle.add(salesRetailStatisticsIndustryTitle);

        // 子表头
        List<ExcelColumn> salesRetailStatisticsIndustryChildren= new ArrayList<>();
        salesRetailStatisticsIndustryTitle.setChildren(salesRetailStatisticsIndustryChildren);

        salesRetailStatisticsIndustryChildren.add(new ExcelColumn("门店" ,"branchname" ,32));
        salesRetailStatisticsIndustryChildren.add(new ExcelColumn("日期" ,"saletime" ,12));
        salesRetailStatisticsIndustryChildren.add(new ExcelColumn("品名" ,"commodityname" ,32));
        salesRetailStatisticsIndustryChildren.add(new ExcelColumn("品类" ,"categoryname" ,32));
        salesRetailStatisticsIndustryChildren.add(new ExcelColumn("品牌" ,"brandname" ,32));
        salesRetailStatisticsIndustryChildren.add(new ExcelColumn("销售数量" ,"salenums" ,12));
        salesRetailStatisticsIndustryChildren.add(new ExcelColumn("退货数量" ,"canreturnnums" ,12));
        salesRetailStatisticsIndustryChildren.add(new ExcelColumn("销售金额" ,"paysubtotal" ,12));
        salesRetailStatisticsIndustryChildren.add(new ExcelColumn("销售成本" ,"costsaleamount" ,12));
        salesRetailStatisticsIndustryChildren.add(new ExcelColumn("毛利率" ,"grossprofitrate" ,12));

        /******************************销售报表-零售统计 服装行******************************/

       /******************************销售报表-门店统计 Title star******************************/

        ExcelColumn shopCount = new ExcelColumn("门店统计" ,"" ,0);
        salesStoresSumCollectTitle.add(shopCount);
        // zibiaotiao
        List<ExcelColumn> cTiTle = new ArrayList<>();
        shopCount.setChildren(cTiTle);
        cTiTle.add(new ExcelColumn("门店" ,"branchname" ,32));
        cTiTle.add(new ExcelColumn("日期" ,"saletime" ,32));
        cTiTle.add(new ExcelColumn("销售金额" ,"paysubtotal" ,32));
        cTiTle.add(new ExcelColumn("毛利" ,"profit" ,32));
        cTiTle.add(new ExcelColumn("库存金额" ,"storagetotal" ,32));

        /******************************销售报表-门店统计Title end******************************/
        /******************************销售报表-门店统计--按支付方式******************************/

        ExcelColumn salesStoresCount = new ExcelColumn("门店统计" ,"" ,0);
        salesStoresSumPayCollectTitle.add(salesStoresCount);

        // zibiaotiao
        List<ExcelColumn> salesStoresCountTiTle = new ArrayList<>();
        salesStoresCount.setChildren(salesStoresCountTiTle);

        salesStoresCountTiTle.add(new ExcelColumn("门店" ,"branchname" ,32));
        salesStoresCountTiTle.add(new ExcelColumn("统计日期" ,"saletime" ,32));
        salesStoresCountTiTle.add(new ExcelColumn("付款方式" ,"payname" ,32));
        salesStoresCountTiTle.add(new ExcelColumn("金额" ,"payamount" ,32));
        /******************************销售报表-统计分析-商品明细导出Title start******************************/
        ExcelColumn queryTotalDetailListTitle = new ExcelColumn("统计分析-商品明细" ,"" ,0);
        // 子表头
        List<ExcelColumn>  queryTotalDetailListyChildren= new ArrayList<>();
        queryTotalDetailListTitle.setChildren(queryTotalDetailListyChildren);

        queryTotalDetailListyChildren.add(new ExcelColumn("统计日期" ,"daterange" ,24));
        queryTotalDetailListyChildren.add(new ExcelColumn("品名" ,"commodityname" ,64));
        queryTotalDetailListyChildren.add(new ExcelColumn("品类" ,"categoryname" ,64));
        queryTotalDetailListyChildren.add(new ExcelColumn("销量" ,"saledcount_sum" ,12));
        queryTotalDetailListyChildren.add(new ExcelColumn("销售额" ,"saledvolume_sum" ,24));
        queryTotalDetailListyChildren.add(new ExcelColumn("销售成本" ,"saledcost_sum" ,24));
        queryTotalDetailListyChildren.add(new ExcelColumn("毛利" ,"saledprofit_sum" ,24));
        queryTotalDetailListyChildren.add(new ExcelColumn("退货数" ,"returncount_sum" ,24));

        queryTotalDetailListCollectTitle.add(queryTotalDetailListTitle);
        /******************************销售报表-统计分析-商品明细导出Title end******************************/


        /******************************销售报表汇总导出Title end******************************/


        /******************************销售报表汇总导出Title end******************************/


        /******************************库存报表-库存统计导出Title start******************************/
        ExcelColumn StorageStatisticsTitle = new ExcelColumn("库存报表统计_商品", "", 170);

        List<ExcelColumn> storageStatisticsExcelList = new ArrayList<>();
        storageStatisticsExcelList.add(new ExcelColumn("门店" ,"branchname",17));
        storageStatisticsExcelList.add(new ExcelColumn("统计日期" ,"querytime",34));
        storageStatisticsExcelList.add(new ExcelColumn("货号" ,"commoditycode",17));
        storageStatisticsExcelList.add(new ExcelColumn("品名" ,"commodityname",17));
        storageStatisticsExcelList.add(new ExcelColumn("品类" ,"categoryname",17));
        storageStatisticsExcelList.add(new ExcelColumn("品牌" ,"brandname",17));

        ExcelColumn currentStorageStatistics = new ExcelColumn("期初结余" ,"" ,34);
        List<ExcelColumn> currentStorageStatisticsChildren = new ArrayList<>();
        currentStorageStatisticsChildren.add(new ExcelColumn("结余数量","currentstorage" ,17));
        currentStorageStatisticsChildren.add(new ExcelColumn("结余金额","starttotal" ,17));
        currentStorageStatistics.setChildren(currentStorageStatisticsChildren);
        storageStatisticsExcelList.add(currentStorageStatistics);

        ExcelColumn currentOutStatistics = new ExcelColumn("本期出库" ,"" ,34);
        List<ExcelColumn> currentOutStatisticsChildren = new ArrayList<>();
        currentOutStatisticsChildren.add(new ExcelColumn("出库数量","outnums" ,17));
        currentOutStatisticsChildren.add(new ExcelColumn("出库金额","outtotal" ,17));
        currentOutStatistics.setChildren(currentOutStatisticsChildren);
        storageStatisticsExcelList.add(currentOutStatistics);

        ExcelColumn currentInStatistics = new ExcelColumn("本期入库" ,"" ,34);
        List<ExcelColumn> currentInStatisticsChildren = new ArrayList<>();
        currentInStatisticsChildren.add(new ExcelColumn("入库数量","innums" ,17));
        currentInStatisticsChildren.add(new ExcelColumn("入库金额","intotal" ,17));
        currentInStatistics.setChildren(currentInStatisticsChildren);
        storageStatisticsExcelList.add(currentInStatistics);

        ExcelColumn currentLossStatistics = new ExcelColumn("本期报损" ,"" ,34);
        List<ExcelColumn> currentLossStatisticsChildren = new ArrayList<>();
        currentLossStatisticsChildren.add(new ExcelColumn("报损数量","lossnums" ,17));
        currentLossStatisticsChildren.add(new ExcelColumn("报损金额","losstotal" ,17));
        currentLossStatistics.setChildren(currentLossStatisticsChildren);
        storageStatisticsExcelList.add(currentLossStatistics);

        ExcelColumn currentSaleStatistics = new ExcelColumn("本期销售" ,"" ,34);
        List<ExcelColumn> currentSaleStatisticsChildren = new ArrayList<>();
        currentSaleStatisticsChildren.add(new ExcelColumn("销售数量","salenums" ,17));
        currentSaleStatisticsChildren.add(new ExcelColumn("销售金额","saletotal" ,17));
        currentSaleStatistics.setChildren(currentSaleStatisticsChildren);
        storageStatisticsExcelList.add(currentSaleStatistics);

        ExcelColumn currentRemindStatistics = new ExcelColumn("本期结存" ,"" ,34);
        List<ExcelColumn> currentRemindStatisticsChildren = new ArrayList<>();
        currentRemindStatisticsChildren.add(new ExcelColumn("结存数量","remindstorage" ,17));
        currentRemindStatisticsChildren.add(new ExcelColumn("结存金额","currenttotal" ,17));
        currentRemindStatistics.setChildren(currentRemindStatisticsChildren);
        storageStatisticsExcelList.add(currentRemindStatistics);

        StorageStatisticsTitle.setChildren(storageStatisticsExcelList);
        storageStatisticsCollectTitle.add(StorageStatisticsTitle);
        /******************************库存报表-库存统计导出Title end******************************/



        /******************************库存报表-库存明细导出Title start******************************/
        ExcelColumn StorageCategoryStatisticsTitle = new ExcelColumn("库存报表明细", "", 170);

        List<ExcelColumn> storageStatisticsCategoryExcelList = new ArrayList<>();
        storageStatisticsCategoryExcelList.add(new ExcelColumn("门店" ,"branchname",17));
        storageStatisticsCategoryExcelList.add(new ExcelColumn("统计日期" ,"querytime",34));
        storageStatisticsCategoryExcelList.add(new ExcelColumn("品类" ,"categoryname",17));

        ExcelColumn currentStorageCategoryStatistics = new ExcelColumn("期初结余" ,"" ,34);
        List<ExcelColumn> currentStorageCategoryStatisticsChildren = new ArrayList<>();
        currentStorageCategoryStatisticsChildren.add(new ExcelColumn("结余数量","currentstorage" ,17));
        currentStorageCategoryStatisticsChildren.add(new ExcelColumn("结余金额","starttotal" ,17));
        currentStorageCategoryStatistics.setChildren(currentStorageStatisticsChildren);
        storageStatisticsCategoryExcelList.add(currentStorageStatistics);

        ExcelColumn currentOutCategoryStatistics = new ExcelColumn("本期出库" ,"" ,34);
        List<ExcelColumn> currentOutCategoryStatisticsChildren = new ArrayList<>();
        currentOutCategoryStatisticsChildren.add(new ExcelColumn("出库数量","outnums" ,17));
        currentOutCategoryStatisticsChildren.add(new ExcelColumn("出库金额","outtotal" ,17));
        currentOutCategoryStatistics.setChildren(currentOutCategoryStatisticsChildren);
        storageStatisticsCategoryExcelList.add(currentOutCategoryStatistics);

        ExcelColumn currentInCategoryStatistics = new ExcelColumn("本期入库" ,"" ,34);
        List<ExcelColumn> currentInCategoryStatisticsChildren = new ArrayList<>();
        currentInCategoryStatisticsChildren.add(new ExcelColumn("入库数量","innums" ,17));
        currentInCategoryStatisticsChildren.add(new ExcelColumn("入库金额","intotal" ,17));
        currentInCategoryStatistics.setChildren(currentInCategoryStatisticsChildren);
        storageStatisticsCategoryExcelList.add(currentInCategoryStatistics);

        ExcelColumn currentLossCategoryStatistics = new ExcelColumn("本期报损" ,"" ,34);
        List<ExcelColumn> currentLossCategoryStatisticsChildren = new ArrayList<>();
        currentLossCategoryStatisticsChildren.add(new ExcelColumn("报损数量","lossnums" ,17));
        currentLossCategoryStatisticsChildren.add(new ExcelColumn("报损金额","losstotal" ,17));
        currentLossCategoryStatistics.setChildren(currentLossCategoryStatisticsChildren);
        storageStatisticsCategoryExcelList.add(currentLossCategoryStatistics);

        ExcelColumn currentSaleCategoryStatistics = new ExcelColumn("本期销售" ,"" ,34);
        List<ExcelColumn> currentSaleCategoryStatisticsChildren = new ArrayList<>();
        currentSaleCategoryStatisticsChildren.add(new ExcelColumn("销售数量","salenums" ,17));
        currentSaleCategoryStatisticsChildren.add(new ExcelColumn("销售金额","saletotal" ,17));
        currentSaleCategoryStatistics.setChildren(currentSaleCategoryStatisticsChildren);
        storageStatisticsCategoryExcelList.add(currentSaleCategoryStatistics);

        ExcelColumn currentRemindCategoryStatistics = new ExcelColumn("本期结存" ,"" ,34);
        List<ExcelColumn> currentRemindCategoryStatisticsChildren = new ArrayList<>();
        currentRemindCategoryStatisticsChildren.add(new ExcelColumn("结存数量","remindstorage" ,17));
        currentRemindCategoryStatisticsChildren.add(new ExcelColumn("结存金额","currenttotal" ,17));
        currentRemindCategoryStatistics.setChildren(currentRemindCategoryStatisticsChildren);
        storageStatisticsCategoryExcelList.add(currentRemindCategoryStatistics);

        StorageCategoryStatisticsTitle.setChildren(storageStatisticsCategoryExcelList);
        storageCategoryStatisticsCollectTitle.add(StorageCategoryStatisticsTitle);
        /******************************库存报表-库存统计导出Title end******************************/



        /******************************库存报表-库存统计导出Title start******************************/
        ExcelColumn StorageStatisticsBrandTitle = new ExcelColumn("库存统计_品牌", "", 170);

        List<ExcelColumn> storageStatisticsBrandExcelList = new ArrayList<>();
        storageStatisticsBrandExcelList.add(new ExcelColumn("门店" ,"branchname",17));
        storageStatisticsBrandExcelList.add(new ExcelColumn("统计日期" ,"querytime",34));
        storageStatisticsBrandExcelList.add(new ExcelColumn("品牌" ,"brandname",17));

        ExcelColumn currentStorageBrandStatistics = new ExcelColumn("期初结余" ,"" ,34);
        List<ExcelColumn> currentStorageBrandStatisticsChildren = new ArrayList<>();
        currentStorageBrandStatisticsChildren.add(new ExcelColumn("结余数量","currentstorage" ,17));
        currentStorageBrandStatisticsChildren.add(new ExcelColumn("结余金额","starttotal" ,17));
        currentStorageBrandStatistics.setChildren(currentStorageStatisticsChildren);
        storageStatisticsBrandExcelList.add(currentStorageStatistics);

        ExcelColumn currentOutBrandStatistics = new ExcelColumn("本期出库" ,"" ,34);
        List<ExcelColumn> currentOutBrandStatisticsChildren = new ArrayList<>();
        currentOutBrandStatisticsChildren.add(new ExcelColumn("出库数量","outnums" ,17));
        currentOutBrandStatisticsChildren.add(new ExcelColumn("出库金额","outtotal" ,17));
        currentOutBrandStatistics.setChildren(currentOutBrandStatisticsChildren);
        storageStatisticsBrandExcelList.add(currentOutBrandStatistics);

        ExcelColumn currentInBrandStatistics = new ExcelColumn("本期入库" ,"" ,34);
        List<ExcelColumn> currentInBrandStatisticsChildren = new ArrayList<>();
        currentInBrandStatisticsChildren.add(new ExcelColumn("入库数量","innums" ,17));
        currentInBrandStatisticsChildren.add(new ExcelColumn("入库金额","intotal" ,17));
        currentInBrandStatistics.setChildren(currentInBrandStatisticsChildren);
        storageStatisticsBrandExcelList.add(currentInBrandStatistics);

        ExcelColumn currentLossBrandStatistics = new ExcelColumn("本期报损" ,"" ,34);
        List<ExcelColumn> currentLossBrandStatisticsChildren = new ArrayList<>();
        currentLossBrandStatisticsChildren.add(new ExcelColumn("报损数量","lossnums" ,17));
        currentLossBrandStatisticsChildren.add(new ExcelColumn("报损金额","losstotal" ,17));
        currentLossBrandStatistics.setChildren(currentLossBrandStatisticsChildren);
        storageStatisticsBrandExcelList.add(currentLossBrandStatistics);

        ExcelColumn currentSaleBrandStatistics = new ExcelColumn("本期销售" ,"" ,34);
        List<ExcelColumn> currentSaleBrandStatisticsChildren = new ArrayList<>();
        currentSaleBrandStatisticsChildren.add(new ExcelColumn("销售数量","salenums" ,17));
        currentSaleBrandStatisticsChildren.add(new ExcelColumn("销售金额","saletotal" ,17));
        currentSaleBrandStatistics.setChildren(currentSaleBrandStatisticsChildren);
        storageStatisticsBrandExcelList.add(currentSaleBrandStatistics);

        ExcelColumn currentRemindBrandStatistics = new ExcelColumn("本期结存" ,"" ,34);
        List<ExcelColumn> currentRemindBrandStatisticsChildren = new ArrayList<>();
        currentRemindBrandStatisticsChildren.add(new ExcelColumn("结存数量","remindstorage" ,17));
        currentRemindBrandStatisticsChildren.add(new ExcelColumn("结存金额","currenttotal" ,17));
        currentRemindBrandStatistics.setChildren(currentRemindBrandStatisticsChildren);
        storageStatisticsBrandExcelList.add(currentRemindBrandStatistics);

        StorageStatisticsBrandTitle.setChildren(storageStatisticsBrandExcelList);
        storageBrandStatisticsCollectTitle.add(StorageStatisticsBrandTitle);
        /******************************库存报表-库存统计导出Title end******************************/

        /******************************库存报表-库存统计(服装)导出Title start******************************/
        ExcelColumn StorageStatisticsClothTitle = new ExcelColumn("库存报表统计", "", 170);

        List<ExcelColumn> storageStatisticsClothExcelList = new ArrayList<>();
        storageStatisticsClothExcelList.add(new ExcelColumn("门店" ,"branchname",17));
        storageStatisticsClothExcelList.add(new ExcelColumn("日期" ,"querytime",34));
        storageStatisticsClothExcelList.add(new ExcelColumn("货号" ,"spucode",17));
        storageStatisticsClothExcelList.add(new ExcelColumn("品名" ,"commodityname",17));
        storageStatisticsClothExcelList.add(new ExcelColumn("品类" ,"categoryname",17));
        storageStatisticsClothExcelList.add(new ExcelColumn("期初结余" ,"currentstorage",17));
        storageStatisticsClothExcelList.add(new ExcelColumn("本期出入库" ,"changenums",17));

        ExcelColumn currentSaleStatisticsCloth = new ExcelColumn("本期销售" ,"" ,34);
        List<ExcelColumn> currentSaleStatisticsClothChildren = new ArrayList<>();
        currentSaleStatisticsClothChildren.add(new ExcelColumn("销售数量","salenums" ,17));
        currentSaleStatisticsClothChildren.add(new ExcelColumn("销售金额","saletotal" ,17));
        currentSaleStatisticsCloth.setChildren(currentSaleStatisticsClothChildren);
        storageStatisticsClothExcelList.add(currentSaleStatisticsCloth);

        ExcelColumn currentRemindStatisticsCloth = new ExcelColumn("本期结存" ,"" ,34);
        List<ExcelColumn> currentRemindStatisticsCltohChildren = new ArrayList<>();
        currentRemindStatisticsCltohChildren.add(new ExcelColumn("结存数量","remindstorage" ,17));
        currentRemindStatisticsCltohChildren.add(new ExcelColumn("结存金额","currenttotal" ,17));
        currentRemindStatisticsCloth.setChildren(currentRemindStatisticsCltohChildren);
        storageStatisticsClothExcelList.add(currentRemindStatisticsCloth);

        StorageStatisticsClothTitle.setChildren(storageStatisticsClothExcelList);
        storageStatisticsClothCollectTitle.add(StorageStatisticsClothTitle);
        /******************************库存报表-库存统计导出Title end******************************/

        /******************************库存报表-库存明细导出Title start******************************/
        ExcelColumn StorageInventoryTitle = new ExcelColumn("库存报表明细", "", 204);

        List<ExcelColumn> storageInventoryExcelList = new ArrayList<>();
        storageInventoryExcelList.add(new ExcelColumn("业务类型" ,"businesstname",17));
        storageInventoryExcelList.add(new ExcelColumn("业务单号" ,"billcode",30));
        storageInventoryExcelList.add(new ExcelColumn("业务日期" ,"createtime",17));
        storageInventoryExcelList.add(new ExcelColumn("货号" ,"commoditycode",17));
        storageInventoryExcelList.add(new ExcelColumn("条形码" ,"barcode",17));
        storageInventoryExcelList.add(new ExcelColumn("品名" ,"commodityname",17));
        storageInventoryExcelList.add(new ExcelColumn("颜色/尺码" ,"specvalue",17));
        storageInventoryExcelList.add(new ExcelColumn("品类" ,"categoryname",17));
        storageInventoryExcelList.add(new ExcelColumn("品牌" ,"brandname",17));

        ExcelColumn storageChangeStatistics = new ExcelColumn("出入库" ,"" ,34);
        List<ExcelColumn> storageChangeStatisticsChildren = new ArrayList<>();
        storageChangeStatisticsChildren.add(new ExcelColumn("数量","changenums" ,17));
        storageChangeStatisticsChildren.add(new ExcelColumn("金额","amount" ,17));
        storageChangeStatistics.setChildren(storageChangeStatisticsChildren);
        storageInventoryExcelList.add(storageChangeStatistics);
        storageInventoryExcelList.add(new ExcelColumn("剩余库存" ,"currentstorage",17));

        StorageInventoryTitle.setChildren(storageInventoryExcelList);
        storageInventoryCollectTitle.add(StorageInventoryTitle);
        /******************************库存报表-库存明细导出Title end******************************/

        /******************************库存报表-库存明细(服装)导出Title start******************************/
        ExcelColumn StorageInventoryClothTitle = new ExcelColumn("库存报表明细", "", 204);

        List<ExcelColumn> storageInventoryClothExcelList = new ArrayList<>();
        storageInventoryClothExcelList.add(new ExcelColumn("业务类型" ,"businesstname",17));
        storageInventoryClothExcelList.add(new ExcelColumn("业务单号" ,"billcode",30));
        storageInventoryClothExcelList.add(new ExcelColumn("业务日期" ,"createtime",17));
        storageInventoryClothExcelList.add(new ExcelColumn("货号" ,"spucode",17));
        storageInventoryClothExcelList.add(new ExcelColumn("条形码" ,"barcode",17));
        storageInventoryClothExcelList.add(new ExcelColumn("品名" ,"commodityname",17));
        storageInventoryClothExcelList.add(new ExcelColumn("颜色/尺码" ,"specvalue",17));
        storageInventoryClothExcelList.add(new ExcelColumn("品类" ,"categoryname",17));
        storageInventoryClothExcelList.add(new ExcelColumn("品牌" ,"brandname",17));

        ExcelColumn storageChangeStatisticsCloth = new ExcelColumn("出入库" ,"" ,34);
        List<ExcelColumn> storageChangeStatisticsClothChildren = new ArrayList<>();
        storageChangeStatisticsClothChildren.add(new ExcelColumn("数量","changenums" ,17));
        storageChangeStatisticsClothChildren.add(new ExcelColumn("金额","amount" ,17));
        storageChangeStatisticsCloth.setChildren(storageChangeStatisticsClothChildren);
        storageInventoryClothExcelList.add(storageChangeStatisticsCloth);
        storageInventoryClothExcelList.add(new ExcelColumn("剩余库存" ,"currentstorage",17));

        StorageInventoryClothTitle.setChildren(storageInventoryClothExcelList);
        storageInventoryClothCollectTitle.add(StorageInventoryClothTitle);
        /******************************库存报表-库存明细导出Title end******************************/

        /******************************服装行业商品明细汇总导出Title start******************************/
        commodityDetailByIndustryCollectTitle.add(new ExcelColumn("*商品名称" ,"commodityname" ,32));
        commodityDetailByIndustryCollectTitle.add(new ExcelColumn("货号" ,"spucode" ,32));
        commodityDetailByIndustryCollectTitle.add(new ExcelColumn("商品状态" ,"commoditystatus" ,12));
        commodityDetailByIndustryCollectTitle.add(new ExcelColumn("销售价格" ,"saleprice" ,12));
        commodityDetailByIndustryCollectTitle.add(new ExcelColumn("会员价" ,"memberprice" ,12));
        commodityDetailByIndustryCollectTitle.add(new ExcelColumn("进价" ,"buyprice" ,12));
        commodityDetailByIndustryCollectTitle.add(new ExcelColumn("配送价" ,"dispatchprice" ,12));
        commodityDetailByIndustryCollectTitle.add(new ExcelColumn("批发价" ,"wholesaleprice" ,12));

        /******************************服装行业商品明细汇总导出Title  end******************************/
        /******************************服装行业商品明细汇总导出 2.0 Title start******************************/
        commodityDetailByIndustry2CollectTitle.add(new ExcelColumn("*商品名称" ,"commodityname" ,32));
        commodityDetailByIndustry2CollectTitle.add(new ExcelColumn("货号" ,"spucode" ,32));
        commodityDetailByIndustry2CollectTitle.add(new ExcelColumn("销售价格" ,"saleprice" ,12));
        commodityDetailByIndustry2CollectTitle.add(new ExcelColumn("会员价" ,"memberprice" ,12));
        //commodityDetailByIndustry2CollectTitle.add(new ExcelColumn("进价" ,"buyprice" ,24));
        commodityDetailByIndustry2CollectTitle.add(new ExcelColumn("配送价" ,"dispatchprice" ,12));
        commodityDetailByIndustry2CollectTitle.add(new ExcelColumn("批发价" ,"wholesaleprice" ,12));
        commodityDetailByIndustry2CollectTitle.add(new ExcelColumn("商品状态" ,"commoditystatus" ,12));
        commodityDetailByIndustry2CollectTitle.add(new ExcelColumn("品牌名称" ,"brandname" ,12));
        commodityDetailByIndustry2CollectTitle.add(new ExcelColumn("类别名称" ,"categoryname" ,12));
        commodityDetailByIndustry2CollectTitle.add(new ExcelColumn("助记码" ,"mnemonic" ,24));

        /******************************服装行业商品明细汇总导出Title  end******************************/

 /*  ******************************服装行业商品明细汇总导出Title start******************************/


        //commodityByIndustryCollectTitle.add(new ExcelColumn("条形码" ,"barcode" ,24));
        commodityByIndustryCollectTitle.add(new ExcelColumn("*商品名称" ,"commodityname" ,32));
        commodityByIndustryCollectTitle.add(new ExcelColumn("货号" ,"spucode" ,24));
      //  commodityByIndustryCollectTitle.add(new ExcelColumn("单位" ,"unitname" ,12));
        //commodityByIndustryCollectTitle.add(new ExcelColumn("颜色" ,"skucolor" ,12));
        //commodityByIndustryCollectTitle.add(new ExcelColumn("尺码" ,"skusize" ,12));
        //commodityByIndustryCollectTitle.add(new ExcelColumn("进货价" ,"buyprice" ,12));
        commodityByIndustryCollectTitle.add(new ExcelColumn("销售价格" ,"saleprice" ,12));
        //commodityByIndustryCollectTitle.add(new ExcelColumn("会员价" ,"memberprice" ,12));
        //commodityByIndustryCollectTitle.add(new ExcelColumn("批发价" ,"wholesaleprice" ,12));
        commodityByIndustryCollectTitle.add(new ExcelColumn("商品状态" ,"commoditystatus" ,12));
        commodityByIndustryCollectTitle.add(new ExcelColumn("品牌" ,"brandname" ,32));
        commodityByIndustryCollectTitle.add(new ExcelColumn("类别" ,"categoryname" ,32));
        commodityByIndustryCollectTitle.add(new ExcelColumn("助记码" ,"mnemonic" ,24));
        commodityByIndustryCollectTitle.add(new ExcelColumn("供应商" ,"suppliername" ,64));

      //  commodityByIndustryCollectTitle.add(new ExcelColumn("季节" ,"season" ,12));

        /******************************服装行业商品明细汇总导出Title  end******************************/

        /******************************商品明细汇总导出 1.0Title start******************************/
        commodityDetailByCollectTitle.add(new ExcelColumn("货号" ,"commoditycode" ,24));
        commodityDetailByCollectTitle.add(new ExcelColumn("条形码" ,"barcodes" ,24));
        commodityDetailByCollectTitle.add(new ExcelColumn("品名" ,"commodityname" ,32));
        commodityDetailByCollectTitle.add(new ExcelColumn("销售方式" ,"pricing" ,24));
        commodityDetailByCollectTitle.add(new ExcelColumn("PLU" ,"plu" ,24));
        commodityDetailByCollectTitle.add(new ExcelColumn("单位" ,"unitname" ,12));
        commodityDetailByCollectTitle.add(new ExcelColumn("进货价" ,"buyprice" ,12));
        commodityDetailByCollectTitle.add(new ExcelColumn("零售价" ,"saleprice" ,12));
        commodityDetailByCollectTitle.add(new ExcelColumn("会员价" ,"memberprice" ,12));
        commodityDetailByCollectTitle.add(new ExcelColumn("有效期(天)" ,"validtime" ,12));
        commodityDetailByCollectTitle.add(new ExcelColumn("规格" ,"spec" ,24));
        commodityDetailByCollectTitle.add(new ExcelColumn("类别" ,"categoryname" ,32));
        commodityDetailByCollectTitle.add(new ExcelColumn("品牌" ,"brandname" ,32));
        commodityDetailByCollectTitle.add(new ExcelColumn("进货规格" ,"brandname" ,32));
        commodityDetailByCollectTitle.add(new ExcelColumn("供应商名称" ,"buyspec" ,64));
        commodityDetailByCollectTitle.add(new ExcelColumn("商品状态" ,"commoditystatus" ,12));
        commodityDetailByCollectTitle.add(new ExcelColumn("商品类型" ,"commoditytype" ,16));
        commodityDetailByCollectTitle.add(new ExcelColumn("库管类型" ,"storagetype" ,16));
        commodityDetailByCollectTitle.add(new ExcelColumn("是否允许积分" ,"canscore" ,12));
        commodityDetailByCollectTitle.add(new ExcelColumn("积分值" ,"score" ,12));
        commodityDetailByCollectTitle.add(new ExcelColumn("导购员是否提成" ,"commission" ,12));
        commodityDetailByCollectTitle.add(new ExcelColumn("提成值/提成率" ,"commissionall" ,12));
        commodityDetailByCollectTitle.add(new ExcelColumn("进项税" ,"intaxrate" ,12));
        commodityDetailByCollectTitle.add(new ExcelColumn("销项税" ,"outtaxrate" ,12));


        /******************************服装行业商品明细汇总导出Title  end******************************/

        /******************************商品明细汇总导出 1.0Title start******************************/
        commodityDetailBy2CollectTitle.add(new ExcelColumn("货号" ,"commoditycode" ,12));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("条形码" ,"barcodes" ,24));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("品名" ,"commodityname" ,64));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("销售方式" ,"pricing" ,12));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("PLU" ,"plu" ,12));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("单位" ,"unitname" ,12));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("进货价" ,"buyprice" ,12));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("零售价" ,"saleprice" ,12));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("会员价" ,"memberprice" ,12));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("批发价" ,"wholesaleprice" ,12));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("配送价" ,"dispatchprice" ,12));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("有效期(天)" ,"validtime" ,12));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("规格" ,"spec" ,23));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("类别" ,"categoryname" ,23));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("品牌" ,"brandname" ,24));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("进货规格" ,"brandname" ,12));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("供应商名称" ,"buyspec" ,32));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("商品状态" ,"commoditystatus" ,12));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("商品类型" ,"commoditytype" ,32));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("库管类型" ,"storagetype" ,32));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("是否允许积分" ,"canscore" ,12));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("积分值" ,"score" ,12));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("导购员是否提成" ,"commission" ,12));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("提成值/提成率" ,"commissionall" ,12));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("进项税" ,"intaxrate" ,12));
        commodityDetailBy2CollectTitle.add(new ExcelColumn("销项税" ,"outtaxrate" ,12));


        /******************************服装行业商品明细汇总导出Title  end******************************/

        /******************************库存报表-库存查询导出Title start******************************/
        ExcelColumn StorageDetailTitle = new ExcelColumn("库存报表明细", "", 115);

        List<ExcelColumn> storageDetailExcelList = new ArrayList<>();
        storageDetailExcelList.add(new ExcelColumn("货号" ,"commoditycode",30));
        storageDetailExcelList.add(new ExcelColumn("品名" ,"commodityname",17));
        storageDetailExcelList.add(new ExcelColumn("品类" ,"categoryname",17));
        storageDetailExcelList.add(new ExcelColumn("品牌" ,"brandname",17));
        storageDetailExcelList.add(new ExcelColumn("库存数量" ,"storage",17));
        storageDetailExcelList.add(new ExcelColumn("库存金额" ,"storageprice",17));

        StorageDetailTitle.setChildren(storageDetailExcelList);
        storageDetailCollectTitle.add(StorageDetailTitle);
        /******************************库存报表-库存查询导出Title end******************************/
	}

    public static List<ExcelColumn> getSaleCommodityCollectTitle() {
        return saleCommodityCollectTitle;
    }

    public static List<ExcelColumn> getSaleBillCollectTitle() {
        return saleBillCollectTitle;
    }

    public static List<ExcelColumn> getSaleDetailCollectTitle() {
        return saleDetailCollectTitle;
    }
    //服装行业商品明细导出
    public static List<ExcelColumn> getCommodityDetailByIndustryCollectTitle() {
        return commodityDetailByIndustryCollectTitle;
    }
    //服装行业商品明细导出
    public static List<ExcelColumn> getCommodityDetailByIndustry2CollectTitle() {
        return commodityDetailByIndustry2CollectTitle;
    }
    //服装行业商品档案导出
    public static List<ExcelColumn> getCommodityByIndustryCollectTitle() {
        return commodityByIndustryCollectTitle;
    }
    //行业商品明细导出  //软件版本（1.标准版 2.连锁版）
    public static List<ExcelColumn> getCommodityDetailByCollectTitle() {
        return commodityDetailByCollectTitle;
    }
    //行业商品明细导出
    public static List<ExcelColumn> getCommodityDetailBy2CollectTitle() {
        return commodityDetailBy2CollectTitle;
    }

    public static List<ExcelColumn> getPurchaseStatisticsCollectTitle() {
        purchaseStatisticsCollectTitle.get(0).setTitle("采购报表统计");
        return purchaseStatisticsCollectTitle;
    }

    public static List<ExcelColumn> getPurchaseStatisticsClothCollectTitle() {
        purchaseStatisticsClothCollectTitle.get(0).setTitle("采购报表统计_服装");
        return purchaseStatisticsClothCollectTitle;
    }

    public static List<ExcelColumn> getPurchaseDetailsCollectTitle() {
        purchaseDetailsCollectTitle.get(0).setTitle("采购报表明细");
        return purchaseDetailsCollectTitle;
    }

    public static List<ExcelColumn> getPurchaseDetailsClothCollectTitle() {
        purchaseDetailsClothCollectTitle.get(0).setTitle("采购报表明细_服装");
        return purchaseDetailsClothCollectTitle;
    }

    public static List<ExcelColumn> getSalesManageDatailCollectTitle() {
        salesManageDatailCollectTitle.get(0).setTitle("收银流水");
        return salesManageDatailCollectTitle;
    }
    public static List<ExcelColumn> getCommoditySaleWaterForReport() {
       commoditySaleWaterForReportCollectTitle.get(0).setTitle("商品销售流水");
        return commoditySaleWaterForReportCollectTitle;
    }

    public static List<ExcelColumn> getSalesRetailStatisticsCollectTitle() {
        salesRetailStatisticsCollectTitle.get(0).setTitle("商品销售统计");
        return salesRetailStatisticsCollectTitle;
    }
    public static List<ExcelColumn> getSalesRetailStatistics1CollectTitle() {
        salesRetailStatistics1CollectTitle.get(0).setTitle("商品销售统计");
        return salesRetailStatistics1CollectTitle;
    }
    public static List<ExcelColumn> getSalesRetailStatistics2CollectTitle() {
        salesRetailStatistics2CollectTitle.get(0).setTitle("商品销售统计");
        return salesRetailStatistics2CollectTitle;
    }
    //服装行业的导出
    public static List<ExcelColumn> getSalesRetailStatisticsIndustryCollectTitle() {
        salesRetailStatisticsIndustryCollectTitle.get(0).setTitle("零售统计");
        return salesRetailStatisticsIndustryCollectTitle;
    }

    public static List<ExcelColumn> getSalesStoresSumCollectTitle() {
        salesStoresSumCollectTitle.get(0).setTitle("门店统计");
        return salesStoresSumCollectTitle;
    }
    //按支付方式
    public static List<ExcelColumn> getSalesStoresSumPayCollectTitle() {
        salesStoresSumPayCollectTitle.get(0).setTitle("门店统计-按支付方式");
        return salesStoresSumPayCollectTitle;
    }

    public static List<ExcelColumn> getStorageStatisticsCollectTitle() {
        storageStatisticsCollectTitle.get(0).setTitle("库存报表统计");
        return storageStatisticsCollectTitle;
    }

    public static List<ExcelColumn> getStorageStatisticsClothCollectTitle() {
        storageStatisticsClothCollectTitle.get(0).setTitle("库存报表统计_服装");
        return storageStatisticsClothCollectTitle;
    }
    public static List<ExcelColumn> getStorageInventoryCollectTitle() {
        storageInventoryCollectTitle.get(0).setTitle("库存报表明细");
        return storageInventoryCollectTitle;
    }

    public static List<ExcelColumn> getStorageInventoryClothCollectTitle() {
        storageInventoryClothCollectTitle.get(0).setTitle("库存报表明细");
        return storageInventoryClothCollectTitle;
    }

    public static List<ExcelColumn> getStorageDetailCollectTitle() {
        storageDetailCollectTitle.get(0).setTitle("库存查询");
        return storageDetailCollectTitle;
    }

    /**********************************销售报表，统计分析报表***********************************************/
    public static List<ExcelColumn> queryTotalDetailListCollectTitle() {
        queryTotalDetailListCollectTitle.get(0).setTitle("商品分析明细");
        return queryTotalDetailListCollectTitle;
    }

    public static List<ExcelColumn> getStorageCategoryStatisticsCollectTitle() {
        storageCategoryStatisticsCollectTitle.get(0).setTitle("库存报表统计_品类");
        return storageCategoryStatisticsCollectTitle;
    }

    public static List<ExcelColumn> getStorageBrandStatisticsCollectTitle() {
        storageBrandStatisticsCollectTitle.get(0).setTitle("库存报表统计_品牌");
        return storageBrandStatisticsCollectTitle;
    }
}
