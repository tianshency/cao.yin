<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>还款计划明细</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlLoanRepayPlanDetailController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlLoanRepayPlanDetailPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlLoanRepayPlanDetailPage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlLoanRepayPlanDetailPage.updateDate }">
					<input id="version" name="version" type="hidden" value="${zmlLoanRepayPlanDetailPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							合同:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="contractId" type="list"
									dictTable="zml_loan_contract" dictField="id" dictText="contract_no" defaultVal="${zmlLoanRepayPlanDetailPage.contractId}" hasLabel="false"  title="合同"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							用户:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="userId" type="list"
									dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="${zmlLoanRepayPlanDetailPage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							当期期数:
						</label>
					</td>
					<td class="value">
					     	 <input id="profitPeriod" name="profitPeriod" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当期期数</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							当期开始日:
						</label>
					</td>
					<td class="value">
							   <input id="profitStartDate" name="profitStartDate" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当期开始日</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							当期结束日:
						</label>
					</td>
					<td class="value">
							   <input id="profitEndDate" name="profitEndDate" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当期结束日</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							当期应还本金:
						</label>
					</td>
					<td class="value">
					     	 <input id="profitPrincipal" name="profitPrincipal" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当期应还本金</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							当期应还利息:
						</label>
					</td>
					<td class="value">
					     	 <input id="profitInterest" name="profitInterest" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当期应还利息</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							当期应还罚息:
						</label>
					</td>
					<td class="value">
					     	 <input id="profitPenalty" name="profitPenalty" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当期应还罚息</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							当期应还滞纳金:
						</label>
					</td>
					<td class="value">
					     	 <input id="profitNonpayment" name="profitNonpayment" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当期应还滞纳金</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							当期应还其他费用:
						</label>
					</td>
					<td class="value">
					     	 <input id="profitOtherFee" name="profitOtherFee" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当期应还其他费用</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							当期应还合计:
						</label>
					</td>
					<td class="value">
					     	 <input id="profitRepaySum" name="profitRepaySum" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当期应还合计</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							当期实还本金:
						</label>
					</td>
					<td class="value">
					     	 <input id="repayPrincipal" name="repayPrincipal" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当期实还本金</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							当期实还利息:
						</label>
					</td>
					<td class="value">
					     	 <input id="repayInterest" name="repayInterest" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当期实还利息</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							当期实还罚息:
						</label>
					</td>
					<td class="value">
					     	 <input id="repayPenalty" name="repayPenalty" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当期实还罚息</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							当期实还滞纳金:
						</label>
					</td>
					<td class="value">
					     	 <input id="repayNonpayment" name="repayNonpayment" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当期实还滞纳金</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							当期实还其他费用:
						</label>
					</td>
					<td class="value">
					     	 <input id="repayOtherFee" name="repayOtherFee" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当期实还其他费用</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							当期实还总额:
						</label>
					</td>
					<td class="value">
					     	 <input id="repaySum" name="repaySum" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当期实还总额</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							最后还款日:
						</label>
					</td>
					<td class="value">
							   <input id="lastRepayDate" name="lastRepayDate" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">最后还款日</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							当期合同余额:
						</label>
					</td>
					<td class="value">
					     	 <input id="endCurrentPrincipalbalance" name="endCurrentPrincipalbalance" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">当期合同余额</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							利率:
						</label>
					</td>
					<td class="value">
					     	 <input id="rate" name="rate" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">利率</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							逾期利率:
						</label>
					</td>
					<td class="value">
					     	 <input id="overdueRate" name="overdueRate" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">逾期利率</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							逾期天数:
						</label>
					</td>
					<td class="value">
					     	 <input id="overdueDays" name="overdueDays" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">逾期天数</label>
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
									typeGroupCode="repay_plan_sts" defaultVal="${zmlLoanRepayPlanDetailPage.status}" hasLabel="false"  title="状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							调整日期:
						</label>
					</td>
					<td class="value">
							   <input id="adjustdate" name="adjustdate" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">调整日期</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							最近一次还款时间:
						</label>
					</td>
					<td class="value">
							   <input id="recentlyRepayTime" name="recentlyRepayTime" type="text" style="width: 150px" 
					      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">最近一次还款时间</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
					     	 <input id="remarks" name="remarks" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml_loan/repay_plan_detail/zmlLoanRepayPlanDetail.js"></script>		
