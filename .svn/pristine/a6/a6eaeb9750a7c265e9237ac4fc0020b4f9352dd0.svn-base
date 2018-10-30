<#include "../../default_cfg.ftl"/>
<script type="text/javascript"
        src="${resource.address}/js/datetimepicker/jquery.datetimepicker.js?version=${resource.version}"></script>
<link href="${resource.address}/js/datetimepicker/jquery.datetimepicker.css?version=${resource.version}"
      rel="stylesheet" type="text/css"/>
<script>

    function delete_choose_act_group_item(e) {
        $(e).parent().parent().remove();
    }

    function getFullTimeStr(str) {
        if (str == '') {
            return '00';
        }

        if (str.length == 1) {
            return '0' + str;
        }
        return str;
    }

    function add_input_masker() {

        $('#act_group_list_tbody input').each(function () {

            var config = {
                rightAlign: false,
                min: 0
            }

            var max = $(this).attr('max') || '';
            var min = $(this).attr('min') || '';

            if (max != '') {
                config['max'] = parseInt(max);
            }

            if (min != '') {
                config['min'] = parseInt(min);
            }

            $(this).inputmask("integer", config);

            $(this).blur(function () {
                var oldval = $(this).attr('val');
                var newval = $(this).val();
                var key = $(this).attr('key');
                var key2 = $(this).attr('key2');

                if (newval == '') {
                    $(this).val('0');
                }

                if (key == 'h') {
                    if (oldval != newval) {
                        $(this).parent().next().find('input').val('0');
                    }
                }

                if (key == 'm') {
                    var hval = $(this).parent().prev().find('input').val();
                    if (hval == '24') {
                        $(this).val('0');
                    }
                }

                var tdDom = $(this).parent().parent().parent();
                if (key2 == 'start') {
                    var starttime = '' + getFullTimeStr(tdDom.find('input[key="h"]').val()) + getFullTimeStr(tdDom.find('input[key="m"]').val());
                    var endtime = '' + getFullTimeStr(tdDom.next().find('input[key="h"]').val()) + getFullTimeStr(tdDom.next().find('input[key="m"]').val());
                    var starttimeint = parseInt(starttime);
                    var endtimeint = parseInt(endtime);

                    if (starttimeint > endtimeint) {
                        tdDom.next().find('input[key="h"]').val(tdDom.find('input[key="h"]').val());
                        tdDom.next().find('input[key="m"]').val(tdDom.find('input[key="m"]').val());
                    }
                }

                if (key2 == 'end') {
                    var starttime = '' + getFullTimeStr(tdDom.prev().find('input[key="h"]').val()) + getFullTimeStr(tdDom.prev().find('input[key="m"]').val());
                    var endtime = '' + getFullTimeStr(tdDom.find('input[key="h"]').val()) + getFullTimeStr(tdDom.find('input[key="m"]').val());
                    var starttimeint = parseInt(starttime);
                    var endtimeint = parseInt(endtime);

                    if (starttimeint > endtimeint) {
                        tdDom.prev().find('input[key="h"]').val(tdDom.find('input[key="h"]').val());
                        tdDom.prev().find('input[key="m"]').val(tdDom.find('input[key="m"]').val());
                    }
                }

                $(this).attr('val', newval);
            })
        })
    }

    function add_datetime_picker() {

        $('#act_group_list_tbody .datetimepicker').each(function () {

            var format = $(this).attr('date-format') || 'H:i';

            var option = {
                lang: "zh",
                step: 1,
                showSecond: true,
                datepicker: false,
                timepicker: true,
                closeOnDateSelect: true,
                format: format
            };

            $(this).datetimepicker(option);
        });
    }

    function choose_act_group() {
        ChooseActGroup.open(null, function (data) {

            var dataArr = data.data;

            if(dataArr && dataArr.length>0){

                for(var index in dataArr){
                    var dataItem = dataArr[index];
                    var trHtml = '<tr groupid="'+dataItem.id+'"><td>' + getString(dataItem.name) + '</td><td key="start"><div class="form-inline">' +
                            '<div class="input-group form-group" style="width:84px" >' +
                            '<input maxlength="2" key="h" key2="start" max="24" min="0" type="text" class="form-control" itype="integer" value="0"> <span class="input-group-addon">时</span></div> <div  class="input-group form-group" style="width:84px" >' +
                            '<input maxlength="2" key="m" key2="start" max="59" min="0" type="text" class="form-control" itype="integer" value="0"> <span class="input-group-addon">分</span></div></div></td><td key="end"><div class="form-inline"><div class="input-group form-group" style="width:84px" >' +
                            '<input maxlength="2" key="h" key2="end" max="24" min="0" type="text" class="form-control" itype="integer" value="0"> <span class="input-group-addon">时</span></div> <div  class="input-group form-group" style="width:84px" >' +
                            '<input maxlength="2" key="m" key2="end" max="59" min="0" type="text" class="form-control" itype="integer" value="0"> <span class="input-group-addon">分</span></div></div></td><td><a class="btn btn-default  btn-sm tooltips" href="javascript:void(0);" onclick="delete_choose_act_group_item(this)" data-original-title="删除"><i class="fa fa-times"></i></a></td></tr>';
                    $('#act_group_list_tbody').append(trHtml);
                }

                add_datetime_picker();
                add_input_masker();
            } else {
                AlertMsg('请选择期刊');
            }
        })
    }

    function checkDataList(dataList) {
        debugger;
        for (var i = 0; i < dataList.length; i++) {
            var starttime = dataList[i].starttimeint;
            var endtime = dataList[i].endtimeint;
            for (var j = 0; j < dataList.length; j++) {
                if(i == j){
                    continue;
                }
                var starttime2 = dataList[j].starttimeint;
                var endtime2 = dataList[j].endtimeint;
                if(starttime > starttime2 && starttime < endtime2){
                    return false;
                }
                if(endtime > starttime2 && endtime < endtime2){
                    return false;
                }
            }
        }
        return true;
    }

    function save_act_push() {

        var dataList = [];
        $('#act_group_list_tbody tr').each(function () {

            var startTdDom = $(this).find('td[key="start"]');
            var endTdDom = $(this).find('td[key="end"]');
            var groupid = $(this).attr('groupid');

            dataList.push({
                starttime: getFullTimeStr(startTdDom.find('input[key="h"]').val()) + ':' + getFullTimeStr(startTdDom.find('input[key="m"]').val()),
                endtime: getFullTimeStr(endTdDom.find('input[key="h"]').val()) + ':' + getFullTimeStr(endTdDom.find('input[key="m"]').val()),
                starttimeint: parseInt(getFullTimeStr(startTdDom.find('input[key="h"]').val()) + getFullTimeStr(startTdDom.find('input[key="m"]').val())),
                endtimeint: parseInt(getFullTimeStr(endTdDom.find('input[key="h"]').val()) + getFullTimeStr(endTdDom.find('input[key="m"]').val())),
                groupid:groupid
            })
        })

        if (dataList.length == 0) {
            AlertMsg('请选择期刊！');
            return;
        }

        if(!checkDataList(dataList)){
            AlertMsg('期刊播放时段存在冲突，请修改后重新提交！');
            return;
        }

        ChooseActPush.callback(dataList);
    }
</script>
<div class="modal-header">
    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
    <h3>投放设置</h3>
</div>
<div class="modal-body">
    <div class="alert alert-info">
        可以设置每天多个时段需要上刊的期刊
    </div>
    <div class="table-responsive">
        <table class="table table-hover">
            <thead class="navbar-inner">
            <tr>
                <th> 期刊名称</th>
                <th width="200"> 开始时间</th>
                <th width="200"> 结束时间</th>
                <th width="80">操作</th>
            </tr>
            </thead>
            <tfoot>
            <tr>
                <td colspan="4">
                    <a class="btn btn-default"
                       href="javascript:choose_act_group()"><i
                            class="fa fa-plus"></i> 选择期刊</a>

                </td>
            </tr>
            </tfoot>
            <tbody id="act_group_list_tbody">
            </tbody>
        </table>
    </div>
</div>
<div class="modal-footer">
    <a href="#" class="btn btn-primary" onclick="save_act_push()">确定</a>
    <a href="#" class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</a>
</div>