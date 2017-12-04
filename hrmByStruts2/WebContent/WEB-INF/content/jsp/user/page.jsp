<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/content/jsp/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="bootstrap/css/pagination.css" />
<script type="text/javascript" src="bootstrap/js/jquery.pagination.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
//点击分页按钮后触发的动作
var currentPage = ${result.currentPage};
var totalPage = ${result.totalPage};

function toPage(i){
	document.getElementById("form").action='userlist?pageNum='+i;
	document.getElementById("form").submit();
}
$(function(){
	$("#firstPage").click(function(){
		if(currentPage==1){
			alert("这是第一页！");
			return false;
		}else{
			$("#form").attr("action","userlist?pageNum="+1);
			$("#form").submit();
			return true;
		}
	});
	
	$("#prevPage").click(function(){
		if(currentPage==1){
			alert("这是第一页！");
			return false;
		}else{
			$("#form").attr("action","userlist?pageNum="+(currentPage-1));
			$("#form").submit();
			return true;
		}
	});
	
	$("#nextPage").click(function(){
		if(currentPage==totalPage){
			alert("这是最后一页！");
			return false;
		}else{
			$("#form").attr("action","userlist?pageNum="+(currentPage+1));
			$("#form").submit();
			return true;
		}
	});
	
	$("#lastPage").click(function(){
		if(currentPage==totalPage){
			alert("这是最后一页！");
			return false;
		}else{
			$("#form").attr("action","userlist?pageNum="+totalPage);
			$("#form").submit();
			return true;
		}
	});
});

$(function(){
	$("#search").click(function(){
		$("#form").attr("action","userlist");
	});
	$("#delete").click(function(){
		$("#form").attr("action","deleteuser");
	});
});
</script>
</head>
<body>
	<div style="float:left;">
		<%@ include file="/WEB-INF/content/jsp/left.jsp" %>
	</div>
	<div style="margin-left:180px">
		<s:form  id="form">
			<!-- 搜索用户 -->
			用户名：<s:textfield name="searchUsername"/>
			状态：<s:textfield name="searchStatus"/>
			<button id="search" class="btn btn-default">搜索</button>
			<button id="delete" class="btn btn-default">删除</button>
			<!-- 显示用户列表 -->
			<table class="table table-hover">
				<tr align="center">
					<th><input type="checkbox" name="checkAll" id="checkAll"></th>
					<th>登录名</th>
					<th>密码</th>
					<th>用户名</th>
					<th>状态</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
				<s:iterator value="#session.userlist" var="user" status="stat"> 
					<s:url value="toedituser" var="toedit">
						<s:param name="index" value="#stat.index"/>
					</s:url>
				    <tr>
						<td><input class="checkboxSub" type="checkbox" value='<s:property value='#user.id'/>' name="indexs"/></td>
						<td><s:property value="#user.loginname"/></td>
						<td><s:property value="#user.password"/></td>
						<td><s:property value="#user.username"/></td>
						<td><s:property value="#user.status"/></td>
						<td><s:date name="#user.createdate" format="yyyy年MM月dd日"/></td>
						<td><s:a href="%{#toedit}">操作</s:a></td>
					</tr>
				</s:iterator>
			</table>
		</s:form><br/>
		共${result.totalRecord }条记录共${result.totalPage }页&nbsp;&nbsp;
		当前第${result.currentPage }页&nbsp;&nbsp;
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
		    <li><a href="#" id="nextPage">下一页</a></li>
		    <li><a href="#" id="lastPage">尾页</a></li>
		</ul>
	</div>
	<!-- <div id="News-Pagination" style="margin:0 auto;"></div> -->
</body>
</html>