/**取消订单--------------这个功能也没有*/   
function cancleOrderInfoById(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"/orderController/updateOrderSts.do",//
	    data: {orderId:event.data.orderId,sts:6},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	//如果状态为假，则给出提示
	    	if(!msg.success){
	    		mui.alert(msg.content, '消息提示', function() {
	    			return;
	    		});
	    		return;
	    	}
	    	else{
	    		mui.alert("取消订单成功");
	    		//转到订单列表页
	    		
	    	}
	    }
	});
}

/**删除订单*/
function delOrderInfoById(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"orderController/delOrder.do",//
	    data: {orderId:event.data.orderId},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	//如果状态为假，则给出提示
	    	if(!msg.success){
	    		mui.alert(msg.content, '消息提示', function() {
	    			return;
	    		});
	    		return;
	    	}
	    	else{
	    		mui.alert("删除成功");
	    		location.href=activePath+"orderController/toOrderlist.do";
	    	}
	    }
	});
}

/**再次购买 通过商品id下订单   这个功能暂时没有*/
function againPurchase(event){
	//将订单中的商品加入到购物车
	location.href="";
	
}
/** js需要把时间戳转为为普通格式 2010-10-20 10:00:00 */
function getLocalTime(nS) {     
    return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");      
} 

/**订单状态处理方法*/
function dealOrderStatus(orderStatus){
	var states ="未付款";
	if(1==orderStatus) states="处理中";
	else if(2==orderStatus) states="商家已发货";
	else if(3==orderStatus) states="未付余款";
	else if(4==orderStatus) states="订单完成";
	else if(5==orderStatus) states="系统取消";
	else if(6==orderStatus) states="用户取消";
	return states
}

/**获得订单详细信息*/
function getOrderDetailInfo(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"orderController/orderDetail.do",//
	    data: {orderId:orderId},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	//如果状态为假，则给出提示
	    	if(!msg.success){
	    		mui.alert(msg.content, '消息提示', function() {
	    			return;
	    		});
	    		return;
	    	}
	    	else{
	    		//订单信息
	    		var order = msg.map.order;
	    		$(".orderDetail-ddh span").html(order.orderNum);
	    		$(".orderDetail-xdsj span").html(getLocalTime(order.createDate));
	    		$(".orderDetail-mjfk").html(dealOrderStatus(order.status));
	    		
	    		//地址信息
	    		var address = msg.map.address;
	    		$("#userName").html(address.consignee);
	    		$("#phone").html(address.phone);
	    		$("#address").html(address.address);
	    		//商品信息
	    		var mercId="";
	    		var orderCommoditys = msg.map.orderCommodity;
	    		
	    		$("#orderDetail-li").show();
	    		$.each(orderCommoditys, function(i, n){
	    			if(mercId==""|| (mercId!="" && mercId!=n.merchantsId)){
		    			if(mercId!=""){
		    				row.appendTo(".order_list");
		    			}
		    			mercId=n.merchantsId;
		    			row= $("#orderDetail-li").clone();	
		    			row.find(".order_title span").html(n.merchantsName);
		    			row.find("#goodsImg").attr("src",basePath+"static/img/huafei.jpg");//图片
		    			row.find("#bl_title").html(n.commodityName);
		    			row.find("#bl_price").html(n.price+"<b>X"+n.allCount+"</b>");
		    			row.find("#bl_number").html("合计："+n.allAmount+"元 (含运费"+n.logisticsFee+")");
		    			row.find(".order_main").bind("click",{orderId:order.id,id:n.commodityId},showCommodityInfo());
		    			
		    		}
		    		else{
		    			var rowchild = $("#orderDetail-li").find(".order_main").clone();
		    			rowchild.find(".order_title span").html(n.merchantsName);
		    			rowchild.find("#goodsImg").attr("src",basePath+"static/img/huafei.jpg");//图片
		    			rowchild.find("#bl_title").html(n.commodityName);
		    			rowchild.find("#bl_price").html(n.price+"<b>X"+n.allCount+"</b>");
		    			rowchild.find("#bl_number").html("合计："+n.allAmount+"元 (含运费"+logisticsFee+")");
		    			rowchild.bind("click",{orderId:order.id,id:n.commodityId},showCommodityInfo());
		    			rowchild.appendTo(row);
		    		}
		    		if(i==orderCommoditys.length-1){
		    			row.appendTo(".order_list");
		    		}
	    		});
	    		$("#orderDetail-li").hide();
	    		
	    		//商品总金额
	    		$("#merchartsMoney").html(order.allAmount);
	    		//运费总金额
	    		$("#fareMoney").html(order.logisticsFee);
	    		//应付款总金额
	    		$(".money").html(order.allAmount*1+order.logisticsFee*1);
	    	}
	    }
	});
}


function showCommodityInfo(event){
	location.href=activePath+"commodityController/toCommodityDetail";
}

/** 页面加载完成后直接加载方法 获得对应的数据 */
$(function(){
	$("#cancleBtn").click(function(){
		cancleOrderInfoById();
	});
	
	$("#againBtn").click(function(){
		
	});
	getOrderDetailInfo();
});