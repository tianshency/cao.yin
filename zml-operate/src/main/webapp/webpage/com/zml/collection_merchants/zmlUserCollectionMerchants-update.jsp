<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>我的收藏商家</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlUserCollectionMerchantsController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlUserCollectionMerchantsPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlUserCollectionMerchantsPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlUserCollectionMerchantsPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlUserCollectionMerchantsPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlUserCollectionMerchantsPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlUserCollectionMerchantsPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlUserCollectionMerchantsPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlUserCollectionMerchantsPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlUserCollectionMerchantsPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlUserCollectionMerchantsPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户ID:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="userId" type="list"
										dictTable="zml_user" dictField="id" dictText="user_name" defaultVal="${zmlUserCollectionMerchantsPage.userId}" hasLabel="false"  title="用户ID"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户ID</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								标签:
							</label>
						</td>
						<td class="value">
						     	 <input id="label" name="label" type="text" style="width: 150px" class="inputxt"  value='${zmlUserCollectionMerchantsPage.label}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标签</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商家类型:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="collectionType" type="list"
										typeGroupCode="crop_type" defaultVal="${zmlUserCollectionMerchantsPage.collectionType}" hasLabel="false"  title="商家类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商家类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								商家ID:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="merchantsId" type="list"
										dictTable="t_s_depart" dictField="org_code" dictText="departname" defaultVal="${zmlUserCollectionMerchantsPage.merchantsId}" hasLabel="false"  title="商家ID"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商家ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商家名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="merchantsName" name="merchantsName" type="text" style="width: 150px" class="inputxt"  value='${zmlUserCollectionMerchantsPage.merchantsName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商家名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								商家logo:
							</label>
						</td>
						<td class="value">
						      		<input id="merchantsLogo" name="merchantsLogo" type="text" style="width: 150px" class="inputxt"   value='${zmlUserCollectionMerchantsPage.merchantsLogo}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商家logo</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								负责人:
							</label>
						</td>
						<td class="value">
						     	 <input id="principal" name="principal" type="text" style="width: 150px" class="inputxt"  value='${zmlUserCollectionMerchantsPage.principal}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">负责人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								所在地:
							</label>
						</td>
						<td class="value">
						     	 <input id="address" name="address" type="text" style="width: 150px" class="inputxt"  value='${zmlUserCollectionMerchantsPage.address}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所在地</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								网址:
							</label>
						</td>
						<td class="value">
						     	 <input id="url" name="url" type="text" style="width: 150px" class="inputxt"  value='${zmlUserCollectionMerchantsPage.url}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">网址</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						     	 <input id="remarks" name="remarks" type="text" style="width: 150px" class="inputxt"  value='${zmlUserCollectionMerchantsPage.remarks}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
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
										typeGroupCode="collection_sts" defaultVal="${zmlUserCollectionMerchantsPage.status}" hasLabel="false"  title="状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
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
  <script src = "webpage/com/zml/collection_merchants/zmlUserCollectionMerchants.js"></script>		
