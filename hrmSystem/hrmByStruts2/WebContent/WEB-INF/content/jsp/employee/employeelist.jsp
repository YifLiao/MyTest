<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/WEB-INF/content/jsp/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QueryEmployee</title>
<script type="text/javascript">
	var currentPage = ${emplResult.currentPage};
	var totalPage = ${emplResult.totalPage};
	function toPage(i){
		document.getElementById("form").action='empllist?pageNum='+i;
		document.getElementById("form").submit();
	}
	$(function(){
		$("#search").click(function(){
			$("#form").attr("action","empllist");
		});
		$("#delete").click(function(){
			$("#form").attr("action","deleteempl");
		});
		
		//分页
		$("#firstPage").click(function(){
			if(currentPage==1){
				alert("这是第一页！");
				return false;
			}else{
				$("#form").attr("action","empllist?pageNum="+1);
				$("#form").submit();
				return true;
			}
		});
		
		$("#prevPage").click(function(){
			if(currentPage==1){
				alert("这是第一页！");
				return false;
			}else{
				$("#form").attr("action","empllist?pageNum="+(currentPage-1));
				$("#form").submit();
				return true;
			}
		});
		
		$("#nextPage").click(function(){
			if(currentPage==totalPage){
				alert("这是最后一页！");
				return false;
			}else{
				$("#form").attr("action","empllist?pageNum="+(currentPage+1));
				$("#form").submit();
				return true;
			}
		});
		
		$("#lastPage").click(function(){
			if(currentPage==totalPage){
				alert("这是最后一页！");
				return false;
			}else{
				$("#form").attr("action","empllist?pageNum="+totalPage);
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
			<!-- 搜索员工 -->
			<table class="table table-hover">
				<tr>
					<%-- <s:textfield name="searchEmpname"/> --%>
					<td>
						职位：<s:select list="joblist" name="searchJobname" listValue="name" listKey="id" headerKey="-1" headerValue="请选择"/>
					</td>
					<td>姓名：<s:textfield name="searchName"/></td>
					<td>身份证号码：<s:textfield name="searchCardId"/></td>
				</tr>
				<tr>
					<td>性别：<s:select list="#{0:'女',1:'男'}" name="searchSex" listValue="value" listKey="key" headerKey="-1" headerValue="请选择"/></td>
					<td>手机：<s:textfield name="searchTel" /></td>
					<td>所属部门：<s:select list="deptlist" name="searchDeptname" listValue="name" listKey="id" headerKey="-1" headerValue="请选择" /></td>
				</tr>
			</table>
			
			<button id="search" class="btn btn-default">搜索</button>&nbsp;
			<button id="delete" class="btn btn-default">删除</button>
			<!-- 显示用户列表 -->
			<table class="table table-hover">
				<tr align="center">
					<th><input type="checkbox" name="checkAll" id="checkAll"></th>
					<th>姓名</th>
					<th>性别</th>
					<th>手机号码</th>
					<th>邮箱</th>
					<th>职位</th>
					<th>学历</th>
					<th>身份证号码</th>
					<th>部门</th>
					<th>联系地址</th>
					<th>建档日期</th>
					<th>操作</th>
				</tr>
				<s:iterator value="#session.empllist" var="empl" status="stat"> 
					<s:url value="toeditempl" var="toedit">
						<s:param name="index" value="#stat.index"/>
						<s:param name="pageNum" >
							<s:property value="#session.emplResult.currentPage"/>
						</s:param>
					</s:url>
				    <tr>
						<td><input class="checkboxSub" type="checkbox" value='<s:property value='#empl.id'/>' name="indexs"/></td>
						<td><s:property value="#empl.name"/></td>
						<s:if test="#empl.sex==1"></s:if>
						<td>
							<s:if test="#empl.sex==1">
								男
							</s:if>
							<s:elseif test="#empl.sex==0">
								女
							</s:elseif>
						</td>
						<td><s:property value="#empl.phone"/></td>
						<td><s:property value="#empl.eMail"/></td>
						<td><s:property value="#empl.job.name"/></td>
						<td><s:property value="#empl.education"/></td>
						<td><s:property value="#empl.cardId"/></td>
						<td><s:property value="#empl.dept.name"/></td>
						<td><s:property value="#empl.address"/></td>
						<td><s:date name="#empl.createDay" format="yyyy年MM月dd日"/></td>
						<td><s:a href="%{#toedit}">操作</s:a></td>
					</tr>
				</s:iterator>
			</table>
			
		</s:form>
		<span>共<s:property value="#session.emplResult.totalRecord"/>条记录
		共<s:property value="#session.emplResult.totalPage"/>页&nbsp;&nbsp;
		当前第<s:property value="#session.emplResult.currentPage"/>页&nbsp;&nbsp;</span>
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