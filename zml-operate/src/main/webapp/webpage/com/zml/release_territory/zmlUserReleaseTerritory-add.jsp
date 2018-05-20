<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>发布土地</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlUserReleaseTerritoryController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlUserReleaseTerritoryPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlUserReleaseTerritoryPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlUserReleaseTerritoryPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlUserReleaseTerritoryPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlUserReleaseTerritoryPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlUserReleaseTerritoryPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlUserReleaseTerritoryPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlUserReleaseTerritoryPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlUserReleaseTerritoryPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlUserReleaseTerritoryPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							类型名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="type" name="type" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类型名称</label>
						</td>
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
							发布人:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="userId" type="list"
									dictTable="zml_user" dictField="id" dictText="user_name" defaultVal="${zmlUserReleaseTerritoryPage.userId}" hasLabel="false"  title="发布人"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发布人</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							发布类型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="releaseType" type="list"
									dictTable="zml_user_release_classify" dictField="id" dictText="name" defaultVal="${zmlUserReleaseTerritoryPage.releaseType}" hasLabel="false"  title="发布类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发布类型</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							位置:
						</label>
					</td>
					<td class="value">
					     	 <input id="position" name="position" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">位置</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							封面照片:
						</label>
					</td>
					<td class="value">
					      		<input id="coverImg" name="coverImg" type="text" style="width: 150px" class="inputxt"  
>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">封面照片</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							面积:
						</label>
					</td>
					<td class="value">
					     	 <input id="area" name="area" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">面积</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							价格:
						</label>
					</td>
					<td class="value">
					     	 <input id="price" name="price" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">价格</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							土地类型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="landType" type="list"
									typeGroupCode="land_type" defaultVal="${zmlUserReleaseTerritoryPage.landType}" hasLabel="false"  title="土地类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">土地类型</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							地势:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="terrain" type="list"
									typeGroupCode="terrain" defaultVal="${zmlUserReleaseTerritoryPage.terrain}" hasLabel="false"  title="地势"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地势</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							有效开始日期:
						</label>
					</td>
					<td class="value">
							   <input id="validStartDate" name="validStartDate" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">有效开始日期</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							有效结束日期:
						</label>
					</td>
					<td class="value">
							   <input id="validEndDate" name="validEndDate" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">有效结束日期</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
					     	 <input id="remarks" name="remarks" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="status" type="list"
									typeGroupCode="release_sts" defaultVal="${zmlUserReleaseTerritoryPage.status}" hasLabel="false"  title="状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml/release_territory/zmlUserReleaseTerritory.js"></script>		
