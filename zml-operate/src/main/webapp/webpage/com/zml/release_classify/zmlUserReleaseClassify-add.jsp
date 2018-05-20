<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>发布类型</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlUserReleaseClassifyController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlUserReleaseClassifyPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlUserReleaseClassifyPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlUserReleaseClassifyPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlUserReleaseClassifyPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlUserReleaseClassifyPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlUserReleaseClassifyPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlUserReleaseClassifyPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlUserReleaseClassifyPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlUserReleaseClassifyPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlUserReleaseClassifyPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							分类:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="classify" type="list"
									typeGroupCode="release_classify" defaultVal="${zmlUserReleaseClassifyPage.classify}" hasLabel="false"  title="分类"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">分类</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">名称</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							介绍:
						</label>
					</td>
					<td class="value">
					     	 <input id="introduction" name="introduction" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">介绍</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							标志:
						</label>
					</td>
					<td class="value">
					      		<input id="logo" name="logo" type="text" style="width: 150px" class="inputxt"  
>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标志</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							父ID:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="parentId" type="list"
									dictTable="zml_user_release_classify" dictField="id" dictText="name" defaultVal="${zmlUserReleaseClassifyPage.parentId}" hasLabel="false"  title="父ID"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">父ID</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否热门推荐:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="isHotRecommend" type="list"
									typeGroupCode="sf_yn" defaultVal="${zmlUserReleaseClassifyPage.isHotRecommend}" hasLabel="false"  title="是否热门推荐"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否热门推荐</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否热门搜索:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="isHotSearch" type="list"
									typeGroupCode="sf_yn" defaultVal="${zmlUserReleaseClassifyPage.isHotSearch}" hasLabel="false"  title="是否热门搜索"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否热门搜索</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml/release_classify/zmlUserReleaseClassify.js"></script>		
