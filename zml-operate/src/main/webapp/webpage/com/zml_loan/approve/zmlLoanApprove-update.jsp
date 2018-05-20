<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>借款审批结果</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlLoanApproveController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlLoanApprovePage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlLoanApprovePage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlLoanApprovePage.updateDate }">
					<input id="version" name="version" type="hidden" value="${zmlLoanApprovePage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="userId" type="list"
										dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="${zmlLoanApprovePage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
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
										dictTable="zml_loan_application" dictField="id" dictText="subject" defaultVal="${zmlLoanApprovePage.applId}" hasLabel="false"  title="申请"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申请</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								额度:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="creditId" type="list"
										dictTable="zml_loan_risk_credit" dictField="id" dictText="credit_subjet" defaultVal="${zmlLoanApprovePage.creditId}" hasLabel="false"  title="额度"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">额度</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								授信额度:
							</label>
						</td>
						<td class="value">
						     	 <input id="creditAmount" name="creditAmount" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanApprovePage.creditAmount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">授信额度</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								服务费:
							</label>
						</td>
						<td class="value">
						     	 <input id="fee" name="fee" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanApprovePage.fee}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">服务费</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								批准金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="approveAmount" name="approveAmount" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanApprovePage.approveAmount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">批准金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								批准利率:
							</label>
						</td>
						<td class="value">
						     	 <input id="interestRate" name="interestRate" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanApprovePage.interestRate}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">批准利率</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								批准期限:
							</label>
						</td>
						<td class="value">
						     	 <input id="term" name="term" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanApprovePage.term}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">批准期限</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								批准期限单位:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="termUnit" type="list"
										typeGroupCode="periods_unit" defaultVal="${zmlLoanApprovePage.termUnit}" hasLabel="false"  title="批准期限单位"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">批准期限单位</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								建议审批额度:
							</label>
						</td>
						<td class="value">
						     	 <input id="argeeAmount" name="argeeAmount" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanApprovePage.argeeAmount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">建议审批额度</label>
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
										typeGroupCode="approval_flag" defaultVal="${zmlLoanApprovePage.approvalFlag}" hasLabel="false"  title="审批标识"></t:dictSelect>     
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
										dictTable="t_s_base_user" dictField="id" dictText="realname" defaultVal="${zmlLoanApprovePage.approvalUserId}" hasLabel="false"  title="审批人"></t:dictSelect>     
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
									  <input id="approvalDate" name="approvalDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value='<fmt:formatDate value='${zmlLoanApprovePage.approvalDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批时间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								审批意见:
							</label>
						</td>
						<td class="value">
						     	 <input id="approvalOpinion" name="approvalOpinion" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanApprovePage.approvalOpinion}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批意见</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml_loan/approve/zmlLoanApprove.js"></script>		
