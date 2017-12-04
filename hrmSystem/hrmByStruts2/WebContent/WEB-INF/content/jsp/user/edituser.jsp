<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/content/jsp/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EditUser</title>
<script type="text/javascript" src="bootstrap/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(function(){
		$("#add").click(function(){
			$("#editform").attr("action","edituser");
		});
		$("#cancle").click(function(){
			$("#editform").attr("action","userlist");
		});
	});
</script>
</head>
<body>
	<div style="float:left;">
		<%@ include file="/WEB-INF/content/jsp/left.jsp" %>
	</div>
	<div style="margin-left:180px">
	<!-- adduser -->
	<h4>edit User</h4>
		<s:form id="editform">
			<s:hidden name="index" />
			<s:hidden name="userbean.id" />
			<s:hidden name="pageNum" />
			<table class="table table-hover">
				<tr align="center">
					<td>姓名:</td><td><s:textfield name="userbean.username"/></td>
					<td>状态:</td><td><s:textfield name="userbean.status"/></td>
				</tr>
				<tr align="center">
					<td>登录名:</td><td><s:textfield name="userbean.loginname"/></td>
					<td>密码:</td><td><s:textfield name="userbean.password"/></td>
				</tr>
			</table>
			<button id="add" class="btn btn-default">保存</button>
			<button id="cancle" class="btn btn-default">取消</button>
		</s:form>
	</div>
</body>
</html>