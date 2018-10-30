<@override name="jstext">
<style type="text/css">

</style>
<script>
    function updateConfig() {
        var wxappsecret = $('#wxappsecret').val();
        var alipayappid = $('#alipayappid').val();
        var alipaypublickey = $('#alipaypublickey').val();
        var alipayappprivatekey = $('#alipayappprivatekey').val();
        var wxappid = $('#wxappid').val();
        var wxmerchantid = $('#wxmerchantid').val();
        var params = {
            wxappsecret: wxappsecret,
            alipayappid: alipayappid,
            alipaypublickey: alipaypublickey,
            alipayappprivatekey: alipayappprivatekey,
            wxappid: wxappid,
            wxmerchantid: wxmerchantid
        };
        var url = '${ctxPath}/shopconfig/updateConfig.action';
        ajaxInSameDomain(url, params, function (result) {
            if (result) {
                AlertMsg(result);
            } else {
                AlertMsg('系统设置成功', function () {
                    window.location.reload();
                });
            }
        })
    }
</script>
</@override>
<@override name="right">
<div class="alert alert-info">
    如果您需要接入支付宝、微信等第三方线上支付，请<a href="https://ls.zhonglunnet.com/dcms-web/about.html" style="font-weight: bold;color:red" target="_blank">联系我们</a>.
</div>
<#--<form action="#" method="post" class="form-horizontal" role="form" id="addForm">
    <div class="panel panel-default">
        <div class="panel-heading main-content-title"></div>
        <div class="panel-body">

            &lt;#&ndash;<div class="form-body">
                <div class="form-group">
                    <label class="control-label col-md-3">支付宝APP_ID:
                        <span class="required" aria-required="true">*</span>
                    </label>

                    <div class="col-md-4">
                        <input id="alipayappid" name="alipayappid" type="text" class="form-control"
                               value="${shopconfig.alipayappid!""}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">支付宝帐号公钥:
                        <span class="required" aria-required="true">*</span>
                    </label>

                    <div class="col-md-4">
                    &lt;#&ndash;<input id="alipayidkey" name="alipayidkey" type="text" class="form-control"
                           style="width:1000px;height:50px"
                           value="${shopconfig.alipayidkey!""}">&ndash;&gt;

                        <textarea class="form-control" id="alipaypublickey" name="alipaypublickey" rows="3"
                                  style="resize: none;">${shopconfig.alipaypublickey!""}</textarea>
                    </div>
                </div>


                <div class="form-group">
                    <label class="control-label col-md-3">商户账号私钥:
                        <span class="required" aria-required="true">*</span>
                    </label>

                    <div class="col-md-4">
                    &lt;#&ndash; <input id="shopidkey" name="shopidkey" type="text" class="form-control" style="width:1000px;height:50px"
                            value="${shopconfig.shopidkey!""}">


&ndash;&gt;
                        <textarea class="form-control" id="alipayappprivatekey" name="alipayappprivatekey" rows="3"
                                  style="resize: none;">${shopconfig.alipayappprivatekey!""}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">微信APP_ID:
                        <span class="required" aria-required="true">*</span>
                    </label>

                    <div class="col-md-4">
                        <input id="wxappid" name="wxappid" type="text"
                               class="form-control"
                               value="${shopconfig.wxappid!""}">

                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3">微信APP_SECRET:
                        <span class="required" aria-required="true">*</span>
                    </label>

                    <div class="col-md-4">
                        <input id="wxappsecret" name="wxappsecret" type="text"
                               class="form-control"
                               value="${shopconfig.wxappsecret!""}">

                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3">微信支付商户号:
                        <span class="required" aria-required="true">*</span>
                    </label>

                    <div class="col-md-4">
                        <input id="wxmerchantid" name="wxmerchantid" type="text"
                               class="form-control"
                               value="${shopconfig.wxmerchantid!""}">

                    </div>
                </div>


            </div>&ndash;&gt;

        </div>
    </div>

    <div class="form-group col-sm-12">
        &lt;#&ndash;<input type="button" value="提交" class="btn btn-primary col-lg-1" onclick="updateConfig()">&ndash;&gt;
    </div>

</form>-->

</@override>
<@override name="window">
</@override>
<@extends name="../base_main.ftl"/>