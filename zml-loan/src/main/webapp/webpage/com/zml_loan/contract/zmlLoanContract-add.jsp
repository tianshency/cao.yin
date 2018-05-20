<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>借款合同</title>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="zmlLoanContractController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${zmlLoanContractPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlLoanContractPage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlLoanContractPage.updateDate }">
					<input id="version" name="version" type="hidden" value="${zmlLoanContractPage.version }">
	<table cellpadding="0" cellspacing="1" class="formtable">
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
				<label class="Validform_label">合同号:</label>
			</td>
			<td class="value">
		     	 <input id="contractNo" name="contractNo" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">合同号</label>
			</td>
			<td align="right">
				<label class="Validform_label">产品:</label>
			</td>
			<td class="value">
		     	 <input id="productId" name="productId" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">产品</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">授信额度:</label>
			</td>
			<td class="value">
		     	 <input id="creditAmt" name="creditAmt" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">授信额度</label>
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
				<label class="Validform_label">合同余额:</label>
			</td>
			<td class="value">
		     	 <input id="contractBalance" name="contractBalance" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">合同余额</label>
			</td>
			<td align="right">
				<label class="Validform_label">服务费:</label>
			</td>
			<td class="value">
		     	 <input id="fee" name="fee" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">服务费</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">中间方:</label>
			</td>
			<td class="value">
		     	 <input id="intermediaries" name="intermediaries" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">中间方</label>
			</td>
			<td align="right">
				<label class="Validform_label">出借人:</label>
			</td>
			<td class="value">
		     	 <input id="lender" name="lender" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">出借人</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">利率:</label>
			</td>
			<td class="value">
		     	 <input id="interestRate" name="interestRate" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">利率</label>
			</td>
			<td align="right">
				<label class="Validform_label">罚息率:</label>
			</td>
			<td class="value">
		     	 <input id="penaltyRate" name="penaltyRate" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">罚息率</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">合同路径:</label>
			</td>
			<td class="value">
		     	 <input id="contractPath" name="contractPath" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">合同路径</label>
			</td>
			<td align="right">
				<label class="Validform_label">还款方式:</label>
			</td>
			<td class="value">
					<t:dictSelect field="repayment" type="list"
						typeGroupCode="repayment"  hasLabel="false"  title="还款方式"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">还款方式</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">合同开始日:</label>
			</td>
			<td class="value">
					  <input id="startTime" name="startTime" type="text" style="width: 150px" 
							 class="Wdate" onClick="WdatePicker()" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">合同开始日</label>
			</td>
			<td align="right">
				<label class="Validform_label">合同到期日:</label>
			</td>
			<td class="value">
					  <input id="endTime" name="endTime" type="text" style="width: 150px" 
							 class="Wdate" onClick="WdatePicker()" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">合同到期日</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">期限:</label>
			</td>
			<td class="value">
		     	 <input id="term" name="term" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">期限</label>
			</td>
			<td align="right">
				<label class="Validform_label">期限单位:</label>
			</td>
			<td class="value">
					<t:dictSelect field="termUnit" type="list"
						typeGroupCode="periods_unit"  hasLabel="false"  title="期限单位"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">期限单位</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">逾期次数:</label>
			</td>
			<td class="value">
		     	 <input id="overTime" name="overTime" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">逾期次数</label>
			</td>
			<td align="right">
				<label class="Validform_label">逾期天数:</label>
			</td>
			<td class="value">
		     	 <input id="overDays" name="overDays" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">逾期天数</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">罚息:</label>
			</td>
			<td class="value">
		     	 <input id="imposeInterest" name="imposeInterest" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">罚息</label>
			</td>
			<td align="right">
				<label class="Validform_label">滞纳金:</label>
			</td>
			<td class="value">
		     	 <input id="penalty" name="penalty" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">滞纳金</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">状态:</label>
			</td>
			<td class="value">
					<t:dictSelect field="status" type="list"
						typeGroupCode="contract_sts"  hasLabel="false"  title="状态"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">状态</label>
			</td>
			<td align="right">
				<label class="Validform_label">还款状态:</label>
			</td>
			<td class="value">
					<t:dictSelect field="repayStatus" type="list"
						typeGroupCode=""  hasLabel="false"  title="还款状态"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">还款状态</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">借款次数:</label>
			</td>
			<td class="value">
		     	 <input id="loanFrequency" name="loanFrequency" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">借款次数</label>
			</td>
		</tr>
	</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="zmlLoanContractController.do?zmlLoanContractDocumentList&id=${zmlLoanContractPage.id}" icon="icon-search" title="合同文档" id="zmlLoanContractDocument"></t:tab>
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
							<t:dictSelect field="zmlLoanContractDocumentList[#index#].userId" type="list"
										dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="" hasLabel="false"  title="用户"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">用户</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlLoanContractDocumentList[#index#].fileFlag" type="list"
										typeGroupCode="file_flag" defaultVal="" hasLabel="false"  title="标识"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">标识</label>
				  </td>
				  <td align="left">
					  	<input name="zmlLoanContractDocumentList[#index#].imagePath" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">图片路径</label>
				  </td>
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
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">描述</label>
				  </td>
				  <td align="left">
					  	<input name="zmlLoanContractDocumentList[#index#].seqNo" maxlength="4" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">序号</label>
				  </td>
				  <td align="left">
					  	<input name="zmlLoanContractDocumentList[#index#].archivesNo" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">档案编号</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlLoanContractDocumentList[#index#].approvalFlag" type="list"
										typeGroupCode="approval_flag" defaultVal="" hasLabel="false"  title="审批标识"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">审批标识</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlLoanContractDocumentList[#index#].approvalUserId" type="list"
										dictTable="t_s_base_user" dictField="id" dictText="realname" defaultVal="" hasLabel="false"  title="审批人"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">审批人</label>
				  </td>
				  <td align="left">
					      	<input name="zmlLoanContractDocumentList[#index#].approvalDate" maxlength="20" 
						  		type="text"  class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width:120px;"
						  		 >
					  <label class="Validform_label" style="display: none;">审批时间</label>
				  </td>
				  <td align="left">
					  	<input name="zmlLoanContractDocumentList[#index#].approvalOpinion" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">审批意见</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/zml_loan/contract/zmlLoanContract.js"></script>
	