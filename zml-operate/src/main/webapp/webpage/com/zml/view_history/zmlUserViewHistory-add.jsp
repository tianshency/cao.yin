<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>用户浏览记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlUserViewHistoryController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlUserViewHistoryPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlUserViewHistoryPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlUserViewHistoryPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlUserViewHistoryPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlUserViewHistoryPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlUserViewHistoryPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlUserViewHistoryPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlUserViewHistoryPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlUserViewHistoryPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlUserViewHistoryPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							关注类型:
						</label>
					</td>
					<td class="value">
					     	 <input id="concernType" name="concernType" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关注类型</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="commodityId" name="commodityId" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品ID</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							浏览次数:
						</label>
					</td>
					<td class="value">
					     	 <input id="browsingTimes" name="browsingTimes" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">浏览次数</label>
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
  <script src = "webpage/com/zml/view_history/zmlUserViewHistory.js"></script>		
