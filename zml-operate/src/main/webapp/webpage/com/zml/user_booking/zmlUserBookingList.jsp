<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlUserBookingList" checkbox="true" fitColumns="false" title="我的预定" actionUrl="zmlUserBookingController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="用户ID"  field="userId"   query="true" queryMode="single" dictionary="zml_user,id,user_name"  width="120"></t:dgCol>
   <t:dgCol title="对方ID"  field="otherSideId"   query="true" queryMode="single" dictionary="zml_user,id,user_name"  width="120"></t:dgCol>
   <t:dgCol title="预订类型"  field="bookingType"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预订ID"  field="bookingId"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="数量"  field="bookingNum"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="计划交易日"  field="planDealDate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户名"  field="userName"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="对方名称"  field="otherSideName"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预定描述"  field="bookingDetail"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remarks"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"    queryMode="single" dictionary="normal_del_sts" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlUserBookingController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="zmlUserBookingController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlUserBookingController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlUserBookingController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlUserBookingController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml/user_booking/zmlUserBookingList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlUserBookingListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlUserBookingListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlUserBookingListtb").find("input[name='planDealDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlUserBookingController.do?upload', "zmlUserBookingList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlUserBookingController.do?exportXls","zmlUserBookingList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlUserBookingController.do?exportXlsByT","zmlUserBookingList");
}
 </script>