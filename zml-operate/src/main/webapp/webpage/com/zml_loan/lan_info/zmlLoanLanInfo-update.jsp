<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>借款土地信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlLoanLanInfoController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlLoanLanInfoPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlLoanLanInfoPage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlLoanLanInfoPage.updateDate }">
					<input id="version" name="version" type="hidden" value="${zmlLoanLanInfoPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="userId" type="list"
										dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="${zmlLoanLanInfoPage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								申请:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="applId" type="list"
										dictTable="zml_loan_application" dictField="id" dictText="subject" defaultVal="${zmlLoanLanInfoPage.applId}" hasLabel="false"  title="申请"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申请</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								土地类型:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="lanType" type="list"
										typeGroupCode="lan_type" defaultVal="${zmlLoanLanInfoPage.lanType}" hasLabel="false"  title="土地类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">土地类型</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								总面积:
							</label>
						</td>
						<td class="value">
						     	 <input id="totalArea" name="totalArea" type="text" style="width: 150px" class="inputxt"  datatype="n" value='${zmlLoanLanInfoPage.totalArea}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总面积</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								面积单位:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="areaUnit" type="list"
										typeGroupCode="area_unit" defaultVal="${zmlLoanLanInfoPage.areaUnit}" hasLabel="false"  title="面积单位"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">面积单位</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								位置:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="position" type="list"
										typeGroupCode="lan_position" defaultVal="${zmlLoanLanInfoPage.position}" hasLabel="false"  title="位置"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">位置</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						  	 	<textarea id="remarks" style="width:600px;" class="inputxt" rows="6" name="remarks">${zmlLoanLanInfoPage.remarks}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
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
  <script src = "webpage/com/zml_loan/lan_info/zmlLoanLanInfo.js"></script>		
