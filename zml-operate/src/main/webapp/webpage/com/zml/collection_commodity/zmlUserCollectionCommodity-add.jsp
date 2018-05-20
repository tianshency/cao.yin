<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>我的收藏商品</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlUserCollectionCommodityController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlUserCollectionCommodityPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlUserCollectionCommodityPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlUserCollectionCommodityPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlUserCollectionCommodityPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlUserCollectionCommodityPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlUserCollectionCommodityPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlUserCollectionCommodityPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlUserCollectionCommodityPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlUserCollectionCommodityPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlUserCollectionCommodityPage.version }">
					<input id="commodityName" name="commodityName" type="hidden" value="${zmlUserCollectionCommodityPage.commodityName }">
					<input id="commodityCoverImg" name="commodityCoverImg" type="hidden" value="${zmlUserCollectionCommodityPage.commodityCoverImg }">
					<input id="merchantsName" name="merchantsName" type="hidden" value="${zmlUserCollectionCommodityPage.merchantsName }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户ID:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="userId" type="list"
									dictTable="zml_user" dictField="id" dictText="user_name" defaultVal="${zmlUserCollectionCommodityPage.userId}" hasLabel="false"  title="用户ID"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户ID</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							标签:
						</label>
					</td>
					<td class="value">
					     	 <input id="label" name="label" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标签</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品类型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="commodityType" type="list"
									dictTable="zml_commodity_classify" dictField="id" dictText="name" defaultVal="${zmlUserCollectionCommodityPage.commodityType}" hasLabel="false"  title="商品类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品类型</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品ID:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="commodityId" type="list"
									dictTable="zml_commodity" dictField="id" dictText="name" defaultVal="${zmlUserCollectionCommodityPage.commodityId}" hasLabel="false"  title="商品ID"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品ID</label>
						</td>
					</tr>
				<tr>
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
							状态:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="status" type="list"
									typeGroupCode="collection_sts" defaultVal="${zmlUserCollectionCommodityPage.status}" hasLabel="false"  title="状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
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
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml/collection_commodity/zmlUserCollectionCommodity.js"></script>		
