<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="cfUserSearchHistoryList" checkbox="true" fitColumns="false" title="搜索历史" actionUrl="cfUserSearchHistoryController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版本"  field="version"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户"  field="userId"    queryMode="single" dictionary="cf_user,id,name"  width="120"></t:dgCol>
   <t:dgCol title="搜索内容"  field="content"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"    queryMode="single" dictionary="search_sts" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="cfUserSearchHistoryController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="cfUserSearchHistoryController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="cfUserSearchHistoryController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="cfUserSearchHistoryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="cfUserSearchHistoryController.do?goUpdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml/user_find/cfUserSearchHistoryList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#cfUserSearchHistoryListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#cfUserSearchHistoryListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'cfUserSearchHistoryController.do?upload', "cfUserSearchHistoryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("cfUserSearchHistoryController.do?exportXls","cfUserSearchHistoryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("cfUserSearchHistoryController.do?exportXlsByT","cfUserSearchHistoryList");
}
 </script>