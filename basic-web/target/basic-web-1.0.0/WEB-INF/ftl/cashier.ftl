<@override name="jstext">

<style>
    .cashier-title {
        font-weight: bold;
        color: #666;
    }

    .cashier_item {
        width: calc(100% - 40px);
        margin-left: 20px;
        padding: 0;
        float: left;
        border-bottom: 1px solid #dddddd;
    }

    .cashier_item img {
        float: left;
        height: 60px;
    }

    .cashier_item input {
        float: left;
        height: 20px;
        width: 20px;
        /* line-height: 31px; */
        margin: 20px;
    }

    .cashier_item .cashier_money {
        color: black;
        display: block;
        min-width: 100px;
        height: 100%;
        float: right;
        margin-top: 20px;
        margin-bottom: 0;
    }

    .cashier_item span {
        color: #ff5d5b;
        font-size: 17px;
        font-weight: bold;
    }

    .cashier_body .selecteditem {
        padding: 8px 0;
        border: 2px solid #b0c2e1;
    }

    .panel-foot {
        width: 100%;
        height: 100px;
    }

    .panel-foot .btn {
        float: right;
        margin-right: 35px;
        margin-top: 24px;
    }

    .disableitem {
        display: none;
    }
</style>
</@override>
<@override name="right">
<div class="row">
    <div class="col-md-2">
    </div>
    <div class="col-md-8">
        <div class="panel panel-default">
            <div class="panel-heading panel_heading cashier-title">
                订单提交成功，请尽快付款！&nbsp; 订单编号：${billcode!''}
            </div>
            <div class="panel-body cashier_body">
                <div class="cashier_item selecteditem" onclick="select_cashier_item(this)">
                    <input type="checkbox" name="cashier_selection" class="cashier_selection" checked="checked"
                           value="alipay">
                    <input id="billcode" name="billcode" type="hidden" value="${billcode!''}">
                    <input id="shopcode" name="shopcode" type="hidden" value="${shopcode!''}">
                    <input id="payflag" name="payflag" type="hidden" value="${payflag!''}">
                    <input id="account" name="account" type="hidden" value="${account!''}">
                    <img src="http://091801.zhonglunnet.com/web/images/ali_pay.png">

                    <div class="cashier_money">支付<span>${account!''}</span>元</div>
                </div>
                <div class="cashier_item" onclick="select_cashier_item(this)">
                    <input type="checkbox" name="cashier_selection" class="cashier_selection" value="wxpay"
                           onclick="select_cashier_item(this)">
                    <img src="http://091801.zhonglunnet.com/web/images/wx_pay.png">

                    <div class="cashier_money">支付<span>${account!''}</span>元</div>
                </div>
                <div class="cashier_item disableitem" onclick="select_cashier_item(this)">
                    <input type="checkbox" name="cashier_selection" class="cashier_selection"
                           onclick="select_cashier_item(this)">
                    <img src="http://dev.cnzhonglunnet.com:8080/dcms-web/images/union_pay.png">

                    <div class="cashier_money">支付<span>${account!''}</span>元</div>
                </div>
            </div>

        </div>
        <div class="form-group col-sm-12">
            <input type="button" value="提交" class="btn btn-primary col-lg-1" onclick="paySms()">
            <input type="button" onclick="back()" style="margin-left:10px;" value="返回"
                   class="btn btn-default">
        </div>
    </div>

    <div class="col-md-2">
    </div>
</div>

<script>
    function select_cashier_item(obj) {
        $(".cashier_selection").each(function () {
            $(this)[0].checked = false;
        });
        $(".cashier_item").removeClass("selecteditem");
        $(obj).addClass("selecteditem");
        $(obj).find("input")[0].checked = true;
    }

    function paySms() {
        var shopcode = $('#shopcode').val();
        var billcode = $('#billcode').val();
        var payflag = $('#payflag').val();
        var payamount = $('#account').val();

        var payment = $('input:checkbox[name="cashier_selection"]:checked').val();

        // todo
        if (payment == 'wxpay') {
            window.location.href = ' http://dev.cnzhonglunnet.com:8080/dcms-web/pays-web/web/wxpay/scanPay2Self.action?shopcode=' + shopcode + '&billcode=' + billcode + '&payamount=' + payamount + '&businessorigin=' + payflag;
        } else {
            window.location.href = ' http://dev.cnzhonglunnet.com:8080/dcms-web/pays-web/web/alipay/scanPay2Self.action?shopcode=' + shopcode + '&billcode=' + billcode + '&payamount=' + payamount + '&businessorigin=' + payflag;
        }
    }

</script>
</@override>
<@override name="window">
</@override>
<@extends name="base_main.ftl"/>
