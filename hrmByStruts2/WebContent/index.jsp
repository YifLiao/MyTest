<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
<script src="bootstrap/js/jquery-1.12.4.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	$(function(){
		$("#login").click(function(){
			$("#form").attr("action","userlogin");
		});
		$("#register").click(function(){
			$("#form").attr("action","toregister");
		});
	});
</script>
</head>
<body>
	<s:form action="userlogin" id="form">
		<table class="table">
			<tr align="center">
				<td><label>登录名：</label>
				<s:textfield name="user.loginname"/></td>
			</tr>
			<tr align="center">
				<td><label>密码：</label>
				<s:password name="user.password"/></td>
			</tr>
			<tr align="center">
				<td>
					<button class="btn btn-default" id="login">登录</button>
					<button class="btn btn-default" id="register">注册</button>
				</td>
			</tr>
		</table>
	</s:form>
</body>
</html>