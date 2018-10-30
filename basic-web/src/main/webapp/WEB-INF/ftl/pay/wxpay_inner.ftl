<@override name="pagetitle">
<title>微信支付</title>
</@override>
<@override name="jsfile">
</@override>
<@override name="cssfile">
<link rel="stylesheet" type="text/css" href="${ctxPath}/css/mui.min.css"/>
</@override>
<@override name="jstext">
<script>
    $().ready(function () {
        if (typeof WeixinJSBridge == "undefined") {
            if (document.addEventListener) {
                document.addEventListener('WeixinJSBridgeReady', jsApiCall, false);
            } else if (document.attachEvent) {
                document.attachEvent('WeixinJSBridgeReady', jsApiCall);
                document.attachEvent('onWeixinJSBridgeReady', jsApiCall);
            }
        } else {
            jsApiCall();
        }
    });

    function jsApiCall() {
        WeixinJSBridge.invoke(
                'getBrandWCPayRequest',
                {
                    "appId": "${payMap.appId}",
                    "timeStamp": "${payMap.timeStamp}",
                    "nonceStr": "${payMap.nonceStr}",
                    "package": "${payMap.package}",
                    "signType": "${payMap.signType}",
                    "paySign": "${payMap.paySign}"
                }
                ,
                function (res) {
                    var returnflag='${returnflag}';
                    if (res.err_msg == "get_brand_wcpay_request:ok") {

                        //tps("支付完成", "success", 2000);
                        hiToast("支付完成");
                        if(returnflag=='1'){
                            setTimeout(function () {
                                location.href = "${zl.web.wxms.domain}/wxcs/order/paysuccess.action?shopcode=${shopcode}&ticketcode=${businesscode}&totalamount=${totalamount}&tradecode=${tradecode}";
                            }, 1000);
                        }else if(returnflag=='2'){
                            setTimeout(function () {
                                location.href = "${zl.web.wxms.domain}/wxcs/order/rechargesuccess.action?shopcode=${shopcode}&ticketcode=${businesscode}&totalamount=${totalamount}&tradecode=${tradecode}";
                            }, 1000);
                        }

                    }
                    if (res.err_msg == "get_brand_wcpay_request:cancel") {
                        //tps("支付取消", "", 2000);
                        hiToast("支付取消");
                        setTimeout(function () {
                             location.href = "${zl.web.wxms.domain}/wxcs/order/list.action?shopcode=${shopcode}";
                        }, 2000);
                    }
                    if (res.err_msg == "get_brand_wcpay_request:fail") {
                        hiToast("支付失败，错误提示：" + res.err_desc);
                        location.href = "${zl.web.wxms.domain}/wxcs/order/list.action?shopcode=${shopcode}";
                    }
                }
        );
    }
</script>
</@override>
<@override name="csstext">
<style>
</style>
</@override>
<@override name="content">
<!-- <header class="mui-bar mui-bar-nav ct-header">
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"  href=""></a>
    <h1 class="mui-title">微信支付</h1>
</header> -->
<div class="mui-content">
</div>
</@override>
<@override name="footer">
</@override>
<@override name="window">
</@override>
<@extends name="./base_page.ftl"/>