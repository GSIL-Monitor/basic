<@override name="jstext">
<script>
    function updateCache() {

        var url = '${ctxPath}/cache/update.action';
        var params = {};
        ajaxSyncInSameDomain(url, params, function (result) {
            if (result) {
                AlertMsg(result);
            } else {
                AlertMsg("更新完毕！", function () {
                    window.location.href = '${ctxPath}/cache/manage.action';
                });
            }
        })
    }
</script>
</@override>
<@override name="right">
<div class="tab-pane" id="pane">
    <div class="portlet light portlet-fit portlet-form">
        <div class="portlet-title">
            <div class="caption font-dark">
                <i class="icon-settings font-dark"></i>
                <span class="caption-subject bold uppercase main-content-title"></span>
            </div>
        </div>

        <div class="portlet-body form">
            <!-- BEGIN FORM-->
            <form action="" method="post" class="form-horizontal " role="form" id="form-user">
                <div class="form-body">

                    <div class="form-group">
                        <label class="control-label col-md-3">缓存类型</label>

                        <div class="col-md-4">
                            <div class="mt-checkbox-inline">
                                <label class="mt-checkbox">
                                    <input type="checkbox" checked> 数据缓存
                                    <span></span>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="button" onclick="updateCache()" class="btn green">刷新缓存</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <!-- END FORM-->
        </div>
    </div>
</div>
</@override>
<@override name="window">
</@override>
<@extends name="../base_main.ftl"/>
