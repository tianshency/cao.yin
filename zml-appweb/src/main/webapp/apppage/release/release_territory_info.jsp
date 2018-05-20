<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>发布信息</title>
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	<!-- 强制让文档的宽度与设备的宽度保持1:1，并且文档最大的宽度比例是1.0，且不允许用户点击屏幕放大浏览 -->
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width, minimal-ui">
	<!-- iphone设备中的safari私有meta标签，它表示：允许全屏模式浏览 -->
	<meta name="apple-mobile-web-app-capable" content="yes">
	
	<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
	<%-- <link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css"/> --%>
	<link rel="stylesheet" href="${ctxContents}css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctxContents}css/feedback.css" />
	<link rel="stylesheet" type="text/css" href="${ctxContents}css/media.css"/>
</head>
<body>
	<div class="main_container">
		<form action="${ctx}/releaseInfoController/releaseFoodInfo.do"  method="POST" enctype="multipart/form-data" id="form1" style="margin-bottom: 0;">
			<div style="display:none;">
				<input type="text" id="type" name="type" class="w100" placeholder="类型" value="${childType }">
			</div>
			<div class="publish-article-title">
				<select id="landType">
					<option value="saab">选择土地类型</option>
	  				<option value="1">旱地</option>
	  				<option value="2">山地</option>
	  				<option value="3">稻田</option>
				</select>
			</div>
			<div class="publish-article-title2">
				<div class="title-tips">标题</div>
				<textarea id="title"  name="title"  class="w100" maxlength="40" placeholder="文章标题"></textarea>
			</div>
	   		<div class="feedback-img publish-article-img">
	        	<div class="z_photo">
	            	<div class="z_file" id="file1">
                		<input type="file" id="upload1" style="display: none;" name="upload" onchange="document.getElementById('headImg1').src=getFullPath(this);" >
        				<img src="${ctxContents}img/feedback.png" name="showImg" id="headImg1" onclick="clickFile(this,'upload1');">
            		</div>
	        	</div>
	        	<div class="z_mask">
	            	<div class="z_alert">
	                	<p>确定要删除这张图片吗？</p>
	                	<p>
	                   		 <span class="z_cancel">取消</span>
	                    	<span class="z_sure">确定</span>
	                	</p>
	            	</div>
	        	</div>
	    	</div>		
			<div class="publish-article-content">
				<div class="title-tips">正文</div>
				<!-- <textarea rows="4" cols="50" id="remark" name="remark"></textarea> -->
				<textarea class="article-content"  placeholder="正文内容" id="remark" name="remark"></textarea>
			</div>
		</form>
	</div>
	<div class="footer_info">
		<button type="button" class="mui-btn mui-btn-primary" id="submitBtn">提交</button>
		<button type="button" class="mui-btn mui-btn-primary mui-btn-outlined" onclick="window.history.go(-1);">返回</button>
	</div>
	<script src="${ctxContents}js/common/jquery-1.11.3.min.js"></script>
	<script src="${ctxContents}js/mui.min.js"></script>
	<script src="${ctxContents}dist/index.min.js"></script>
	<script src="${ctxContents}js/index.js"></script>
	<script src="${ctxContents}js/ajaxfileuploads.js"></script>

	<script type="text/javascript">
		var parentType="${parentType}";
		var userId="${sessionScope.zmlUser.id}";
		
		var staticImgSrc="${ctxContents}img/feedback.png";
		function clickFile(obj,fileId){
			if($(obj).attr("src")!=staticImgSrc){
				  var mask = document.getElementsByClassName("z_mask")[0];
		          var cancel = document.getElementsByClassName("z_cancel")[0];
		          var sure = document.getElementsByClassName("z_sure")[0];
		          mask.style.display = "block";
                  cancel.onclick = function() {
                      mask.style.display = "none";
                  };
                  sure.onclick = function() {
                      mask.style.display = "none";
                      $(obj).attr("src",staticImgSrc);
                      $(obj).parent().remove();
                  };
			}
			else{
				$("#"+fileId).click();
			}
		}
		 
		var showPic="";
	    //上传图片时浏览图片
		function getFullPath(obj) {
			if (obj) {
				if(showPic.indexOf(obj.id) < 0)  showPic=showPic+","+obj.id;
				if (obj.files) {
					//在本元素后面插入同级的兄弟节点
					var num1 = (obj.id).substr((obj.id).length-1,1);
					var num2 = (obj.id).substr((obj.id).length-2,2);
					var num0=0;
					if($.isNumeric(num2)){
						num0=num2*1+1
						num1 = num2;
					}
					else{
						num0=num1*1+1
					}
				 	var fileStr = '<div class="z_file" id="file'+num0+'">'+
               		'<input type="file" id="upload'+num0+'" style="display: none;" name="upload" onchange="document.getElementById(\'headImg'+num0+'\').src=getFullPath(this);" >'+
       				'<img src="${ctxContents}img/feedback.png" name="showImg" id="headImg'+num0+'" onclick="clickFile(this,\'upload'+num0+'\');">'+
            		'</div>';
            		$("#file"+num1).after(fileStr);
					//obj.parent().append(fileStr);
					return window.URL.createObjectURL(obj.files[0]);
				}
				return obj.value;
			}
		}
	</script>
	<script src="${ctxContents}js/release/releaseterritoryinfo.js"></script>
</body>
</html>
