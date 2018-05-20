// JavaScript Document
$(document).ready(function(){
	/*购物车货物加减法计算*/
	/*加法计算*/
	$(".goodscar_main .bl_right .bl_tag .bl_number .add").click(function(){
		var num = $(this).parent().index();
		var value = $(this).parent().find(".num").val();
		value++;
		if(value>=0){
			$(this).parent().find(".num").val(value);
		}
		
		//当选中一个商品时需要计算一次商品的数量和总价
		/*var pricss = 0;
		for (var i = 0; i < nums; i++) {
			if($(".goodscar_main ul").children().eq(i).find(".circle").css("background-color") == "rgb(191, 57, 42)"){
				var num_temp = 0;
				var price_temp = 0;
				num_temp += parseInt($(".goodscar_main ul").children().eq(i).find("div").find(".money").find(".num").val());
				var tmep = $(".goodscar_main ul").children().eq(i).find("div").find(".money").find("font").html();
				tmep = tmep.substring(0,tmep.length-1);
				price_temp += parseFloat(tmep);
				pricss+= num_temp*price_temp;
			}
		}
		$(".settlement_left").html("<font class=\"zongji\">总计：</font><font class=\"money\">￥"+pricss+"</font><br />（共"+num_temp+"件，不包含运费）");*/
	});
	/*减法计算*/
	$(".goodscar_main .bl_right .bl_tag .bl_number .del").click(function(){
		var num = $(this).parent().index();
		var value = $(this).parent().find(".num").val();
		value--;
		if(value>=0){
			$(this).parent().find(".num").val(value);
		}
		
		
		/*//当选中一个商品时需要计算一次商品的数量和总价
		var pricss = 0;
		for (var i = 0; i < nums; i++) {
			if($(".main_con_goods ul").children().eq(i).find(".circle").css("background-color") == "rgb(191, 57, 42)"){
				var num_temp = 0;
				var price_temp = 0;
				num_temp += parseInt($(".main_con_goods ul").children().eq(i).find("div").find(".money").find(".num").val());
				var tmep = $(".main_con_goods ul").children().eq(i).find("div").find(".money").find("font").html();
				tmep = tmep.substring(0,tmep.length-1);
				price_temp += parseFloat(tmep);
				pricss+= num_temp*price_temp;
			}
		}
		$(".settlement_left").html("<font class=\"zongji\">总计：</font><font class=\"money\">￥"+pricss+"</font><br />（共"+num_temp+"件，不包含运费）");*/
	});
	
});