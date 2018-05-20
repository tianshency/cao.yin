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
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlLoanWfTaskController.do?doApprove" tiptype="1" >
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
						审核额度:
					</label>
				</td>
				<td class="value">
				     	 <input id="approvalAmt" name="approvalAmt" type="text" style="width: 150px" class="inputxt"  datatype="n" value=''>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">审核额度</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						利率:
					</label>
				</td>
				<td class="value">
				     	 <input id="approvalInterestRate" name="approvalInterestRate" type="text" style="width: 150px" class="inputxt"  datatype="n" value=''>
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
				     	 <input id="approvalTerm" name="approvalTerm" type="text" style="width: 150px" class="inputxt"  value=''>
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
								typeGroupCode="periods_unit" defaultVal="" hasLabel="false"  title="审批期限单位"></t:dictSelect>     
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">审批期限单位</label>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						还款方式:
					</label>
				</td>
				<td class="value">
					<t:dictSelect field="repayment" type="list"
								typeGroupCode="repayment" defaultVal="" hasLabel="false"  title="还款方式"></t:dictSelect>     
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">还款方式</label>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						审批结果:
					</label>
				</td>
				<td class="value">
							<t:dictSelect field="approvalFlag" type="list"
								typeGroupCode="approval_flag" defaultVal="" hasLabel="false"  title="审批结果"></t:dictSelect>     
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">审批结果</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						审批意见:
					</label>
				</td>
				<td class="value">
				  	<textarea id="approvalOpinion" style="width:600px;" class="inputxt" rows="6" name="approvalOpinion"></textarea>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">审批意见</label>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="4">
					<label class="Validform_label">
						<input type="submit" value="提交审批">
					</label>
				</td>
			</tr>
	</table>
   </t:formvalid>
 </body>
  <script src = "webpage/com/zml_loan/wf_task/zmlLoanWfTask.js"></script>		
