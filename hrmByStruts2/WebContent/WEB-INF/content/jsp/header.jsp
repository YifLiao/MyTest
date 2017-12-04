<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
<script src="bootstrap/js/jquery-1.12.4.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	$(function(){
		$("#checkAll").click(function(){
			$(".checkboxSub").prop("checked",this.checked);
		})
	});
</script>
</head>
<body>
	<s:url var="logout" value="logout"></s:url>
	<div style="margin-left:50px;">
		<h3 class="h3">
			欢迎！<s:property value="#session.loginname"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<s:a value="%{#logout}">注销</s:a>
		</h3>
	</div>
	<hr>
</body>
</html>