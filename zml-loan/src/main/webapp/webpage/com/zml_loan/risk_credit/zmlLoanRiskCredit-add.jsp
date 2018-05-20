<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>借款风控授信</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlLoanRiskCreditController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlLoanRiskCreditPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlLoanRiskCreditPage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlLoanRiskCreditPage.updateDate }">
					<input id="version" name="version" type="hidden" value="${zmlLoanRiskCreditPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="userId" type="list"
									dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="${zmlLoanRiskCreditPage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							申请:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="applId" type="list"
									dictTable="zml_loan_application" dictField="id" dictText="subject" defaultVal="${zmlLoanRiskCreditPage.applId}" hasLabel="false"  title="申请"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申请</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							授信主题:
						</label>
					</td>
					<td class="value">
					     	 <input id="creditSubjet" name="creditSubjet" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">授信主题</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							评估开始时间:
						</label>
					</td>
					<td class="value">
							   <input id="assessStartTime" name="assessStartTime" type="text" style="width: 150px" 
					      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评估开始时间</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							评估结束时间:
						</label>
					</td>
					<td class="value">
							   <input id="assessEndTime" name="assessEndTime" type="text" style="width: 150px" 
					      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评估结束时间</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							评估金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="assessAmt" name="assessAmt" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评估金额</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="status" type="list"
									typeGroupCode="credit_sts" defaultVal="${zmlLoanRiskCreditPage.status}" hasLabel="false"  title="状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
						  	 <textarea style="width:600px;" class="inputxt" rows="6" id="remarks" name="remarks"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							评估标识:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="flag" type="list"
									typeGroupCode="" defaultVal="${zmlLoanRiskCreditPage.flag}" hasLabel="false"  title="评估标识"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评估标识</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							审批标识:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="approvalFlag" type="list"
									typeGroupCode="approval_flag" defaultVal="${zmlLoanRiskCreditPage.approvalFlag}" hasLabel="false"  title="审批标识"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批标识</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							审批人:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="approvalUserId" type="list"
									dictTable="t_s_base_user" dictField="id" dictText="realname" defaultVal="${zmlLoanRiskCreditPage.approvalUserId}" hasLabel="false"  title="审批人"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批人</label>
						</td>
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
					</tr>
				<tr>
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
  <script src = "webpage/com/zml_loan/risk_credit/zmlLoanRiskCredit.js"></script>		
