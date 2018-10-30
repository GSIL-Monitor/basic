var shop_cart_model = [];
$(function () {
    var start_date = new Date();
    $("#select_pick_time").attr("min-date", start_date.Format("yyyy-MM-dd"));

    var m_date = new Date();
    m_date.setDate(m_date.getDate() + 30);
    $("#select_pick_time").attr("max-date", m_date.Format("yyyy-MM-dd"));
    $(".time:before").click(function () {
        console.log(123);
        $("#select_pick_time").click();
    });
});


function get_branch_data() {
    var current_branch = localStorage.getItem(picked_branch_key);
    if (current_branch != null && current_branch != undefined) {
        current_branch = JSON.parse(current_branch);
        $("#select_branch_address").html(current_branch.barnch_address);
        $("#select_branch_code").val(current_branch.branch_code);
        $("#select_branch_name").val(current_branch.barnch_name);
        $("#contact_branch").attr("href", "tel:" + current_branch.barnch_contact_num);
        $("#contact_branch").attr("contactnum", current_branch.barnch_contact_num);
        $("#contact_branch").attr("contact", current_branch.barnch_contact_name);
        $("#contact_branch").show();
    } else {
        $("#contact_branch").attr("href", "");
        $("#contact_branch").attr("contactnum", "");
        $("#contact_branch").attr("contact", "");
        $("#contact_branch").hide();
    }
}

function Ncallback() {
}

function get_last_user_data() {
    var current_user_data = localStorage.getItem(order_user_date_key);
    if (current_user_data == null) {
        return;
    }
    current_user_data = JSON.parse(current_user_data);
    $("#buyername").val(current_user_data.buyername);
    $("#buyernum").val(current_user_data.buyerphone);
    $("#select_pick_time").val(current_user_data.buyertime);
}

function empty_user_data_time() {
    var current_user_data = localStorage.getItem(order_user_date_key);
    current_user_data = JSON.parse(current_user_data);
    current_user_data.buyertime = "";
    localStorage.setItem(order_user_date_key, JSON.stringify(current_user_data));
}

function update_user_info() {
    var user_name = $("#buyername").val();
    var user_phone = $("#buyernum").val();
    var user_time = $("#select_pick_time").val();
    var temp_user_obj = {
        buyername: user_name,
        buyerphone: user_phone,
        buyertime: user_time,
    };
    localStorage.setItem(order_user_date_key, JSON.stringify(temp_user_obj));
}
//获取购物车中选中的商品数据
function get_shop_cart_data() {
    var current_shopcart = localStorage.getItem(shopcart_key);
    var isslectAll = true;
    if (current_shopcart != null && current_shopcart != undefined && current_shopcart != "[]") {
        current_shopcart = JSON.parse(current_shopcart);
        var html_ = "";
        var total_money = 0;
        for (var i = 0; i < current_shopcart.length; i++) {
            var current_commmodity = current_shopcart[i];
            var good_id = current_commmodity.goodId;
            var good_name = current_commmodity.goodName;
            var good_pic = current_commmodity.goodPic == "" ? "/images/60x60.gif" : current_commmodity.goodPic;
            var good_sale_price = current_commmodity.goodSalePrice;
            var good_unitname = current_commmodity.goodUnitName;
            var good_count = current_commmodity.goodCount;
            var good_Checked = current_commmodity.goodChecked;
            if (good_Checked) {
                html_ += '<li>';
                html_ += '<img  src="' + good_pic + '">';
                html_ += '<h5>' + good_name + '</h5>';
                html_ += '<span class="price"  data_money="' + good_sale_price + '">&yen<em>' + good_sale_price + '</em></span>';
                html_ += '<span class="num">x<em>' + good_count + '</em></span>';
                html_ += '</li>';
                var item_sale_price = FloatMul(good_count, good_sale_price);
                total_money = FloatAdd(total_money, parseFloat(item_sale_price));
                shop_cart_model.push({
                    commodityname: good_name,
                    commoditypic: current_commmodity.goodPic,
                    commodityprice: good_sale_price,
                    barcode: current_commmodity.barcode,
                    saleprice: good_sale_price,
                    payprice: good_sale_price,
                    payamount: item_sale_price,
                    salenums: good_count
                });
            }
        }
        if (html_ == "") {
            location.href = ctxPath + "/wxcs/home/home.action?shopcode=" + shopCode + "&branchcode=" + branchCode;
        }
        $("#shop_cart_list").html(html_);
        $("#order_total").html(total_money);
        $("#order_total").attr("total_money", total_money);
    } else {
        location.href = ctxPath + "/wxcs/home/home.action?shopcode=" + shopCode + "&branchcode=" + branchCode;
    }
}

function submit_order(paysdomain, openid) {
    show_mask();
    var branch_code = $("#select_branch_code").val();
    var branch_name = $("#select_branch_name").val();
    var branch_add = $("#select_branch_address").html();
    var buyername = $("#buyername").val();
    var buyermobile = $("#buyernum").val();
    //门店联系方式和电话
    var contactnum = $("#contact_branch").attr("contactnum");
    var contact = $("#contact_branch").attr("contact");
    var total_money = $("#order_total").attr("total_money");
    var pick_time = $("#select_pick_time").val();
    var details = JSON.stringify(shop_cart_model);
    if (IsEmptyObj(branch_add)) {
        hiToast("请选择提货地址");
        hide_mask();
        return;
    }
    if (IsEmptyObj(buyername)) {
        hiToast("请填写用户名");
        hide_mask();
        return;
    }
    if (IsEmptyObj(buyermobile)) {
        hiToast("请留下联系方式");
        hide_mask();
        return;
    }
    if (!(/^1[34578]\d{9}$/.test(buyermobile))) {
        hiToast("手机号码格式有误");
        hide_mask();
        return;
    }
    if (IsEmptyObj(pick_time)) {
        hiToast("请选择取货时间");
        hide_mask();
        return;
    }
    var postdata = {
        shopcode: shopCode,
        totalamount: total_money,
        buyername: buyername,
        buyermobile: buyermobile,
        branchcode: branch_code,
        branchname: branch_name,
        branchaddress: branch_add,
        contact: contact,
        contactnum: contactnum,
        picktime: pick_time,
        details: details
    };
    var url = ctxPath + "/wxcs/order/saveorder.action"
    $.ajax({
        url: url,
        type: "POST",
        data: postdata,
        dataType: "json",
        success: function (json) {
            if (json.success == "1") {
                hiToast("订单提交成功");
                $(".shop_cart_buy_block").hide();
                empty_shopcart_selected();
                empty_user_data_time();
                setTimeout(function () {
                    location.href = paysdomain + "/wxpay/entry/innerPay2Shop.action?businesscode=" + json.ordercode + "&totalamount=" + postdata.totalamount + "&openid=" + openid + "&businessorigin=wxms-innerPay&shopcode=" + shopCode + "&branchcode=" + branchCode;
                }, 1000)
            } else {
                hiToast("订单提交失败");
                hide_mask();
            }
        },
        error: function (err) {
            hiToast("订单提交失败，网络错误");
            hide_mask();
        }
    });
}

function show_mask() {
    $("#dateshadow").show();
}

function hide_mask() {
    $("#dateshadow").hide();
}
//清空购物车中选中的商品
function empty_shopcart_selected() {
    var current_shopcart = localStorage.getItem(shopcart_key);
    if (current_shopcart != null && current_shopcart != undefined && current_shopcart != "[]") {
        current_shopcart = JSON.parse(current_shopcart);
        current_shopcart = $.grep(current_shopcart, function (n) {
            return !n.goodChecked;
        });
        current_shopcart = JSON.stringify(current_shopcart);
        localStorage.setItem(shopcart_key, current_shopcart);
    }
}

function go_branch_list() {
    location.href = ctxPath + "/wxcs/branch/list.action?shopcode=" + shopCode;
}
