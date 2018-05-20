<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlUserReleaseClassifyList" checkbox="true" fitColumns="false" title="发布类型" actionUrl="zmlUserReleaseClassifyController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="分类"  field="classify"    queryMode="single" dictionary="release_classify" width="120"></t:dgCol>
   <t:dgCol title="名称"  field="name"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="介绍"  field="introduction"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标志"  field="logo"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="父ID"  field="parentId"    queryMode="single" dictionary="zml_user_release_classify,id,name"  width="120"></t:dgCol>
   <t:dgCol title="是否热门推荐"  field="isHotRecommend"    queryMode="single" dictionary="sf_yn" width="120"></t:dgCol>
   <t:dgCol title="是否热门搜索"  field="isHotSearch"    queryMode="single" dictionary="sf_yn" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlUserReleaseClassifyController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="zmlUserReleaseClassifyController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlUserReleaseClassifyController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlUserReleaseClassifyController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlUserReleaseClassifyController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml/release_classify/zmlUserReleaseClassifyList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlUserReleaseClassifyListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlUserReleaseClassifyListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlUserReleaseClassifyController.do?upload', "zmlUserReleaseClassifyList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlUserReleaseClassifyController.do?exportXls","zmlUserReleaseClassifyList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlUserReleaseClassifyController.do?exportXlsByT","zmlUserReleaseClassifyList");
}
 </script>