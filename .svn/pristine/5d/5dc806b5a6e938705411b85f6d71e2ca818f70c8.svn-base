<@override name="jsfile">
<script src="${zl.web.resource.address}/js/hightcharts/highcharts.js?version=${zl.web.resource.version}"></script>
</@override>
<@override name="jstext">
<script>
    $(function () {
        search();
    })
    var salecountarr = [];
    var profitcountarr = [];
    var categories = [];
        <#if saleCountList?? && saleCountList?size gt 0>
            <#list saleCountList as countList>
            salecountarr.push(${countList.salescount!0});
            profitcountarr.push(${countList.profitcount!0});
            categories.push('${countList.countdate!''}');
            </#list>
        </#if>

    function search() {
        generateChart(categories, [
            {
                name: '销售金额',
                data: salecountarr
            },
            {
                name: '毛利',
                data: profitcountarr
            }
        ]);
    }

    function generateChart(categories, series) {

        Highcharts.setOptions({
            lang: {
                noData: '暂无数据'
            }
        });

        $('#container').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '五天内销售额统计'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                crosshair: true,
                categories: categories
            },
            yAxis: {
                min: 0,
                title: {
                    text: '元'
                },
                minRange: 1
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y}元</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0,
                    dataLabels:{
                        enabled: true
                    }
                }
            },
            series: series
        });
    }

</script>
</@override>
<@override name="csstext">
<style>

    .home_panel_heading{
        font-size: 18px;
        font-weight:bold;
    }
</style>
</@override>
<@override name="right">

<div class="panel panel-default">
    <div class="panel-heading home_panel_heading">
        销售统计分析
    </div>
    <div class="panel-body">
        <div id="container" style="height:400px"></div>
    </div>
</div>

<div class="row">
    <div class="col-lg-6 col-xs-12 col-sm-12">
        <div class="panel panel-default">
            <div class="panel-heading home_panel_heading">
                畅销商品排行榜
            </div>
            <div class="panel-body">
                <div class="tab-pane active">
                    <div class="table-responsive">
                        <table class="table  table-hover ">
                            <thead>
                            <tr>
                                <th> 商品名称</th>
                                <th> 单价</th>
                                <th> 销量</th>
                            </tr>
                            </thead>
                            <tbody>
                                <#if bestSales?? && bestSales?size gt 0>
                                    <#list bestSales as object>
                                    <tr>
                                        <td> ${object.commodityname!''}</td>
                                        <td> ${object.paysaleprice!''}</td>
                                        <td> ${object.nums!''}</td>
                                    </tr>
                                    </#list>
                                <#else>
                                <tr>
                                    <td colspan="3" align="center">暂无相关数据</td>
                                </tr>
                                </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6 col-xs-12 col-sm-12">
        <div class="panel panel-default">
            <div class="panel-heading home_panel_heading">
                商品库存预警
            </div>
            <div class="panel-body">
                <div class="tab-content">
                    <div class="tab-pane active">
                        <div class="table-responsive">
                            <table class="table  table-hover ">
                                <thead>
                                <tr>
                                    <th> 商品名称</th>
                                    <th> 数量</th>
                                    <th> 实收</th>
                                    <th> 库存预警(低位)</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#if badSales?? &&badSales?size gt 0>
                                        <#list badSales as object>
                                        <tr>
                                            <td> ${object.commodityname!''}</td>
                                            <td> ${object.storage!''}</td>
                                            <td> ${object.cost!''}</td>
                                            <td> ${object.stockwarninglow!''}</td>
                                        </tr>
                                        </#list>
                                    <#else>
                                    <tr>
                                        <td colspan="4" align="center">暂无相关数据</td>
                                    </tr>
                                    </#if>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</@override>
<@override name="window">
</@override>
<@extends name="./base_main.ftl"/>