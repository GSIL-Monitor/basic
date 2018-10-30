<@override name="jstext">
<script>


    function select_current(obj, account) {

        $(".select_button").removeClass("active");

        $(obj).addClass("active");

        $('#account').val(account);
    }

    function saveSmsPayBill() {

        var shopcode = $('#shopcode').val();
        var account = $('#account').val();
        var payflag = $('#payflag').val();

        var chooseflag = $(".form-group .active").length == 1;
        var smsspec = $(".form-group .active").attr("data-value");

        if (!chooseflag) {
            AlertMsg('请选择所购买的短信规格！');
            return;
        }

        var msg = '您当前选择的短信规格是:' + $(".form-group .active").html().trim() + '<br/>';
        msg += '需要缴费:' + account + '<br/>';
        msg += '是否需要继续？';


        ConfirmMsg(msg, function (yes) {
            if (yes) {
                window.location.href = '${ctxPath}/pay/mode/select.action?shopcode=' + shopcode + '&smsspec=' + smsspec + '&payflag=' + payflag;
            }
        });
    }

</script>
<style>
    .select_button {
        display: block;
        float: left;
        width: 121px;
        height: 35px;
        text-align: center;
        padding: 4px 15px;
        line-height: 24px;
        border-radius: 4px;
        background-color: #e8e8e8;
        margin-right: 5px;
        color: #727272;
        cursor: pointer;
    }

    #searchForm .active {
        background-color: #43bfe3;
        color: white;
    }
</style>
</@override>
<@override name="right">
<!-- BEGIN FORM-->
<form action="#" method="post" class="form-horizontal" role="form" id="searchForm">

    <div class="panel panel-default">
        <div class="panel-body">
            <div class="form-body">

                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="control-label col-md-3">短信剩余数量 :</label>

                            <div class="col-md-6">
                                <input style="border:none;width:100px;height:35px;" readonly
                                    <#if (shop.smscount!'') !="">
                                       value="${shop.smscount!''}条"
                                    <#else>
                                       value="0条"
                                    </#if>/>
                                <input type="hidden" name="payflag" id="payflag" value="PAY_FLAG_SMS"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">

                    <div class="col-md-12">

                        <div class="form-group">
                            <div class="col-md-12">
                                <label class="control-label col-md-3">短信规格 : </label>
                                <input id="shopcode" name="shopcode" type="hidden" value="${shop.shopcode!''}">
                                <#if configList?? && configList?size gt 0>
                                    <#list configList as config>
                                        <div class="select_button " id="spec100"
                                             data-value="smsspec${config.account!''}"
                                             onclick="select_current(this,' ${config.account!''}.00元')">
                                        ${config.name!''}
                                        </div>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
                &nbsp;

                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="control-label col-md-3">总计 :</label>

                            <div class="col-md-6">
                                <input style="border:none;width:100px;height:35px;" readonly
                                       name="account"
                                       id="account"
                                       value="0.00元"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <div class="form-group col-sm-12">
        <input type="button" value="提交" class="btn btn-primary col-lg-1" onclick="saveSmsPayBill()">
        <input type="button" onclick="back()" style="margin-left:10px;" value="返回"
               class="btn btn-default">
    </div>

</form>


</@override>
<@override name="window">
</@override>
<@extends name="base_main.ftl"/>
