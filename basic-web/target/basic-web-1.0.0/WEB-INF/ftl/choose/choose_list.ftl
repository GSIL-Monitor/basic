<#include "../default_cfg.ftl"/>
<div class="table-responsive">
    <table class="table table-hover">
        <thead>
        <tr>
        <#list RequestParameters.columnnames?split(",") as columnname>
            <th>${columnname!''}</th>
        </#list>
            <th width="80">操作</th>
        </tr>
        </thead>
        <tbody>
        <#if pageInfo?? && pageInfo.list??>
            <#list pageInfo.list as data>
            <tr>
                <#list RequestParameters.columns?split(",") as column>
                    <td>${data[column]!''}</td>
                </#list>
                <td>
                    <a href="javascript:void(0);"
                       onclick="ChooseModal.callback({<#list RequestParameters.backcolumns?split(",") as backcolumn><#if backcolumn_index != 0>,</#if>${backcolumn!''}:'${data[backcolumn]!''}'</#list>})">选择</a>
                </td>
            </tr>
            </#list>
        <#else>
        <tr>
            <td valign="top" colspan="1" class="dataTables_empty">暂无数据</td>
        </tr>
        </#if>
        </tbody>
    </table>
</div>
<#if pageInfo?? && pageInfo.totalRecords gt 0>
<form id="choose_list_form" method="post"  xx-pager-post-html="module-menus">
    <#import "../pager.ftl" as q>
			                    <@q.pager page=pageInfo.page pageSize=pageInfo.pageSize totalRecords=pageInfo.totalRecords toURL="${ctxPath}/choose/list.action" pageid="choose_list_form"/>
</form>
</#if>