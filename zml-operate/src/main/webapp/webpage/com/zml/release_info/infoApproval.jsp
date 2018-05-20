<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>发布粮食</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlUserReleaseInfoController.do?doApproval" tiptype="1" >
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
							审批结果:
						</label>
					</td>
					<td class="value">
					    通过：<input id="status" name="status" type="radio" style="width: 150px" class="inputxt"  value='1'/>
					    拒绝：<input id="status" name="status" type="radio" style="width: 150px" class="inputxt"  value='2'/>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">审批结果</label>
					</td>
					<td align="right">
						<label class="Validform_label">
						审批意见:
						</label>
					</td>
					<td class="value">
					<input id="approvalOpinion" name="approvalOpinion" type="text" style="width: 150px" class="inputxt"  value=''/>
					</td>
				</tr>
		</table>
	</t:formvalid>
 </body>
  <script src = "webpage/com/zml/release_food/zmlUserReleaseFood.js"></script>		
