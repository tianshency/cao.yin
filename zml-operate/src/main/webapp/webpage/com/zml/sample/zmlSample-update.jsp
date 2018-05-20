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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="zmlSampleController.do?doUpdate" tiptype="1" callback="jeecgFormFileCallBack@Override">
				<input id="id" name="id" type="hidden" value="${zmlSamplePage.id }">
				<input id="createDate" name="createDate" type="hidden" value="${zmlSamplePage.createDate }">
				<input id="updateDate" name="updateDate" type="hidden" value="${zmlSamplePage.updateDate }">
				<input id="version" name="version" type="hidden" value="${zmlSamplePage.version }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">图片:</label>
		      		<input id="imgSample" name="imgSample" type="text" style="width: 150px" class="inputxt"  value='${zmlSamplePage.imgSample}'>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">文件:</label>
					<table id="fileTable"></table>
						<table></table>
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
					  <input id="shijian" name="shijian" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"
		      						 value='<fmt:formatDate value='${zmlSamplePage.shijian}' type="date" pattern="yyyy-MM-dd"/>'>    
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
	  	//加载 已存在的 文件
	  	$(function(){
	  		var table = $("#fileTable");
	  		var cgFormId=$("input[name='id']").val();
	  		$.ajax({
	  		   type: "post",
	  		   url: "zmlSampleController.do?getFiles&id=" +  cgFormId,
	  		   success: function(data){
	  			 var arrayFileObj = jQuery.parseJSON(data).obj;
	  			 
	  			$.each(arrayFileObj,function(n,file){
	  				var tr = $("<tr style=\"height:34px;\"></tr>");
	  				var td_title = $("<td>" + file.title + "</td>")
	  		  		var td_download = $("<td><a href=\"commonController.do?viewFile&fileid=" + file.fileKey + "&subclassname=org.jeecgframework.web.cgform.entity.upload.CgUploadEntity\" title=\"下载\">下载</a></td>")
	  		  		var td_view = $("<td><a href=\"javascript:void(0);\" onclick=\"openwindow('预览','commonController.do?openViewFile&fileid=" + file.fileKey + "&subclassname=org.jeecgframework.web.cgform.entity.upload.CgUploadEntity','fList',700,500)\">预览</a></td>");
	  		  		var td_del = $("<td><a href=\"javascript:void(0)\" class=\"jeecgDetail\" onclick=\"del('cgUploadController.do?delFile&id=" + file.fileKey + "',this)\">删除</a></td>");
	  		  		
	  		  		tr.appendTo(table);
	  		  		td_title.appendTo(tr);
	  		  		td_download.appendTo(tr);
	  		  		td_view.appendTo(tr);
	  		  		td_del.appendTo(tr);
	  			 });
	  		   }
	  		});
	  	})
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
