<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
$(document).ready(function(){
  $(".btn1").click(function(){
  $(".sub").slideToggle();
  console.log("click");
  });
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>left</title>
</head>
<body>
	<s:url var="userlist" value="userlist"></s:url>
	<s:url var="toadduser" value="toadduser"></s:url>
	<s:url var="deptlist" value="deptlist"></s:url>
	<s:url var="toadddept" value="toadddept"></s:url>
	<s:url var="joblist" value="joblist"></s:url>
	<s:url var="toaddjob" value="toaddjob"></s:url>
	<s:url var="empllist" value="empllist"></s:url>
	<s:url var="toaddempl" value="toaddempl"></s:url>
	<s:url var="documlist" value="documlist"></s:url>
	<s:url var="touploaddocum" value="touploaddocum"></s:url>
	<!-- 用户页面相关 -->
	<button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#collapseUser">
		用户管理
	</button>
	<div id="collapseUser" class="collapse">
		<s:a value="%{#userlist}" class="btn btn-default">用户查询</s:a><br>
		<s:a value="%{#toadduser}" class="btn btn-default">添加用户</s:a><br>
	</div><br>
	<!-- 部门页面相关 -->
	<button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#collapseDept">
		部门管理
	</button>
	<div id="collapseDept" class="collapse">
		<s:a value="%{#deptlist}" class="btn btn-default">部门查询</s:a><br>
		<s:a value="%{#toadddept}" class="btn btn-default">添加部门</s:a><br>
	</div><br>	
	
	<!-- 用职位面相关 -->
	<button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#collapseJob">
		职位管理
	</button>
	<div id="collapseJob" class="collapse">
		<s:a value="%{#joblist}" class="btn btn-default">职位查询</s:a><br>
		<s:a value="%{#toaddjob}" class="btn btn-default">添加职位</s:a><br>
	</div><br>
	<!-- 员工页面相关 -->
	<button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#collapseEmpl">
		员工管理
	</button>
	<div id="collapseEmpl" class="collapse">
		<s:a value="%{#empllist}" class="btn btn-default">员工查询</s:a><br>
		<s:a value="%{#toaddempl}" class="btn btn-default">添加员工</s:a><br>
	</div><br>
	<!-- 文件页面相关 -->
	<button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#collapseFile">
		文件管理
	</button>
	<div id="collapseFile" class="collapse">
		<s:a value="%{#documlist}" class="btn btn-default">文件查询</s:a><br>
		<s:a value="%{#touploaddocum}" class="btn btn-default">上传文件</s:a><br>
	</div><br>
</body>
</html>