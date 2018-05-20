<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>购物车</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlUserCartsController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlUserCartsPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlUserCartsPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlUserCartsPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlUserCartsPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlUserCartsPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlUserCartsPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlUserCartsPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlUserCartsPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlUserCartsPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlUserCartsPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="userId" type="list"
										dictTable="zml_user" dictField="id" dictText="user_name" defaultVal="${zmlUserCartsPage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商品:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="commodityId" type="list"
										dictTable="zml_commodity" dictField="id" dictText="name" defaultVal="${zmlUserCartsPage.commodityId}" hasLabel="false"  title="商品"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								数量:
							</label>
						</td>
						<td class="value">
						     	 <input id="amount" name="amount" type="text" style="width: 150px" class="inputxt"  datatype="n" value='${zmlUserCartsPage.amount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">数量</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								状态:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="status" type="list"
										typeGroupCode="carts_sts" defaultVal="${zmlUserCartsPage.status}" hasLabel="false"  title="状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml/carts/zmlUserCarts.js"></script>		
