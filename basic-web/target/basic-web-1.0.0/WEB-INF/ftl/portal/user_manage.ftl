<@override name="jstext">
<script>

    function delUser(id) {

        ConfirmMsg("确定要删除此用户？", function (yes) {
            if (yes) {
                var url = '${ctxPath}/portal/user/delUser.action';
                var params = {
                    id: id
                };
                ajaxSyncInSameDomain(url, params, function (result) {
                    if (result) {
                        AlertMsg(result);
                    } else {
                        window.location.href = '${ctxPath}/portal/user/manage.action';
                    }
                })
            }
        });
    }

    function undoPassword(id) {
        ConfirmMsg("确定要重置该用户的密码？", function (yes) {
            if (yes) {
                var url = '${ctxPath}/portal/user/undoPassword.action';
                var params = {
                    id: id
                };
                ajaxSyncInSameDomain(url, params, function (result) {
                    if (result) {
                        AlertMsg(result);
                    } else {
                        AlertMsg("密码已重置");
                    }
                })
            }
        });
    }

    function search() {
        $("#searchForm").attr('action', UrlHelper.getSearchUrl('${ctxPath}/portal/user/manage.action'));
        $("#searchForm").submit();
    }

</script>
</@override>
<@override name="right">
<div class="row">
    <div class="col-md-12">
        <div class="portlet light ">
            <div class="portlet-title">
                <div class="caption font-dark">
                    <i class="icon-settings font-dark"></i>
                    <span class="caption-subject bold uppercase main-content-title">用户管理 </span>
                </div>
                <div class="actions">
                    <div class="btn-group">
                        <button id="sample_editable_1_new" class="btn btn-default  btn-sm"
                                onclick="goto('${ctxPath}/portal/user/toAdd.action','searchForm')"><i
                                class="fa fa-plus"></i> 新增

                        </button>
                    </div>
                </div>
            </div>

            <div class="portlet-body form">
                <form action="#" id="searchForm" class="form-horizontal" method="post">
                    <div class="form-body">
                        <div class="row">
                            <div class="col-md-4">

                                <div class="form-group">
                                    <label class="control-label col-md-3">关键字</label>

                                    <div class="col-md-9">
                                        <input type="text" id="keyword" name="keyword"
                                               value="${RequestParameters.keyword!''}" class="form-control"
                                               placeholder="可输入用户名/姓名进行查询">

                                    </div>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <button type="button" class="btn btn-default " onclick="search()">
                                    <i class="fa fa-search"></i> 搜索
                                </button>

                            </div>
                        </div>

                    </div>
                </form>

                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th> 用户名</th>
                            <th> 姓名</th>
                            <th> 状态</th>
                            <th> 门店</th>
                            <th> 创建时间</th>
                            <th> 操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#if pageInfo?? && pageInfo.list??>
                                <#list pageInfo.list as user>
                                <tr class="odd gradeX">
                                    <td> ${user.username!''}</td>
                                    <td>
                                    ${user.fullname!''}
                                    </td>
                                    <td>
                                        <#if (user.flag!0) == 1>
                                            <span class="label label-success">正常</span>
                                        <#else>
                                            <span class="label label-danger">被禁用</span>
                                        </#if>
                                    </td>
                                    <td class="center">${user.branchname!''}</td>
                                    <td class="center">${user.createtime!''}</td>
                                    <td>
                                        <a href="javascript:undoPassword(${user.id});"
                                           class="btn btn-default btn-sm tooltips" data-original-title="重置密码">
                                            <i class="fa fa-refresh"></i>
                                        </a>

                                        <a href="javascript:goto('${ctxPath}/portal/user/toUpdate.action?id=${user.id}','searchForm');"
                                           class="btn btn-default  btn-sm tooltips" data-original-title="修改">
                                            <i class="fa fa-edit"></i>
                                        </a>

                                        <a href="javascript:delUser(${user.id});"
                                           class="btn btn-default  btn-sm tooltips" data-original-title="删除">
                                            <i class="fa fa-times"></i>
                                        </a>
                                    </td>
                                </tr>
                                </#list>
                            <#else>
                            <tr>
                                <td valign="top" colspan="6" class="dataTables_empty">暂无数据</td>
                            </tr>
                            </#if>

                        </tbody>
                    </table>
                </div>
            </div>
            <#if pageInfo?? && pageInfo.totalRecords gt 0>
                <div class="row">
                    <div class="col-md-12 col-sm-12">
                        <form id="user_list_form" method="post">
                            <#import "../pager.ftl" as q>
			                    <@q.pager page=pageInfo.page pageSize=pageInfo.pageSize totalRecords=pageInfo.totalRecords toURL="${ctxPath}/portal/user/manage.action" pageid="user_list_form"/>
                        </form>
                    </div>
                </div>
            </#if>
        </div>
    </div>
</div>

</@override>
<@override name="window">
</@override>
<@extends name="../base_main.ftl"/>