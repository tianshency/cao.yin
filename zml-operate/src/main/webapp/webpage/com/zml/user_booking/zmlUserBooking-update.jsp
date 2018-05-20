<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>我的预定</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlUserBookingController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlUserBookingPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlUserBookingPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlUserBookingPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlUserBookingPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlUserBookingPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlUserBookingPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlUserBookingPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlUserBookingPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlUserBookingPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlUserBookingPage.version }">
					<input id="userName" name="userName" type="hidden" value="${zmlUserBookingPage.userName }">
					<input id="otherSideName" name="otherSideName" type="hidden" value="${zmlUserBookingPage.otherSideName }">
					<input id="bookingDetail" name="bookingDetail" type="hidden" value="${zmlUserBookingPage.bookingDetail }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户ID:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="userId" type="list"
										dictTable="zml_user" dictField="id" dictText="user_name" defaultVal="${zmlUserBookingPage.userId}" hasLabel="false"  title="用户ID"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户ID</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								对方ID:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="otherSideId" type="list"
										dictTable="zml_user" dictField="id" dictText="user_name" defaultVal="${zmlUserBookingPage.otherSideId}" hasLabel="false"  title="对方ID"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">对方ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								预订类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="bookingType" name="bookingType" type="text" style="width: 150px" class="inputxt"  value='${zmlUserBookingPage.bookingType}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预订类型</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								预订ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="bookingId" name="bookingId" type="text" style="width: 150px" class="inputxt"  value='${zmlUserBookingPage.bookingId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预订ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								数量:
							</label>
						</td>
						<td class="value">
						     	 <input id="bookingNum" name="bookingNum" type="text" style="width: 150px" class="inputxt"  value='${zmlUserBookingPage.bookingNum}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">数量</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								计划交易日:
							</label>
						</td>
						<td class="value">
									  <input id="planDealDate" name="planDealDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${zmlUserBookingPage.planDealDate}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">计划交易日</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						     	 <input id="remarks" name="remarks" type="text" style="width: 150px" class="inputxt"  value='${zmlUserBookingPage.remarks}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								状态:
							</label>
						</td>
						<td class="value">
						     	 <input id="status" name="status" type="text" style="width: 150px" class="inputxt"  value='${zmlUserBookingPage.status}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml/user_booking/zmlUserBooking.js"></script>		
