<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册失败</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
<script src="bootstrap/js/jquery-1.12.4.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<!-- register success-->
	<h4 align="center" class="h4">
		注册失败！请检查登录名或用户状态
		<a href="${pageContext.request.contextPath}/toregister">返回注册</a>
	</h4>

</body>
</html>