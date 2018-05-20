<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlLoanWfTaskList" checkbox="true" fitColumns="false" title="待办任务列表" actionUrl="zmlLoanWfTaskController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"    queryMode="single" dictionary="loan_wf_node" width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户"  field="userId"   query="true" queryMode="single" dictionary="zml_user,id,real_name"  width="120"></t:dgCol>
   <t:dgCol title="业务"  field="bizId"   query="true" queryMode="single" dictionary="zml_loan_application,id,subject"  width="120"></t:dgCol>
   <t:dgCol title="任务类型"  field="taskType" query="true" queryMode="single" dictionary="loan_task_type" width="120"></t:dgCol>
   <%-- <t:dgCol title="任务主题"  field="taskSubject"   query="true" queryMode="single"  width="120"></t:dgCol> --%>
   <%-- <t:dgCol title="产品编码"  field="productId"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="来源"  field="taskSource"    queryMode="single" dictionary="task_source" width="120"></t:dgCol> --%>
   <t:dgCol title="审核额度"  field="approvalAmt"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="利率"  field="approvalInterestRate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审批期限"  field="approvalTerm"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审批期限单位"  field="approvalTermUnit"    queryMode="single" dictionary="approval_term_unit" width="120"></t:dgCol>
   <t:dgCol title="任务开始时间"  field="startTime" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="任务完成时间"  field="endTime" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审批标识"  field="approvalFlag"   query="true" queryMode="single" dictionary="approval_flag" width="120"></t:dgCol>
   <t:dgCol title="审批人"  field="approvalUserId"   query="true" queryMode="single" dictionary="t_s_base_user,id,realname"  width="120"></t:dgCol>
   <t:dgCol title="审批时间"  field="approvalDate"   query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="审批意见"  field="approvalOpinion"    queryMode="single"  width="120"></t:dgCol>
   <%-- <t:dgCol title="是否通知审批人"  field="isNotic"   query="true" queryMode="single" dictionary="sf_sy" width="120"></t:dgCol>
   <t:dgCol title="通知内容"  field="noticContent"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="分配人"  field="fromUserId"    queryMode="single" dictionary="t_s_base_user,id,realname"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlLoanWfTaskController.do?doDel&id={id}" /> 
   <t:dgToolBar title="录入" icon="icon-add" url="zmlLoanWfTaskController.do?goAdd" funname="add"></t:dgToolBar>--%>
    <t:dgToolBar funname="goApplyApprove()" title="审批"></t:dgToolBar>
   <%-- <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlLoanWfTaskController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <%-- <t:dgToolBar title="查看" icon="icon-search" url="zmlLoanWfTaskController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml_loan/wf_task/zmlLoanWfTaskList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlLoanWfTaskListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanWfTaskListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanWfTaskListtb").find("input[name='startTime']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanWfTaskListtb").find("input[name='endTime']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlLoanWfTaskController.do?upload', "zmlLoanWfTaskList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlLoanWfTaskController.do?exportXls","zmlLoanWfTaskList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlLoanWfTaskController.do?exportXlsByT","zmlLoanWfTaskList");
}
//审批
function goApplyApprove(){
	var rowsData = $('#zmlLoanWfTaskList').datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('请选择审批业务！');
		return;
	}
	if (rowsData.length>1) {
		tip('请选择一条审批业务');
		return;
	}
	var bizId = rowsData[0].bizId;
	var wfId = rowsData[0].id;
	var taskType = rowsData[0].taskType;
	alert(bizId + "===" + wfId + "===" +taskType);
	var url = "zmlLoanWfTaskController.do?goBizApprove&bizId="+bizId+"&wfId="+wfId+"&bizType="+taskType;
    //查看
	//createdetailwindow("审批", url, "100%", "100%");
	createwindow("审批", url, "100%", "100%");
}
 </script>