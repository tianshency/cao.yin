<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>刷新缓存</title>
<script type="text/javascript" src="${ctxContents}js/jquery-1.11.2.js"></script>
</head>
<body>
<button onclick="refreshCache()">刷新缓存</button>
</body>
<script type="text/javascript">
	function refreshCache(){
		alert("refreshCache...");
		$.ajax({
		    type: "get",//使用get方法访问后台
		    dataType: "json",//返回json格式的数据
		    url: activePath+"systemController.do?refreshRedis",//
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
		    	else{
		    		mui.alert("删除成功");
		    	}
		    }
		});
	}
</script>
</html>