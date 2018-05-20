/**
 * 发布详情页面
 */
function getReleaseShowDetail(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"releaseInfoController/releaseInfoDetail.do",//
	    data: {id:releaseId,flag:flag},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	//如果状态为假，则给出提示
	    	if(!msg.success){
	    		mui.alert(msg.content, '消息提示', function() {
	    		
	    		});
	    		window.location.go(-1);
	    	}
	    	//如果状态为真，则做如下赋值操作
	    	var data = msg.map.releaseInfo;
	    	$(".title").html(data.title);
	    	$("#userName").html(data.userName);
	    	$("#createDate").html(data.createDate);
	    	if(data.coverImg!=null){
	    		var arr = data.coverImg.split("_min.");
	    		if(arr.length>1){
		    		var max_img = arr[0]+"_max."+arr[1];
		    		$("#coverImg").attr("src",picturePath+"picture/"+max_img);
	    		}
	    	}
	    	if(data.releaseType=="03" || data.releaseType=="04")
	    		$("#remark").html(data.remarks);
	    	else if(data.type=="02"){
	    		$("#remark").html(data.remarks);
	    	}
	    	else{
	    		$("#remark").html(data.remark);
	    	}
	    }
	});
}

$(function(){
	getReleaseShowDetail();
})