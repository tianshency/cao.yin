<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>用户支付流水</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlUserPayTurnoverController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlUserPayTurnoverPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlUserPayTurnoverPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlUserPayTurnoverPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlUserPayTurnoverPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlUserPayTurnoverPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlUserPayTurnoverPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlUserPayTurnoverPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlUserPayTurnoverPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlUserPayTurnoverPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlUserPayTurnoverPage.version }">
					<input id="remarks" name="remarks" type="hidden" value="${zmlUserPayTurnoverPage.remarks }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								订单编号:
							</label>
						</td>
						<td class="value">
						     	 <input id="orderNum" name="orderNum" type="text" style="width: 150px" class="inputxt"  value='${zmlUserPayTurnoverPage.orderNum}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单编号</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								支付类型:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="payType" type="list"
										typeGroupCode="pay_type" defaultVal="${zmlUserPayTurnoverPage.payType}" hasLabel="false"  title="支付类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								支付方式:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="payWay" type="list"
										typeGroupCode="pay_way" defaultVal="${zmlUserPayTurnoverPage.payWay}" hasLabel="false"  title="支付方式"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付方式</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								支付方式名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="payName" name="payName" type="text" style="width: 150px" class="inputxt"  value='${zmlUserPayTurnoverPage.payName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付方式名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								总金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="totalAmount" name="totalAmount" type="text" style="width: 150px" class="inputxt"  value='${zmlUserPayTurnoverPage.totalAmount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总金额</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								付款金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="payAmout" name="payAmout" type="text" style="width: 150px" class="inputxt"  value='${zmlUserPayTurnoverPage.payAmout}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">付款金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								状态:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="status" type="list"
										typeGroupCode="pay_sts" defaultVal="${zmlUserPayTurnoverPage.status}" hasLabel="false"  title="状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
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
  <script src = "webpage/com/zml/pay_turnover/zmlUserPayTurnover.js"></script>		
