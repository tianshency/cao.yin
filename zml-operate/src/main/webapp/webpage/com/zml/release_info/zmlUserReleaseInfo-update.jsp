<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>发布信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
		<link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css" />
		<script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlUserReleaseInfoController.do?doUpdate" tiptype="1" callback="jeecgFormFileCallBack@Override">
		<input id="id" name="id" type="hidden" value="${zmlUserReleaseInfoPage.id }">
		<input id="createName" name="createName" type="hidden" value="${zmlUserReleaseInfoPage.createName }">
		<input id="createDate" name="createDate" type="hidden" value="${zmlUserReleaseInfoPage.createDate }">
		<input id="updateDate" name="updateDate" type="hidden" value="${zmlUserReleaseInfoPage.updateDate }">
		<input id="version" name="version" type="hidden" value="${zmlUserReleaseInfoPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							发布人:
						</label>
					</td>
					<td class="value">
					     	 <input id="userId" name="userId" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseInfoPage.userId}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">发布人</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							发布人名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="userName" name="userName" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseInfoPage.userName}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">发布人名称</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发布人电话:
						</label>
					</td>
					<td class="value">
					     	 <input id="userPhone" name="userPhone" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseInfoPage.userPhone}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">发布人电话</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
					     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseInfoPage.title}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">标题</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发布类型:
						</label>
					</td>
					<td class="value">
					     	 <input id="releaseType" name="releaseType" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseInfoPage.releaseType}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">发布类型</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							封面照片:
						</label>
					</td>
					<td class="value">
								<table id="fileTable"></table>
									<table></table>
									<script type="text/javascript">
										var serverMsg="";
										$(function(){
											$('#coverImg').uploadify({
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
													$('#coverImg').uploadify("settings", "formData", {
														'cgFormId':cgFormId,
														'cgFormName':'zml_user_release_info',
														'cgFormField':'COVER_IMG'
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
									<span id="file_uploadspan"><input type="file" name="coverImg" id="coverImg" /></span> 
									<div class="form" id="filediv_file"></div>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">封面照片</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							数量:
						</label>
					</td>
					<td class="value">
					     	 <input id="amount" name="amount" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseInfoPage.amount}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">数量</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							数量单位:
						</label>
					</td>
					<td class="value">
					     	 <input id="amountUnit" name="amountUnit" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseInfoPage.amountUnit}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">数量单位</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							价格:
						</label>
					</td>
					<td class="value">
					     	 <input id="price" name="price" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseInfoPage.price}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">价格</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							价格单位:
						</label>
					</td>
					<td class="value">
					     	 <input id="priceUnit" name="priceUnit" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseInfoPage.priceUnit}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">价格单位</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							有效开始日期:
						</label>
					</td>
					<td class="value">
								  <input id="validStartDate" name="validStartDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${zmlUserReleaseInfoPage.validStartDate}' type="date" pattern="yyyy-MM-dd"/>'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">有效开始日期</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							有效结束日期:
						</label>
					</td>
					<td class="value">
								  <input id="validEndDate" name="validEndDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${zmlUserReleaseInfoPage.validEndDate}' type="date" pattern="yyyy-MM-dd"/>'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">有效结束日期</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value">
					     	 <input id="description" name="description" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseInfoPage.description}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">描述</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
					     	 <input id="remarks" name="remarks" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseInfoPage.remarks}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">备注</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
								<t:dictSelect field="status" type="list"
									typeGroupCode="release_sts" defaultVal="${zmlUserReleaseInfoPage.status}" hasLabel="false"  title="状态"></t:dictSelect>     
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">状态</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							浏览数量:
						</label>
					</td>
					<td class="value">
					     	 <input id="viewCount" name="viewCount" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseInfoPage.viewCount}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">浏览数量</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							收藏数量:
						</label>
					</td>
					<td class="value">
					     	 <input id="collectionCount" name="collectionCount" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseInfoPage.collectionCount}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">收藏数量</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							关注数量:
						</label>
					</td>
					<td class="value">
					     	 <input id="attentionCount" name="attentionCount" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseInfoPage.attentionCount}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">关注数量</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否审核:
						</label>
					</td>
					<td class="value">
					     	 <input id="isVerify" name="isVerify" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseInfoPage.isVerify}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">是否审核</label>
					</td>
			<td align="right">
				<label class="Validform_label">
				</label>
			</td>
			<td class="value">
			</td>
				</tr>
		</table>
	</t:formvalid>
 </body>
  <script src = "webpage/com/zml/release_info/zmlUserReleaseInfo.js"></script>		
	  	<script type="text/javascript">
		  	//加载 已存在的 文件
		  	$(function(){
		  		var table = $("#fileTable");
		  		var cgFormId=$("input[name='id']").val();
		  		$.ajax({
		  		   type: "post",
		  		   url: "zmlUserReleaseInfoController.do?getFiles&id=" +  cgFormId,
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
				$('#coverImg').uploadify('upload', '*');		
			}
			
			var neibuClickFlag = false;
			function neibuClick() {
				neibuClickFlag = true; 
				$('#btn_sub').trigger('click');
			}
			function cancel() {
				$('#coverImg').uploadify('cancel', '*');
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
