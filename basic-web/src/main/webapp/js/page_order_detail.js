function cancle_order(obj, flag) {
    var current_order_code = $(obj).attr("data_order_code");
    var postdata = {
        shopcode: shopCode,
        branchcode:branchCode,
        ordercode: current_order_code,
    };
    var url = ctxPath + "/wxcs/order/cancleorder.action"
    $.ajax({
        url: url,
        type: "POST",
        data: postdata,
        dataType: "json",
        success: function(json) {
            if (json.success == "1") {
                hiToast("订单提已关闭");
                setTimeout(function() {
                    if (flag == 1) {
                        location.href = ctxPath + "/wxcs/order/list.action?orderstatus=&shopcode=" + shopCode+ "&branchcode=" + branchCode;
                    } else {
                        location.href = ctxPath + "/wxcs/order/list.action?ordercode=" + current_order_code + "&shopcode=" + shopCode+ "&branchcode=" + branchCode;
                    }
                }, 2000);
            } else {
                hiToast("订单关闭失败");
            }
        },
        error: function(err) {
            hiToast("订单关闭失败，网络错误");
        }
    });
}

function delete_order(obj) {
    var current_order_code = $(obj).attr("data_order_code");
    var postdata = {
        branchcode:branchCode,
        shopcode: shopCode,
        ordercode: current_order_code,
    };
    var url = ctxPath + "/wxcs/order/deleteorder.action"
    $.ajax({
        url: url,
        type: "POST",
        data: postdata,
        dataType: "json",
        success: function(json) {
            if (json.success == "1") {
                hiToast("订单删除成功");
                setTimeout(function() {
                    location.href = ctxPath + "/wxcs/order/list.action?orderstatus=&shopcode=" + shopCode+ "&branchcode=" + branchCode;
                }, 2000);
            } else {
                hiToast("订单删除失败");
            }
        },
        error: function(err) {
            hiToast("订单删除失败，网络错误");
        }
    });
}

function go_to_order_detail(obj) {
    if ($(obj).attr("data-ordercode") != "") {
        location.href = ctxPath + "/wxcs/order/detail.action?shopcode=" + shopCode + "&ordercode=" + $(obj).attr("data-ordercode")+ "&branchcode=" + branchCode;
    }
}

function switch_list(statusname, obj) {
    $(obj).addClass('cur');
    $(obj).siblings().removeClass('cur');
    if (statusname == "") {
        $(".order-list").show();
    } else {
        $(".order-list").hide();
        $(".orderstatus" + statusname).show();
    }
}