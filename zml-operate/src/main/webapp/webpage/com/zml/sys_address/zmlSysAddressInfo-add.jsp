<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>地理位置信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlSysAddressInfoController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlSysAddressInfoPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlSysAddressInfoPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlSysAddressInfoPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlSysAddressInfoPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlSysAddressInfoPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlSysAddressInfoPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlSysAddressInfoPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlSysAddressInfoPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlSysAddressInfoPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlSysAddressInfoPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
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
					<td align="right">
						<label class="Validform_label">
							来源标识:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="sourceFlag" type="list"
									typeGroupCode="source_flg" defaultVal="${zmlSysAddressInfoPage.sourceFlag}" hasLabel="false"  title="来源标识"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">来源标识</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							精度:
						</label>
					</td>
					<td class="value">
					     	 <input id="addrPrecision" name="addrPrecision" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">精度</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							纬度:
						</label>
					</td>
					<td class="value">
					     	 <input id="addrLatitude" name="addrLatitude" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">纬度</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							地点名称:
						</label>
					</td>
					<td class="value">
						  	 <textarea style="width:600px;" class="inputxt" rows="6" id="addressName" name="addressName"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地点名称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							省份:
						</label>
					</td>
					<td class="value">
					     	 <input id="province" name="province" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">省份</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							市级:
						</label>
					</td>
					<td class="value">
					     	 <input id="city" name="city" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">市级</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							县城:
						</label>
					</td>
					<td class="value">
					     	 <input id="county" name="county" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">县城</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							村子:
						</label>
					</td>
					<td class="value">
					     	 <input id="village" name="village" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">村子</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							组名:
						</label>
					</td>
					<td class="value">
					     	 <input id="groupName" name="groupName" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">组名</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							屯名:
						</label>
					</td>
					<td class="value">
					     	 <input id="tuenName" name="tuenName" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">屯名</label>
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
  <script src = "webpage/com/zml/sys_address/zmlSysAddressInfo.js"></script>		
