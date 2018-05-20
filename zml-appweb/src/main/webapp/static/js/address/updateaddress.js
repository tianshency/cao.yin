/** 添加地址信息*/
function updateAddressInfo(){
	var appendName = $("#appendName").val();
	var tel = $("#tel").val();
	var appendDizhi = $("#appendDizhi").val();
	if (tel != ""){  
        var p1 = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;  
        var me = false;  
        if (p1.test(tel)) me=true;  
        if (!me){  
        	$("#tel").value='';  
            mui.toast('请输入正确的手机号码~'); 
            $("#tel").focus();  
            return;  
        }
	}
	else{
		
	}
	if(appendName==null || appendName==""){
		 mui.toast('请输入收件人名称~'); 
		 $("#appendName").focus();  
         return;  
	}
	if(appendDizhi==null || appendDizhi==""){
		mui.toast('请输入收件人地址~'); 
		$("#appendDizhi").focus();  
        return; 
	}
	
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"myUserController/updateUserAddress.do",//
	    data: {isDefault:radio,id:id,consignee:encodeURI(appendName),phone:tel,address:encodeURI(appendDizhi)},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	//如果状态为假，则给出提示
	    	if(!msg.success){
	    		mui.alert(msg.content, '消息提示', function() {
	    			return;
	    		});
	    		return;
	    	}
	    	mui.alert("修改成功~", '消息提示', function() {
	    		location.href=activePath+"myUserController/toUserAddressList.do";
	    	});
	    }
	});
}


$(function(){
	$(".address-save").click(function(){
		updateAddressInfo();
	})
})

