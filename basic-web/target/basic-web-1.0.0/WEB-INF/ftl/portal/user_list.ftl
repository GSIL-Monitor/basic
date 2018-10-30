<#include "../default_cfg.ftl"/>
<script>
    //分页
    function jumpPage(pageid, no, url) {
        var pageCount = 0;
    <#if pageInfo??>
        pageCount = ${((pageInfo.totalRecords+pageInfo.pageSize -1)/pageInfo.pageSize)?int};
    </#if>
        if (no > pageCount) {
            no = pageCount;
        }
        if (no < 1) {
            no = 1;
        }

        $("#" + pageid).find("input[name='page']").attr("value", no);

        refreshUserList($('#' + pageid).serialize());
    }
</script>

<table class="table table-hover">
    <thead class="navbar-inner">
    <tr>
        <th>姓名</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <#if pageInfo?? && pageInfo.list??>
        <#list pageInfo.list as user>
        <tr>
            <td>${user.fullname!''}</td>
            <td>
                <a href="javascript:void(0);" onclick="ChooseUser.callback({id:'${user.id!''}',name:'${user.fullname!''}'})">选择</a>
            </td>
        </tr>
        </#list>
    <#else>
    <tr>
        <td valign="top" colspan="2" class="dataTables_empty">暂无数据</td>
    </tr>
    </#if>

    </tbody>
</table>
<#if pageInfo?? && pageInfo.totalRecords gt 0>
<form id="user_list_form" method="post">
    <#import "../pager.ftl" as q>
			                    <@q.pager page=pageInfo.page pageSize=pageInfo.pageSize totalRecords=pageInfo.totalRecords toURL="${ctxPath}/portal/user/list.action" pageid="user_list_form"/>
</form>
</#if>