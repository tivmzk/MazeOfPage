<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="/js/thumbnail_change.js"></script>
<div class="wrapper">
	<div class="article-title border-b-1 border-color-sub pt-20">
		<h2 class="text-black">수정</h2>
	</div>
	<div class="py-50">
		<form method="post" class="novel-form" enctype="multipart/form-data">
			<input type="text" value="${sessionScope.user.id}" hidden="hidden" name="member">
			<div class="flex justify-between">
				<div class="pr-10">
					<div class="form-input">
						<label>제목</label>
						<input type="text" class="w-100p" name="title" value="${novel.title}">
					</div>
					<div class="form-input">
						<label>장르</label>
						<select name="genre">
							<c:forEach var="item" items="${list}">
								<option value="${item.code}" ${novel.genre == item.code ? 'selected' : ''}>${item.contents}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div >
					<input type="file" hidden="hidden" id="hidden-input" name="thumbnail">
					<div class="thumbnail active">
						<div class="no-input">
							<img src="/svg/add_file.svg">
							<span>썸네일을<br> 등록하려면 클릭</span>
						</div>
						<div class="input">
							<img id="input-image" src="/thumbnail/${novel.image == null ? novel.preview : novel.image.fullname}">
						</div>
					</div>
				</div>
			</div>
			<div class="form-input">
				<label>내용</label>
				<textarea rows="20" cols="50" name="info">${novel.info}</textarea>
			</div>
			<div class="py-30 flex justify-end">
				<div class="btn">
					<button>확인</button>
				</div>
			</div>
		</form>
	</div>
</div>