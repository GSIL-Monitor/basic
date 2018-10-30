<@override name="jstext">
<script>

    function addObject() {

        var name = $('#name').val();

        if (name == '') {
            AlertMsg('名称不能为空！');
            return;
        }

        var url = '${ctxPath}/paytype/add.action';
        var params = $("#addForm").serialize();

        ajaxSyncInSameDomain(url, params, function (result) {
            if (result) {
                AlertMsg(result);
            } else {
                AlertMsg("保存成功!", function () {
                    window.location.href = '${ctxPath}/paytype/manage.action';
                });
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

                <div class="form-group">
                    <label class="control-label col-md-3">名称
                        <span class="required" aria-required="true"> * </span></label>

                    <div class="col-md-4">
                        <input id="name" name="name" type="text" class="form-control" value="">
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="form-group col-sm-12">
        <input type="button" value="提交" class="btn btn-primary col-lg-1" onclick="addObject()">
        <input type="button" onclick="back()" style="margin-left:10px;" value="返回列表"
               class="btn btn-default">
    </div>

</form>

</@override>

<@extends name="../base_main.ftl"/>
