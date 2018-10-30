$(function() {
    var current_branch = localStorage.getItem(picked_branch_key);
    if (current_branch != null && current_branch != undefined) {
        current_branch = JSON.parse(current_branch);
        $(".branch_list_radio").each(function() {
            $(this).removeClass("checkbox_selected").addClass("checkbox_select");
        });
        $("#" + current_branch.branch_code).removeClass("checkbox_select").addClass("checkbox_selected");
    }
});

function select_branch(obj) {
    $(".branch_list_radio").each(function() {
        $(this).removeClass("checkbox_selected").addClass("checkbox_select");
    });
    $(obj).find(".branch_list_radio").removeClass("checkbox_select").addClass("checkbox_selected");
    var current_li = $(obj);
    var branch_code = current_li.attr("branch_code");
    var barnch_name = current_li.find(".branch_list_name").html();
    var barnch_contact_name = current_li.find(".branch_contact_name").html();
    var barnch_contact_num = current_li.find(".branch_contact_num").html();
    var barnch_address = current_li.find(".branch_address").html();
    var select_branch_obj = {
        branch_code: branch_code,
        barnch_name: barnch_name,
        barnch_contact_name: barnch_contact_name,
        barnch_contact_num: barnch_contact_num,
        barnch_address: barnch_address
    };
    localStorage.setItem(picked_branch_key, JSON.stringify(select_branch_obj));
    location.href = ctxPath + "/wxcs/order/edit.action?shopcode=" + shopCode+ "&branchcode=" + branch_code;
}