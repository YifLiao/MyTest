<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>正在播放</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
<script src="bootstrap/js/jquery-1.12.4.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	$(function(){
		$("#favorites").click(function(){
			//alert($("#times").html());
			$.post("${pageContext.request.contextPath}/addGoodCount",
					{'goodCounts':$("#times").html()},
					function(data,status){
						alert(data);
						$("#times").html(data+1);
					})
		});
	})
</script>
</head>
<body>
	<s:form>
		<s:hidden name="moviepath"/>
		<s:hidden name="goodCounts"/>
		<s:hidden name="index"/>
		<div align="center">
			<video src="<s:property value="moviepath"/>" 
			 controls="controls" autoplay="autoplay" width="200px" height="500px">
				您的浏览器不支持 video 标签。
			</video>
		</div>
	<!-- 评论和点赞区 -->
	
		<!-- Ajax刷新 -->
		<!-- 点赞区 -->
		<div>
			<button id="favorites"><img alt="zan" src="img/zan.jpg" width="20px" height="20px"/></button>
			<!-- 显示点赞的次数 -->
			<span id="times"><s:property value="goodCounts"/></span>
		</div>
		<!-- 评论区 -->
		<div>
			<s:textarea cols="14" rows="7" value="content"/>
		</div>
		<div id="comment"></div>
	</s:form>
</body>
</html>