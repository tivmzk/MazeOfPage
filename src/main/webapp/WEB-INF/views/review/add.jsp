<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="wrapper">
	<div>
		<h2>감상평 작성</h2>
	</div>
	<div>
		<form method="post">
			<input type="hidden" value="${sessionScope.user.id}" name="member">
			<input type="hidden" value="${novel}" name="novel">
			<div>
				<label for="">제목</label>
				<input type="text" name="title" required/>
			</div>
			<div>
				<label for="">내용</label>
				<textarea name="contents" id="" cols="30" rows="10" required></textarea>
			</div>
			<div>
				<div>
					<button>확인</button>
				</div>
				<div>
					<span onclick="history.back()">취소</span>
				</div>
			</div>
		</form>
	</div>
</div>