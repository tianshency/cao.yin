<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlLoanApplicationList" checkbox="true" fitColumns="false" title="借款申请表" actionUrl="zmlLoanApplicationController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="主题"  field="subject"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户"  field="userId"   query="true" queryMode="single" dictionary="zml_user,id,real_name"  width="120"></t:dgCol>
   <t:dgCol title="申请编号"  field="applyNo"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="类型"  field="loanType"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="申请人姓名"  field="name"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="职业"  field="profession"    queryMode="single" dictionary="profession" width="120"></t:dgCol>
   <t:dgCol title="申请人电话"  field="phone"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="借款理由"  field="loanReason"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="借款金额"  field="amount"   query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="借款期限"  field="periods"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="借款期限单位"  field="periodsUnit"    queryMode="single" dictionary="periods_unit" width="120"></t:dgCol>
   <t:dgCol title="利率"  field="interestRate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="罚息率"  field="penaltyRate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="applySts"   query="true" queryMode="single" dictionary="apply_sts" width="120"></t:dgCol>
   <t:dgToolBar title="录入" icon="icon-add" url="zmlLoanApplicationController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="初审" icon="icon-edit" url="zmlLoanApplicationController.do?goApplyApprove" funname="update" width="100%" height="100%"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml_loan/application/zmlLoanApplicationList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlLoanApplicationListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanApplicationListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
 </script>