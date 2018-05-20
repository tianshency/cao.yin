var totalMoney = 0.00; //总价钱
var totalWeight=0.00;//总重量
var totalFar=0.00;//总运费
/**获取用户默认地址及商品信息*/
function getDefaultAddress(){
	if(ids==null || ids=="" || ids==undefined){
		mui.alert("数据错误，请刷新后重试!");
	}
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"orderController/findSettlementInfo.do",//
	    data: {userId:userId,ids:ids},//要发送的数据
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
	    		data = msg.map.selectCartList;
	    		if(data.length>0){
	    			$("#template").show();
	    		}
	    		$.each(data, function(i, n){
	    			var row = $("#template").clone();
	    			row.find(".bl_img img").attr("src",n.cover_img);
	    			row.find(".bl_right .bl_title").html(n.commodityName);
	    			row.find("#price").html(n.realPrice);
	    			row.find("#amount").html("X"+n.amount);
	    			row.appendTo(".orderDetail-li");
	    			totalMoney =totalMoney*1+1*n.realPrice*n.amount;
	    			totalFar = totalFar+n.fare*1;
	    		});
	    		$("#template").hide();
	    		$("#totalMoney").html(totalMoney);
	    		$("#fare").html(totalFar);
	    		var total=totalMoney+totalFar;
	    		$(".money").html(total);
	    	}
	    }
	});
}

//public ReMsg createOrder(String  ids, String  userId, String userRemarks, String addressId, BigDecimal allPrice, HttpServletRequest request) {
http://127.0.0.1:8080/zml-appweb/orderController.do?createOrder&ids=402881875977bb55015977c063d90000,402881e859555740015955d672310015&allPrice=256.88&addressId=402881875980c9e6015980d75ab70009&userRemarks=okokokok1111&userId=402881875980c9e6015980d75ab70008
/**创建订单*/
function createOrder(){
	userRemarks = $("#userRemarks").val();
	var addressId=1;
	var money = $("#money").html();
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"orderController/createOrder.do",//
	    data: {ids:ids,userId:userId,userRemarks:userRemarks,addressId:addressId,allPrice:money},//要发送的数据
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
	    		mui.alert(msg.content, '消息提示', function() {
	    			location.href=activePath+"orderController/toOrderlist.do";
	    		});
	    	}
	    }
	});
}


/** 获得地址列表*/
function getMyAddress(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"myUserController/userAddressList.do",//
	    data: {},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	//如果状态为假，则给出提示
	    	if(!msg.success){
	    		mui.alert(msg.content, '消息提示', function() {
	    			return;
	    		});
	    		return;
	    	}
	    	//如果状态为真，则做如下赋值操作
	    	$("#templateAddress").show();
	    	var data = msg.map.data; 
	    	var href =activePath+"myUserController/toBannerDetail.do";
	    	$.each(data, function(i, n){
	    			var row = $("#templateAddress").clone();	    	
			    	row.find("#consignee").html(n.consignee);
			    	row.find("#phone").html(n.phone);
			    	row.find("#address").html(n.address);
			    	row.find("#isDefault").val(n.isDefault);
			    	var ss = row.find("#isDefault");
			    	if(n.isDefault==1){
			    		row.find("#isDefault").attr("checked",true);
			    	}
			    	else{
			    		row.find("#isDefault").attr("checked",false);
			    	}
			    	row.find("#isDefault").bind("click",{data:n,obj:row.find("#isDefault")},setDefaultAddress);
			    	/*row.find("#edit").bind("click",{id:n.id},updateAddressInfo);
			    	row.find("#remove").bind("click",{obj:row,id:n.id},removeAddressInfo);*/
			    	row.appendTo("#parentAddress");//添加到模板的容器中
	    	});
	    	 $("#templateAddress").hide();
	    }
	});
}

/** 修改默认地址信息*/
function setDefaultAddress(event){
	var radio = 1;
	var obj = $(event.data.obj);
	var checked =obj[0].checked;
	if(checked==true){
		radio=1;
	}
	else{
		radio=2;
	}
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"myUserController/updateUserAddress.do",//
	    data: {isDefault:radio,id:event.data.id},//要发送的数据
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
	    		var data = event.data.data
				$("#consignee").html(data.consignee);
				$("#tel").html(data.phone);
				$("#address").html(data.address);
				$("#payInfo").show();
				$("#addressInfo").hide();
	    	}
	    }
	});
}


/** 页面加载完成后直接加载方法 获得对应的数据 */
$(function(){
	$("#createOrder").click(function(){
		createOrder();
	});
	
	//点击箭头获取地址列表
	$("#arrowAddress").click(function(){
		$("#payInfo").hide();
		$("#addressInfo").show();
	});
	getMyAddress();	
	getDefaultAddress();
});