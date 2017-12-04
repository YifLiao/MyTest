<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户注册</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
<script src="bootstrap/js/jquery-1.12.4.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	$(function(){
		$("#name").blur(function(){
			$.post("${pageContext.request.contextPath}/checkregister",
				{'user.name':$("#name").val()},function(data,status){
					alert(data);
				})
		});
		$("#rePass").blur(function(){
			$.post("${pageContext.request.contextPath}/checkregister",
					{'user.pass':$("#pass").val(),'rePass':$("#rePass").val()},
					function(data,status){
						alert(data);
					})
		});
	})
</script>
</head>
<body>
	<div style="width: 500px;height: 400px;margin:0 auto;">
		<s:form action="userAction_register">
			<table class="table">
				<tr align="center">
					<td><s:textfield name="user.name" placeholder="请输入账号" id="name"/></td>
				</tr>
				<tr align="center">
					<td><s:password name="user.pass" placeholder="请输入6-16位密码" id="pass"/></td>
				</tr>
				<tr align="center">
					<td><s:password name="repass" placeholder="请重新输入密码" id="rePass"/></td>
				</tr>
				<tr align="center">
					<td><input type="submit" class="btn btn-default" value="注册"></td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>