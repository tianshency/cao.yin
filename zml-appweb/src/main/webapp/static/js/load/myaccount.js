/**
 * 
 */
function getLoadInfo(){
	var collectionType = $("#collectionType").val();
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"myCollectionController/findMerchantsByParamList.do",//
	    data: {userId:userId,collectionType:collectionType,page:curPage,rows:pageSize},//要发送的数据
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
	    			row.find("#coverImg").html(n.coverImg);
			    	row.find("#dataName").html(n.dataName);
			    	row.find("#updateDate").html(n.updateDate);
			    	row.find("#price").html(n.price);
			    	row.find("#remarks").html(n.remarks);
			    	var status = n.status;
			    	if(status==1){
			    		row.find("#status").html("正常");
			    	}
			    	else if(status==2){
			    		row.find("#status").html("删除");
			    	}
			    	else{
			    		row.find("#status").html("失效");
			    	}
			    	//row.bind("click",{url:url},findDetailInfo);
			    	row.appendTo("#Collection");//添加到模板的容器中
	    	});
	    	$("#template").hide();
	    }
	});
}