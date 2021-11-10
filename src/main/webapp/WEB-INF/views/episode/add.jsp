<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script src="/js/episode_edit.js"></script>
<script>
	const novelCode = ${code};
	const mode = 0;
</script>
<div class="wrapper">
	<div class="article-title flex border-b-1 border-color-gray justify-between pt-20">
		<h2 class="bootstrap-none-h text-black">에피소드 작성</h2>
		<c:if test="${episodeList.size() == 0}">
			<h3 class="bootstrap-none-h text-gray">이 에피소드가 시작지점입니다</h3>
		</c:if>
	</div>
	<form method="post" class="episode-form">
		<input type="hidden" value="${episodeList.size() == 0 ? 1 : 0}" name="isStart">
		<div class="form-item">
			<div class="form-input">
				<label for="title">제목</label>
				<input type="text" name="title" id="title"/>
			</div>
		</div>
		<div class="form-item">
			<div class="form-input">
				<label for="episode-story">내용</label>
				<textarea id="episode-story" name="contents"></textarea>
			</div>
		</div>
		<div class="form-item">
			<div class="flex justify-between item-center">
				<div class="form-title">
					<h3 class="">선택지</h3>
				</div>
				<div class="episode-btn-box">
					<button id="episode-add-btn" type="button">+</button>
				</div>
			</div>
			<div class="option-wrapper">
			</div>
		</div>
		<div class="flex justify-end pt-30">
			<div class="button">
				<button id="ok-btn">확인</button>
			</div>
		</div>
	</form>
</div>