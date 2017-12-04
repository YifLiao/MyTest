<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/content/jsp/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QueryDept</title>
<script type="text/javascript">
	var currentPage = ${deptResult.currentPage};
	var totalPage = ${deptResult.totalPage};
	function toPage(i){
		document.getElementById("form").action='deptlist?pageNum='+i;
		document.getElementById("form").submit();
	}
	$(function(){
		$("#search").click(function(){
			$("#form").attr("action","deptlist");
		});
		$("#delete").click(function(){
			$("#form").attr("action","deletedept");
		});
		
		//分页
		$("#firstPage").click(function(){
			if(currentPage==1){
				alert("这是第一页！");
				return false;
			}else{
				$("#form").attr("action","deptlist?pageNum="+1);
				$("#form").submit();
				return true;
			}
		});
		
		$("#prevPage").click(function(){
			if(currentPage==1){
				alert("这是第一页！");
				return false;
			}else{
				$("#form").attr("action","deptlist?pageNum="+(currentPage-1));
				$("#form").submit();
				return true;
			}
		});
		
		$("#nextPage").click(function(){
			if(currentPage==totalPage){
				alert("这是最后一页！");
				return false;
			}else{
				$("#form").attr("action","deptlist?pageNum="+(currentPage+1));
				$("#form").submit();
				return true;
			}
		});
		
		$("#lastPage").click(function(){
			if(currentPage==totalPage){
				alert("这是最后一页！");
				return false;
			}else{
				$("#form").attr("action","deptlist?pageNum="+totalPage);
				$("#form").submit();
				return true;
			}
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
			部门名称：<s:textfield name="searchName"/>
			<button id="search" class="btn btn-default">搜索</button>
			<button id="delete" class="btn btn-default">删除</button>
			<!-- 显示部门列表 -->
			<table class="table table-hover">
				<tr align="center">
					<th><input type="checkbox" name="checkAll" id="checkAll"></th>
					<th>部门名称</th>
					<th>详细信息</th>
					<th>操作</th>
				</tr>
				<s:iterator value="#session.deptlist" var="dept" status="stat"> 
					<s:url value="toeditdept" var="toedit">
						<s:param name="index" value="#stat.index"/>
						<s:param name="pageNum" >
							<s:property value="#session.deptResult.currentPage"/>
						</s:param>
					</s:url>
				    <tr class="list">
						<td><input class="checkboxSub" type="checkbox" value='<s:property value='#dept.id'/>' name="indexs"/></td>
						<td><s:property value="#dept.name"/></td>
						<td><s:property value="#dept.remark"/></td>
						<td><s:a href="%{#toedit}">操作</s:a></td>
					</tr>
				</s:iterator>
			</table>
			
		</s:form>
		<span>共<s:property value="#session.deptResult.totalRecord"/>条记录
		共<s:property value="#session.deptResult.totalPage"/>页&nbsp;&nbsp;
		当前第<s:property value="#session.deptResult.currentPage"/>页&nbsp;&nbsp;</span>
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
</body>
</html>