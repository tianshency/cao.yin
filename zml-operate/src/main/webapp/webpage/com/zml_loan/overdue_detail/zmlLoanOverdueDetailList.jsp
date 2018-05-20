<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlLoanOverdueDetailList" checkbox="true" fitColumns="false" title="逾期明细" actionUrl="zmlLoanOverdueDetailController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版本"  field="version"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="合同"  field="contractId"   query="true" queryMode="single" dictionary="zml_loan_contract,id,contract_no"  width="120"></t:dgCol>
   <t:dgCol title="用户"  field="userId"   query="true" queryMode="single" dictionary="zml_user,id,real_name"  width="120"></t:dgCol>
   <t:dgCol title="还款计划"  field="repayPlanDetailId"   query="true" queryMode="single" dictionary="zml_loan_repay_plan_detail,id,profit_period"  width="120"></t:dgCol>
   <t:dgCol title="逾期开始时间"  field="overStartDate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="应还本金"  field="principal"   query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="应还利息"  field="interest"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="应还罚息"  field="imposeInterest"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="滞纳金"  field="penalty"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="期次"  field="period"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlLoanOverdueDetailController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="zmlLoanOverdueDetailController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlLoanOverdueDetailController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlLoanOverdueDetailController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlLoanOverdueDetailController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml_loan/overdue_detail/zmlLoanOverdueDetailList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlLoanOverdueDetailListtb").find("input[name='overStartDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlLoanOverdueDetailController.do?upload', "zmlLoanOverdueDetailList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlLoanOverdueDetailController.do?exportXls","zmlLoanOverdueDetailList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlLoanOverdueDetailController.do?exportXlsByT","zmlLoanOverdueDetailList");
}
 </script>