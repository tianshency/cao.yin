<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>用户信息记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlUserRecordController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlUserRecordPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlUserRecordPage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlUserRecordPage.updateDate }">
					<input id="version" name="version" type="hidden" value="${zmlUserRecordPage.version }">
					<input id="userId" name="userId" type="hidden" value="${zmlUserRecordPage.userId }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							申请:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="applyId" type="list"
									dictTable="zml_loan_application" dictField="id" dictText="subject" defaultVal="${zmlUserRecordPage.applyId}" hasLabel="false"  title="申请"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申请</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							真实姓名:
						</label>
					</td>
					<td class="value">
					     	 <input id="realName" name="realName" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">真实姓名</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户类型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="type" type="list"
									typeGroupCode="" defaultVal="${zmlUserRecordPage.type}" hasLabel="false"  title="用户类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户类型</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							电话:
						</label>
					</td>
					<td class="value">
					     	 <input id="phone" name="phone" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电话</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							身份证号码:
						</label>
					</td>
					<td class="value">
					     	 <input id="identificationNumber" name="identificationNumber" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">身份证号码</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							生日:
						</label>
					</td>
					<td class="value">
							   <input id="birthDay" name="birthDay" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生日</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							年龄:
						</label>
					</td>
					<td class="value">
					     	 <input id="age" name="age" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">年龄</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							性别:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="sex" type="list"
									typeGroupCode="sex" defaultVal="${zmlUserRecordPage.sex}" hasLabel="false"  title="性别"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">性别</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							户口所在地:
						</label>
					</td>
					<td class="value">
					     	 <input id="accountLocation" name="accountLocation" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">户口所在地</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							发证机关:
						</label>
					</td>
					<td class="value">
					     	 <input id="issuingAuthority" name="issuingAuthority" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发证机关</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							有效期起:
						</label>
					</td>
					<td class="value">
							   <input id="validStart" name="validStart" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">有效期起</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							有效期至:
						</label>
					</td>
					<td class="value">
							   <input id="validEnd" name="validEnd" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">有效期至</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否实名认证:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="isVerified" type="list"
									typeGroupCode="sf_yn" defaultVal="${zmlUserRecordPage.isVerified}" hasLabel="false"  title="是否实名认证"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否实名认证</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							实名认证方式:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="verifiedMode" type="list"
									typeGroupCode="verified_mode" defaultVal="${zmlUserRecordPage.verifiedMode}" hasLabel="false"  title="实名认证方式"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">实名认证方式</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							昵称:
						</label>
					</td>
					<td class="value">
					     	 <input id="wxNickname" name="wxNickname" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">昵称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							性别:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="wxSex" type="list"
									typeGroupCode="sex" defaultVal="${zmlUserRecordPage.wxSex}" hasLabel="false"  title="性别"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">性别</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							国家:
						</label>
					</td>
					<td class="value">
					     	 <input id="wxCountry" name="wxCountry" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">国家</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							省份:
						</label>
					</td>
					<td class="value">
					     	 <input id="wxProvince" name="wxProvince" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">省份</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							城市:
						</label>
					</td>
					<td class="value">
					     	 <input id="wxCity" name="wxCity" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">城市</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							上次修改时间:
						</label>
					</td>
					<td class="value">
							   <input id="wxSubscribeTime" name="wxSubscribeTime" type="text" style="width: 150px" 
					      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上次修改时间</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							列表:
						</label>
					</td>
					<td class="value">
					     	 <input id="wxTagidList" name="wxTagidList" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">列表</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							订阅:
						</label>
					</td>
					<td class="value">
					     	 <input id="wxSubscribe" name="wxSubscribe" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订阅</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							语言:
						</label>
					</td>
					<td class="value">
					     	 <input id="wxLanguage" name="wxLanguage" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">语言</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							OpenId:
						</label>
					</td>
					<td class="value">
					     	 <input id="wxOpenid" name="wxOpenid" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">OpenId</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							组ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="wxGroupid" name="wxGroupid" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">组ID</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							头像:
						</label>
					</td>
					<td class="value">
					     	 <input id="wxHeadimgurl" name="wxHeadimgurl" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">头像</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
					     	 <input id="wxRemark" name="wxRemark" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							账号:
						</label>
					</td>
					<td class="value">
					     	 <input id="accountNumber" name="accountNumber" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">账号</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml_loan/zmlUserRecord/zmlUserRecord.js"></script>		
