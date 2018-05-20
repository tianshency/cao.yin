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
	    	$("#template").show();
	    	var data = msg.map.data; 
	    	var href =activePath+"myUserController/toBannerDetail.do";
	    	$.each(data, function(i, n){
	    			var row = $("#template").clone();	    	
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
			    	row.find("#isDefault").bind("click",{id:n.id,obj:this},setDefaultAddress);
			    	row.find("#edit").bind("click",{id:n.id},updateAddressInfo);
			    	row.find("#remove").bind("click",{obj:row,id:n.id},removeAddressInfo);
			    	row.appendTo("#parent");//添加到模板的容器中
	    	});
	    	 $("#template").hide();
	    }
	});
}

/**修改地址*/
function updateAddressInfo(event){
	location.href=activePath+"myUserController/toUpdateUserAddress.do?id="+event.data.id;
}

/** 修改默认地址信息*/
function setDefaultAddress(event){
	var radio = 1;
	var obj = $(event.data.obj);
	if(obj.checked){
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
	    }
	});
}


/** 删除地址信息 */
function removeAddressInfo(event){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"myUserController/delUserAddress.do",//
	    data: {addressId:event.data.id},//要发送的数据
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
	    		var obj = event.data.obj;
	    		$(obj).remove();  
	    	}
	    }
	});
}

$(function(){
	getMyAddress();
})

