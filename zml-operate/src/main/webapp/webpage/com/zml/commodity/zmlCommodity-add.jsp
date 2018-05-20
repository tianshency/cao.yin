<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>商品</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
		}
	});
	$(".tabs-wrap").css('width','100%');
  });
 </script>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="zmlCommodityController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${zmlCommodityPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlCommodityPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlCommodityPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlCommodityPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlCommodityPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlCommodityPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlCommodityPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlCommodityPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlCommodityPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlCommodityPage.version }">
					<input id="merchantsId" name="merchantsId" type="hidden" value="${zmlCommodityPage.merchantsId }">
					<input id="manufacturers" name="manufacturers" type="hidden" value="${zmlCommodityPage.manufacturers }">
					<input id="batchNumber" name="batchNumber" type="hidden" value="${zmlCommodityPage.batchNumber }">
					<input id="reserveAmount" name="reserveAmount" type="hidden" value="${zmlCommodityPage.reserveAmount }">
					<input id="details" name="details" type="hidden" value="${zmlCommodityPage.details }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">商品名称:</label>
			</td>
			<td class="value">
		     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">商品名称</label>
			</td>
			<td align="right">
				<label class="Validform_label">总数量:</label>
			</td>
			<td class="value">
		     	 <input id="totalAmount" name="totalAmount" type="text" style="width: 150px" class="inputxt"datatype="n" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">总数量</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">数量单位:</label>
			</td>
			<td class="value">
					<t:dictSelect field="amountUnit" type="list"
						typeGroupCode="amount_unit"  hasLabel="false"  title="数量单位"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">数量单位</label>
			</td>
			<td align="right">
				<label class="Validform_label">商品规格:</label>
			</td>
			<td class="value">
					<t:dictSelect field="commercialSpecification" type="list"
						typeGroupCode="specification"  hasLabel="false"  title="商品规格"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">商品规格</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">规格重量:</label>
			</td>
			<td class="value">
		     	 <input id="specificationWeight" name="specificationWeight" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">规格重量</label>
			</td>
			<td align="right">
				<label class="Validform_label">原价格:</label>
			</td>
			<td class="value">
		     	 <input id="price" name="price" type="text" style="width: 150px" class="inputxt"datatype="n" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">原价格</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">实际价格:</label>
			</td>
			<td class="value">
		     	 <input id="realPrice" name="realPrice" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">实际价格</label>
			</td>
			<td align="right">
				<label class="Validform_label">生产日期:</label>
			</td>
			<td class="value">
					  <input id="productionDate" name="productionDate" type="text" style="width: 150px" 
							 class="Wdate" onClick="WdatePicker()" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">有效期:</label>
			</td>
			<td class="value">
		     	 <input id="periodOfValidity" name="periodOfValidity" type="text" style="width: 150px" class="inputxt"datatype="n" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">有效期</label>
			</td>
			<td align="right">
				<label class="Validform_label">有效期单位:</label>
			</td>
			<td class="value">
					<t:dictSelect field="povUnit" type="list"
						typeGroupCode=""  hasLabel="false"  title="有效期单位"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">有效期单位</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">是否推荐:</label>
			</td>
			<td class="value">
					<t:dictSelect field="isRecommend" type="list"
						typeGroupCode="is_default"  hasLabel="false"  title="是否推荐"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否推荐</label>
			</td>
			<td align="right">
				<label class="Validform_label">是否热卖:</label>
			</td>
			<td class="value">
					<t:dictSelect field="isHot" type="list"
						typeGroupCode="is_default"  hasLabel="false"  title="是否热卖"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否热卖</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">一级分类:</label>
			</td>
			<td class="value">
					<t:dictSelect field="classifyOneLevel" type="list"
						dictTable="zml_commodity_classify" dictField="id" dictText="name"  hasLabel="false"  title="一级分类"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">一级分类</label>
			</td>
			<td align="right">
				<label class="Validform_label">二级分类:</label>
			</td>
			<td class="value">
					<t:dictSelect field="classifyTwoLevel" type="list"
						dictTable="zml_commodity_classify" dictField="id" dictText="name"  hasLabel="false"  title="二级分类"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">二级分类</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">状态:</label>
			</td>
			<td class="value">
					<t:dictSelect field="status" type="list"
						typeGroupCode="commodity_sts"  hasLabel="false"  title="状态"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">状态</label>
			</td>
			<td align="right">
				<label class="Validform_label">运费:</label>
			</td>
			<td class="value">
		     	 <input id="fare" name="fare" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">运费</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">介绍:</label>
			</td>
			<td class="value" colspan="3">
					<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
					<script type="text/javascript"  charset="utf-8" src="plug-in/ueditor/ueditor.all.min.js"></script>
			    	<textarea name="introduction" id="introduction" style="width: 650px;height:300px"></textarea>
				    <script type="text/javascript">
				        var editor = UE.getEditor('introduction');
				    </script>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">介绍</label>
			</td>
		</tr>
	</table>
	<div style="width: auto;height: 200px;">
		<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
		<div style="width:800px;height:1px;"></div>
		<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
		 <t:tab href="zmlCommodityController.do?zmlCommodityDocList&id=${zmlCommodityPage.id}" icon="icon-search" title="商品文档" id="zmlCommodityDoc"></t:tab>
		 <t:tab href="zmlCommodityController.do?zmlCommodityStandardList&id=${zmlCommodityPage.id}" icon="icon-search" title="适用标准" id="zmlCommodityStandard"></t:tab>
		</t:tabs>
	</div>
	</t:formvalid>
	<!-- 添加 附表明细 模版 -->
	<table style="display:none">
	<tbody id="add_zmlCommodityDoc_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  <t:dictSelect field="zmlCommodityDocList[#index#].flag" type="list"
										typeGroupCode="doc_sts" defaultVal="" hasLabel="false"  title="标识"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">标识</label>
				  </td>
				  <td align="left">
					  <t:dictSelect field="zmlCommodityDocList[#index#].type" type="list"
										typeGroupCode="type_sts" defaultVal="" hasLabel="false"  title="类型"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">类型</label>
				  </td>
				  <!-- <td align="left">
					   <input name="zmlCommodityDocList[#index#].imagePath" maxlength="200" 
						  		type="text" class="inputxt"  style="width:120px;">
					  <label class="Validform_label" style="display: none;">图片路径</label>
				  </td> -->
				  <td align="left">
					   <input type="hidden" id="zmlCommodityDocList[#index#].filePath" name="zmlCommodityDocList[#index#].filePath" />
						<a  target="_blank" id="zmlCommodityDocList[#index#].filePath_href">未上传</a>
					  <br>
					   <input class="ui-button" type="button" value="上传附件"
						onclick="commonUpload(commonUploadDefaultCallBack,'zmlCommodityDocList\\[#index#\\]\\.filePath')"/>
					  <label class="Validform_label" style="display: none;">文件路径</label>
				  </td>
				  <td align="left">
					  <input name="zmlCommodityDocList[#index#].details" maxlength="100" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">描述</label>
				  </td>
			</tr>
		 </tbody>
	  <tbody id="add_zmlCommodityStandard_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					 <t:dictSelect field="zmlCommodityStandardList[#index#].corpId" type="list"
										dictTable="zml_crop_type" dictField="id" dictText="name" defaultVal="" hasLabel="false"  title="农作物种类"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">农作物种类</label>
				  </td>
				  <td align="left">
					  	<input name="zmlCommodityStandardList[#index#].dosageStart" maxlength="10" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="n">
					  <label class="Validform_label" style="display: none;">亩用量起</label>
				  </td>
				  <td align="left">
					  	<input name="zmlCommodityStandardList[#index#].dosageEnd" maxlength="10" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="n">
					  <label class="Validform_label" style="display: none;">亩用量至</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlCommodityStandardList[#index#].dosageUnit" type="list"
										typeGroupCode="specification" defaultVal="" hasLabel="false"  title="用量单位"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">用量单位</label>
				  </td>
				  <td align="left">
					  <input name="zmlCommodityStandardList[#index#].useDescription" maxlength="500" 
						  		type="text" class="inputxt"  style="width:120px;">
					  <label class="Validform_label" style="display: none;">使用说明</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/zml/commodity/zmlCommodity.js"></script>
	