<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>审批合同</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
		}
	});
	$(".tabs-wrap").css('width','100%');
  });
 </script>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="zmlLoanContractController.do?doApprove" >
	<input id="id" name="id" type="hidden" value="${zmlLoanContractPage.id }">
	<input id="wfId" name="wfId" type="hidden" value="${wfId }">
	<input id="createDate" name="createDate" type="hidden" value="${zmlLoanContractPage.createDate }">
	<input id="updateDate" name="updateDate" type="hidden" value="${zmlLoanContractPage.updateDate }">
	<input id="version" name="version" type="hidden" value="${zmlLoanContractPage.version }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">用户</label>
			</td>
			<td class="value">
				<t:dictSelect field="userId" type="list"
					dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="${zmlLoanContractPage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">用户${failMsg}</label>
			</td>
			<td align="right">
				<label class="Validform_label">申请:</label>
			</td>
			<td class="value">
				<t:dictSelect field="applId" type="list"
					dictTable="zml_loan_application" dictField="id" dictText="subject" defaultVal="${zmlLoanContractPage.applId}" hasLabel="false"  title="申请"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">申请</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">合同号:</label>
			</td>
			<td class="value">
		     	 <input id="contractNo" name="contractNo" type="text" style="width: 150px" class="inputxt" value='${zmlLoanContractPage.contractNo}' readonly="readonly">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">合同号</label>
			</td>
			<td align="right">
				<label class="Validform_label">产品:</label>
			</td>
			<td class="value">
		     	 <input id="productId" name="productId" type="text" style="width: 150px" class="inputxt" value='${zmlLoanContractPage.productId}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">产品</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">授信额度:</label>
			</td>
			<td class="value">
		     	 <input id="creditAmt" name="creditAmt" type="text" style="width: 150px" class="inputxt" value='${zmlLoanContractPage.creditAmt}' readonly="readonly">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">授信额度</label>
			</td>
			<td align="right">
				<label class="Validform_label">合同金额:</label>
			</td>
			<td class="value">
		     	 <input id="contractAmt" name="contractAmt" type="text" style="width: 150px" class="inputxt" value='${zmlLoanContractPage.contractAmt}' readonly="readonly">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">合同金额</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">服务费:</label>
			</td>
			<td class="value">
		     	 <input id="fee" name="fee" type="text" style="width: 150px" class="inputxt" value='${zmlLoanContractPage.fee}' readonly="readonly">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">服务费</label>
			</td>
			<td align="right">
				<label class="Validform_label">还款方式:</label>
			</td>
			<td class="value">
					<t:dictSelect field="repayment" type="list"
						typeGroupCode="repayment" defaultVal="${zmlLoanContractPage.repayment}" hasLabel="false"  title="还款方式"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">还款方式</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">利率:</label>
			</td>
			<td class="value">
		     	 <input id="interestRate" name="interestRate" type="text" style="width: 150px" class="inputxt" value='${zmlLoanContractPage.interestRate}' readonly="readonly">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">利率</label>
			</td>
			<td align="right">
				<label class="Validform_label">罚息率:</label>
			</td>
			<td class="value">
		     	 <input id="penaltyRate" name="penaltyRate" type="text" style="width: 150px" class="inputxt" value='${zmlLoanContractPage.penaltyRate}' readonly="readonly">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">罚息率</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">期限:</label>
			</td>
			<td class="value">
		     	 <input id="term" name="term" type="text" style="width: 150px" class="inputxt" value='${zmlLoanContractPage.term}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">期限</label>
			</td>
			<td align="right">
				<label class="Validform_label">期限单位:</label>
			</td>
			<td class="value">
					<t:dictSelect field="termUnit" type="list"
						typeGroupCode="periods_unit" defaultVal="${zmlLoanContractPage.termUnit}" hasLabel="false"  title="期限单位"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">期限单位</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">放款日:</label>
			</td>
			<td class="value">
			    <input id="startTime" name="payDay" type="text" style="width: 150px" 
      				class="Wdate" onClick="WdatePicker()"  value='<fmt:formatDate value='${zmlLoanContractPage.payDay}' type="date" pattern="yyyy-MM-dd"/>' datatype="*">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">放款日</label>
			</td>
			<td align="right">
				<label class="Validform_label">放款方式:</label>
			</td>
			<td class="value">
					<t:dictSelect field="paymentType" type="list"
						typeGroupCode="payment_type" defaultVal="${zmlLoanContractPage.paymentType}" hasLabel="false"  title="放款方式"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">放款方式</label>
			</td>
		</tr>
		<c:choose>
			<c:when test="${zmlLoanContractPage.paymentType == '1' }">
				<tr>
				    <td align="right">
						<label class="Validform_label">收款人:<label>
					</td>
					<td class="value">
				     	 <input id="userName" name="userName" type="text" style="width: 150px" class="inputxt" value='${zmlLoanContractPage.userName}' datatype="*" readonly="readonly">
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">收款人</label>
					</td>
				    <td align="right">
						<label class="Validform_label">收款银行:</label>
					</td>
					<td class="value">
				     	 <input id="bankBranch" name="bankBranch" type="text" style="width: 150px" class="inputxt" value='${zmlLoanContractPage.bankBranch}' datatype="*" readonly="readonly">
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">收款银行</label>
					</td>
				</tr>
				<tr>
				    <td align="right">
						<label class="Validform_label">卡号:<label>
					</td>
					<td class="value">
				     	 <input id="accountNumber" name="accountNumber" type="text" style="width: 150px" class="inputxt" value='${zmlLoanContractPage.accountNumber}' datatype="*" readonly="readonly">
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">卡号</label>
					</td>
					<td align="right">
						<label class="Validform_label">交易流水号:<label>
					</td>
					<td class="value">
				     	 <input id="tradeNo" name="tradeNo" type="text" style="width: 150px" class="inputxt" value='${zmlLoanContractPage.tradeNo}' datatype="*">
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">交易流水号</label>
					</td>
				</tr>
			</c:when>
			<c:when test="${zmlLoanContractPage.paymentType == '2' }">
				<tr>
				    <td align="right">
						<label class="Validform_label">微信号:<label>
					</td>
					<td class="value">
				     <input id="accountNumber" name="accountNumber" type="text" style="width: 150px" class="inputxt" value='${zmlLoanContractPage.accountNumber}' datatype="*" readonly="readonly">
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">微信号</label>
					</td>
					<td align="right">
						<label class="Validform_label">交易流水号:<label>
					</td>
					<td class="value">
				     	<input id="tradeNo" name="tradeNo" type="text" style="width: 150px" class="inputxt" value='${zmlLoanContractPage.tradeNo}' datatype="*">
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">交易流水号</label>
					</td>
				</tr>
			</c:when>
			<c:when test="${zmlLoanContractPage.paymentType == '3' }">
				<tr>
				    <td align="right">
						<label class="Validform_label">支付宝账号:<label>
					</td>
					<td class="value">
				     <input id="accountNumber" name="accountNumber" type="text" style="width: 150px" class="inputxt" value='${zmlLoanContractPage.accountNumber}' datatype="*" readonly="readonly">
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">支付宝账号</label>
					</td>
					<td align="right">
						<label class="Validform_label">交易流水号:<label>
					</td>
					<td class="value">
				     	<input id="tradeNo" name="tradeNo" type="text" style="width: 150px" class="inputxt" value='${zmlLoanContractPage.tradeNo}' datatype="*">
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">交易流水号</label>
					</td>
				</tr>
			</c:when>
		</c:choose>
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
		     	 <input id="approvalOpinion" name="approvalOpinion" type="text" style="width: 150px" class="inputxt" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">审批意见</label>
			</td>
		</tr>
		
		</table>
		<div style="width: auto;height: 200px;">
			<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
			<div style="width:800px;height:1px;"></div>
			<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
			 <t:tab href="zmlLoanContractController.do?zmlLoanContractDocumentList&id=${zmlLoanContractPage.id}" icon="icon-search" title="合同文档111" id="zmlLoanContractDocument"></t:tab>
			</t:tabs>
		</div>
		</t:formvalid>
		<!-- 添加 附表明细 模版 -->
		<table style="display:none">
		<tbody id="add_zmlLoanContractDocument_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
				     <input type="hidden" name="zmlLoanContractDocumentList[#index#].userId" value="${zmlLoanContractPage.userId }">
					 <t:dictSelect field="zmlLoanContractDocumentList[#index#].docType" type="list"
								typeGroupCode="contract_doc_type" defaultVal="" hasLabel="false"  title="文档类型"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">文档类型</label>
				  </td>
				  <!-- <td align="left">
					  	<input name="zmlLoanContractDocumentList[#index#].imagePath" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;"
					  		>
					  <label class="Validform_label" style="display: none;">图片路径</label>
				  </td> -->
				  <td align="left">
						<input type="hidden" id="zmlLoanContractDocumentList[#index#].filePath" name="zmlLoanContractDocumentList[#index#].filePath" />
						<a  target="_blank" id="zmlLoanContractDocumentList[#index#].filePath_href">未上传</a>
					   <br>
					   <input class="ui-button" type="button" value="上传附件"
									onclick="commonUpload(commonUploadDefaultCallBack,'zmlLoanContractDocumentList\\[#index#\\]\\.filePath')"/>
					  <label class="Validform_label" style="display: none;">文件路径</label>
				  </td>
				  <td align="left">
					  	<input name="zmlLoanContractDocumentList[#index#].details" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;"
					  		>
					  <label class="Validform_label" style="display: none;">描述</label>
				  </td>
				  <!-- <td align="left">
					  	<input name="zmlLoanContractDocumentList[#index#].seqNo" maxlength="4" 
					  		type="text" class="inputxt"  style="width:120px;"
					  		>
					  <label class="Validform_label" style="display: none;">序号</label>
				  </td> -->
				  <td align="left">
					  	<input name="zmlLoanContractDocumentList[#index#].archivesNo" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"
					  		>
					  <label class="Validform_label" style="display: none;">档案编号</label>
				  </td>
				  <td align="left">
					 <t:dictSelect field="zmlLoanContractDocumentList[#index#].approvalFlag" type="list"
						typeGroupCode="approval_flag" defaultVal="" hasLabel="false"  title="审批结果"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">审批结果</label>
				  </td>
				  <td align="left">
					  	<input name="zmlLoanContractDocumentList[#index#].approvalOpinion" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;"
					  		>
					  <label class="Validform_label" style="display: none;">审批意见</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/zml_loan/contract/zmlLoanContract.js"></script>	
 <script type="text/javascript">
 	//根据放款方式设置 页面输入内容
 	function setPayment(type){
 		
 	}
 </script>
