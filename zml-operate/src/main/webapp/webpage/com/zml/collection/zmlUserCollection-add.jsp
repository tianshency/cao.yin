<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>我的收藏</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlUserCollectionController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlUserCollectionPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlUserCollectionPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlUserCollectionPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlUserCollectionPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlUserCollectionPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlUserCollectionPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlUserCollectionPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlUserCollectionPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlUserCollectionPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlUserCollectionPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="userId" type="list"
									dictTable="zml_user" dictField="id" dictText="user_name" defaultVal="${zmlUserCollectionPage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							label:
						</label>
					</td>
					<td class="value">
					     	 <input id="label" name="label" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">label</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							收藏类型:
						</label>
					</td>
					<td class="value">
					     	 <input id="collectionType" name="collectionType" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收藏类型</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							收藏数据ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="dataId" name="dataId" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收藏数据ID</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							数据名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="dataName" name="dataName" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">数据名称</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							网址:
						</label>
					</td>
					<td class="value">
					     	 <input id="url" name="url" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">网址</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
						  	 <textarea style="width:600px;" class="inputxt" rows="6" id="remarks" name="remarks"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="status" type="list"
									typeGroupCode="collection_sts" defaultVal="${zmlUserCollectionPage.status}" hasLabel="false"  title="状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml/collection/zmlUserCollection.js"></script>		
