<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>逾期明细</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlLoanOverdueDetailController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlLoanOverdueDetailPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlLoanOverdueDetailPage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlLoanOverdueDetailPage.updateDate }">
					<input id="version" name="version" type="hidden" value="${zmlLoanOverdueDetailPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								合同:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="contractId" type="list"
										dictTable="zml_loan_contract" dictField="id" dictText="contract_no" defaultVal="${zmlLoanOverdueDetailPage.contractId}" hasLabel="false"  title="合同"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="userId" type="list"
										dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="${zmlLoanOverdueDetailPage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								还款计划:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="repayPlanDetailId" type="list"
										dictTable="zml_loan_repay_plan_detail" dictField="id" dictText="profit_period" defaultVal="${zmlLoanOverdueDetailPage.repayPlanDetailId}" hasLabel="false"  title="还款计划"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">还款计划</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								逾期开始时间:
							</label>
						</td>
						<td class="value">
									  <input id="overStartDate" name="overStartDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${zmlLoanOverdueDetailPage.overStartDate}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">逾期开始时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								应还本金:
							</label>
						</td>
						<td class="value">
						     	 <input id="principal" name="principal" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanOverdueDetailPage.principal}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">应还本金</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								应还利息:
							</label>
						</td>
						<td class="value">
						     	 <input id="interest" name="interest" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanOverdueDetailPage.interest}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">应还利息</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								应还罚息:
							</label>
						</td>
						<td class="value">
						     	 <input id="imposeInterest" name="imposeInterest" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanOverdueDetailPage.imposeInterest}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">应还罚息</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								滞纳金:
							</label>
						</td>
						<td class="value">
						     	 <input id="penalty" name="penalty" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanOverdueDetailPage.penalty}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">滞纳金</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								期次:
							</label>
						</td>
						<td class="value">
						     	 <input id="period" name="period" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanOverdueDetailPage.period}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">期次</label>
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
  <script src = "webpage/com/zml_loan/overdue_detail/zmlLoanOverdueDetail.js"></script>		
