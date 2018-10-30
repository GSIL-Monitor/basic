<@override name="jsfile">
<script src="${zl.web.resource.address}/js/qrcodejs-master/qrcode.js?version=${zl.web.resource.version}"></script>
</@override>
<@override name="jstext">
<script>

    var queryPayResultInterval;
    $(function () {
        new QRCode(document.getElementById('qrcode'), '${codeurl!''}');

        //轮询判断是否已经支付完成
        queryPayResultInterval = setInterval(queryPayResult, 2000);
    })

    //轮询判断是否已经支付完成
    function queryPayResult() {

        var url = '${ctxPath}/mt/web/system/wxpay/entry/queryPayResult.action';
        var params = {
            ordercode: '${ordercode!''}'
        };

        ajaxInSameDomain(url, params, function (data) {
            debugger;
            var  orderstatus=data.data.orderstatus;
            var  url=data.data.returnurl;
            var  businessorigin=data.data.businessorigin;
            if (orderstatus && orderstatus == '5') {
                //清除定时器
                clearInterval(queryPayResultInterval);

                AlertMsg('支付完成', function () {
                    if(businessorigin && businessorigin == 'srvms-serverRecharge'){
                        window.location.href = '${zl.web.servreturn.domain}'+url;
                    }else if(businessorigin && businessorigin == 'bugms-service'){
                        window.location.href = '${zl.web.servreturn.domain}'+url;
                    }else {
                        window.location.href = '${zl.web.return.domain}'+url;
                    }
                });
            }
        })
    }
</script>
</@override>
<@override name="csstext">
<style>
    .cashier-title {
        font-weight: bold;
        color: #666;
    }

    .panel_subtitle {
        margin-bottom: 20px;
        font-size: 18px;
        font-family: "Microsoft Yahei";
        color: #666;
    }

    .qrcode_title {
        position: absolute;
        top: -36px;
        right: 10px;
        color: #ff5d5b;
        width: 250px;
        height: 50px;
        text-align: left;
    }

    .panel_qrcode {
        width: 50%;
        height: 383px;

        float: left;
        position: relative;
    }

    .qrcode_img {
        width: 100%;
        height: 250px;
        float: right;
    }

    .qrcode_img img {
        width: 250px;
        height: 250px;
        float: right;
        margin-right: 10px;
        margin-top: 20px;
    }

    .qrcode_phone {
        width: 50%;
        float: left;
    }

    .qrcode_phone img {
        width: 300px;
        float: left;
        margin-top: -8px;
        margin-left: 45px;
    }

    .wxpaydesc {
        width: 254px;
        height: 60px;
        float: right;
        padding: 8px 0 8px 100px;
        margin: 40px 10px 0 0px;
        background: url(${zl.web.resource.address}/images/icon-red.png) 30px 8px no-repeat #ff7674;
    }

    .wxpaydesc p {
        margin: 0;
        font-size: 14px;
        line-height: 22px;
        color: #fff;
        font-weight: 700;
    }

    .pay_money {
        float: right;
        line-height: 18px;
    }

    .pay_money .money_text {
        font-size: 18px;
        vertical-align: bottom;
        color: #ff5d5b;
        margin: 0 3px;
        line-height: 20px;
    }

    .pc-wrap {
        display: block;
        height: 60px;
        line-height: 56px;
        padding: 0 20px;
        -moz-transition: all .1s;
        -o-transition: all .1s;
        -webkit-transition: all .1s;
        transition: all .1s;
        color: #2ea7e7;
    }
</style>
</@override>
<@override name="right">
<div class="row">
    <div class="col-md-2">
    </div>
    <div class="col-md-8">
        <div class="panel panel-default cashier-title">
            <div class="panel-heading">
                订单提交成功，请尽快付款！订单号：${ordercode!''}
              <span class="pay_money">
                  应付金额<span class="money_text">${payamount!''}</span>元
              </span>
            </div>
            <div class="panel-body">
                <div class="panel_subtitle">微信支付</div>
                <div class="panel_qrcode">
                    <div class="qrcode_title" style="display: none;">二维码已过期，刷新页面获取新二维码</div>
                    <div class="qrcode_img" id="qrcode">
                    </div>
                    <div class="wxpaydesc">
                        <p>请使用微信扫一扫</p>

                        <p>扫描二维码支付</p>
                    </div>
                </div>
                <div class="qrcode_phone">
                    <img src="${zl.web.resource.address}/images/phone-bg.png">
                </div>
            </div>
        <#--<div class="panel-footer">-->
        <#--<a class="pc-wrap" onclick="window.history.back()">-->
        <#--<i class="pc-w-arrow-left">&lt;</i>-->
        <#--<strong>选择其他支付方式</strong>-->
        <#--</a>-->
        <#--</div>-->
        </div>

    </div>

    <div class="col-md-2">
    </div>
</div>
</@override>
<@override name="window">
</@override>
<@extends name="base_paymain.ftl"/>
