<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>商品分类</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
		<link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css" />
		<script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlCommodityClassifyController.do?doAdd" tiptype="1" callback="jeecgFormFileCallBack@Override">
					<input id="id" name="id" type="hidden" value="${zmlCommodityClassifyPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlCommodityClassifyPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlCommodityClassifyPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlCommodityClassifyPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlCommodityClassifyPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlCommodityClassifyPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlCommodityClassifyPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlCommodityClassifyPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlCommodityClassifyPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlCommodityClassifyPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							分类:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="classify" type="list"
									typeGroupCode="commodity_classify" defaultVal="${zmlCommodityClassifyPage.classify}" hasLabel="false"  title="分类"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">分类</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							分类代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="classifyCode" name="classifyCode" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">分类代码</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">名称</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							介绍:
						</label>
					</td>
					<td class="value">
					     	 <input id="introduction" name="introduction" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">介绍</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							图标:
						</label>
					</td>
					<td class="value">
								<table></table>
								<div class="form jeecgDetail"> 
									<script type="text/javascript">
										var serverMsg="";
										$(function(){
											$('#logo').uploadify({
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
												uploader:'zmlCommodityClassifyController.do?saveFiles&jsessionid='+$("#sessionUID").val()+'',
												onUploadStart : function(file) { 
													var cgFormId=$("input[name='id']").val();
													$('#logo').uploadify("settings", "formData", {
														'cgFormId':cgFormId,
														'cgFormName':'zml_commodity_classify',
														'cgFormField':'LOGO'
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
									<span id="file_uploadspan"><input type="file" name="logo" id="logo" /></span> 
								</div> 
								<div class="form" id="filediv_file"></div>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标志</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							父ID:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="parentId" type="list"
									dictTable="zml_commodity_classify" dictField="id" dictText="name" defaultVal="${zmlCommodityClassifyPage.parentId}" hasLabel="false"  title="父ID"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">父ID</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否热门推荐:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="isHotRecommend" type="list"
									typeGroupCode="sf_yn" defaultVal="${zmlCommodityClassifyPage.isHotRecommend}" hasLabel="false"  title="是否热门推荐"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否热门推荐</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否热门搜索:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="isHotSearch" type="list"
									typeGroupCode="sf_yn" defaultVal="${zmlCommodityClassifyPage.isHotSearch}" hasLabel="false"  title="是否热门搜索"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否热门搜索</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							排序号:
						</label>
					</td>
					<td class="value">
					     	 <input id="orderNum" name="orderNum" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">排序号</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="status" type="list"
									typeGroupCode="is_use" defaultVal="${zmlCommodityClassifyPage.status}" hasLabel="false"  title="状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml/commodity_classify/zmlCommodityClassify.js"></script>		
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
				$('#logo').uploadify('upload', '*');		
			}
			
			var neibuClickFlag = false;
			function neibuClick() {
				neibuClickFlag = true; 
				$('#btn_sub').trigger('click');
			}
			function cancel() {
				$('#logo').uploadify('cancel', '*');
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
