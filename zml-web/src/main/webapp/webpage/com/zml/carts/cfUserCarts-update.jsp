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
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="cfUserCartsController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${cfUserCartsPage.id }">
					<input id="createName" name="createName" type="hidden" value="${cfUserCartsPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${cfUserCartsPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${cfUserCartsPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${cfUserCartsPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${cfUserCartsPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${cfUserCartsPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${cfUserCartsPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${cfUserCartsPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${cfUserCartsPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="userId" type="list"
										dictTable="cf_user" dictField="id" dictText="name" defaultVal="${cfUserCartsPage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
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
										dictTable="cf_commodity" dictField="id" dictText="name" defaultVal="${cfUserCartsPage.commodityId}" hasLabel="false"  title="商品"></t:dictSelect>     
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
						     	 <input id="amount" name="amount" type="text" style="width: 150px" class="inputxt"  datatype="n" value='${cfUserCartsPage.amount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">数量</label>
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
  <script src = "webpage/com/zml/carts/cfUserCarts.js"></script>		
