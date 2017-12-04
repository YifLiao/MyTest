<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
<script src="bootstrap/js/jquery-1.12.4.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	var currentPage = ${result.currentPage};
	var totalPage = ${result.totalPage};
	$(function(){
		$("#search").click(function(){
			$("#form").attr("action","movieAction_tosearchmovie");
		});
		//分页
		$("#firstPage").click(function(){
			if(currentPage==1){
				alert("这是第一页！");
				return false;
			}else{
				$("#form").attr("action","movieAction_tosearchmovie?pageNum="+1);
				$("#form").submit();
				return true;
			}
		});
		
		$("#prevPage").click(function(){
			if(currentPage==1){
				alert("这是第一页！");
				return false;
			}else{
				$("#form").attr("action","movieAction_tosearchmovie?pageNum="+(currentPage-1));
				$("#form").submit();
				return true;
			}
		});
		
		$("#nextPage").click(function(){
			if(currentPage==totalPage){
				alert("这是最后一页！");
				return false;
			}else{
				$("#form").attr("action","movieAction_tosearchmovie?pageNum="+(currentPage+1));
				$("#form").submit();
				return true;
			}
		});
		
		$("#lastPage").click(function(){
			if(currentPage==totalPage){
				alert("这是最后一页！");
				return false;
			}else{
				$("#form").attr("action","movieAction_tosearchmovie?pageNum="+totalPage);
				$("#form").submit();
				return true;
			}
		});
		
/*  		$(".toPage").click(function(i){
			
			$("#form").attr("action","movieAction_tosearchmovie?pageNum="+i);
			$("#form").submit();
			return true;
		});  */
	});
</script>
<title>影片列表</title>
</head>
<body>
	<h3>搜索列表</h3>
	<s:form id="form">
		<s:textfield name="searchMovie" placeholder="请输入影片名字"/>
		<button class="btn btn-default" id="search">搜索</button>
		<hr class="hr">
		<!-- 遍历movielist -->
		<div class="container">
			<div class="row" style="margin-top: 20px">
				<s:iterator value="movielist" var="movie">
					<s:url value="movieAction_playmovie" var="playmovie">
						<s:param name="moviepath" value="#movie.filepath"/>
						<s:param name="goodCounts" value="#movie.goodCount"/>
						<s:param name="index" value="#movie.id"/>
					</s:url>
					<div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
						<div class="media" style="background:white;border:none;">
							<s:a value="%{#playmovie}" target="_blank">
								<div class="thumbnail" style="border:none">
									<img alt="cover" src="<s:property value="#movie.filepic"/>" width="100px" height="135px">
								</div>
								<div class="caption" align="center">
									<s:property value="#movie.mvName"/>
								</div>
							</s:a>
						</div>
					</div>
				</s:iterator>
			</div>
			<br>
		<!-- 分页 -->
			<s:include value="/WEB-INF/content/jsp/page/pager.jsp" />
		</div>
	</s:form>
</body>
</html>