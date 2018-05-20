<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlLoanRiskCreditList" checkbox="true" fitColumns="false" title="借款风控授信" actionUrl="zmlLoanRiskCreditController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版本"  field="version"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户"  field="userId"    queryMode="single" dictionary="zml_user,id,real_name"  width="120"></t:dgCol>
   <t:dgCol title="申请"  field="applId"    queryMode="single" dictionary="zml_loan_application,id,subject"  width="120"></t:dgCol>
   <t:dgCol title="授信主题"  field="creditSubjet"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="评估开始时间"  field="assessStartTime" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="评估结束时间"  field="assessEndTime" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="评估金额"  field="assessAmt"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"    queryMode="single" dictionary="credit_sts" width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remarks"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="评估标识"  field="flag"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审批标识"  field="approvalFlag"    queryMode="single" dictionary="approval_flag" width="120"></t:dgCol>
   <t:dgCol title="审批人"  field="approvalUserId"    queryMode="single" dictionary="t_s_base_user,id,realname"  width="120"></t:dgCol>
   <t:dgCol title="审批时间"  field="approvalDate" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审批意见"  field="approvalOpinion"    queryMode="single"  width="120"></t:dgCol>
  <%--  <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
  <t:dgDelOpt title="删除" url="zmlLoanRiskCreditController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/> --%>
   <t:dgToolBar title="录入" icon="icon-add" url="zmlLoanRiskCreditController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="手动授信" icon="icon-edit" url="zmlLoanRiskCreditController.do?goUpdate" funname="update"></t:dgToolBar>
  <%--  <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlLoanRiskCreditController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlLoanRiskCreditController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <%-- <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml_loan/risk_credit/zmlLoanRiskCreditList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlLoanRiskCreditListtb").find("input[name='assessStartTime']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanRiskCreditListtb").find("input[name='assessEndTime']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanRiskCreditListtb").find("input[name='approvalDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlLoanRiskCreditController.do?upload', "zmlLoanRiskCreditList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlLoanRiskCreditController.do?exportXls","zmlLoanRiskCreditList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlLoanRiskCreditController.do?exportXlsByT","zmlLoanRiskCreditList");
}
 </script>