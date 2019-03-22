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
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogVo.title}</h1>
			<ul>
				<c:choose>
				<c:when test="${empty loginuser }">
					<li><a href="${pageContext.servletContext.contextPath}/user/join">회원가입</a><li>
					<li><a href="${pageContext.servletContext.contextPath}/user/login">로그인</a><li>
				</c:when>
				<c:otherwise>
					<c:choose>
					<c:when test="${loginuser.id eq siteId}">
						<li><a href="${pageContext.servletContext.contextPath}/admin/${loginuser.id}">블로그 관리</a><li>
					</c:when>
				    <c:when test="${!empty loginuser }">
				    	<li><a href="${pageContext.servletContext.contextPath}/${loginuser.id}">내 블로그</a><li>
				    </c:when>
				    </c:choose>
				    <li><a href="${pageContext.servletContext.contextPath}/user/logout">로그아웃</a><li>
				    <li>${loginuser.name }님 안녕하세요 ^^;</li>
				</c:otherwise>
				
				
			</c:choose>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postVo.title }</h4>
					<p>
						${postVo.content}
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postlist}" var="list" varStatus="status">
						<li><a href="${pageContext.servletContext.contextPath}/${siteId}/${list.category_no}/${list.no}">
						${list.title}</a> <span>${list.reg_date }</span>	</li>
					</c:forEach>					
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img id="profile" src="${pageContext.request.contextPath}/${blogVo.logo }" onerror="this.src='${pageContext.request.contextPath }/assets/images/spring-logo.jpg'">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:forEach items="${categorylist }" var="vo" varStatus="status">
				<li><a href="${pageContext.servletContext.contextPath}/${siteId}/${vo.no}">${vo.name }</a></li>
			</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>${blogVo.title }</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>