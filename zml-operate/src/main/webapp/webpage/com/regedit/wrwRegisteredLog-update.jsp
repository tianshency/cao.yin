<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>注册日志</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wrwRegisteredLogController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${wrwRegisteredLogPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${wrwRegisteredLogPage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${wrwRegisteredLogPage.updateDate }">
					<input id="version" name="version" type="hidden" value="${wrwRegisteredLogPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								手机号:
							</label>
						</td>
						<td class="value">
						     	 <input id="phone" name="phone" type="text" style="width: 150px" class="inputxt"  value='${wrwRegisteredLogPage.phone}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">手机号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								任务:
							</label>
						</td>
						<td class="value">
						     	 <input id="taskId" name="taskId" type="text" style="width: 150px" class="inputxt"  value='${wrwRegisteredLogPage.taskId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">任务</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								来源:
							</label>
						</td>
						<td class="value">
						     	 <input id="source" name="source" type="text" style="width: 150px" class="inputxt"  value='${wrwRegisteredLogPage.source}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">来源</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								状态:
							</label>
						</td>
						<td class="value">
						     	 <input id="satus" name="satus" type="text" style="width: 150px" class="inputxt"  value='${wrwRegisteredLogPage.satus}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								请求信息:
							</label>
						</td>
						<td class="value">
						     	 <input id="requestInfo" name="requestInfo" type="text" style="width: 150px" class="inputxt"  value='${wrwRegisteredLogPage.requestInfo}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">请求信息</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						     	 <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  value='${wrwRegisteredLogPage.remark}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml_loan/log/wrwRegisteredLog.js"></script>		
