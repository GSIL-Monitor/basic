<#include "../default_cfg.ftl"/>
<div class="table-scrollable">
    <table class="table table-hover table-bordered">
    <thead>
    <tr>
        <th width="48px"> 序号</th>
        <th width="160px"> 编码</th>
        <th> 名称</th>
        <th> 操作</th>
    </tr>
    </thead>
    <tbody>
    <#if pageInfo?? && pageInfo.list??>
        <#import "../rownum.ftl" as q>
        <#list pageInfo.list as object>
        <tr class="odd gradeX">
            <td> <@q.rownum page=pageInfo.page pageSize=pageInfo.pageSize index=object_index /></td>
            <td> ${object.paytypecode!''}</td>
            <td> ${object.name!''}</td>
            <td>
                <a class="btn btn-default  btn-sm tooltips"
                   href="javascript:openUpdatePay('${object.id}');"
                   data-original-title="修改">
                    <i class="fa fa-edit"></i></a>
                <a href="javascript:delObject(${object.id})"
                   class="btn btn-default  btn-sm tooltips" data-original-title="删除">
                    <i class="fa fa-times"></i> </a>

            </td>
        </tr>
        </#list>
    </#if>
    <tr>
        <td colspan="4">
            <a class="btn btn-default"
               href="javascript:openAddPay();"><i
                    class="fa fa-plus"></i> 添加支付方式</a>
        </td>
    </tr>
    </tbody>
</table>
</div>
<#if pageInfo?? && pageInfo.totalRecords gt 0>
<div class="row">
    <div class="col-md-12 col-sm-12">
        <form class="zl-content-pager-form" method="post">
            <#import "../zlf_common_pager.ftl" as q>
			                    <@q.pager page=pageInfo.page pageSize=pageInfo.pageSize totalRecords=pageInfo.totalRecords/>
        </form>
    </div>
</div>
</#if>