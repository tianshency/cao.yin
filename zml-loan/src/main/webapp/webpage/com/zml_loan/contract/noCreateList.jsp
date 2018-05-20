<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlLoanApplicationList" checkbox="true" fitColumns="false" title="创建列表" actionUrl="zmlLoanApplicationController.do?datagrid&status=2" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <%-- <t:dgCol title="流程状态"  field="bpmStatus"    queryMode="single"  width="120"></t:dgCol> --%>
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
   <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol> --%>
   <%-- <t:dgDelOpt title="删除" url="zmlLoanApplicationController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/> --%>
   <t:dgToolBar title="创建合同" icon="icon-edit" url="zmlLoanApplicationController.do?goCreateContract" funname="update"></t:dgToolBar>
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
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlLoanApplicationController.do?upload', "zmlLoanApplicationList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlLoanApplicationController.do?exportXls","zmlLoanApplicationList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlLoanApplicationController.do?exportXlsByT","zmlLoanApplicationList");
}
 </script>