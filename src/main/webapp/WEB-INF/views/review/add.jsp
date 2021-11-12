<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="wrapper" id="post-edit-wrapper">
	<div class="article-title pt-30 pb-10 border-b-1 border-color-sub header">
		<h2 class="text-black">감상평 작성</h2>
	</div>
	<div class="inner-wrapper">
		<form method="post" class="form">
			<input type="hidden" value="${sessionScope.user.id}" name="member">
			<input type="hidden" value="${novel}" name="novel">
			<div class="title">
				<label>제목</label>
				<input type="text" name="title" required/>
			</div>
			<div class="contents">
				<label for="">내용</label>
				<textarea name="contents" id="" cols="30" rows="10" required></textarea>
			</div>
			<div class="btn-box">
				<div class="button mr-5">
					<button>확인</button>
				</div>
				<div class="button btn-gray">
					<span onclick="history.back()">취소</span>
				</div>
			</div>
		</form>
	</div>
</div>