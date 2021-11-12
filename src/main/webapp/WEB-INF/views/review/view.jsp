<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="wrapper">
	<div>
		<h2>감상평</h2>
		<a href="../list">목록으로 돌아가기</a>
	</div>
	<div>
		<div>
			<h3>${item.title}</h3>
			<span>${item.novelTitle}</span>
			<span>${item.nickname}</span>
			<span><fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd"/></span>
			<c:if test="${item.member == sessionScope.user.id}">
				<div>
					<div>
						<a href="../update/${item.code}">수정</a>
					</div>
					<div>
						<a href="../delete/${item.code}">삭제</a>
					</div>
				</div>
			</c:if>
		</div>
		<div>
			<p>
				${item.contents}
			</p>
		</div>
	</div>
</div>