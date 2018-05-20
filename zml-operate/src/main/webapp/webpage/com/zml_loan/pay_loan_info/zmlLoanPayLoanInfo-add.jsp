<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>放款信息</title>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="zmlLoanPayLoanInfoController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${zmlLoanPayLoanInfoPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlLoanPayLoanInfoPage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlLoanPayLoanInfoPage.updateDate }">
					<input id="version" name="version" type="hidden" value="${zmlLoanPayLoanInfoPage.version }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">合同:</label>
			</td>
			<td class="value">
					<t:dictSelect field="contractId" type="list"
						dictTable="zml_loan_contract" dictField="id" dictText="contract_no"  hasLabel="false"  title="合同"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">合同</label>
			</td>
			<td align="right">
				<label class="Validform_label">申请:</label>
			</td>
			<td class="value">
					<t:dictSelect field="applId" type="list"
						dictTable="zml_loan_application" dictField="id" dictText="subject"  hasLabel="false"  title="申请"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">申请</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">用户:</label>
			</td>
			<td class="value">
					<t:dictSelect field="userId" type="list"
						dictTable="zml_user" dictField="id" dictText="real_name"  hasLabel="false"  title="用户"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">用户</label>
			</td>
			<td align="right">
				<label class="Validform_label">放款编号:</label>
			</td>
			<td class="value">
		     	 <input id="payLoanNum" name="payLoanNum" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">放款编号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">产品:</label>
			</td>
			<td class="value">
		     	 <input id="productId" name="productId" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">产品</label>
			</td>
			<td align="right">
				<label class="Validform_label">操作人ID:</label>
			</td>
			<td class="value">
					<t:dictSelect field="operatorId" type="list"
						dictTable="t_s_base_user" dictField="id" dictText="realname"  hasLabel="false"  title="操作人ID"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">操作人ID</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">币种:</label>
			</td>
			<td class="value">
					<t:dictSelect field="currency" type="list"
						typeGroupCode="currency"  hasLabel="false"  title="币种"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">币种</label>
			</td>
			<td align="right">
				<label class="Validform_label">放款日:</label>
			</td>
			<td class="value">
					  <input id="payDate" name="payDate" type="text" style="width: 150px" 
							 class="Wdate" onClick="WdatePicker()" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">放款日</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">到期日:</label>
			</td>
			<td class="value">
					  <input id="arriveDate" name="arriveDate" type="text" style="width: 150px" 
							 class="Wdate" onClick="WdatePicker()" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">到期日</label>
			</td>
			<td align="right">
				<label class="Validform_label">利率:</label>
			</td>
			<td class="value">
		     	 <input id="contractRate" name="contractRate" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">利率</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">逾期利率:</label>
			</td>
			<td class="value">
		     	 <input id="overduerate" name="overduerate" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">逾期利率</label>
			</td>
			<td align="right">
				<label class="Validform_label">合同金额:</label>
			</td>
			<td class="value">
		     	 <input id="contractAmt" name="contractAmt" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">合同金额</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">服务费:</label>
			</td>
			<td class="value">
		     	 <input id="fee" name="fee" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">服务费</label>
			</td>
			<td align="right">
				<label class="Validform_label">放款金额:</label>
			</td>
			<td class="value">
		     	 <input id="payAmt" name="payAmt" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">放款金额</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">状态:</label>
			</td>
			<td class="value">
					<t:dictSelect field="status" type="list"
						typeGroupCode="pay_sts"  hasLabel="false"  title="状态"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">状态</label>
			</td>
			<td align="right">
				<label class="Validform_label">放款序号:</label>
			</td>
			<td class="value">
		     	 <input id="payLoanIndex" name="payLoanIndex" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">放款序号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">是否上传借据:</label>
			</td>
			<td class="value">
					<t:dictSelect field="isUpload" type="list"
						typeGroupCode="sf_yn"  hasLabel="false"  title="是否上传借据"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否上传借据</label>
			</td>
			<td align="right">
				<label class="Validform_label">额外费用:</label>
			</td>
			<td class="value">
		     	 <input id="loanPremium" name="loanPremium" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">额外费用</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">付款类型:</label>
			</td>
			<td class="value">
					<t:dictSelect field="paymentType" type="list"
						typeGroupCode="payment_type"  hasLabel="false"  title="付款类型"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">付款类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">银行名称:</label>
			</td>
			<td class="value">
		     	 <input id="bankName" name="bankName" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">银行名称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">银行省份:</label>
			</td>
			<td class="value">
		     	 <input id="bankAddressProvince" name="bankAddressProvince" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">银行省份</label>
			</td>
			<td align="right">
				<label class="Validform_label">银行城市:</label>
			</td>
			<td class="value">
		     	 <input id="bankAddressCity" name="bankAddressCity" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">银行城市</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">银行:</label>
			</td>
			<td class="value">
		     	 <input id="bankBranch" name="bankBranch" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">银行</label>
			</td>
			<td align="right">
				<label class="Validform_label">账号:</label>
			</td>
			<td class="value">
		     	 <input id="accountNumber" name="accountNumber" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">账号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">交易流水号:</label>
			</td>
			<td class="value">
		     	 <input id="tradeNo" name="tradeNo" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">交易流水号</label>
			</td>
			<td align="right">
				<label class="Validform_label">备注:</label>
			</td>
			<td class="value">
		     	 <input id="remarks" name="remarks" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>
	</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="zmlLoanPayLoanInfoController.do?zmlLoanPayDocumentList&id=${zmlLoanPayLoanInfoPage.id}" icon="icon-search" title="放款文档" id="zmlLoanPayDocument"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
	<table style="display:none">
	<tbody id="add_zmlLoanPayDocument_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
							<t:dictSelect field="zmlLoanPayDocumentList[#index#].userId" type="list"
										dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="" hasLabel="false"  title="用户"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">用户</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlLoanPayDocumentList[#index#].docType" type="list"
										typeGroupCode="pay_doc_type" defaultVal="" hasLabel="false"  title="文档类型"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">文档类型</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlLoanPayDocumentList[#index#].fileFlag" type="list"
										typeGroupCode="file_flag" defaultVal="" hasLabel="false"  title="标识"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">标识</label>
				  </td>
				  <td align="left">
					  	<input name="zmlLoanPayDocumentList[#index#].imagePath" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">图片路径</label>
				  </td>
				  <td align="left">
										<input type="hidden" id="zmlLoanPayDocumentList[#index#].filePath" name="zmlLoanPayDocumentList[#index#].filePath" />
										<a  target="_blank" id="zmlLoanPayDocumentList[#index#].filePath_href">未上传</a>
									  <br>
									   <input class="ui-button" type="button" value="上传附件"
													onclick="commonUpload(commonUploadDefaultCallBack,'zmlLoanPayDocumentList\\[#index#\\]\\.filePath')"/>
					  <label class="Validform_label" style="display: none;">文件路径</label>
				  </td>
				  <td align="left">
					  	<input name="zmlLoanPayDocumentList[#index#].details" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">描述</label>
				  </td>
				  <td align="left">
					  	<input name="zmlLoanPayDocumentList[#index#].seqNo" maxlength="4" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">序号</label>
				  </td>
				  <td align="left">
					  	<input name="zmlLoanPayDocumentList[#index#].archivesNo" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">档案编号</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlLoanPayDocumentList[#index#].approvalFlag" type="list"
										typeGroupCode="approval_flag" defaultVal="" hasLabel="false"  title="审批标识"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">审批标识</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlLoanPayDocumentList[#index#].approvalUserId" type="list"
										dictTable="t_s_base_user" dictField="id" dictText="realname" defaultVal="" hasLabel="false"  title="审批人"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">审批人</label>
				  </td>
				  <td align="left">
					      	<input name="zmlLoanPayDocumentList[#index#].approvalDate" maxlength="20" 
						  		type="text"  class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width:120px;"
						  		 >
					  <label class="Validform_label" style="display: none;">审批时间</label>
				  </td>
				  <td align="left">
					  	<input name="zmlLoanPayDocumentList[#index#].approvalOpinion" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">审批意见</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/zml_loan/pay_loan_info/zmlLoanPayLoanInfo.js"></script>
	