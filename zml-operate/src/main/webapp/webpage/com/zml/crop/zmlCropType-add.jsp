<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>农作物类型</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlCropTypeController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlCropTypePage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlCropTypePage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlCropTypePage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlCropTypePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlCropTypePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlCropTypePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlCropTypePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlCropTypePage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlCropTypePage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlCropTypePage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							外部ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">外部ID</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							标志:
						</label>
					</td>
					<td class="value">
					      		<input id="logo" name="logo" type="text" style="width: 150px" class="inputxt"  
>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标志</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							介绍:
						</label>
					</td>
					<td class="value">
								<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
								<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>
						    	<textarea name="description" id="description" style="width: 650px;height:300px"></textarea>
							    <script type="text/javascript">
							        var editor = UE.getEditor('description');
							    </script>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">介绍</label>
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
  <script src = "webpage/com/zml/crop/zmlCropType.js"></script>		
