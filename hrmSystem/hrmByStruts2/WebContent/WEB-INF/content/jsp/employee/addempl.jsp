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
			$("#addform").attr("action","addempl");
		});
		$("#cancle").click(function(){
			$("#addform").attr("action","empllist");
		});
	});
	$(function(){
		$("#cardId").blur(function(){
			$.post("${pageContext.request.contextPath}/checkCardId",
					{'emplbean.cardId':$("#cardId").val()},
					function(data,status){
						$("#mesg").html(data);
					});
		});
	})
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
				<tr align="left">
					<td>姓名:</td><td><s:textfield name="emplbean.name"/></td>
					<td>身份证号码:</td><td><s:textfield name="emplbean.cardId" id="cardId"/><font color="red" id="mesg"></font></td>
				</tr>
				<tr align="left">
					<td>性别:</td><td><s:select list="#{0:'女',1:'男'}" name="emplbean.sex" listValue="value" listKey="key" headerKey="-1" headerValue="请选择"/></td>
					<td>职位:</td><td><s:select list="joblist" name="emplbean.job.id" listValue="name" listKey="id" headerKey="-1" headerValue="请选择"/></td>
				</tr>
				<tr align="left">
					<td>学历:</td><td><s:textfield name="emplbean.education"/></td>
					<td>邮箱:</td><td><s:textfield name="emplbean.eMail"/></td>
				</tr>
				<tr align="left">
					<td>手机:</td><td><s:textfield name="emplbean.phone"/></td>
					<td>电话:</td><td><s:textfield name="emplbean.tel"/></td>
				</tr>
				<tr align="left">
					<td>政治面貌:</td><td><s:textfield name="emplbean.party"/></td>
					<td>QQ号码:</td><td><s:textfield name="emplbean.qqNum"/></td>
				</tr>
				<tr align="left">
					<td>联系地址:</td><td><s:textfield name="emplbean.address"/></td>
					<td>邮政编码:</td><td><s:textfield name="emplbean.postCode"/></td>
				</tr>
				<tr align="left">
					<td>出生日期:</td><td><s:textfield name="emplbean.birthday"/></td>
					<td>民族:</td><td><s:textfield name="emplbean.race"/></td>
				</tr>
				<tr align="left">
					<td>备注:</td><td><s:textfield name="emplbean.remark"/></td>
					<td>所属部门:</td><td><s:select list="deptlist" name="emplbean.dept.id" listValue="name" listKey="id" headerKey="-1" headerValue="请选择" /></td>
				</tr>
			</table>
			<button id="add" class="btn btn-default">添加</button>
			<button id="cancle" class="btn btn-default">取消</button>
		</s:form>
	</div>
</body>
</html>