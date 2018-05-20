<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>意见反馈</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlSysOpinionFeedbackInfoController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlSysOpinionFeedbackInfoPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlSysOpinionFeedbackInfoPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlSysOpinionFeedbackInfoPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlSysOpinionFeedbackInfoPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlSysOpinionFeedbackInfoPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlSysOpinionFeedbackInfoPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlSysOpinionFeedbackInfoPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlSysOpinionFeedbackInfoPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlSysOpinionFeedbackInfoPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlSysOpinionFeedbackInfoPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="userId" name="userId" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户ID</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
					     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标题</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							电话:
						</label>
					</td>
					<td class="value">
					     	 <input id="phone" name="phone" type="text" style="width: 150px" class="inputxt"  datatype="m">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电话</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容:
						</label>
					</td>
					<td class="value">
								<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
								<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>
						    	<textarea name="content" id="content" style="width: 650px;height:300px"></textarea>
							    <script type="text/javascript">
							        var editor = UE.getEditor('content');
							    </script>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">内容</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							图片一:
						</label>
					</td>
					<td class="value">
					      		<input id="imgOne" name="imgOne" type="text" style="width: 150px" class="inputxt"  
>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">图片一</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							图片二:
						</label>
					</td>
					<td class="value">
					      		<input id="imgTwo" name="imgTwo" type="text" style="width: 150px" class="inputxt"  
>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">图片二</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							图片三:
						</label>
					</td>
					<td class="value">
					      		<input id="imgThree" name="imgThree" type="text" style="width: 150px" class="inputxt"  
>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">图片三</label>
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
  <script src = "webpage/com/zml/opinion_feedback/zmlSysOpinionFeedbackInfo.js"></script>		
