<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/content/jsp/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AddUser</title>
<script type="text/javascript" src="bootstrap/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(function(){
		$("#add").click(function(){
			$("#addform").attr("action","adduser");
		});
		$("#cancle").click(function(){
			$("#addform").attr("action","userlist");
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
	<div style="float:left;">
		<%@ include file="/WEB-INF/content/jsp/left.jsp" %>
	</div>
	<div style="margin-left:180px">
	<!-- adduser -->
		<s:form action="adduser" id="addform">
			<table class="table table-hover">
				<tr align="left">
					<td>用户名:</td><td><s:textfield name="userbean.username"/></td>
					<td>状态:</td><td><s:textfield name="userbean.status"/></td>
				</tr>
				<tr align="left">
					<td>登录名:</td><td><s:textfield name="userbean.loginname" id="loginname"/><font color="red" id="mesg"></font></td>
					<td>密码:</td><td><s:textfield name="userbean.password"/></td>
				</tr>
			</table>
			<button id="add" class="btn btn-default">添加</button>
			<button id="cancle" class="btn btn-default">取消</button>
		</s:form>
	</div>
</body>
</html>