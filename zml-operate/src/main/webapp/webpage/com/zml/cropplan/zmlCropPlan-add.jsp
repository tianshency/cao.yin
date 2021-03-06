<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>种植计划</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlCropPlanController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlCropPlanPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlCropPlanPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlCropPlanPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlCropPlanPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlCropPlanPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlCropPlanPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlCropPlanPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlCropPlanPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlCropPlanPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlCropPlanPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							外部ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="openId" name="openId" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">外部ID</label>
						</td>
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
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							种植类型:
						</label>
					</td>
					<td class="value">
					     	 <input id="cropType" name="cropType" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">种植类型</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							土地数量:
						</label>
					</td>
					<td class="value">
					     	 <input id="landAmout" name="landAmout" type="text" style="width: 150px" class="inputxt"  datatype="n">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">土地数量</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							使用商品ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="useCommodityId" name="useCommodityId" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">使用商品ID</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							计算商品数量:
						</label>
					</td>
					<td class="value">
					     	 <input id="calcCommodityAmout" name="calcCommodityAmout" type="text" style="width: 150px" class="inputxt"  datatype="n">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">计算商品数量</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							实际商品数量:
						</label>
					</td>
					<td class="value">
					     	 <input id="realCommodityAmout" name="realCommodityAmout" type="text" style="width: 150px" class="inputxt"  datatype="n">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">实际商品数量</label>
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
  <script src = "webpage/com/zml/cropplan/zmlCropPlan.js"></script>		
