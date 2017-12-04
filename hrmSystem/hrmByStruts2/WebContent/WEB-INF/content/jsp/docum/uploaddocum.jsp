<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/content/jsp/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UploadFile</title>
<script type="text/javascript" src="bootstrap/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(function(){
		$("#add").click(function(){
			$("#addform").attr("action","uploaddocum");
		});
		$("#cancle").click(function(){
			$("#addform").attr("action","documlist");
		});
	});
</script>
</head>
<body>
	<div style="float:left;">
		<%@ include file="/WEB-INF/content/jsp/left.jsp" %>
	</div>
	<div style="margin-left:180px">
	<!-- upload -->
		<s:form action="" id="addform" enctype="multipart/form-data" method="post">
			<table class="table table-hover">
				<tr align="center">
					<td>文件名称:</td><td><s:textfield name="documbean.title"/></td>
					<td>描述:</td><td><s:textfield name="documbean.remark"/></td>
				</tr>
			</table><hr>
			<s:file name="upload" label="选择文件" id="inputfile"/>
			<button id="add" class="btn btn-default">添加</button>
			<button id="cancle" class="btn btn-default">取消</button>
		</s:form>
	</div>
</body>
</html>