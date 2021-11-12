<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="wrapper" id="post-edit-wrapper">
	<div class="article-title pt-30 pb-10 border-b-1 border-color-sub header">
		<h2 class="text-black">공지사항 수정</h2>
	</div>
	<div class="inner-wrapper">
		<form method="post" class="form">
			<input type="hidden" value="${item.mgr}" name="mgr">
			<div class="title">
				<label>제목</label>
				<input type="text" name="title" value="${item.title}" required/>
			</div>
			<div class="contents">
				<label>내용</label>
				<textarea name="contents" id="" cols="30" rows="10" required>${item.contents}</textarea>
			</div>
			<div class="btn-box">
				<div class="button mr-5">
					<button>확인</button>
				</div>
				<div class="button btn-gray">
					<a href="../list" class="back">취소</a>
				</div>
			</div>
		</form>
	</div>
</div>