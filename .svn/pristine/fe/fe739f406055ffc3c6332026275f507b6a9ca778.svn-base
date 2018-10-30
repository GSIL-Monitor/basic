<@override name="jstext">
<script>

    function search() {
        $("#searchForm").attr('action', '${ctxPath}/portal/actpush/manage.action');
        $("#searchForm").submit();
    }

    function pushAct(ids, dataList) {
        var url = '${ctxPath}/portal/actpush/pushAct.action';
        var params = {
            ids: ids,
            actgroups: JSON.stringify(dataList)
        }
        ajaxSyncInSameDomain(url, params, function (result) {
            if (result) {
                AlertMsg(result);
            } else {
                AlertMsg('推送完毕', function () {
                    window.location.reload();
                })
            }
        })
    }

    function cancelAct(ids) {
        var url = '${ctxPath}/portal/actpush/cancelAct.action';
        var params = {
            ids: ids
        }
        ajaxSyncInSameDomain(url, params, function (result) {
            if (result) {
                AlertMsg(result);
            } else {
                AlertMsg('推送已取消', function () {
                    window.location.reload();
                })
            }
        })
    }

    function chooseactgroup(idx, ids) {

        var _ids = ids || '';

        if (idx == '1') {

            $("input[name=checkboxname]:checked").each(function (i, item) {
                if (_ids != "") {
                    _ids += ',';
                }
                _ids = _ids + $(this).val();
            });

            if (checkEmpty(_ids)) {
                AlertMsg('请选择要推送的终端！');
                return;
            }
        }

        if (checkEmpty(_ids)) {
            return;
        }

        ChooseActPush.open(null, function (dataList) {
            pushAct(_ids, dataList);
        })
    }

    function cancelpush(idx, ids) {

        var _ids = ids || '';

        if (idx == '1') {

            $("input[name=checkboxname]:checked").each(function (i, item) {
                if (_ids != "") {
                    _ids += ',';
                }
                _ids = _ids + $(this).val();
            });

            if (checkEmpty(_ids)) {
                AlertMsg('请选择要取消推送的终端！');
                return;
            }
        }

        if (checkEmpty(_ids)) {
            return;
        }

        cancelAct(_ids);
    }
</script>
</@override>
<@override name="right">
<div class="panel panel-info">
    <div class="panel-heading">筛选</div>
    <div class="panel-body">
        <form action="#" method="post" class="form-horizontal" role="form" id="searchForm">
            <div class="form-body">
                <div class="row">
                    <div class="col-md-4">

                        <div class="form-group">
                            <label class="control-label col-md-3">关键字</label>

                            <div class="col-md-9">
                                <input type="text" id="keyword" name="keyword"
                                       value="${RequestParameters.keyword!''}" class="form-control"
                                       placeholder="可输入门店名称/设备编号进行查询">
                            </div>
                        </div>
                    </div>

                    <div class="col-md-2">
                        <button type="button" class="btn btn-default "
                                onclick="search()">
                            <i class="fa fa-search"></i> 搜索
                        </button>
                    </div>
                </div>
            </div>
        </form>

    </div>
</div>

<div class="panel panel-default">
    <div class="panel-body">
        <a class="btn btn-default" onclick="chooseactgroup(1)"><i
                class="fa fa-send"></i> 批量推送</a>
        <a class="btn btn-default" onclick="cancelpush(1)"><i
                class="fa fa-close"></i> 批量取消</a>
        <a class="btn btn-default" onclick="chooseactgroup(2,'${ids!''}')"><i
                class="fa fa-send"></i> 全部推送</a>
        <a class="btn btn-default" onclick="cancelpush(2,'${ids!''}')"><i
                class="fa fa-close"></i> 全部取消</a>
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-body table-responsive">

        <table class="table table-hover">

            <thead>
            <tr>
                <th width="48px"> 序号</th>
                <th width="50">

                    <input type="checkbox" name="checkAllButton" autocomplete="off" onclick="checkAllEvent(this,'checkboxname','`');">

                </th>
                <th> 门店</th>
                <th> 设备编号</th>
                <th> 设备名称</th>
                <th> 唯一码</th>
                <th> 推送状态</th>
                <th> 推送时间</th>
            </tr>
            </thead>
            <tbody>
                <#if pageInfo?? && pageInfo.list??>
                    <#import "../../rownum.ftl" as q>
                    <#list pageInfo.list as object>
                    <tr>
                        <td> <@q.rownum page=pageInfo.page pageSize=pageInfo.pageSize index=object_index /></td>
                        <td>
                            <input type="checkbox" name="checkboxname" value="${object.id!''}" autocomplete="off">
                        </td>
                        <td>${object.branchname!''}</td>
                        <td>${object.devicecode!''}</td>
                        <td>${object.devicename!''}</td>
                        <td>${object.uniquecode!''}</td>
                        <td>
                            <#if object.pushstatus == 0>
                                <span data="1" class="label label-default">未推送</span>
                            <#elseif object.pushstatus == 1>
                                <span class="label label-success">已推送</span>
                            <#elseif object.pushstatus == 2>
                                <span class="label label-danger">已取消</span>
                            </#if>
                            <#if object.pushstatus == 1>
                                <br/>
                                <a href="javascript:goto('${ctxPath}/portal/actpush/toDetail.action?id=${object.id}')">查看详情</a>
                            </#if>
                        </td>
                        <td>
                        <#if object.pushstatus == 1>
                            ${object.pushtime!''}
                        </#if>
                        </td>
                    </tr>
                    </#list>
                <#else>
                <tr>
                    <td valign="top" colspan="8" class="dataTables_empty">暂无数据</td>
                </tr>
                </#if>
            </tbody>
        </table>

        <#if pageInfo?? && pageInfo.totalRecords gt 0>
            <div class="row">
                <div class="col-md-12 col-sm-12">
                    <form id="object_list_form" method="post">
                        <#import "../../pager.ftl" as q>
			                    <@q.pager page=pageInfo.page pageSize=pageInfo.pageSize totalRecords=pageInfo.totalRecords toURL="${ctxPath}/portal/actpush/manage.action" pageid="object_list_form"/>
                    </form>
                </div>
            </div>
        </#if>
    </div>
</div>
</@override>
<@override name="window">
</@override>
<@extends name="../../base_main.ftl"/>