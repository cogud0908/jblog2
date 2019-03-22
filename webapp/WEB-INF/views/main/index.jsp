<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css" type="text/css">
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<ul class="menu">
			<c:choose>
				<c:when test="${empty loginuser }">
					<li><a href="${pageContext.servletContext.contextPath}/user/login">로그인</a><li>
					<li><a href="${pageContext.servletContext.contextPath}/user/join">회원가입</a><li>
				</c:when>
				<c:otherwise>
					<li>${loginuser.name }님 안녕하세요 ^^;</li>
					<li><a href="${pageContext.servletContext.contextPath}/user/logout">로그아웃</a><li>
					<li><a href="${pageContext.servletContext.contextPath}/${loginuser.id}">내 블로그</a><li>
				</c:otherwise>
			</c:choose>
		</ul>
		<form class="search-form" method="post" action="${pageContext.servletContext.contextPath}/search">
			<fieldset>
				<input type="text" name="keyword" />
				<input type="submit" value="검색" value="${search }"/>
			</fieldset>
			<fieldset>
				<input type="radio" name="which" value="blog-title"> <label>블로그 제목</label>
				<input type="radio" name="which" value="tag"> <label>태그</label>
				<input type="radio" name="which" value="blog-user"> <label>블로거</label>
			</fieldset>
		</form>
		<table class="tbl-ex">
			<tr>
				<th>번호</th>
				<th>블로그이름</th>
				<th>블로거</th>
			</tr>
			<c:forEach items="${list }" var="vo" varStatus="status">
				<tr>
					<c:choose>
						<c:when test="${pagelist[0] == pagelist[3] }">
							<td>[${ (pagelist[4] - status.index) }]</td>
						</c:when>
						<c:otherwise>
							<td>[${ ((pagelist[3]-pagelist[0])*10)+(pagelist[4] - status.index) }]</td>
						</c:otherwise>
					</c:choose>
					<td><a href="${pageContext.servletContext.contextPath}/${vo.id}">${vo.title }</a></td>
					<td>${vo.id}</td>							
				</tr>
			</c:forEach>
		</table>
		<div class="pager">
					<ul>
						<c:if test ="${pagelist[1] ne 1 }">
							<li><a href="${pageContext.servletContext.contextPath }/main?page=${pagelist[1]-1 }&search=${search }">◀</a></li>
						</c:if>
						<c:forEach var = "i" begin ="${pagelist[1] }" end = "${pagelist[2] }" varStatus="status" step = "1">
							<li><a href="${pageContext.servletContext.contextPath }/main?page=${status.index }&search=${search }">${status.index}</a></li>
						</c:forEach>
						<c:if test ="${pagelist[2] ne pagelist[3]}">
							<li><a href="${pageContext.servletContext.contextPath }/main?page=${pagelist[1]+5 }&search=${search }">▶</a></li>
						</c:if>
					</ul>
		</div>
	</div>
</body>
</html>