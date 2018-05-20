<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="loading"></div>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlBannerList" checkbox="true" fitColumns="false" title="首页轮播" actionUrl="zmlBannerController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="标题"  field="title"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="图片路径"  field="path"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="内容"  field="content" queryMode="single"  width="500"></t:dgCol>
   <t:dgCol title="是否显示"  field="isView" query="true" queryMode="single" dictionary="sf_yn" width="120"></t:dgCol>
   <t:dgCol title="显示位置"  field="category" query="true" queryMode="single" dictionary="banner_category" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlBannerController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="zmlBannerController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlBannerController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlBannerController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlBannerController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   <t:dgToolBar title="设置显示" icon="icon-put" url="" funname="setShow"></t:dgToolBar>
   <t:dgToolBar title="取消显示" icon="icon-put" url="" funname="setCancelShow"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml/banner/zmlBannerList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
	//给时间控件加上样式
	$("#zmlBannerListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#zmlBannerListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlBannerController.do?upload', "zmlBannerList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlBannerController.do?exportXls","zmlBannerList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlBannerController.do?exportXlsByT","zmlBannerList");
}
//设置显示
function setShow(idVal, nameVal){
	var rowsData = $('#zmlBannerList').datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('请选择一条数据！');
		return;
	}
	if (rowsData.length>1) {
		tip('请选择一条数据！');
		return;
	}
	var titleVal = rowsData[0].title; 
	$.dialog.confirm('确定要将('+titleVal+')显示吗?', function(){
		//确定进入
		var idVal = rowsData[0].id;
		//alert(idVal);
		var urlVal = "zmlBannerController.do?addCache";
	    $.ajax({
	         url:urlVal, 
	         type:'post', 
	         data:'id=' + idVal, 
	         timeout:1500000, 
	         beforeSend:function(XMLHttpRequest){ 
	             $("#loading").html("<img src='${webRoot}/images/loading.gif'/>"); 
	         }, 
	         success:function(data){
	        	//alert('执行结果11：'+data);
	        	var d = eval('(' + data + ')'); 
	            alert(d.content); 
	            if(d.success){
	            	
	            }
	            $("#loading").hide(); 
	         }, 
	         error:function(){ 
	        	alert('失败！：'+d.content); 
	            $("#loading").hide(); 
	        } 
	      }); 
	}, function(){
		//取消进入
			
	});
}

//取消显示
function setCancelShow(){
	var rowsData = $('#zmlBannerList').datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('请选择一条数据！');
		return;
	}
	if (rowsData.length>1) {
		tip('请选择一条数据！');
		return;
	}
	var titleVal = rowsData[0].title; 
	$.dialog.confirm('确定要将('+titleVal+')取消显示吗?', function(){
		//确定进入
		var idVal = rowsData[0].id;
		//alert(idVal);
		var urlVal = "zmlBannerController.do?cancelShow";
	    $.ajax({
	         url:urlVal, 
	         type:'post', 
	         data:'id=' + idVal, 
	         timeout:1500000, 
	         beforeSend:function(XMLHttpRequest){ 
	             $("#loading").html("<img src='${webRoot}/images/loading.gif'/>"); 
	         }, 
	         success:function(data){
	        	//alert('执行结果11：'+data);
	        	var d = eval('(' + data + ')'); 
	            alert(d.content); 
				if(d.success){
	            	
	            }
	            $("#loading").hide(); 
	         }, 
	         error:function(){ 
	        	alert('失败！：'+d.content); 
	            $("#loading").hide(); 
	        } 
	      }); 
	}, function(){
		//取消进入
			
	});
}
 </script>