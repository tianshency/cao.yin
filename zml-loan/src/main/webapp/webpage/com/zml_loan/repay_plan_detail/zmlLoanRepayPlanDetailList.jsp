<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlLoanRepayPlanDetailList" checkbox="true" fitColumns="false" title="还款计划明细" actionUrl="zmlLoanRepayPlanDetailController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版本"  field="version"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="合同"  field="contractId"   query="true" queryMode="single" dictionary="zml_loan_contract,id,contract_no"  width="120"></t:dgCol>
   <t:dgCol title="用户"  field="userId"   query="true" queryMode="single" dictionary="zml_user,id,real_name"  width="120"></t:dgCol>
   <t:dgCol title="当期期数"  field="profitPeriod"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当期开始日"  field="profitStartDate" formatter="yyyy-MM-dd"  query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="当期结束日"  field="profitEndDate" formatter="yyyy-MM-dd"  query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="当期应还本金"  field="profitPrincipal"   query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="当期应还利息"  field="profitInterest"   query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="当期应还罚息"  field="profitPenalty"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当期应还滞纳金"  field="profitNonpayment"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当期应还其他费用"  field="profitOtherFee"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当期应还合计"  field="profitRepaySum"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当期实还本金"  field="repayPrincipal"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当期实还利息"  field="repayInterest"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当期实还罚息"  field="repayPenalty"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当期实还滞纳金"  field="repayNonpayment"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当期实还其他费用"  field="repayOtherFee"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当期实还总额"  field="repaySum"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="最后还款日"  field="lastRepayDate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当期合同余额"  field="endCurrentPrincipalbalance"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="利率"  field="rate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="逾期利率"  field="overdueRate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="逾期天数"  field="overdueDays"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"   query="true" queryMode="single" dictionary="repay_plan_sts" width="120"></t:dgCol>
   <t:dgCol title="调整日期"  field="adjustdate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="最近一次还款时间"  field="recentlyRepayTime" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remarks"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlLoanRepayPlanDetailController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="zmlLoanRepayPlanDetailController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlLoanRepayPlanDetailController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlLoanRepayPlanDetailController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlLoanRepayPlanDetailController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml_loan/repay_plan_detail/zmlLoanRepayPlanDetailList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlLoanRepayPlanDetailListtb").find("input[name='profitStartDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanRepayPlanDetailListtb").find("input[name='profitStartDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanRepayPlanDetailListtb").find("input[name='profitEndDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanRepayPlanDetailListtb").find("input[name='profitEndDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanRepayPlanDetailListtb").find("input[name='lastRepayDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanRepayPlanDetailListtb").find("input[name='adjustdate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanRepayPlanDetailListtb").find("input[name='recentlyRepayTime']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlLoanRepayPlanDetailController.do?upload', "zmlLoanRepayPlanDetailList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlLoanRepayPlanDetailController.do?exportXls","zmlLoanRepayPlanDetailList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlLoanRepayPlanDetailController.do?exportXlsByT","zmlLoanRepayPlanDetailList");
}
 </script>