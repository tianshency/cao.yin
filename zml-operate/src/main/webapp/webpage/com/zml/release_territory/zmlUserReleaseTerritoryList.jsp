<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlUserReleaseTerritoryList" checkbox="true" fitColumns="false" title="发布土地" actionUrl="zmlUserReleaseTerritoryController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="类型名称"  field="type"    queryMode="single" dictionary="rele_territory_type" width="120"></t:dgCol>
   <t:dgCol title="标题"  field="title"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="发布人"  field="userId"   query="true" queryMode="single" dictionary="zml_user,id,user_name"  width="120"></t:dgCol>
   <t:dgCol title="发布类型"  field="releaseType"   query="true" queryMode="single" dictionary="zml_user_release_classify,id,name"  width="120"></t:dgCol>
   <t:dgCol title="位置"  field="position"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="封面照片"  field="coverImg"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="面积"  field="area"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="价格"  field="price"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="土地类型"  field="landType"    queryMode="single" dictionary="land_type" width="120"></t:dgCol>
   <t:dgCol title="地势"  field="terrain"    queryMode="single" dictionary="terrain" width="120"></t:dgCol>
   <t:dgCol title="有效开始日期"  field="validStartDate" formatter="yyyy-MM-dd"  query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="有效结束日期"  field="validEndDate" formatter="yyyy-MM-dd"  query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remarks"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"   query="true" queryMode="single" dictionary="release_sts" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlUserReleaseTerritoryController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="zmlUserReleaseTerritoryController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlUserReleaseTerritoryController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlUserReleaseTerritoryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlUserReleaseTerritoryController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   <t:dgToolBar title="审批" icon="icon-edit" url="zmlUserReleaseTerritoryController.do?goApproval" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml/release_territory/zmlUserReleaseTerritoryList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlUserReleaseTerritoryListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlUserReleaseTerritoryListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlUserReleaseTerritoryListtb").find("input[name='validStartDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlUserReleaseTerritoryListtb").find("input[name='validEndDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlUserReleaseTerritoryController.do?upload', "zmlUserReleaseTerritoryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlUserReleaseTerritoryController.do?exportXls","zmlUserReleaseTerritoryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlUserReleaseTerritoryController.do?exportXlsByT","zmlUserReleaseTerritoryList");
}
 </script>