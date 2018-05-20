<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>用户消息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="cfUserMessageController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${cfUserMessagePage.id }">
					<input id="createName" name="createName" type="hidden" value="${cfUserMessagePage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${cfUserMessagePage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${cfUserMessagePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${cfUserMessagePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${cfUserMessagePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${cfUserMessagePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${cfUserMessagePage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${cfUserMessagePage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${cfUserMessagePage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="userId" type="list"
									dictTable="cf_user" dictField="id" dictText="name" defaultVal="${cfUserMessagePage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							消息内容:
						</label>
					</td>
					<td class="value">
					     	 <input id="conten" name="conten" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">消息内容</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发送时间:
						</label>
					</td>
					<td class="value">
							   <input id="sendTime" name="sendTime" type="text" style="width: 150px" 
					      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发送时间</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发送状态:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="sendFlag" type="list"
									typeGroupCode="send_flag" defaultVal="${cfUserMessagePage.sendFlag}" hasLabel="false"  title="发送状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发送状态</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							消息类型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="msgType" type="list"
									typeGroupCode="msg_type" defaultVal="${cfUserMessagePage.msgType}" hasLabel="false"  title="消息类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">消息类型</label>
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
  <script src = "webpage/com/zml/user_msg/cfUserMessage.js"></script>		
