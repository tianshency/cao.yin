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
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlUserReleaseFoodController.do?doUpdate" tiptype="1" >
		<input id="id" name="id" type="hidden" value="${zmlUserReleaseFoodPage.id }">
		<input id="createName" name="createName" type="hidden" value="${zmlUserReleaseFoodPage.createName }">
		<input id="createBy" name="createBy" type="hidden" value="${zmlUserReleaseFoodPage.createBy }">
		<input id="createDate" name="createDate" type="hidden" value="${zmlUserReleaseFoodPage.createDate }">
		<input id="updateName" name="updateName" type="hidden" value="${zmlUserReleaseFoodPage.updateName }">
		<input id="updateBy" name="updateBy" type="hidden" value="${zmlUserReleaseFoodPage.updateBy }">
		<input id="updateDate" name="updateDate" type="hidden" value="${zmlUserReleaseFoodPage.updateDate }">
		<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlUserReleaseFoodPage.sysOrgCode }">
		<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlUserReleaseFoodPage.sysCompanyCode }">
		<input id="version" name="version" type="hidden" value="${zmlUserReleaseFoodPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							发布人:
						</label>
					</td>
					<td class="value">
					    <input id="userId" name="userId" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseFoodPage.userId}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">发布人</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
					     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseFoodPage.title}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">标题</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							种类:
						</label>
					</td>
					<td class="value">
					    <input id="kind" name="kind" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseFoodPage.kind}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">种类</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							价格:
						</label>
					</td>
					<td class="value">
					    <input id="price" name="price" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseFoodPage.price}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">价格</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							单位:
						</label>
					</td>
					<td class="value">
					    <input id="unit" name="unit" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseFoodPage.unit}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">单位</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							水分:
						</label>
					</td>
					<td class="value">
					    <input id="moisture" name="moisture" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseFoodPage.moisture}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">水分</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							霉变:
						</label>
					</td>
					<td class="value">
					    <input id="mildew" name="mildew" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseFoodPage.mildew}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">霉变</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							容重:
						</label>
					</td>
					<td class="value">
					    <input id="bulkDensity" name="bulkDensity" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseFoodPage.bulkDensity}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">容重</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							粮食类型:
						</label>
					</td>
					<td class="value">
					    <input id="foodType" name="foodType" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseFoodPage.foodType}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">粮食类型</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							筛选类型:
						</label>
					</td>
					<td class="value">
					    <input id="filterType" name="filterType" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseFoodPage.filterType}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">筛选类型</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							年份:
						</label>
					</td>
					<td class="value">
					    <input id="particularYear" name="particularYear" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseFoodPage.particularYear}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">年份</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							总数量:
						</label>
					</td>
					<td class="value">
					    <input id="totalNum" name="totalNum" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseFoodPage.totalNum}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">总数量</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							有效开始日期:
						</label>
					</td>
					<td class="value">
					    <input id="validStartDate" name="validStartDate" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseFoodPage.validStartDate}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">有效开始日期</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							有效结束日期:
						</label>
					</td>
					<td class="value">
					    <input id="validEndDate" name="validEndDate" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseFoodPage.validEndDate}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">有效结束日期</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							封面照片:
						</label>
					</td>
					<td class="value">
					    <input id="coverImg" name="coverImg" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseFoodPage.coverImg}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">封面照片</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
					    <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseFoodPage.remark}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">备注</label>
					</td>
				</tr>
				<%-- <tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
					    <input id="status" name="status" type="text" style="width: 150px" class="inputxt"  value='${zmlUserReleaseFoodPage.status}'>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">状态</label>
					</td>
					<td align="right">
						<label class="Validform_label">
						</label>
					</td>
					<td class="value">
					</td>
				</tr> --%>
		</table>
	</t:formvalid>
 </body>
  <script src = "webpage/com/zml/release_food/zmlUserReleaseFood.js"></script>		
