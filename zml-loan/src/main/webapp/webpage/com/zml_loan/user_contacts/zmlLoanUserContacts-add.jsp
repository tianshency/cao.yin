<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>借款用户联系人</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlLoanUserContactsController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlLoanUserContactsPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlLoanUserContactsPage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlLoanUserContactsPage.updateDate }">
					<input id="version" name="version" type="hidden" value="${zmlLoanUserContactsPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							借款主题:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="applId" type="list"
									dictTable="zml_loan_application" dictField="id" dictText="subject" defaultVal="${zmlLoanUserContactsPage.applId}" hasLabel="false"  title="借款主题"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">借款主题</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							用户:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="userId" type="list"
									dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="${zmlLoanUserContactsPage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							姓名:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							电话:
						</label>
					</td>
					<td class="value">
					     	 <input id="phone" name="phone" type="text" style="width: 150px" class="inputxt"  datatype="m">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电话</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							职业:
						</label>
					</td>
					<td class="value">
					     	 <input id="profession" name="profession" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">职业</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							工作:
						</label>
					</td>
					<td class="value">
					     	 <input id="work" name="work" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工作</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							关系:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="relation" type="list"
									typeGroupCode="relation" defaultVal="${zmlLoanUserContactsPage.relation}" hasLabel="false"  title="关系"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关系</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							身份证号:
						</label>
					</td>
					<td class="value">
					     	 <input id="idNumber" name="idNumber" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">身份证号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							年龄:
						</label>
					</td>
					<td class="value">
					     	 <input id="age" name="age" type="text" style="width: 150px" class="inputxt"  datatype="3n">
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
									typeGroupCode="sex" defaultVal="${zmlLoanUserContactsPage.sex}" hasLabel="false"  title="性别"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">性别</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							住所:
						</label>
					</td>
					<td class="value">
					     	 <input id="address" name="address" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">住所</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							序号:
						</label>
					</td>
					<td class="value">
					     	 <input id="seqNo" name="seqNo" type="text" style="width: 150px" class="inputxt"  datatype="n">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">序号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							审批标识:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="approvalFlag" type="list"
									typeGroupCode="approval_flag" defaultVal="${zmlLoanUserContactsPage.approvalFlag}" hasLabel="false"  title="审批标识"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批标识</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							审批人:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="approvalUserId" type="list"
									dictTable="t_s_base_user" dictField="id" dictText="realname" defaultVal="${zmlLoanUserContactsPage.approvalUserId}" hasLabel="false"  title="审批人"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批人</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							审批时间:
						</label>
					</td>
					<td class="value">
							   <input id="approvalDate" name="approvalDate" type="text" style="width: 150px" 
					      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批时间</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							审批意见:
						</label>
					</td>
					<td class="value">
					     	 <input id="approvalOpinion" name="approvalOpinion" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批意见</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml_loan/user_contacts/zmlLoanUserContacts.js"></script>		
