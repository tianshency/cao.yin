<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wrwRegisteredLogList" checkbox="true" fitColumns="false" title="注册日志" actionUrl="wrwRegisteredLogController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true" query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版本"  field="version"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="手机号"  field="phone"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="任务"  field="taskId"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="来源"  field="source"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="satus"   query="true" queryMode="single" dictionary="registered_sts" width="120"></t:dgCol>
   <t:dgCol title="请求信息"  field="requestInfo"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wrwRegisteredLogController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="wrwRegisteredLogController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wrwRegisteredLogController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wrwRegisteredLogController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="wrwRegisteredLogController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml_loan/log/wrwRegisteredLogList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#wrwRegisteredLogListtb").find("input[name='createDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#wrwRegisteredLogListtb").find("input[name='createDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#wrwRegisteredLogListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wrwRegisteredLogController.do?upload', "wrwRegisteredLogList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wrwRegisteredLogController.do?exportXls","wrwRegisteredLogList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wrwRegisteredLogController.do?exportXlsByT","wrwRegisteredLogList");
}
 </script>