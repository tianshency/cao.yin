<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlLoanRepayRecordList" checkbox="true" fitColumns="false" title="实际还款记录" actionUrl="zmlLoanRepayRecordController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版本"  field="version"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="合同"  field="contractId"   query="true" queryMode="single" dictionary="zml_loan_contract,id,contract_no"  width="120"></t:dgCol>
   <t:dgCol title="用户"  field="userId"   query="true" queryMode="single" dictionary="zml_user,id,real_name"  width="120"></t:dgCol>
   <t:dgCol title="还款计划"  field="repayPlanDetailId"    queryMode="single" dictionary="zml_loan_repay_plan_detail,id,profit_period"  width="120"></t:dgCol>
   <t:dgCol title="还款时间"  field="repayTime" formatter="yyyy-MM-dd hh:mm:ss"  query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="实还本金"  field="repayPrincipal"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="实还利息"  field="repayInterest"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="实还罚息"  field="repayPenalty"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="实还滞纳金"  field="repayNonpayment"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="实还其他费用"  field="repayOtherFee"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="还款金额"  field="repayAmt"   query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="还款渠道"  field="repayChannel"   query="true" queryMode="single" dictionary="repay_channel" width="120"></t:dgCol>
   <t:dgCol title="还款凭证"  field="repayCertificate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"   query="true" queryMode="single" dictionary="repay_sts" width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remarks"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审批标识"  field="approvalFlag"   query="true" queryMode="single" dictionary="approval_flag" width="120"></t:dgCol>
   <t:dgCol title="审批人"  field="approvalUserId"    queryMode="single" dictionary="t_s_base_user,id,realname"  width="120"></t:dgCol>
   <t:dgCol title="审批时间"  field="approvalDate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审批意见"  field="approvalOpinion"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlLoanRepayRecordController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="zmlLoanRepayRecordController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlLoanRepayRecordController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlLoanRepayRecordController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlLoanRepayRecordController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml_loan/repay_record/zmlLoanRepayRecordList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlLoanRepayRecordListtb").find("input[name='repayTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanRepayRecordListtb").find("input[name='repayTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlLoanRepayRecordController.do?upload', "zmlLoanRepayRecordList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlLoanRepayRecordController.do?exportXls","zmlLoanRepayRecordList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlLoanRepayRecordController.do?exportXlsByT","zmlLoanRepayRecordList");
}
 </script>