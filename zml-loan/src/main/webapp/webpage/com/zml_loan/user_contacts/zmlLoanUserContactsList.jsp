<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlLoanUserContactsList" checkbox="true" fitColumns="false" title="借款用户联系人" actionUrl="zmlLoanUserContactsController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版本"  field="version"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="借款主题"  field="applId"    queryMode="single" dictionary="zml_loan_application,id,subject"  width="120"></t:dgCol>
   <t:dgCol title="用户"  field="userId"    queryMode="single" dictionary="zml_user,id,real_name"  width="120"></t:dgCol>
   <t:dgCol title="姓名"  field="name"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="电话"  field="phone"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="职业"  field="profession"    queryMode="single" dictionary="profession" width="120"></t:dgCol>
   <t:dgCol title="工作"  field="work"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="关系"  field="relation"    queryMode="single" dictionary="relation" width="120"></t:dgCol>
   <t:dgCol title="身份证号"  field="idNumber"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="年龄"  field="age"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="性别"  field="sex"    queryMode="single" dictionary="sex" width="120"></t:dgCol>
   <t:dgCol title="住所"  field="address"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="序号"  field="seqNo"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审批标识"  field="approvalFlag"    queryMode="single" dictionary="approval_flag" width="120"></t:dgCol>
   <t:dgCol title="审批人"  field="approvalUserId"    queryMode="single" dictionary="t_s_base_user,id,realname"  width="120"></t:dgCol>
   <t:dgCol title="审批时间"  field="approvalDate" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审批意见"  field="approvalOpinion"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlLoanUserContactsController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="zmlLoanUserContactsController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlLoanUserContactsController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlLoanUserContactsController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlLoanUserContactsController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml_loan/user_contacts/zmlLoanUserContactsList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlLoanUserContactsListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanUserContactsListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanUserContactsListtb").find("input[name='approvalDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlLoanUserContactsController.do?upload', "zmlLoanUserContactsList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlLoanUserContactsController.do?exportXls","zmlLoanUserContactsList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlLoanUserContactsController.do?exportXlsByT","zmlLoanUserContactsList");
}
 </script>