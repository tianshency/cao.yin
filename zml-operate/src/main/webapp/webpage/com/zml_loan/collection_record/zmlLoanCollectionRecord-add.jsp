<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>催收记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlLoanCollectionRecordController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlLoanCollectionRecordPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlLoanCollectionRecordPage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlLoanCollectionRecordPage.updateDate }">
					<input id="version" name="version" type="hidden" value="${zmlLoanCollectionRecordPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							合同:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="contractId" type="list"
									dictTable="zml_loan_contract" dictField="id" dictText="contract_no" defaultVal="${zmlLoanCollectionRecordPage.contractId}" hasLabel="false"  title="合同"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同</label>
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
									dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="${zmlLoanCollectionRecordPage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							催收人:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="operatorId" type="list"
									dictTable="t_s_base_user" dictField="id" dictText="realname" defaultVal="${zmlLoanCollectionRecordPage.operatorId}" hasLabel="false"  title="催收人"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">催收人</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							催收时间:
						</label>
					</td>
					<td class="value">
							   <input id="collectionDate" name="collectionDate" type="text" style="width: 150px" 
					      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">催收时间</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							催收方式:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="collectionWay" type="list"
									typeGroupCode="collection_way" defaultVal="${zmlLoanCollectionRecordPage.collectionWay}" hasLabel="false"  title="催收方式"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">催收方式</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
						  	 <textarea style="width:600px;" class="inputxt" rows="6" id="remark" name="remark"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml_loan/collection_record/zmlLoanCollectionRecord.js"></script>		
