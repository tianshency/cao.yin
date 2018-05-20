<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlLoanContractList" checkbox="true" fitColumns="false" title="借款合同" actionUrl="zmlLoanContractController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版本"  field="version"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户"  field="userId"   query="true" queryMode="single" dictionary="zml_user,id,real_name"  width="120"></t:dgCol>
   <t:dgCol title="申请"  field="applId"   query="true" queryMode="single" dictionary="zml_loan_application,id,subject"  width="120"></t:dgCol>
   <t:dgCol title="合同号"  field="contractNo"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="产品"  field="productId"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="授信额度"  field="creditAmt"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="合同金额"  field="contractAmt"   query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="合同余额"  field="contractBalance"   query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="服务费"  field="fee"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="中间方"  field="intermediaries"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="出借人"  field="lender"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="利率"  field="interestRate"   query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="罚息率"  field="penaltyRate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="合同路径"  field="contractPath"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="还款方式"  field="repayment"    queryMode="single" dictionary="repayment" width="120"></t:dgCol>
   <t:dgCol title="合同开始日"  field="startTime" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="合同到期日"  field="endTime" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="期限"  field="term"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="期限单位"  field="termUnit"    queryMode="single" dictionary="periods_unit" width="120"></t:dgCol>
   <t:dgCol title="逾期次数"  field="overTime"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="逾期天数"  field="overDays"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="罚息"  field="imposeInterest"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="滞纳金"  field="penalty"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"   query="true" queryMode="single" dictionary="contract_sts" width="120"></t:dgCol>
   <t:dgCol title="还款状态"  field="repayStatus"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="借款次数"  field="loanFrequency"    queryMode="single"  width="120"></t:dgCol>
   <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlLoanContractController.do?doDel&id={id}"  urlclass="ace_button" urlfont="fa-trash-o"/> --%>
   <t:dgToolBar title="录入" icon="icon-add" url="zmlLoanContractController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>
   <%-- <t:dgToolBar title="编辑" icon="icon-edit" url="zmlLoanContractController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlLoanContractController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlLoanContractController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
   <%-- <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar> --%>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="审批" icon="icon-edit" url="zmlLoanPayLoanInfoController.do?goPayLoan" funname="update" width="100%" height="100%"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml_loan/contract/zmlLoanContractList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlLoanContractListtb").find("input[name='startTime']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanContractListtb").find("input[name='endTime']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlLoanContractController.do?upload', "zmlLoanContractList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlLoanContractController.do?exportXls","zmlLoanContractList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlLoanContractController.do?exportXlsByT","zmlLoanContractList");
}
 </script>