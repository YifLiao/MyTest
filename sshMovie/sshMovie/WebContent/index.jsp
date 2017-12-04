<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>粤嵌影视</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
<script src="bootstrap/js/jquery-1.12.4.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	$(function(){
		$("#search").click(function(){
			$("#searchForm").attr("action","movieAction_tosearchmovie");
		});
	})
</script>
</head>
<body>
	<div>
		<s:url value="movieAction_toupload" var="toupload">
		</s:url>
		<s:url value="movieAction_playmovie" var="playmovie">
			 <s:param name="moviepath">movie\movie1.mp4</s:param> 
		</s:url>
		<s:url value="userAction_tologin" var="tologin"/>
		<s:url value="userAction_toregister" var="toregister"/>
		<div>
			<s:form id="searchForm">
				<table class="table">
					<tr>
						<td><img alt="logo" src="img/logo.jpg" width="90px" height="70px"></td>
						<td style="padding-top:30px">
							<s:textfield name="searchMovie" placeholder="请输入影片名字"/>
							<button class="btn btn-default" id="search">搜索</button>
						</td>
						<td style="padding-top:30px">
							<s:a value="%{#toupload}">上传</s:a>
							<s:a value="%{#tologin}">登录</s:a>
							<s:a value="%{#toregister}">注册</s:a>
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<!--导航组件-->
		<ul class="nav nav-tabs" style="background:#F8F8F8;">
			<li class="active"><a href="index.jsp">首页</a></li>
			<li><a href="#">影视</a></li>
			<li><a href="#">动漫</a></li>
			<li><a href="#">MTV</a></li>
		</ul>
	</div>	
	<div class="container">
		<div class="row" style="margin-top: 20px">
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
				<div class="media" style="background:white;border:none;">
					<s:a value="%{#playmovie}" target="_blank">
						<div class="thumbnail" style="border:none">
							<img alt="cover" src="${pageContext.request.contextPath}/movie/cover1.jpg" width="200px" height="100px">
						</div>
						<div class="caption" align="center">
							本年度最佳电影
							<%-- <s:a value="%{#playmovie}" target="_blank">movie1</s:a> --%>
						</div>
					</s:a>
				</div>
			</div>
			<!-- 遍历封面 -->
			<%-- <s:iterator value="movielist" var="movie">
				<div class="media" style="background:white;border:none;">
					<div class="thumbnail" style="border:none">
						<img alt="无法显示" src="<s:url value="#movie.filepic"/>"/>
					</div>
					<!-- <div class="caption" align="center">
						<a href="movie\movie1.mp4">movie1</a>
					</div> -->
				</div>
			</s:iterator> --%>
		</div>
	</div>
</body>
</html>