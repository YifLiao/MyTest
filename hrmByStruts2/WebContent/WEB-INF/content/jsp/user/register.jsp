<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
<script src="bootstrap/js/jquery-1.12.4.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="bootstrap/js/json2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#register").click(function(){
			$("#form").attr("action","userregister");
		});
		$("#cancle").click(function(){
			$("#form").attr("action","userlist");
		});
	});
	$(function(){
		$("#loginname").blur(function(){
			$.post("${pageContext.request.contextPath}/checkloginname",
					{'userbean.loginname':$("#loginname").val()},function(data,status){
				//alert(data);
				$("#mesg").html(data);
			})
		});
	})
</script>
</head>
<body>
	<!-- register -->
	<s:form action="userregister" id="form" method="post">
		<div class="form-group" style="width:700px;margin: auto;">
			<table class="table table-bordered">
				<tr><th colspan="2"  align="center">新用户注册</th></tr>
				<tr align="left">
					<td>用户名:<s:textfield name="userbean.username"/></td>
					<td>状态:<s:textfield name="userbean.status" disabled="false"/><font>默认状态不能为0！</font></td>
				</tr>
				<tr align="left">
					<td >登录名:<s:textfield name="userbean.loginname" id="loginname"/><font color="red" id="mesg"></font></td>
					<td>密码:<s:textfield name="userbean.password"/></td>
				</tr>
				<tr align="center">
					<td colspan="2">
					<button id="register" class="btn btn-default">注册</button>
					<a id="cancle" class="btn btn-default" href="index.jsp">取消</a></td>
				</tr>
			</table>
		</div>
	</s:form>
</body>
</html>