<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="wrapper" id="post-view-wrapper">
	<div class="pos-relative article-title pt-30 pb-10 border-b-1 border-color-sub header">
		<h2 class="text-black">감상평</h2>
		<a  class="back" href="../list">목록으로 돌아가기</a>
	</div>
	<div class="form pos-relative">
		<div>
			<h3 class="title">${item.title}</h3>
			<a class="novel" href="/novel/detail/${item.novel}">${item.novelTitle}</a>
			<span class="nickname">${item.nickname}</span>
			<span class="date"><fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd"/></span>
			<c:if test="${item.member == sessionScope.user.id}">
				<div class="btn-box">
					<div>
						<a class="update" href="../update/${item.code}">수정</a>
					</div>
					<div>
						<a class="delete" href="../delete/${item.code}">삭제</a>
					</div>
				</div>
			</c:if>
		</div>
		<div class="contents-wrapper">
			<p class="contents">
				${item.contents}
			</p>
		</div>
	</div>
</div>