<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="/zhuminle/appUserController.do?login" method="POST">
	<table>
		<tr>
			<tb>用户名：</tb>
			<tb><input type="text" name="userName"/></tb>
		</tr>
		<tr>
			<tb>密码：</tb>
			<tb><input type="text" name="userPwd"/></tb>
		</tr>
		<tr>
			<tb><input type="submit" value="登 录"/></tb>
		</tr>
	</table>
</form>
</body>
</html>