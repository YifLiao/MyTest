<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
<script src="bootstrap/js/jquery-1.12.4.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
</head>
<body>
	<div style="width: 500px;height: 400px;margin:0 auto;">
		<s:form action="userAction_login">
			<table class="table">
				<tr>
					<td align="center">用户名：</td>
					<td align="left"><s:textfield name="user.name"/></td>
				</tr>
				<tr align="center">
					<td align="center">密码：</td>
					<td align="left"><s:password name="user.pass"/></td>
				</tr>
				<tr align="center">
					<td colspan="2" align="center">
						<input type="submit" class="btn btn-default" value="登录"/>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>