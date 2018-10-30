<@override name="jstext">
<script>

    function updateUser() {

        var userid = $('#userid').val();
        var headpic = $('#headpic').val();

        if (headpic == '') {
            AlertMsg('请选择头像！');
            return;
        }

        var url = '${ctxPath}/portal/user/updateHeadPic.action';
        var params = {
            id: userid,
            headpic:headpic
        };

        ajaxSyncInSameDomain(url, params, function (result) {
            if (result) {
                AlertMsg(result);
            } else {
                AlertMsg('保存成功');
            }
        })
    }

    function chooseBranch(e) {
        ChooseBranch.open(null, function (data) {
            $(e).parent().prev().prev().val(data.name);
            $(e).parent().prev().val(data.id);
        })
    }

    function openUploadImageWin(domId, multiple) {
        $('#modal-webuploader').modal({
            remote: "${ctxPath}/file/toImageUpload.action?domId=" + domId + "&multiple=" + (multiple || 'single')
        })
    }

    //上传回调函数
    function uploadCallBack(dataList, domId) {
        createImageList(dataList, domId);
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
            <div class="tools">
            </div>
        </div>

        <div class="portlet-body form">
            <!-- BEGIN FORM-->
            <form action="" method="post" class="form-horizontal " role="form" id="form-user">
                <div class="form-body">

                    <div class="form-group">
                        <label class="control-label col-md-3">头像
                            <span class="required" aria-required="true"> * </span>
                        </label>

                        <div class="col-md-4">

                            <div class="input-group">
                                <input id="userid" name="userid" type="hidden" value="${userInfo.id}">
                                <input id="headpic" name="headpic" type="text" class="form-control" value="${userInfo.headpic!''}">
                                <span class="input-group-btn">
                                        <button class="btn btn-default" onclick="openUploadImageWin('headpic')"
                                                type="button">
                                            选择图片
                                        </button>
                                </span>
                            </div>
                            <div class="input-group multi-img-details" style="margin-top:.5em;">
                                <#if userInfo.headpic?? && userInfo.headpic != ''>
                                    <div class="multi-item"><img
                                            src="${attachUrl}/${userInfo.headpic!''}"
                                            style="width: 100px;height: 100px;" class="img-responsive img-thumbnail"><em
                                            class="close" style="position:absolute; top: 0px; right: -14px;"
                                            url="${userInfo.headpic!''}"
                                            title="删除这张图片" onclick="$.fn.FileUploader.deleteItem(this)">×</em></div>
                                </#if>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="button" onclick="updateUser()" class="btn btn-default "><i class="fa fa-save"></i> 保存</button>
                                <button type="button" onclick="back()" class="btn btn-default "><i class="fa fa-reply"></i> 取消</button>
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
    <#include "../pluploader.ftl"/>
</@override>
<@extends name="../base_main.ftl"/>
