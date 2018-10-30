<@override name="jstext">
<script>

    function addAct() {
        var actid = $('#actid').val();
        var title = $('#title').val();

        if (title == '') {
            AlertMsg('请输入标题！');
            return;
        }

        var ids = '';
        $('#actlist tr').each(function () {
            if (ids != '') {
                ids += ',';
            }
            ids += $(this).attr('_id');
        })

        if (checkEmpty(ids)) {
            AlertMsg('请选择广告！');
            return;
        }

        var url = '${ctxPath}/act/group/add.action';
        var params = {
            actids: ids,
            title: title
        }
        ajaxInSameDomain(url, params, function (result) {
            if (result) {
                AlertMsg(result);
            } else {
                AlertMsg("提交成功", function () {
                    window.location.href = '${ctxPath}/act/group/manage.action';
                });
            }
        })
    }


    function addOneNew(act) {
        $('#actlist').append('<tr _id="' + (act.id || '') + '"> <td>' + (act.title || '') + '</td><td>' + (act.type || '') + '</td> <td><a href="javascript:void(0);" onclick="deleteRow(this)" class="btn btn-default  btn-sm">删除<i class="fa fa-times"></i></a><a href="javascript:void(0);" onclick="upRow(this)" class="btn btn-default  btn-sm"><i class="fa fa-arrow-up"></i></a><a href="javascript:void(0);" onclick="downRow(this)" class="btn btn-default  btn-sm"><i class="fa fa-arrow-down"></i></a></td> </tr>')
    }

    function chooseact(e) {
        ChooseAct.open(null, function (act) {
            var title = act.title;
            var count = $('#actlist tr[title="' + title + '"]').size();
            if (count > 0) {
                AlertMsg("列表中已经存在该资源！");
            } else {
                addOneNew(act);
            }
        })
    }

    function deleteRow(e) {
        $(e).parent().parent().remove();
    }
</script>
</@override>
<@override name="right">
<form action="#" method="get" class="form-horizontal" role="form" id="searchForm">
    <div class="panel panel-default">
        <div class="panel-heading main-content-title"></div>
        <div class="panel-body">
            <div class="form-body">

                <div class="form-group">
                    <label class="control-label col-md-3">设备编号
                        
                    </label>

                    <div class="col-md-4">
                        <div class="form-control-static">
                        ${deviceInfo.devicecode!''}
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3">设备名称
                        
                    </label>

                    <div class="col-md-4">
                        <div class="form-control-static">
                        ${deviceInfo.devicename!''}
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">唯一码
                        
                    </label>

                    <div class="col-md-4">
                        <div class="form-control-static">
                        ${deviceInfo.uniquecode!''}
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">门店
                        
                    </label>

                    <div class="col-md-4">
                        <div class="form-control-static">
                        ${deviceInfo.branchname!''}
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3">推送状态
                        
                    </label>

                    <div class="col-md-4">
                        <div class="form-control-static">
                            <label class="label label-success">已推送</label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3">推送时间
                        
                    </label>

                    <div class="col-md-4">
                        <div class="form-control-static">
                        ${deviceInfo.pushtime!''}
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">期刊列表</div>
        <div class="panel-body table-responsive">
            <table class="table table-hover" style="table-layout: fixed">
                <thead>
                <tr>
                    <th> 期刊名称</th>
                    <th> 开始时间</th>
                    <th> 结束时间</th>
                </tr>
                </thead>
                <tbody>
                    <#if groupList?? && groupList?size gt 0>
                        <#list groupList as groupItem>
                        <tr>
                            <td>${groupItem.grouptitle!''}</td>
                            <td>${groupItem.starttime!''}</td>
                            <td>${groupItem.endtime!''}</td>
                        </tr>
                        </#list>

                    </#if>
                </tbody>
            </table>
        </div>
    </div>

    <div class="form-group col-sm-12">
        <input type="button" onclick="back()" style="margin-left:10px;" value="返回列表"
               class="btn btn-default">
    </div>
</form>

</@override>
<@override name="window">
</@override>
<@extends name="../../base_main.ftl"/>
