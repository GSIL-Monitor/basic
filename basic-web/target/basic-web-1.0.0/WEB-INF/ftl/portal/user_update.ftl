<@override name="jstext">
<script>

    function updateUser() {

        var userid = $('#userid').val();
        var username = $('#username').val();
        var password = $('#password').val();
        var fullname = $('#fullname').val();
        var branchcode = $('#branchcode').val();
        var branchname = $('#branchname').val();
        var branchcode = $('#branchcode').val();
        var mindiscount = $('#mindiscount').val();
        var userflag = $('input:radio[name="userflag"]:checked').val();
        var isbuyprice = $('input:radio[name="isbuyprice"]:checked').val();
        var iscashier = $('input:radio[name="iscashier"]:checked').val();
        if (username == '') {
            AlertMsg('用户名不能为空！');
            return;
        }
        if (!/^\d{4}$/.test(username)) {
            AlertMsg('用户名必须为4位数字！');
            return;
        }
        if (fullname == '') {
            AlertMsg('姓名不能为空！');
            return;
        }
        if (branchcode == '') {
            AlertMsg('请选择门店！');
            return;
        }
        if (mindiscount == '') {
            AlertMsg('最低折扣不能为空！');
            return;
        }

        var cashiergrant = '';
        if (iscashier == '1') {
            $("input[name=cashiergrant]:checked").each(function (i, item) {
                if (cashiergrant != '') {
                    cashiergrant += ',';
                }
                cashiergrant += this.value;
            });
        }
        var userrole = '';
        $("input[name=userrole]:checked").each(function (i, item) {
            if (userrole != '') {
                userrole += ',';
            }
            userrole += this.value;
        });
        if (userrole == '') {
            AlertMsg('请设置用户角色！');
            return;
        }

        var url = '${ctxPath}/portal/user/update.action';
        var params = {
            id: userid,
            username: username,
            password: password,
            fullname: fullname,
            branchcode: branchcode,
            branchname: branchname,
            branchcode: branchcode,
            mindiscount: mindiscount,
            iscashier: iscashier,
            cashiergrant: cashiergrant,
            userrole: userrole,
            isbuyprice: isbuyprice,
            flag: userflag
        };

        if(!checkEmpty(password)){
            params.password = password;
        }

        ajaxSyncInSameDomain(url, params, function (result) {
            if (result) {
                AlertMsg(result);
            } else {
                back();
            }
        })
    }

    $(function () {

        $('input:checkbox[code="${cashierRoleCode!''}"]').click(function () {
            var checked = $(this).is(':checked');
            var iscashier = checked ? 1 : 0;
            $('input:radio[name="iscashier"]').each(function () {
                if ($(this).val() == iscashier) {
                    $(this).prop("checked", true);
                } else {
                    $(this).prop("checked", false);
                }
            })
            $('a[href="#tab_3"]').parent().toggle($(this).is(':checked'));
        })

        $("input:radio[name='iscashier']").change(function () { //拨通
            var iscashier = $('input:radio[name="iscashier"]:checked').val();
            if (iscashier == "1") {
                $('a[href="#tab_3"]').parent().show();
                $('input:checkbox[code="${cashierRoleCode!''}"]').prop("checked", true);
            } else {
                $('a[href="#tab_3"]').parent().hide();
                $('input:checkbox[code="${cashierRoleCode!''}"]').prop("checked", false);
            }
        });
    })

</script>
</@override>
<@override name="right">
<div class="tab-pane" id="pane">
    <div class="portlet light portlet-form">
        <div class="portlet-title">
            <div class="caption font-dark">
                <i class="icon-settings font-dark"></i>
                <span class="caption-subject bold uppercase main-content-title"></span>
            </div>
            <div class="tools">
            </div>
        </div>

        <div class="portlet-body form">

            <!-- BEGIN FORM-->
            <form action="" method="post" class="form-horizontal " role="form" id="form-user">

                <div class="tabbable-custom">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#tab_1" data-toggle="tab" aria-expanded="true"> 基本信息 </a>
                        </li>
                        <li class="">
                            <a href="#tab_2" data-toggle="tab" aria-expanded="false"> 用户角色 </a>
                        </li>
                        <li class="" <#if (userInfo.iscashier!0) != 1>style="display: none;"</#if> >
                            <a href="#tab_3" data-toggle="tab" aria-expanded="false"> 收银权限 </a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active in" id="tab_1">

                            <div class="form-body">

                                <div class="form-group">
                                    <label class="control-label col-md-3">用户名
                                        <span class="required" aria-required="true"> * </span>
                                    </label>

                                    <div class="col-md-4">
                                        <input id="userid" name="userid" type="hidden" value="${userInfo.id!''}">
                                        <input id="username" maxlength="4" itype="integer" name="username" type="text"
                                               class="form-control"
                                               value="${userInfo.username!''}">

                                        <div class="help-block">用户名必须为4位数字</div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">姓名
                                        <span class="required" aria-required="true"> * </span></label>

                                    <div class="col-md-4">
                                        <input id="fullname" name="fullname" type="text" class="form-control"
                                               value="${userInfo.fullname!''}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">密码
                                        <span class="required" aria-required="true"> * </span></label>

                                    <div class="col-md-4">
                                        <input id="password" name="password" type="password" class="form-control"
                                               value="${userInfo.password!''}"
                                               autocomplete="off">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">门店
                                        <span class="required" aria-required="true"> * </span>
                                    </label>

                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <input class="form-control" placeholder="选择门店" _column="branchname"
                                                   id="branchname"
                                                   readonly="readonly"
                                                   name="branchname" value="${userInfo.branchname!''}"/>
                                            <input type="hidden" _column="id" id="branchcode" name="branchcode" value="${userInfo.id!''}"/>
                                            <input type="hidden" _column="branchcode" id="branchcode" name="branchcode" value="${userInfo.branchcode!''}"/>
                                            <span class="input-group-btn">
                                                <button class="btn btn-default"
                                                        _key="BranchList"
                                                        _modal="true"
                                                        _title="选择门店"
                                                        _columns="branchcode,branchname"
                                                        _columnnames="编码,名称"
                                                        type="button">
                                                    <i class="icon-magnifier"></i>
                                                </button>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">最低折扣
                                        <span class="required" aria-required="true"> * </span>
                                    </label>

                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <input id="mindiscount" name="mindiscount" type="text" itype="decimal"
                                                   class="form-control"
                                                   value="${(userInfo.mindiscount!100)?string('#0.00')}">
                                            <span class="input-group-addon" id="sizing-addon1">%</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">是否收银员
                                    </label>

                                    <div class="col-md-4">

                                        <div class="mt-radio-inline">
                                            <label class="mt-radio">
                                                <input type="radio" name="iscashier"
                                                       <#if (userInfo.iscashier!0) == 0>checked</#if> value="0"> 否
                                                <span></span>
                                            </label>
                                            <label class="mt-radio">
                                                <input type="radio" name="iscashier"
                                                       <#if (userInfo.iscashier!0) == 1>checked</#if> value="1"> 是
                                                <span></span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">是否进价权限
                                    </label>

                                    <div class="col-md-4">

                                        <div class="mt-radio-inline">
                                            <label class="mt-radio">
                                                <input type="radio" name="isbuyprice"
                                                       <#if (userInfo.isbuyprice!0) == 0>checked</#if> value="0"> 否
                                                <span></span>
                                            </label>
                                            <label class="mt-radio">
                                                <input type="radio" name="isbuyprice"
                                                       <#if (userInfo.isbuyprice!0) == 1>checked</#if> value="1"
                                                       checked> 是
                                                <span></span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">状态
                                    </label>

                                    <div class="col-md-4">

                                        <div class="mt-radio-inline">
                                            <label class="mt-radio">
                                                <input type="radio" name="userflag" value="1"
                                                       <#if (userInfo.flag!0) == 1>checked</#if> > 正常
                                                <span></span>
                                            </label>
                                            <label class="mt-radio">
                                                <input type="radio" name="userflag" value="0"
                                                       <#if (userInfo.flag!0) == 0>checked</#if> > 停用
                                                <span></span>
                                            </label>
                                        </div>

                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="tab-pane fade" id="tab_2">
                            <div class="form-body">
                                <div class="form-group">
                                    <label class="control-label col-md-3">用户角色
                                    </label>

                                    <div class="col-md-4">

                                        <div class="mt-checkbox-inline">
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="userrolecheckall"
                                                       onclick="checkAllEvent(this,'userrole','userrolecheckall');">
                                                全选
                                                <span></span>
                                            </label>
                                        </div>

                                        <div class="mt-checkbox-inline" id="userRoles">
                                            <#if roleList?? && roleList?size gt 0>
                                                <#list roleList as role>
                                                    <label class="mt-checkbox">
                                                        <input type="checkbox" name="userrole"
                                                               code="${role.code!''}" ${role.checked!''}
                                                               value="${role.id!''}"> ${role.name!''}
                                                        <span></span>
                                                    </label>
                                                </#list>
                                            </#if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="tab_3">
                            <div class="form-body">
                                <div class="form-group">
                                    <label class="control-label col-md-3">收银权限
                                    </label>

                                    <div class="col-md-4">

                                        <div class="mt-checkbox-inline">
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="cashiergrantcheckall"
                                                       onclick="checkAllEvent(this,'cashiergrant','cashiergrantcheckall');">
                                                全选
                                                <span></span>
                                            </label>
                                        </div>

                                        <div class="mt-checkbox-inline">
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="cashiergrant"
                                                       <#if (userInfo.cashiergrant!'')?index_of("01")!=-1>checked</#if>
                                                       value="01">
                                                改价权限
                                                <span></span>
                                            </label>
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="cashiergrant"
                                                       <#if (userInfo.cashiergrant!'')?index_of("02")!=-1>checked</#if>
                                                       value="02">
                                                单品折扣
                                                <span></span>
                                            </label>
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="cashiergrant"
                                                       <#if (userInfo.cashiergrant!'')?index_of("03")!=-1>checked</#if>
                                                       value="03">
                                                整单折扣
                                                <span></span>
                                            </label>
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="cashiergrant"
                                                       <#if (userInfo.cashiergrant!'')?index_of("04")!=-1>checked</#if>
                                                       value="04">
                                                交易查询
                                                <span></span>
                                            </label>
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="cashiergrant"
                                                       <#if (userInfo.cashiergrant!'')?index_of("05")!=-1>checked</#if>
                                                       value="05">
                                                按单退货
                                                <span></span>
                                            </label>
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="cashiergrant"
                                                       <#if (userInfo.cashiergrant!'')?index_of("06")!=-1>checked</#if>
                                                       value="06">
                                                商品退货(不按单)
                                                <span></span>
                                            </label>
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="cashiergrant"
                                                       <#if (userInfo.cashiergrant!'')?index_of("07")!=-1>checked</#if>
                                                       value="07">
                                                补打小票
                                                <span></span>
                                            </label>
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="cashiergrant"
                                                       <#if (userInfo.cashiergrant!'')?index_of("08")!=-1>checked</#if>
                                                       value="08">
                                                开钱箱
                                                <span></span>
                                            </label>
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="cashiergrant"
                                                       <#if (userInfo.cashiergrant!'')?index_of("09")!=-1>checked</#if>
                                                       value="09">
                                                会员登记
                                                <span></span>
                                            </label>
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="cashiergrant"
                                                       <#if (userInfo.cashiergrant!'')?index_of("10")!=-1>checked</#if>
                                                       value="10">
                                                收银对账
                                                <span></span>
                                            </label>
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="cashiergrant"
                                                       <#if (userInfo.cashiergrant!'')?index_of("11")!=-1>checked</#if>
                                                       value="11">
                                                库存查询
                                                <span></span>
                                            </label>
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="cashiergrant"
                                                       <#if (userInfo.cashiergrant!'')?index_of("12")!=-1>checked</#if>
                                                       value="12">
                                                系统设置
                                                <span></span>
                                            </label>
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="cashiergrant"
                                                       <#if (userInfo.cashiergrant!'')?index_of("13")!=-1>checked</#if>
                                                       value="13">
                                                会员充值
                                                <span></span>
                                            </label>
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="cashiergrant"
                                                       <#if (userInfo.cashiergrant!'')?index_of("14")!=-1>checked</#if>
                                                       value="14">
                                                赠送
                                                <span></span>
                                            </label>
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="cashiergrant"
                                                       <#if (userInfo.cashiergrant!'')?index_of("15")!=-1>checked</#if>
                                                       value="15">
                                                会员积分增减
                                                <span></span>
                                            </label>
                                            <label class="mt-checkbox">
                                                <input type="checkbox" name="cashiergrant"
                                                       <#if (userInfo.cashiergrant!'')?index_of("16")!=-1>checked</#if>
                                                       value="16">
                                                跨店库存查询
                                                <span></span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="form-body">
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="button" onclick="updateUser()" class="btn btn-default "><i class="fa fa-save"></i> 保存</button>
                            <button type="button" onclick="back()" class="btn btn-default "><i class="fa fa-reply"></i> 取消</button>
                        </div>
                    </div>
                </div>
        </div>
        </form>
    </div>

    <!-- END FORM-->
</div>
</div>
</div>
</@override>
<@override name="window">
    <#include "../system/choose-branch.ftl"/>
</@override>
<@extends name="../base_main.ftl"/>