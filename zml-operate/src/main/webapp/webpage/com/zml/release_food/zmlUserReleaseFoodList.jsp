<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlUserReleaseFoodList" checkbox="true" fitColumns="false" title="发布粮食" actionUrl="zmlUserReleaseFoodController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="发布人"  field="userId"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标题"  field="title"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="种类"  field="kind"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="价格"  field="price"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单位"  field="unit"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="水分"  field="moisture"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="霉变"  field="mildew"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="容重"  field="bulkDensity"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="粮食类型"  field="foodType"   query="true" queryMode="single" dictionary="food_type" width="120"></t:dgCol>
   <t:dgCol title="筛选类型"  field="filterType"    queryMode="single" dictionary="filter_type" width="120"></t:dgCol>
   <t:dgCol title="年份"  field="particularYear"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="总数量"  field="totalNum"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="有效开始日期"  field="validStartDate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="有效结束日期"  field="validEndDate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="封面照片"  field="coverImg"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status" query="true" queryMode="single" dictionary="release_sts" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlUserReleaseFoodController.do?doDel&id={id}"/>
   <t:dgToolBar title="录入" icon="icon-add" url="zmlUserReleaseFoodController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlUserReleaseFoodController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlUserReleaseFoodController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlUserReleaseFoodController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   <t:dgToolBar title="审批" icon="icon-edit" url="zmlUserReleaseFoodController.do?goApproval" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml/release_food/zmlUserReleaseFoodList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
		$("#zmlUserReleaseFoodListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
		$("#zmlUserReleaseFoodListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlUserReleaseFoodController.do?upload', "zmlUserReleaseFoodList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlUserReleaseFoodController.do?exportXls","zmlUserReleaseFoodList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlUserReleaseFoodController.do?exportXlsByT","zmlUserReleaseFoodList");
}
 </script>