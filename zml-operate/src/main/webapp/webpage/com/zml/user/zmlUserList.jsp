<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlUserList" checkbox="true" fitColumns="false" title="用户信息" actionUrl="zmlUserController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="用户名"  field="userName"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="昵称"  field="nickname"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="密码"  field="password"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="联系电话"  field="phone"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="真实姓名"  field="realName"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="身份证号码"  field="identificationNumber"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="年龄"  field="age"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="性别"  field="sex"   query="true" queryMode="single" dictionary="sex" width="120"></t:dgCol>
   <t:dgCol title="登录IP"  field="loginIp"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="登录日期"  field="loginDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户类型"  field="type"   query="true" queryMode="single" dictionary="user_type" width="120"></t:dgCol>
   <t:dgCol title="是否实名认证"  field="isVerified"    queryMode="single" dictionary="sf_yn" width="120"></t:dgCol>
   <t:dgCol title="头像"  field="avatar"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当前帐号是否禁用"  field="locked"    queryMode="single" dictionary="user_locked" width="120"></t:dgCol>
   <t:dgCol title="是否签订注册协议"  field="registrationAgreement"    queryMode="single" dictionary="sf_yn" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlUserController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="zmlUserController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlUserController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlUserController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlUserController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml/user/zmlUserList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlUserListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlUserListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlUserController.do?upload', "zmlUserList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlUserController.do?exportXls","zmlUserList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlUserController.do?exportXlsByT","zmlUserList");
}
 </script>