<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/content/jsp/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AddDept</title>
<script type="text/javascript" src="bootstrap/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(function(){
		$("#add").click(function(){
			$("#addform").attr("action","addjob");
		});
		$("#cancle").click(function(){
			$("#addform").attr("action","joblist");
		});
	});
</script>
</head>
<body>
	<div style="float:left;">
		<%@ include file="/WEB-INF/content/jsp/left.jsp" %>
	</div>
	<div style="margin-left:180px">
	<!-- adddept -->
		<s:form action="" id="addform">
			<table class="table table-hover">
				<tr align="center">
					<td>职位名称:</td><td><s:textfield name="jobbean.name"/></td>
					<td>描述:</td><td><s:textfield name="jobbean.remark"/></td>
				</tr>
			</table>
			<button id="add" class="btn btn-default">添加</button>
			<button id="cancle" class="btn btn-default">取消</button>
		</s:form>
	</div>
</body>
</html>