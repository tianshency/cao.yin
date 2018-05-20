<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlMyReleaseDocList" checkbox="true" fitColumns="false" title="发布文档" actionUrl="zmlMyReleaseDocController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版本"  field="version"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="主图标识"  field="isTop"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="发布ID"  field="releaseId"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标识"  field="fileFlag"   query="true" queryMode="single" dictionary="doc_type" width="120"></t:dgCol>
   <t:dgCol title="类型"  field="releaseType"    queryMode="single" dictionary="release_type" width="120"></t:dgCol>
   <t:dgCol title="图片路径"  field="imagePath"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="文件路径"  field="filePath"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="描述"  field="details"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlMyReleaseDocController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="zmlMyReleaseDocController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlMyReleaseDocController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlMyReleaseDocController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlMyReleaseDocController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml/release_doc/zmlMyReleaseDocList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlMyReleaseDocListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlMyReleaseDocListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlMyReleaseDocController.do?upload', "zmlMyReleaseDocList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlMyReleaseDocController.do?exportXls","zmlMyReleaseDocList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlMyReleaseDocController.do?exportXlsByT","zmlMyReleaseDocList");
}
 </script>