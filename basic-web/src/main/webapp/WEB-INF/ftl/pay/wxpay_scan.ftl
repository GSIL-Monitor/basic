<@override name="jsfile">
<script src="${zl.web.resource.address}/js/qrcodejs-master/qrcode.js?version=${zl.web.resource.version}"></script>
</@override>
<@override name="jstext">
<script>

    var queryPayResultInterval;

    $(function () {
        new QRCode(document.getElementById('qrcode'), '${codeurl!''}');

        //轮询判断是否已经支付完成
        queryPayResultInterval = setInterval(queryPayResult,2000);
    })

    //轮询判断是否已经支付完成
    function queryPayResult(){

        var url = '${ctxPath}/wxpay/queryPayResult.action';
        var params = {
            ordercode:'${ordercode!''}'
        };

        ajaxInSameDomain(url,params,function(data){
            if(data && data == '5'){

                //清除定时器
                clearInterval(queryPayResultInterval);

                AlertMsg('支付完成',function(){
                    window.location.href = '${ctxPath}/index.action';
                });
            }
        })
    }
</script>
</@override>
<@override name="csstext">
<style>
    #qrcode {
        border: 1px solid #ddd;
        width: 298px;
        height: 298px;
        margin:10px auto;
    }
    #qrcode img{
        width: 260px;
        height: 260px;
        margin: 25px auto;
    }
    #wxpaytip{
        padding: 10px;
    }
    .wxpaydesc{
        width: 298px;
        height: 60px;
        padding: 8px 0 8px 125px;
            margin: 10px auto;
        background: url(${zl.web.resource.address}/images/icon-red.png) 50px 8px no-repeat #ff7674;
    }
    .wxpaydesc p {
        margin: 0;
        font-size: 14px;
        line-height: 22px;
        color: #fff;
        font-weight: 700;
}
</style>
</@override>
<#include "../default_cfg.ftl"/>
<div class="modal-header">
    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
    <h4>微信支付</h4>
</div>

<div id="wxpaytip" style="display: none;">二维码已过期，<span style="color: #ff5d5b">刷新</span>页面重新获取二维码。</div>
<div id="qrcode" >
</div>
<div class="wxpaydesc">
    <p>请使用微信扫一扫</p>
    <p>扫描二维码支付</p>
</div>
<div class="modal-footer">
    <a href="#" class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</a>
</div>
<@extends name="../base_main.ftl"/>