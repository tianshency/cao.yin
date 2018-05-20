/**
 *提交发布信息 
 */

function submitReleaseInfo(){
	
	var urlPath=activePath+"releaseInfoController/releaseTerritoryInfo.do";
	if(parentType=="01"){//粮食
		urlPath=activePath+"releaseInfoController/releaseFoodInfo.do";
	}
	else if(parentType=="02"){//土地
		urlPath=activePath+"releaseInfoController/releaseTerritoryInfo.do";
	}
	else if(parentType=="03"){//农机
		
	}
	else{//其它  04
		
	}
	
	var title = $("#title").val();
	var content = $("#remark").val();
	var kind = $("#kind").val();
	var uploads = $("input[name='upload']");
	var type= $("#type").val();
	var totalNum= $("#totalNum").val();
	var unit= $("#unit").val();
	if(title==null || title==""){
		 mui.toast('请输入标题~'); 
		 $("#title").focus();  
         return;  
	}
	else if(content==null || content==""){
		 mui.toast('请输入正文~'); 
		 $("#remark").focus();  
         return;  
	}
	else if(kind==null || kind==""){
		mui.toast('请选择粮食类型~'); 
		$("#kind").focus();  
        return;  
	}
	else if(totalNum==null || totalNum==""){
		mui.toast('请输入总量~'); 
		$("#totalNum").focus();  
        return;  
	}
	else if(unit==null || unit==""){
		mui.toast('请选择单位~'); 
		$("#unit").focus();  
        return;  
	}
	else if(type==null || type==""){
		mui.toast('数据错误 ，请重新进入本公众号~'); 
		$("#type").focus();  
        return;  
	}
	
	var arr ="";
	if(uploads!=null && uploads.length>0){
		for(var i=0;i<uploads.length;i++){
			var strid = uploads[i].id;
			if(i!=uploads.length-1){
				arr=arr+strid+",";
			}
			else{
				arr=arr+strid;
			}
		}
			
	}
	
	$.ajaxFileUpload({
		url : urlPath,
		data : {userId:userId,kind:kind,title:title,remark:content,type:type,totalNum:totalNum,unit:unit},
		type : "post",
		secureuri : false, //是否启用安全提交,默认为false
		fileElementId : arr.split(","),//uploads, //文件选择框的id属性
		dataType : "json",
		success : function(data, s) {
			alert("上传成功！");
			location.href=activePath+"personalController/toPersonalPage.do";
		},
		error : function(data, s, e) {
			alert("上传失败，请重试！");
		}
	});
	
	/*$.ajax({
	    type: "post",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+urlPath,//
	    data: {userId:userId,kind:kind,title:title,type:childType,remark:content},//要发送的数据
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
	});*/
}

$(function(){
	$("#submitBtn").click(function(){
		//$("#form1").submit();
		submitReleaseInfo();
	});
});
 