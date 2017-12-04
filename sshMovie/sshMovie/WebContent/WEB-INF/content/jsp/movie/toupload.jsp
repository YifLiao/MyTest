<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传影片</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
<script src="bootstrap/js/jquery-1.12.4.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
</head>
<body>
	<div style="width:500px;height:500px;margin:0 auto">
	<h3 class="h3">上传视频</h3>
		<!-- 上传文件必须设置为post方式提交和enctype为multipart/form-data -->
		<s:form action="movieAction_upload" enctype="multipart/form-data" method="post">
			<table class="table">
				<tr>
					<td>视频名称：</td>
					<td><s:textfield name="movie.mvName"/></td>
				</tr>
				<tr>
					<td>视频描述：</td>
					<td><s:textarea name="movie.mvDesc" rows="5" cols=""/></td>
				</tr>
				<tr>
					<td>标签：</td>
					<td>
					<s:select list="categorylist" name="cateId" listKey="id" listValue="categoryName"></s:select>
					<%-- <s:textfield name="movie.category.categoryName"/> --%>
					</td>
				</tr>
				<tr>
					<td>文件：</td>
					<td><s:file name="upload" /></td>
				</tr>
				<tr>
					<td>缩略图：</td>
					<td><s:file name="upload"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" class="btn btn-default" value="上传">
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>