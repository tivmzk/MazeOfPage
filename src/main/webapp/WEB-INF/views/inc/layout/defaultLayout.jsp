<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="../parts/meta.jsp" />
</head>
<body>
	<header id="header">
		<tiles:insertAttribute name="header" />
	</header>
	<nav id="nav">
		<tiles:insertAttribute name="nav" />
	</nav>
	<main id="main">
		<tiles:insertAttribute name="main" />
	</main>
	<footer id="footer">
		<tiles:insertAttribute name="footer" />
	</footer>
</body>
</html>