<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>贷款产品</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlLoanProductController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlLoanProductPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlLoanProductPage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlLoanProductPage.updateDate }">
					<input id="wfId" name="wfId" type="hidden" value="${zmlLoanProductPage.wfId }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="proName" name="proName" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">产品名称</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							期限:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="periods" type="list"
									typeGroupCode="periods_sel" defaultVal="${zmlLoanProductPage.periods}" hasLabel="false"  title="期限"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">期限</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							期限单位:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="periodsUnit" type="list"
									typeGroupCode="periods_unit" defaultVal="${zmlLoanProductPage.periodsUnit}" hasLabel="false"  title="期限单位"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">期限单位</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							利率:
						</label>
					</td>
					<td class="value">
					     	 <input id="interestRate" name="interestRate" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">利率</label>
						</td>
						</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							罚息率:
						</label>
					</td>
					<td class="value">
					     	 <input id="penaltyRate" name="penaltyRate" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">罚息率</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							最小金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="minAmount" name="minAmount" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">最小金额</label>
						</td>
						</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							最大金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="maxAmount" name="maxAmount" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">最大金额</label>
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
									typeGroupCode="repayment" defaultVal="${zmlLoanProductPage.repayment}" hasLabel="false"  title="还款方式"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">还款方式</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value">
					     	 <input id="details" name="details" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
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
									typeGroupCode="is_use" defaultVal="${zmlLoanProductPage.status}" hasLabel="false"  title="状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml_loan/product/zmlLoanProduct.js"></script>		
