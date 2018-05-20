<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>贷款工作流任务</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlLoanWfTaskController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlLoanWfTaskPage.id }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${zmlLoanWfTaskPage.bpmStatus }">
					<input id="createName" name="createName" type="hidden" value="${zmlLoanWfTaskPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlLoanWfTaskPage.createBy }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlLoanWfTaskPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlLoanWfTaskPage.updateBy }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlLoanWfTaskPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlLoanWfTaskPage.sysCompanyCode }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlLoanWfTaskPage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlLoanWfTaskPage.updateDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="userId" type="list"
										dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="${zmlLoanWfTaskPage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="bizId" type="list"
										dictTable="zml_loan_application" dictField="id" dictText="subject" defaultVal="${zmlLoanWfTaskPage.bizId}" hasLabel="false"  title="业务"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								任务类型:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="taskType" type="list"
										typeGroupCode="loan_task_type" defaultVal="${zmlLoanWfTaskPage.taskType}" hasLabel="false"  title="任务类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">任务类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								任务主题:
							</label>
						</td>
						<td class="value">
						     	 <input id="taskSubject" name="taskSubject" type="text" style="width: 150px" class="inputxt"  datatype="*" value='${zmlLoanWfTaskPage.taskSubject}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">任务主题</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								产品编码:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="productId" type="list"
										typeGroupCode="" defaultVal="${zmlLoanWfTaskPage.productId}" hasLabel="false"  title="产品编码"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">产品编码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								来源:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="taskSource" type="list"
										typeGroupCode="task_source" defaultVal="${zmlLoanWfTaskPage.taskSource}" hasLabel="false"  title="来源"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">来源</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								审核额度:
							</label>
						</td>
						<td class="value">
						     	 <input id="approvalAmt" name="approvalAmt" type="text" style="width: 150px" class="inputxt"  datatype="n" value='${zmlLoanWfTaskPage.approvalAmt}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审核额度</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								利率:
							</label>
						</td>
						<td class="value">
						     	 <input id="approvalInterestRate" name="approvalInterestRate" type="text" style="width: 150px" class="inputxt"  datatype="n" value='${zmlLoanWfTaskPage.approvalInterestRate}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">利率</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								审批期限:
							</label>
						</td>
						<td class="value">
						     	 <input id="approvalTerm" name="approvalTerm" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanWfTaskPage.approvalTerm}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批期限</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								审批期限单位:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="approvalTermUnit" type="list"
										typeGroupCode="approval_term_unit" defaultVal="${zmlLoanWfTaskPage.approvalTermUnit}" hasLabel="false"  title="审批期限单位"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批期限单位</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								任务开始时间:
							</label>
						</td>
						<td class="value">
									  <input id="startTime" name="startTime" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value='<fmt:formatDate value='${zmlLoanWfTaskPage.startTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">任务开始时间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								任务完成时间:
							</label>
						</td>
						<td class="value">
									  <input id="endTime" name="endTime" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value='<fmt:formatDate value='${zmlLoanWfTaskPage.endTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">任务完成时间</label>
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
										typeGroupCode="approval_flag" defaultVal="${zmlLoanWfTaskPage.approvalFlag}" hasLabel="false"  title="审批标识"></t:dictSelect>     
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
										dictTable="t_s_base_user" dictField="id" dictText="realname" defaultVal="${zmlLoanWfTaskPage.approvalUserId}" hasLabel="false"  title="审批人"></t:dictSelect>     
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
						     	 <input id="approvalDate" name="approvalDate" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanWfTaskPage.approvalDate}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批时间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								审批意见:
							</label>
						</td>
						<td class="value">
						  	 	<textarea id="approvalOpinion" style="width:600px;" class="inputxt" rows="6" name="approvalOpinion">${zmlLoanWfTaskPage.approvalOpinion}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批意见</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否通知审批人:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="isNotic" type="list"
										typeGroupCode="sf_sy" defaultVal="${zmlLoanWfTaskPage.isNotic}" hasLabel="false"  title="是否通知审批人"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否通知审批人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								通知内容:
							</label>
						</td>
						<td class="value">
						  	 	<textarea id="noticContent" style="width:600px;" class="inputxt" rows="6" name="noticContent">${zmlLoanWfTaskPage.noticContent}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">通知内容</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								分配人:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="fromUserId" type="list"
										dictTable="t_s_base_user" dictField="id" dictText="realname" defaultVal="${zmlLoanWfTaskPage.fromUserId}" hasLabel="false"  title="分配人"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">分配人</label>
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
  <script src = "webpage/com/zml_loan/wf_task/zmlLoanWfTask.js"></script>		
