<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="wrapper">
	<div>
		<h2>공지사항 수정</h2>
	</div>
	<div>
		<form method="post">
			<input type="hidden" value="${item.mgr}" name="mgr">
			<div>
				<label for="">제목</label>
				<input type="text" name="title" value="${item.title}" required/>
			</div>
			<div>
				<label for="">내용</label>
				<textarea name="contents" id="" cols="30" rows="10" required>${item.contents}</textarea>
			</div>
			<div>
				<div>
					<button>확인</button>
				</div>
				<div>
					<a href="../list">취소</a>
				</div>
			</div>
		</form>
	</div>
</div>