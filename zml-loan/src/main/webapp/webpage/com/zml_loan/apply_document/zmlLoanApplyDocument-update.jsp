<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>借款申请文档</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
		<link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css" />
		<script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlLoanApplyDocumentController.do?doUpdate" tiptype="1" callback="jeecgFormFileCallBack@Override">
					<input id="id" name="id" type="hidden" value="${zmlLoanApplyDocumentPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlLoanApplyDocumentPage.createDate }">
					<input id="version" name="version" type="hidden" value="${zmlLoanApplyDocumentPage.version }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlLoanApplyDocumentPage.updateDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="userId" type="list"
										dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="${zmlLoanApplyDocumentPage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								申请主题:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="applId" type="list"
										dictTable="zml_loan_application" dictField="id" dictText="subject" defaultVal="${zmlLoanApplyDocumentPage.applId}" hasLabel="false"  title="申请主题"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申请主题</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								文档类型:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="docType" type="list"
										typeGroupCode="apply_doc_type" defaultVal="${zmlLoanApplyDocumentPage.docType}" hasLabel="false"  title="文档类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">文档类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								标识:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="fileFlag" type="list"
										typeGroupCode="file_flag" defaultVal="${zmlLoanApplyDocumentPage.fileFlag}" hasLabel="false"  title="标识"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标识</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								图片路径:
							</label>
						</td>
						<td class="value">
						     	 <input id="imagePath" name="imagePath" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanApplyDocumentPage.imagePath}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">图片路径</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								文件路径:
							</label>
						</td>
						<td class="value">
									<table id="fileTable"></table>
										<table></table>
										<script type="text/javascript">
											var serverMsg="";
											$(function(){
												$('#filePath').uploadify({
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
														$('#filePath').uploadify("settings", "formData", {
															'cgFormId':cgFormId,
															'cgFormName':'zml_loan_apply_document',
															'cgFormField':'FILE_PATH'
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
										<span id="file_uploadspan"><input type="file" name="filePath" id="filePath" /></span> 
										<div class="form" id="filediv_file"></div>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">文件路径</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								描述:
							</label>
						</td>
						<td class="value">
						     	 <input id="details" name="details" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanApplyDocumentPage.details}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								序号:
							</label>
						</td>
						<td class="value">
						     	 <input id="seqNo" name="seqNo" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanApplyDocumentPage.seqNo}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">序号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								审批标识:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="approvalFlag" type="list"
										typeGroupCode="approval_flag" defaultVal="${zmlLoanApplyDocumentPage.approvalFlag}" hasLabel="false"  title="审批标识"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批标识</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								审批人:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="approvalUserId" type="list"
										dictTable="t_s_base_user" dictField="id" dictText="realname" defaultVal="${zmlLoanApplyDocumentPage.approvalUserId}" hasLabel="false"  title="审批人"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								审批时间:
							</label>
						</td>
						<td class="value">
									  <input id="approvalDate" name="approvalDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value='<fmt:formatDate value='${zmlLoanApplyDocumentPage.approvalDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批时间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								审批意见:
							</label>
						</td>
						<td class="value">
						     	 <input id="approvalOpinion" name="approvalOpinion" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanApplyDocumentPage.approvalOpinion}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批意见</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml_loan/apply_document/zmlLoanApplyDocument.js"></script>		
	  	<script type="text/javascript">
		  	//加载 已存在的 文件
		  	$(function(){
		  		var table = $("#fileTable");
		  		var cgFormId=$("input[name='id']").val();
		  		$.ajax({
		  		   type: "post",
		  		   url: "zmlLoanApplyDocumentController.do?getFiles&id=" +  cgFormId,
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
				$('#filePath').uploadify('upload', '*');		
			}
			
			var neibuClickFlag = false;
			function neibuClick() {
				neibuClickFlag = true; 
				$('#btn_sub').trigger('click');
			}
			function cancel() {
				$('#filePath').uploadify('cancel', '*');
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
