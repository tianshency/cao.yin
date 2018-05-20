<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>例子</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
		<link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css" />
		<script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="zmlSampleController.do?doAdd" tiptype="1" callback="jeecgFormFileCallBack@Override">
				<input id="id" name="id" type="hidden" value="${zmlSamplePage.id }">
				<input id="createDate" name="createDate" type="hidden" value="${zmlSamplePage.createDate }">
				<input id="updateDate" name="updateDate" type="hidden" value="${zmlSamplePage.updateDate }">
				<input id="version" name="version" type="hidden" value="${zmlSamplePage.version }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">图片:</label>
		       <div class="form jeecgDetail">
				<script type="text/javascript">
				var serverMsg="";
				var m = new Map();
				$(function(){$('#imgSample').uploadify(
					{buttonText:'添加LOGO',
					auto:false,
					progressData:'speed',
					multi:true,
					height:25,
					overrideEvents:['onDialogClose'],
					fileTypeDesc:'图片格式:',
					queueID:'imagediv_imgSample',
					fileTypeExts:'*.jpg;*.jpeg;*.gif;*.png;*.bmp',
					fileSizeLimit:'3MB',swf:'plug-in/uploadify/uploadify.swf',	
					uploader:'cgUploadController.do?saveFiles&jsessionid='+$("#sessionUID").val()+'',
					onUploadStart : function(file) { 
						var cgFormId=$("input[name='id']").val();
						$('#imgSample').uploadify("settings", "formData", {'cgFormId':cgFormId,'cgFormName':'zml_sample','cgFormField':'imgSample'});} ,
					onQueueComplete : function(queueData) {
						 var win = frameElement.api.opener;
						 win.reloadTable();
						 win.tip(serverMsg);
						 frameElement.api.close();},
					onUploadSuccess : function(file, data, response) {var d=$.parseJSON(data);if(d.success){var win = frameElement.api.opener;serverMsg = d.msg;}},onFallback : function(){tip("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试")},onSelectError : function(file, errorCode, errorMsg){switch(errorCode) {case -100:tip("上传的文件数量已经超出系统限制的"+$('#imgSample').uploadify('settings','queueSizeLimit')+"个文件！");break;case -110:tip("文件 ["+file.name+"] 大小超出系统限制的"+$('#imgSample').uploadify('settings','fileSizeLimit')+"大小！");break;case -120:tip("文件 ["+file.name+"] 大小异常！");break;case -130:tip("文件 ["+file.name+"] 类型不正确！");break;}},
					onUploadProgress : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) { }});});
					</script><span id="image_uploadspan"><div id="imgSample" class="uploadify" style="height: 25px; width: 120px;"><object id="SWFUpload_0" type="application/x-shockwave-flash" data="plug-in/uploadify/uploadify.swf?preventswfcaching=1484995006354" width="120" height="25" class="swfupload" style="position: absolute; z-index: 1;"><param name="wmode" value="transparent"><param name="movie" value="plug-in/uploadify/uploadify.swf?preventswfcaching=1484995006354"><param name="quality" value="high"><param name="menu" value="false"><param name="allowScriptAccess" value="always"><param name="flashvars" value="movieName=SWFUpload_0&amp;uploadURL=%2Fzml-operate%2FcgUploadController.do%3FsaveFiles%26jsessionid%3Dundefined&amp;useQueryString=false&amp;requeueOnError=false&amp;httpSuccess=&amp;assumeSuccessTimeout=30&amp;params=&amp;filePostName=Filedata&amp;fileTypes=*.jpg%3B*.jpeg%3B*.gif%3B*.png%3B*.bmp&amp;fileTypesDescription=%E5%9B%BE%E7%89%87%E6%A0%BC%E5%BC%8F%3A&amp;fileSizeLimit=15MB&amp;fileUploadLimit=0&amp;fileQueueLimit=999&amp;debugEnabled=false&amp;buttonImageURL=%2Fzml-operate%2F&amp;buttonWidth=120&amp;buttonHeight=25&amp;buttonText=&amp;buttonTextTopPadding=0&amp;buttonTextLeftPadding=0&amp;buttonTextStyle=color%3A%20%23000000%3B%20font-size%3A%2016pt%3B&amp;buttonAction=-110&amp;buttonDisabled=false&amp;buttonCursor=-2"></object><div id="imgSample-button" class="uploadify-button " style="height: 25px; line-height: 25px; width: 120px;"><span class="uploadify-button-text">添加LOGO</span></div></div></span>
			</div>
		    </div>
			<div class="form">
		      <label class="Validform_label">文件:</label>
					<table></table>
					<div class="form jeecgDetail"> 
						<script type="text/javascript">
							var serverMsg="";
							$(function(){
								$('#fileSample').uploadify({
									buttonText:'添加文件',
									auto:false,
									progressData:'speed',
									multi:true,
									height:25,
									overrideEvents:['onDialogClose'],
									fileTypeDesc:'文件格式:',
									queueID:'filediv_file',
									fileTypeExts:'*.rar;*.zip;*.doc;*.docx;*.txt;*.ppt;*.xls;*.xlsx;*.html;*.htm;*.pdf;*.jpg;*.gif;*.png',
									fileSizeLimit:'15MB',
									swf:'plug-in/uploadify/uploadify.swf',	
									uploader:'cgUploadController.do?saveFiles&jsessionid='+$("#sessionUID").val()+'',
									onUploadStart : function(file) { 
										var cgFormId=$("input[name='id']").val();
										$('#fileSample').uploadify("settings", "formData", {
											'cgFormId':cgFormId,
											'cgFormName':'zml_sample',
											'cgFormField':'FILE_SAMPLE'
										});
									} ,
									onQueueComplete : function(queueData) {
										 var win = frameElement.api.opener;
										 win.reloadTable();
										 win.tip(serverMsg);
										 frameElement.api.close();
									},
									onUploadSuccess : function(file, data, response) {
										var d=$.parseJSON(data);
										if(d.success){
											var win = frameElement.api.opener;
											serverMsg = d.msg;
										}
									},
									onFallback: function() {
					                    tip("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试")
					                },
					                onSelectError: function(file, errorCode, errorMsg) {
					                    switch (errorCode) {
					                    case - 100 : tip("上传的文件数量已经超出系统限制的" + $('#file').uploadify('settings', 'queueSizeLimit') + "个文件！");
					                        break;
					                    case - 110 : tip("文件 [" + file.name + "] 大小超出系统限制的" + $('#file').uploadify('settings', 'fileSizeLimit') + "大小！");
					                        break;
					                    case - 120 : tip("文件 [" + file.name + "] 大小异常！");
					                        break;
					                    case - 130 : tip("文件 [" + file.name + "] 类型不正确！");
					                        break;
					                    }
					                },
					                onUploadProgress: function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {}
								});
							});
						</script>
						<span id="file_uploadspan"><input type="file" name="fileSample" id="fileSample" /></span> 
					</div> 
					<div class="form" id="filediv_file"></div>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">复选:</label>
					<t:dictSelect field="fuxuankuan" type="checkbox"
										typeGroupCode="fieltype" defaultVal="${zmlSamplePage.fuxuankuan}" hasLabel="false"  title="复选"></t:dictSelect>     
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">单选:</label>
					<t:dictSelect field="danxuan" type="radio"
										typeGroupCode="sf_yn" defaultVal="${zmlSamplePage.danxuan}" hasLabel="false"  title="单选"></t:dictSelect>     
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">时间:</label>
					  <input id="shijian" name="shijian" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">多文本:</label>
						<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
						<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>
				    	<textarea name="duowenben" id="duowenben" style="width: 650px;height:300px"></textarea>
					    <script type="text/javascript">
					        var editor = UE.getEditor('duowenben');
					    </script>
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>
  <script src = "webpage/com/zml/sample/zmlSample.js"></script>	
  	<script type="text/javascript">
  		function jeecgFormFileCallBack(data){
  			if (data.success == true) {
				uploadFile(data);
			} else {
				if (data.responseText == '' || data.responseText == undefined) {
					$.messager.alert('错误', data.msg);
					$.Hidemsg();
				} else {
					try {
						var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
						$.messager.alert('错误', emsg);
						$.Hidemsg();
					} catch(ex) {
						$.messager.alert('错误', data.responseText + '');
					}
				}
				return false;
			}
			if (!neibuClickFlag) {
				var win = frameElement.api.opener;
				win.reloadTable();
			}
  		}
  		function upload() {
			$('#fileSample').uploadify('upload', '*');		
		}
		
		var neibuClickFlag = false;
		function neibuClick() {
			neibuClickFlag = true; 
			$('#btn_sub').trigger('click');
		}
		function cancel() {
			$('#fileSample').uploadify('cancel', '*');
		}
		function uploadFile(data){
			if(!$("input[name='id']").val()){
				if(data.obj!=null && data.obj!='undefined'){
					$("input[name='id']").val(data.obj.id);
				}
			}
			if($(".uploadify-queue-item").length>0){
				upload();
			}else{
				if (neibuClickFlag){
					alert(data.msg);
					neibuClickFlag = false;
				}else {
					var win = frameElement.api.opener;
					win.reloadTable();
					win.tip(data.msg);
					frameElement.api.close();
				}
			}
		}
  	</script>
  	