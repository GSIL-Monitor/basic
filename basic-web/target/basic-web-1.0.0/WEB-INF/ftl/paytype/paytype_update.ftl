<@override name="jstext">
<script>


    function updateObject() {

        var paytypecode = $('#paytypecode').val();
        var name = $('#name').val();

        if (paytypecode == '') {
            AlertMsg('编码不能为空！');
            return;
        }
        if (name == '') {
            AlertMsg('名称不能为空！');
            return;
        }

        var url = '${ctxPath}/paytype/update.action';
        var params = $("#addForm").serialize();

        ajaxInSameDomain(url, params, function (result) {
            if (result) {
                AlertMsg(result);
            } else {
                back();
            }
        })
    }

</script>
</@override>
<@override name="right">
<form action="#" method="post" class="form-horizontal" role="form" id="addForm">
    <div class="panel panel-default">
        <div class="panel-heading main-content-title"></div>
        <div class="panel-body">

            <div class="form-body">
                <input id="id" name="id" type="hidden" class="form-control" value="${obj.id}">

                <div class="form-group">
                    <label class="control-label col-md-3">编码
                        <span class="required" aria-required="true"> * </span>
                    </label>

                    <div class="col-md-4">
                        <input readonly="readonly" id="paytypecode" name="paytypecode" type="text" class="form-control"
                               value="${obj.paytypecode!''}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">名称
                        <span class="required" aria-required="true"> * </span></label>

                    <div class="col-md-4">
                        <input id="name" name="name" type="text" class="form-control" value="${obj.name!''}">
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="form-group col-sm-12">
        <input type="button" value="提交" class="btn btn-primary col-lg-1" onclick="updateObject()">
        <input type="button" onclick="back()" style="margin-left:10px;" value="返回列表"
               class="btn btn-default">
    </div>

</form>

</@override>
<@extends name="../base_main.ftl"/>

