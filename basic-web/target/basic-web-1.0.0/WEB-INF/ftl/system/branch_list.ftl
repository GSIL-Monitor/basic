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

        refreshBranchList($('#' + pageid).serialize());
    }
</script>
<div class="table-responsive">
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th>编码</th>
            <th>名称</th>
            <th width="80">操作</th>
        </tr>
        </thead>
        <tbody>
        <#if dataList?? && dataList?size gt 0>
            <#list dataList as data>
            <tr>
                <td>${data.branchcode!''}</td>
                <td>${data.branchname!''}</td>
                <td>
                    <a href="javascript:void(0);"
                       onclick="ChooseBranch.callback({id:'${data.id!''}',name:'${data.branchname!''}'})"
                       class="btn btn-default btn-sm">选择</a>
                </td>
            </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>
<#if pageInfo?? && pageInfo.totalRecords gt 0>
<form id="shop_list_form" method="post">
    <#import "../pager.ftl" as q>
			                    <@q.pager page=pageInfo.page pageSize=pageInfo.pageSize totalRecords=pageInfo.totalRecords toURL="${ctxPath}/shop/list.action" pageid="shop_list_form"/>
</form>
</#if>