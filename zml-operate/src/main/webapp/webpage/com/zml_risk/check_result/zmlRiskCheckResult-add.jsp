<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>风控检查结果</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlRiskCheckResultController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlRiskCheckResultPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlRiskCheckResultPage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlRiskCheckResultPage.updateDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							申请:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="applyId" type="list"
									dictTable="zml_loan_application" dictField="id" dictText="subject" defaultVal="${zmlRiskCheckResultPage.applyId}" hasLabel="false"  title="申请"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申请</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="userId" type="list"
									dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="${zmlRiskCheckResultPage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							模型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="modeId" type="list"
									dictTable="zml_risk_mode_config" dictField="id" dictText="name" defaultVal="${zmlRiskCheckResultPage.modeId}" hasLabel="false"  title="模型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">模型</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							结果:
						</label>
					</td>
					<td class="value">
					     	 <input id="result" name="result" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">结果</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
						  	 <textarea style="width:600px;" class="inputxt" rows="6" id="remarkes" name="remarkes"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml_loan/check_result/zmlRiskCheckResult.js"></script>		
