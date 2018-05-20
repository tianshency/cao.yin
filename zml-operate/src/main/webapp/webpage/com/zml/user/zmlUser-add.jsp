<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>用户信息</title>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="zmlUserController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${zmlUserPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlUserPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlUserPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlUserPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlUserPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlUserPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlUserPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlUserPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlUserPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlUserPage.version }">
					<input id="password" name="password" type="hidden" value="${zmlUserPage.password }">
					<input id="loginIp" name="loginIp" type="hidden" value="${zmlUserPage.loginIp }">
					<input id="loginDate" name="loginDate" type="hidden" value="${zmlUserPage.loginDate }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">用户名:</label>
			</td>
			<td class="value">
		     	 <input id="userName" name="userName" type="text" style="width: 150px" class="inputxt"datatype="*" >
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
				<label class="Validform_label">联系电话:</label>
			</td>
			<td class="value">
		     	 <input id="phone" name="phone" type="text" style="width: 150px" class="inputxt"datatype="m" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">联系电话</label>
			</td>
			<td align="right">
				<label class="Validform_label">真实姓名:</label>
			</td>
			<td class="value">
		     	 <input id="realName" name="realName" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">真实姓名</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">身份证号码:</label>
			</td>
			<td class="value">
		     	 <input id="identificationNumber" name="identificationNumber" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">身份证号码</label>
			</td>
			<td align="right">
				<label class="Validform_label">年龄:</label>
			</td>
			<td class="value">
		     	 <input id="age" name="age" type="text" style="width: 150px" class="inputxt"datatype="n" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">年龄</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">性别:</label>
			</td>
			<td class="value">
					<t:dictSelect field="sex" type="list"
						typeGroupCode="sex"  hasLabel="false"  title="性别"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">性别</label>
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
				<label class="Validform_label">是否实名认证:</label>
			</td>
			<td class="value">
					<t:dictSelect field="isVerified" type="list"
						typeGroupCode="sf_yn"  hasLabel="false"  title="是否实名认证"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否实名认证</label>
			</td>
			<td align="right">
				<label class="Validform_label">头像:</label>
			</td>
			<td class="value">
		      		<input id="avatar" name="avatar" type="text" style="width: 150px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">头像</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">当前帐号是否禁用:</label>
			</td>
			<td class="value">
					<t:dictSelect field="locked" type="list"
						typeGroupCode="user_locked"  hasLabel="false"  title="当前帐号是否禁用"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">当前帐号是否禁用</label>
			</td>
			<td align="right">
				<label class="Validform_label">是否签订注册协议:</label>
			</td>
			<td class="value">
					<t:dictSelect field="registrationAgreement" type="list"
						typeGroupCode="sf_yn"  hasLabel="false"  title="是否签订注册协议"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否签订注册协议</label>
			</td>
		</tr>
	</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="zmlUserController.do?zmlUserAddressList&id=${zmlUserPage.id}" icon="icon-search" title="用户地址" id="zmlUserAddress"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
	<table style="display:none">
	<tbody id="add_zmlUserAddress_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  	<input name="zmlUserAddressList[#index#].consignee" maxlength="20" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">收货人</label>
				  </td>
				  <td align="left">
					  	<input name="zmlUserAddressList[#index#].phone" maxlength="11" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="m">
					  <label class="Validform_label" style="display: none;">电话</label>
				  </td>
				  <td align="left">
					  	<input name="zmlUserAddressList[#index#].address" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="*">
					  <label class="Validform_label" style="display: none;">地址</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlUserAddressList[#index#].isDefault" type="list"
										typeGroupCode="is_default" defaultVal="" hasLabel="false"  title="是否默认"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">是否默认</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlUserAddressList[#index#].type" type="list"
										typeGroupCode="address_type" defaultVal="" hasLabel="false"  title="地址类型"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">地址类型</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlUserAddressList[#index#].flag" type="list"
										typeGroupCode="addr_flag" defaultVal="" hasLabel="false"  title="地址标识"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">地址标识</label>
				  </td>
				  <td align="left">
					  	<input name="zmlUserAddressList[#index#].addrPrecision" maxlength="20" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">精度</label>
				  </td>
				  <td align="left">
					  	<input name="zmlUserAddressList[#index#].addrLatitude" maxlength="20" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">纬度</label>
				  </td>
				  <td align="left">
					  	<input name="zmlUserAddressList[#index#].addressName" maxlength="300" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">地点名称</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlUserAddressList[#index#].province" type="list"
										typeGroupCode="" defaultVal="" hasLabel="false"  title="省份"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">省份</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlUserAddressList[#index#].city" type="list"
										typeGroupCode="" defaultVal="" hasLabel="false"  title="市级"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">市级</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlUserAddressList[#index#].county" type="list"
										typeGroupCode="" defaultVal="" hasLabel="false"  title="县城"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">县城</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlUserAddressList[#index#].village" type="list"
										typeGroupCode="" defaultVal="" hasLabel="false"  title="村子"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">村子</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlUserAddressList[#index#].tuen" type="list"
										typeGroupCode="" defaultVal="" hasLabel="false"  title="屯组"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">屯组</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/zml/user/zmlUser.js"></script>
	