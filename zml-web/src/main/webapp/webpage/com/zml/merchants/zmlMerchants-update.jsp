<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>商户信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlMerchantsController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlMerchantsPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlMerchantsPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlMerchantsPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlMerchantsPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlMerchantsPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlMerchantsPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlMerchantsPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlMerchantsPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlMerchantsPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlMerchantsPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								商户名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="merchantsName" name="merchantsName" type="text" style="width: 150px" class="inputxt"  datatype="*" value='${zmlMerchantsPage.merchantsName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商户名称</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商家地址:
							</label>
						</td>
						<td class="value">
						     	 <input id="merchantsAdress" name="merchantsAdress" type="text" style="width: 150px" class="inputxt"  datatype="*" value='${zmlMerchantsPage.merchantsAdress}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商家地址</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								商家描述:
							</label>
						</td>
						<td class="value">
						     	 <input id="merchantsDescription" name="merchantsDescription" type="text" style="width: 150px" class="inputxt"  value='${zmlMerchantsPage.merchantsDescription}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商家描述</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								营业执照:
							</label>
						</td>
						<td class="value">
						     	 <input id="businesslicense" name="businesslicense" type="text" style="width: 150px" class="inputxt"  datatype="*" value='${zmlMerchantsPage.businesslicense}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">营业执照</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								标志:
							</label>
						</td>
						<td class="value">
						      		<input id="logo" name="logo" type="text" style="width: 150px" class="inputxt"   value='${zmlMerchantsPage.logo}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标志</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								封面图片:
							</label>
						</td>
						<td class="value">
						      		<input id="coverImg" name="coverImg" type="text" style="width: 150px" class="inputxt"   value='${zmlMerchantsPage.coverImg}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">封面图片</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								客服电话:
							</label>
						</td>
						<td class="value">
						     	 <input id="consumerHotline" name="consumerHotline" type="text" style="width: 150px" class="inputxt"  value='${zmlMerchantsPage.consumerHotline}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客服电话</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								负责人:
							</label>
						</td>
						<td class="value">
						     	 <input id="leader" name="leader" type="text" style="width: 150px" class="inputxt"  value='${zmlMerchantsPage.leader}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">负责人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								负责人电话:
							</label>
						</td>
						<td class="value">
						     	 <input id="leaderTelephone" name="leaderTelephone" type="text" style="width: 150px" class="inputxt"  datatype="m" value='${zmlMerchantsPage.leaderTelephone}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">负责人电话</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								评级:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="grade" type="list"
										typeGroupCode="merchants_grade" defaultVal="${zmlMerchantsPage.grade}" hasLabel="false"  title="评级"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评级</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml/merchants/zmlMerchants.js"></script>		
