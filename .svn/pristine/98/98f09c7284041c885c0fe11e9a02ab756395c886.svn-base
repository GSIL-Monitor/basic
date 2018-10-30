<@override name="jstext">
<script>
    zlf.common.initSearchUrl('${ctxPath}/paytype/manage/detail.action');

    function delObject(id) {

        ConfirmMsg("确定要删除此条数据？", function (yes) {
            if (yes) {
                var url = '${ctxPath}/paytype/delObject.action';
                var params = {
                    id: id
                };
                ajaxSyncInSameDomain(url, params, function (result) {
                    if (result) {
                        AlertMsg(result);
                    } else {
                        AlertMsg("删除成功",function(){
                            zlf.common.search();
                        });
                    }
                })
            }
        });
    }

    //打开新增支付弹框
    function openAddPay(){
        $('#pay_add form')[0].reset();
        $('#pay_add').modal('show');
    }
    //提交新增支付方式
    function addObject(){

        var name = $('#pay_add [name="name"]').val();
        if (name == '') {
            AlertMsg('名称不能为空！');
            return;
        }
        var params = {
            name: name
        };
        var url = '${ctxPath}/paytype/add.action';
        ajaxSyncInSameDomain(url, params, function (result) {
            if (result) {
                AlertMsg(result);
            } else {
                $('#pay_add').modal('hide');
                AlertMsg("保存成功!", function () {
                    zlf.common.search();
                });
            }
        })
    }

    //打开修改支付弹框
    function openUpdatePay(id){
        $('#pay_update form')[0].reset();
        var url = '${ctxPath}/paytype/getObject.action';
        var params = {
            id: id
        };

        ajaxSyncInSameDomain(url, params, function (result) {
            if (result) {
                $('#pay_update [name="id"]').val(id);
                $('#pay_update [name="paytypecode"]').val(result.paytypecode || '');
                $('#pay_update [name="name"]').val(result.name || '');
                $('#pay_update').modal('show');
            }
        })

    }
    //提交修改支付弹框
    function updateObject() {

        var id = $('#pay_update [name="id"]').val();
        var paytypecode = $('#pay_update [name="paytypecode"]').val();
        var name = $('#pay_update [name="name"]').val();
        if (paytypecode == '') {
            AlertMsg('编码不能为空！');
            return;
        }
        if (name == '') {
            AlertMsg('名称不能为空！');
            return;
        }

        var url = '${ctxPath}/paytype/update.action';
        var params = {
            id: id,
            name: name,
            paytypecode:  paytypecode
        };

        ajaxInSameDomain(url, params, function (result) {
            if (result) {
                AlertMsg(result);
            } else {
                $('#pay_update').modal('hide');
                AlertMsg('保存成功',function(){
                    zlf.common.search();
                });
            }
        })
    }

    $(function(){
        zlf.common.search();
    })
</script>
</@override>
<@override name="right">

<div class="panel panel-default">
    <div class="panel-body table-responsive zl-content-container">
        <#include "./paytype_manage_detail.ftl">
    </div>
</div>

</@override>
<@override name="window">
<div id="pay_add" class="modal fade" tabindex="-1" role="dialog" aria-hidden="false">
    <div class="modal-dialog" style="width: 600px">
        <div class="modal-content">
            <div class="modal-header">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h3>添加支付方式</h3>
            </div>
            <div class="modal-body">
            <!--open-->
                <form action="#" method="post" class="form-horizontal" role="form" id="addForm">
                            <div class="form-body">
                                <div class="form-group">
                                    <label class="control-label col-md-3">名称
                                        <span class="required" aria-required="true"> * </span></label>

                                    <div class="col-md-7">
                                        <input id="name" name="name" type="text" class="form-control" value="">
                                    </div>
                                </div>
                            </div>


                </form>
            <!--final-->
            </div>
            <div class="modal-footer">
                <a href="#" class="btn btn-primary" onclick="addObject()">确定</a>
                <a href="#" class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</a>
            </div>
        </div>
    </div>
</div>
<div id="pay_update" class="modal fade" tabindex="-1" role="dialog" aria-hidden="false">
    <div class="modal-dialog" style="width: 600px">
        <div class="modal-content">
            <div class="modal-header">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h3>修改支付方式</h3>
            </div>
            <div class="modal-body">
                <form action="#" method="post" class="form-horizontal" role="form" id="addForm">
                            <div class="form-body">
                                <input id="id" name="id" type="hidden" class="form-control" value="">

                                <div class="form-group">
                                    <label class="control-label col-md-3">编码
                                        <span class="required" aria-required="true"> * </span>
                                    </label>

                                    <div class="col-md-7">
                                        <input readonly="readonly" id="paytypecode" name="paytypecode" type="text" class="form-control"
                                               value="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">名称
                                        <span class="required" aria-required="true"> * </span></label>

                                    <div class="col-md-7">
                                        <input id="name" name="name" type="text" class="form-control" value="">
                                    </div>
                                </div>
                            </div>


                </form>
            </div>
            <div class="modal-footer">
                <a href="#" class="btn btn-primary" onclick="updateObject()">确定</a>
                <a href="#" class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</a>
            </div>
        </div>
    </div>
</div>
</@override>
<@extends name="../base_main.ftl"/>