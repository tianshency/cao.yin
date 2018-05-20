<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>用户</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
		}
	});
	$(".tabs-wrap").css('width','100%');
  });
 </script>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="cfUserController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${cfUserPage.id }">
					<input id="createName" name="createName" type="hidden" value="${cfUserPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${cfUserPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${cfUserPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${cfUserPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${cfUserPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${cfUserPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${cfUserPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${cfUserPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${cfUserPage.version }">
					<input id="password" name="password" type="hidden" value="${cfUserPage.password }">
					<input id="loginIp" name="loginIp" type="hidden" value="${cfUserPage.loginIp }">
					<input id="loginDate" name="loginDate" type="hidden" value="${cfUserPage.loginDate }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">用户名:</label>
			</td>
			<td class="value">
		     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"datatype="*" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">用户名</label>
			</td>
			<td align="right">
				<label class="Validform_label">昵称:</label>
			</td>
			<td class="value">
		     	 <input id="nickname" name="nickname" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">昵称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">手机:</label>
			</td>
			<td class="value">
		     	 <input id="phone" name="phone" type="text" style="width: 150px" class="inputxt"datatype="m" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">手机</label>
			</td>
			<td align="right">
				<label class="Validform_label">用户类型:</label>
			</td>
			<td class="value">
					<t:dictSelect field="type" type="list"
						typeGroupCode="user_type"  hasLabel="false"  title="用户类型"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">用户类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">用户状态:</label>
			</td>
			<td class="value">
					<t:dictSelect field="locked" type="list"
						typeGroupCode="user_locked"  hasLabel="false"  title="用户状态"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">用户状态</label>
			</td>
		</tr>
	</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="cfUserController.do?cfUserAddressList&id=${cfUserPage.id}" icon="icon-search" title="用户地址" id="cfUserAddress"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
	<table style="display:none">
	<tbody id="add_cfUserAddress_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  	<input name="cfUserAddressList[#index#].name" maxlength="20" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="*">
					  <label class="Validform_label" style="display: none;">姓名</label>
				  </td>
				  <td align="left">
					  	<input name="cfUserAddressList[#index#].phone" maxlength="11" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="m">
					  <label class="Validform_label" style="display: none;">电话</label>
				  </td>
				  <td align="left">
					  	<input name="cfUserAddressList[#index#].address" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="*">
					  <label class="Validform_label" style="display: none;">地址</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="cfUserAddressList[#index#].isDefault" type="list"
										typeGroupCode="is_default" defaultVal="" hasLabel="false"  title="是否默认"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">是否默认</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="cfUserAddressList[#index#].type" type="list"
										typeGroupCode="address_type" defaultVal="" hasLabel="false"  title="地址类型"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">地址类型</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/zml/user/cfUser.js"></script>
	