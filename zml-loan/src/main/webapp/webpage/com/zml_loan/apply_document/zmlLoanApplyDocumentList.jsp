<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlLoanApplyDocumentList" checkbox="true" fitColumns="false" title="借款申请文档" actionUrl="zmlLoanApplyDocumentController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版本"  field="version"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户"  field="userId"   query="true" queryMode="single" dictionary="zml_user,id,real_name"  width="120"></t:dgCol>
   <t:dgCol title="申请主题"  field="applId"   query="true" queryMode="single" dictionary="zml_loan_application,id,subject"  width="120"></t:dgCol>
   <t:dgCol title="文档类型"  field="docType"   query="true" queryMode="single" dictionary="apply_doc_type" width="120"></t:dgCol>
   <t:dgCol title="标识"  field="fileFlag"    queryMode="single" dictionary="file_flag" width="120"></t:dgCol>
   <t:dgCol title="图片路径"  field="imagePath"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="文件路径"  field="filePath"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="描述"  field="details"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="序号"  field="seqNo"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审批标识"  field="approvalFlag"   query="true" queryMode="single" dictionary="approval_flag" width="120"></t:dgCol>
   <t:dgCol title="审批人"  field="approvalUserId"   query="true" queryMode="single" dictionary="t_s_base_user,id,realname"  width="120"></t:dgCol>
   <t:dgCol title="审批时间"  field="approvalDate" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审批意见"  field="approvalOpinion"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlLoanApplyDocumentController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="zmlLoanApplyDocumentController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlLoanApplyDocumentController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlLoanApplyDocumentController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlLoanApplyDocumentController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml_loan/apply_document/zmlLoanApplyDocumentList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlLoanApplyDocumentListtb").find("input[name='approvalDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlLoanApplyDocumentController.do?upload', "zmlLoanApplyDocumentList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlLoanApplyDocumentController.do?exportXls","zmlLoanApplyDocumentList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlLoanApplyDocumentController.do?exportXlsByT","zmlLoanApplyDocumentList");
}
 </script>