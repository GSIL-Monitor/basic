  var good_confirm_obj = null;
  $(function() {
      get_shop_cart_count();
      var mySwiper = new Swiper('.swiper-container', {
          direction: 'horizontal',
          loop: true,
          autoplay: 3000,
          speed: 300,
          // 如果需要分页器
          pagination: '.swiper-pagination',
      })
  });
  //显示购物确认弹框
  function show_confirm_good(obj) {
      var current_good_item = $(obj).parent();
      var commodity_name = current_good_item.find(".my_commodity_title").html();
      var commodity_intro = current_good_item.find(".good_item_intro").val();
      var commodity_pic = current_good_item.find("img").attr("src");
      var commodity_saleprice = current_good_item.find(".my_commodity_price").attr("data_money");
      var commodity_id = current_good_item.find(".good_item_id").val();
      var commodity_unitname = current_good_item.find(".good_item_unitname").val();
      var commodity_specs = current_good_item.find(".good_item_specs").val();
      var commodity_barcode = current_good_item.find(".good_item_barcode").val();
      $("#good_confirm_img_src").attr("src", commodity_pic);
      $("#good_confirm_title").html(commodity_name);
      $("#good_confirm_intro").html(commodity_intro);
      $("#good_confirm_price").html("&yen;" + commodity_saleprice).attr("data_money", commodity_saleprice);
      $("#add_good_confirm_block").attr("data_good_id", commodity_id);
      $("#add_good_confirm_block").attr("data_good_barcode", commodity_barcode);
      var c_specs = commodity_specs.split(',');
      $("#good_confirm_unitname").html("");
      for (var i = 0; i < c_specs.length; i++) {
        if(i > 4){
          continue;
        }
        $("#good_confirm_unitname").append("<a>"+c_specs[i]+"</a>");
      }
      $("#good_confirm_nums").html(1);
      $('.full-bg').show();
      $('.cart-wrap').slideDown();
  }
  //搜索商品
  function search_commodity(obj) {
      var key_word = $(obj).val();
      location.href = ctxPath + "/wxcs/commodity/list.action?keyword=" + key_word + "&shopcode=" + shopCode+ "&branchcode=" + branchCode;
  }
  //关闭购物确认弹框
  function close_good_confirm_block() {
      $('.full-bg').hide();
      $('.cart-wrap').slideUp();
      $("#good_confirm_img_src").attr("src", "")
      $("#good_confirm_title").html("")
      $("#good_confirm_price").html("").attr("data_money", 0);
      $("#good_confirm_unitname").html("");
      $("#good_confirm_nums").html(1);
      $("#add_good_confirm_block").attr("data_good_id", "");
      $("#add_good_confirm_block").attr("data_good_barcode", "");
  }
  //添加商品到购物车
  function add_good_to_shopcart(obj, flag) {
      var good_id = $("#add_good_confirm_block").attr("data_good_id");
      var good_barcode = $("#add_good_confirm_block").attr("data_good_barcode");
      var good_name = $("#good_confirm_title").html();
      var good_pic = $("#good_confirm_img_src").attr("src");
      var good_sale_price = $("#good_confirm_price").attr("data_money");
      var good_unitname = $("#good_confirm_unitname").html();
      var good_count = $("#good_confirm_nums").html();
      var good_checked = true;
      var temp_cart_model = {
          goodId: good_id,
          goodName: good_name,
          goodPic: good_pic,
          goodSalePrice: good_sale_price,
          goodUnitName: good_unitname,
          goodCount: good_count,
          goodChecked: good_checked,
          barcode: good_barcode
      };
      var current_shopcart = localStorage.getItem(shopcart_key);
      if (current_shopcart != null && current_shopcart != undefined) {
          current_shopcart = JSON.parse(current_shopcart);
          temp_shopcart = $.grep(current_shopcart, function(n) {
              return n.goodId == good_id;
          });
          if (temp_shopcart.length > 0) {
              var temp_good_count = parseInt(temp_shopcart[0].goodCount) + parseInt(good_count);
              temp_shopcart[0].goodCount = temp_good_count;
          } else {
              current_shopcart.push(temp_cart_model);
          }
          current_shopcart = JSON.stringify(current_shopcart);
          localStorage.setItem(shopcart_key, current_shopcart);
      } else {
          var temp_array = [];
          temp_array.push(temp_cart_model);
          localStorage.setItem(shopcart_key, JSON.stringify(temp_array));
      }
      if (flag == 0) {
          close_good_confirm_block();
          get_shop_cart_count();
          hiToast("已成功添加至购物车");
      } else if (flag == 1) {
          location.href = ctxPath + "/wxcs/commodity/shopcart.action?shopcode=" + shopCode+ "&branchcode=" + branchCode;
      } else {
          get_shop_cart_count();
          hiToast("已成功添加至购物车");
      }
  }
  //购物车商品数总计
  function get_shop_cart_count() {
      var current_shopcart = localStorage.getItem(shopcart_key);
      if (current_shopcart != null && current_shopcart != undefined) {
          current_shopcart = JSON.parse(current_shopcart);
          var commoditycount = 0;
          for (var i = 0; i < current_shopcart.length; i++) {
             commoditycount += parseInt(current_shopcart[i].goodCount);
          }
          $(".shopcart_count").html(commoditycount);
      } else {
          $(".shopcart_count").html(0);
      }
  }
  //从购物车中删除商品
  function remove_good_from_shopcart(good_id) {
      var current_shopcart = localStorage.getItem(shopcart_key);
      if (current_shopcart != null && current_shopcart != undefined) {
          current_shopcart = JSON.parse(current_shopcart);
          current_shopcart = $.grep(current_shopcart, function(n) {
              return (n.goodId != good_id && n.goodId != undefined);
          });
          current_shopcart = JSON.stringify(current_shopcart);
          localStorage.setItem(shopcart_key, current_shopcart);
      }
  }

  function clear_shop_cart() {
      localStorage.removeItem(shopcart_key);
      show_shop_cart_list();
                $("#shop_cart_count").show();
          $("#shop_cart_clear").hide();
          $("#shop_cart_clear").css("background","#43cfa7");
  }
  //获取单个商品购物车中的数量
  function get_good_count_from_shopcart(good_id) {
      var current_shopcart = localStorage.getItem(shopcart_key);
      if (current_shopcart != null && current_shopcart != undefined) {
          current_shopcart = JSON.parse(current_shopcart);
          current_shopcart = $.grep(current_shopcart, function(n) {
              return n.goodId == good_id;
          });
          if (current_shopcart.length > 0) {
              return current_shopcart[0].goodCount;
          } else {
              return 1;
          }
      } else {
          return 1;
      }
  }
  //更新单个商品购物车数量
  function update_good_count_cart(good_id, itemcount) {
      var current_shopcart = localStorage.getItem(shopcart_key);
      if (current_shopcart != null && current_shopcart != undefined) {
          current_shopcart = JSON.parse(current_shopcart);
          for (var i = 0; i < current_shopcart.length; i++) {
              var current_good = current_shopcart[i];
              if (current_good.goodId == good_id) {
                  current_good.goodCount = itemcount;
              }
          }
          current_shopcart = JSON.stringify(current_shopcart);
          localStorage.setItem(shopcart_key, current_shopcart);
      }
  }
  //更新购物车商品选中状态
  function update_shop_cart_checked(obj) {
      var good_id = $(obj).attr("data_good_id");
      var good_checked = $(obj).hasClass("checkbox_selected");
      var current_shopcart = localStorage.getItem(shopcart_key);
      if (current_shopcart != null && current_shopcart != undefined) {
          current_shopcart = JSON.parse(current_shopcart);
          var temp_good = $.grep(current_shopcart, function(n) {
              return n.goodId == good_id;
          });
          if (temp_good.length > 0) {
              temp_good[0].goodChecked = good_checked;
          }
          current_shopcart = JSON.stringify(current_shopcart);
          localStorage.setItem(shopcart_key, current_shopcart);
      }
      show_check_all_btn();
  }
  //显示购物车列表
  function show_shop_cart_list() {
      var current_shopcart = localStorage.getItem(shopcart_key);
      var isslectAll = true;
      $("#shop_cart_list").html('<dt class="cl"><span>购物车</span><a href="javascript:void(0)" id="edit" onclick="shopchart_edit_mode(this)">编辑</a></dt>');
      if (current_shopcart != null && current_shopcart != undefined && current_shopcart != "[]") {
          current_shopcart = JSON.parse(current_shopcart);
          var html_ = "";
          for (var i = 0; i < current_shopcart.length; i++) {
              var good_id = current_shopcart[i].goodId;
              var good_name = current_shopcart[i].goodName;
              var good_pic = current_shopcart[i].goodPic;
              var good_sale_price = current_shopcart[i].goodSalePrice;
              var good_unitname = current_shopcart[i].goodUnitName;
              var good_count = current_shopcart[i].goodCount;
              var good_Checked = current_shopcart[i].goodChecked;
              html_ += '<dd class="shop_cart_item">';
              if (good_Checked) {
                  html_ += '<i class="checkbox_selected cart_item_checked" onclick="check_option_click(this);update_shop_cart_checked(this)" data_good_id="' + good_id + '"></i>';
              } else {
                  html_ += '<i class="checkbox_select cart_item_checked" onclick="check_option_click(this);update_shop_cart_checked(this)" data_good_id="' + good_id + '"></i>';
                  isslectAll = false;
              }
              html_ += '<img  src="' + good_pic + '">';
              html_ += '<h5>' + good_name + '</h5>';
              html_ += '<p class="spec">规格：' + good_unitname + '</p>';
              html_ += '<p class="price"><span class="pp my_commodity_price" data_money="' + good_sale_price + '">&yen<em>' + good_sale_price + '</em></span></p>';
              html_ += '<div class="count-right cl"  data_good_id="' + good_id + '">';
              html_ += '<a href="javascript:void(0)" class="add" onclick="change_nums(this,2);shop_cart_check_delete(this);"  ></a>';
              html_ += '<span class="number_selecter">' + good_count + '</span>';
              html_ += '<a href="javascript:void(0)" class="reduce" onclick="change_nums(this,2);shop_cart_check_delete(this);"  ></a>';
              html_ += '</div>';
              html_ += '<button type="button" class="mui-btn my-btn-danger" style="display:none;float:right;" data_good_id="' + good_id + '" onclick="remove_cart_good(this)">删除</button>';
              html_ += '</dd>';
          }
          $("#shop_cart_list").append(html_);
          show_check_all_btn();
      } else {
          hiToast("购物车是空的");
      }
      shop_cart_sum();
  }
  //购物车全选按钮点击
  function shop_cart_check_all(obj) {
      if ($(obj).hasClass("checkbox_select")) {
          $(".cart_item_checked").each(function() {
              $(this).removeClass("checkbox_selected").addClass("checkbox_select");
          });
      } else {
          $(".cart_item_checked").each(function() {
              $(this).removeClass("checkbox_select").addClass("checkbox_selected");
          });
      }
      shop_cart_sum();
  }
  //显示全选按钮
  function show_check_all_btn() {
      var isslectAll = true;
      $(".cart_item_checked").each(function() {
          if ($(this).hasClass("checkbox_select")) {
              isslectAll = false;
          }
      });
      if (isslectAll) {
          $("#shop_cart_check_all_btn").removeClass("checkbox_select").addClass("checkbox_selected");
      } else {
          $("#shop_cart_check_all_btn").removeClass("checkbox_selected").addClass("checkbox_select");
      }
  }
  //购物车商品数量增减检查是发删除商品
  function shop_cart_check_delete(obj) {
      var good_id = $(obj).parent().attr("data_good_id");
      var num_target = $(obj).siblings(".number_selecter");
      var current_count = parseInt(num_target.html());
      if (current_count == 0) {
          remove_good_from_shopcart(good_id);
          $(obj).parent().parent().remove();
      } else {
          update_good_count_cart(good_id, current_count);
      }
      shop_cart_sum();
  }

  function remove_cart_good(obj) {
      remove_good_from_shopcart($(obj).attr("data_good_id"));
      $(obj).parent().remove();
      shop_cart_sum();
  }
  //购物车统计选中商品数量和金额
  function shop_cart_sum() {
      var total_count = 0;
      var total_money = 0;
      $(".shop_cart_item").each(function() {
          if ($(this).find(".cart_item_checked").hasClass("checkbox_selected")) {
              var itemsaleprice = $(this).find('.my_commodity_price').attr('data_money');
              var itemSalePrice = parseFloat(itemsaleprice);
              var itemCount = $(this).find('.number_selecter').html();
              itemCount = parseFloat(itemCount);
              var current_money = FloatMul(itemCount, itemSalePrice);
              total_money = FloatAdd(total_money, current_money);
              total_count = FloatAdd(total_count, itemCount);
          }
      });
      $("#cart_good_sum").html(total_money);
  }
  //购物车前往订单页面
  function go_to_order(obj) {
      if ($("#cart_good_sum").html() == 0) {
          hiToast("请挑选商品");
          return;
      }
      location.href = ctxPath + "/wxcs/order/edit.action?shopcode=" + shopCode+ "&branchcode=" + branchCode;
  }

  function shopchart_edit_mode(obj) {
      if ($(obj).html() == "编辑") {
          //进入编辑模式
          $(obj).html("完成");
          $(".shop_cart_item").each(function() {
              $(this).find(".cl").hide();
              $(this).find(".my-btn-danger").show();
          });
          $("#shop_cart_count").hide();
          $("#shop_cart_clear").show();
          $("#shop_cart_clear").css("background","#dd524d");
      } else {
          //进入正常模式
          $(obj).html("编辑");
          $(".shop_cart_item").each(function() {
              $(this).find(".cl").show();
              $(this).find(".my-btn-danger").hide();
          });
          $("#shop_cart_count").show();
          $("#shop_cart_clear").hide();
          $("#shop_cart_clear").css("background","#43cfa7");
      }
  }