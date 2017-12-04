<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
<script src="bootstrap/js/jquery-1.12.4.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	function toPage(i){
		document.getElementById("form").action='movieAction_tosearchmovie?pageNum='+i;
		document.getElementById("form").submit();
	}
</script>
</head>
<body>
	<div align="center">
		<span>共<s:property value="result.totalRecord"/>条记录
			共<s:property value="result.totalPage"/>页&nbsp;&nbsp;
			当前第<s:property value="result.currentPage"/>页&nbsp;&nbsp;</span>
			<ul class="pagination" id="pagination" style="margin: 0 auto;">
			    <li><a href="#" id="firstPage">首页</a></li>
			    <li><a href="#" id="prevPage">上一页</a></li>
			    <s:iterator begin="1" end="result.totalPage" var="i">
			   		 <li>
			   		 	<a href="#" class="toPage" onclick="toPage(<s:property value="#i"/>)">
			   		 		<s:property value="#i"/>
			   		 	</a>
			   		 </li>
			   	</s:iterator>
			   <%-- 	<s:iterator begin="1" end="result.totalPage" var="i">
			   		 <li>
			   		 	<a href="#" class="toPage">
			   		 		<s:property value="#i"/>
			   		 	</a>
			   		 </li>
			   	</s:iterator> --%>
			    <li><a href="#" id="nextPage">下一页</a></li>
			    <li><a href="#" id="lastPage">尾页</a></li>
			</ul>
	</div>
</body>
</html>