<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>借款申请表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlLoanApplicationController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlLoanApplicationPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlLoanApplicationPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlLoanApplicationPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlLoanApplicationPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlLoanApplicationPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlLoanApplicationPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlLoanApplicationPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlLoanApplicationPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlLoanApplicationPage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${zmlLoanApplicationPage.bpmStatus }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="userId" type="list"
										dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="${zmlLoanApplicationPage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								申请编号:
							</label>
						</td>
						<td class="value">
						     	 <input id="applyNo" name="applyNo" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanApplicationPage.applyNo}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申请编号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="loanType" name="loanType" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanApplicationPage.loanType}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								申请人姓名:
							</label>
						</td>
						<td class="value">
						     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanApplicationPage.name}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申请人姓名</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								职业:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="profession" type="list"
										typeGroupCode="profession" defaultVal="${zmlLoanApplicationPage.profession}" hasLabel="false"  title="职业"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">职业</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								申请人电话:
							</label>
						</td>
						<td class="value">
						     	 <input id="phone" name="phone" type="text" style="width: 150px" class="inputxt"  datatype="m" value='${zmlLoanApplicationPage.phone}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申请人电话</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								借款理由:
							</label>
						</td>
						<td class="value">
						     	 <input id="loanReason" name="loanReason" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanApplicationPage.loanReason}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">借款理由</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								借款金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="amount" name="amount" type="text" style="width: 150px" class="inputxt"  datatype="n" value='${zmlLoanApplicationPage.amount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">借款金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								借款期限:
							</label>
						</td>
						<td class="value">
						     	 <input id="periods" name="periods" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanApplicationPage.periods}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">借款期限</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								借款期限单位:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="periodsUnit" type="list"
										typeGroupCode="periods_unit" defaultVal="${zmlLoanApplicationPage.periodsUnit}" hasLabel="false"  title="借款期限单位"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">借款期限单位</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								利率:
							</label>
						</td>
						<td class="value">
						     	 <input id="interestRate" name="interestRate" type="text" style="width: 150px" class="inputxt"  datatype="n" value='${zmlLoanApplicationPage.interestRate}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">利率</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								罚息率:
							</label>
						</td>
						<td class="value">
						     	 <input id="penaltyRate" name="penaltyRate" type="text" style="width: 150px" class="inputxt"  datatype="n" value='${zmlLoanApplicationPage.penaltyRate}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">罚息率</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								状态:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="applySts" type="list"
										typeGroupCode="apply_sts" defaultVal="${zmlLoanApplicationPage.applySts}" hasLabel="false"  title="状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								主题:
							</label>
						</td>
						<td class="value">
						     	 <input id="subject" name="subject" type="text" style="width: 150px" class="inputxt"  value='${zmlLoanApplicationPage.subject}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">主题</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml_loan/application/zmlLoanApplication.js"></script>		
