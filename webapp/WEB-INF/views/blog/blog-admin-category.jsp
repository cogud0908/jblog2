<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">

var count = 0;
var deleteNo = 0;

$(document).ready(function() { 
	
	var categoryAjax = function() {

		$.ajax({
			url: "${pageContext.request.contextPath }/api/category/list/${loginuser.id}",
			type: "get",
			dataType: "json",
			data: "",
			success: function(response){
				
				if(response.result == "fail"){
					console.warn(repsonse.data);
					return;
				}
				
				$.each(response.data, function(index, vo){
					render(vo, false);
				});
			},
			error: function(xhr, status, e){
				console.error(status + ":"+ e);
			}
		});
	}
	
	categoryAjax();
});
	

var render = function(vo, mode){
	
	// 현업에 가면 이렇게 안한다.
	var htmls = 
		"<tr>" +
		"<td>"+(++count)+"</td>"+
		"<td>"+vo.name+"</td>" +
		"<td>"+vo.description+"</td>" +
		"<td>"+vo.postCount+"</td>" +
		"<td>";
		if(vo.postCount == 0) {
			htmls += "<a id ='ajax-delete' href='' data-no='"+vo.no+"'>" +
			"<img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></a>";
		}
		htmls += "</td>"+
		"</tr>"; 
				
	if(mode == true)
	{
		$("#ajax-list").prepend(htmls);
	} else{
		$("#ajax-list").append(htmls);
	}
}

var categoryDeleteAjax = function() {
					
	$.ajax({
		url: "${pageContext.request.contextPath }/api/category/delete?no="+deleteNo,
		type: "get",
		dataType: "json",
		data: "",
		success: function(response){
			
			if(response.result == "fail"){
				console.warn(repsonse.data);
				return;
			}
			
		},
		error: function(xhr, status, e){
			console.error(status + ":"+ e);
		}
	});
};
	
var categoryInsertAjax = function() {
	
		$.ajax({
			url: "${pageContext.request.contextPath }/api/category/insert/${loginuser.id}",
			type: "get",
			dataType: "json",
			data: encodeURI("name="+$("#name").val()+"&description="+$("#description").val()),
			success: function(response){
				if(response.result == "fail"){
					console.warn(repsonse.data);
					return;
				}
				
				render(response.data, false);
				
				$("#name").val("");
				$("#description").val("");
			},
			error: function(xhr, status, e){
				console.error(status + ":"+ e);
			}
		});
	
}

$(function() {
	$("#category-insert").submit(function(){
		event.preventDefault();
		categoryInsertAjax();
	});	
});

$(document).on("click","#ajax-list td a", function(){
	event.preventDefault();
	console.log("clicked:"+$(this).data('no'));
	deleteNo = $(this).data("no");
	
	categoryDeleteAjax();
	
	$(this).parent().parent().remove();
});



</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>Spring 이야기</h1>
			<ul>
				<li><a href="${pageContext.servletContext.contextPath}/${loginuser.id}">블로그 메인</a><li>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<li>${loginuser.name }님 안녕하세요 ^^;</li>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.servletContext.contextPath}/admin/${loginuser.id}">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.servletContext.contextPath}/admin/write/${loginuser.id}">글작성</a></li>
				</ul>
		      	<table class="admin-cat" id = "ajax-list">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>설명</th>
		      			<th>포스트 수</th>
		      			<th>삭제</th>      			
		      		</tr>	      		
		      		<%-- <c:forEach items="${list }" var="vo" varStatus="status">
					<tr id ="ajax-list" data-no="${vo.no }">
						<td>${(status.index)+1}</td>
						<td>${vo.name }</td>
						<td>${vo.description }</td>
						<td><a id ="ajax-delete" href="">
						<img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a></td>
					</tr>  		  
					</c:forEach> --%>
				</table>
      	
      			<form action="" id = "category-insert">
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" id = "name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="description" id="description"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input id="ajax-submit" type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
		      	</form>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>