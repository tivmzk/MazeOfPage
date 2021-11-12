<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="wrapper">
	<div>
		<h2>공지사항</h2>
		<a href="../list">목록으로 돌아가기</a>
	</div>
	<div>
		<div>
			<h3>${item.title}</h3>
			<span>${item.nickname}</span>
			<span><fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd"/></span>
		</div>
		<div>
			<p>
				${item.contents}
			</p>
		</div>
	</div>
</div>