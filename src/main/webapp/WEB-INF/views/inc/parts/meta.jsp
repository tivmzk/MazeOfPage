<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>Maze Of Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> -->
<script src="/js/jQuery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/elements.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/Cat.png" />
<script>
	const curr_user = {
			id:`${sessionScope.user.id}`,
			nickname:`${sessionScope.user.nickname}`,
			mgr:`${sessionScope.user.mgr}`
	};
</script>