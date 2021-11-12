<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	const profile_user = `${item.member}`;
	const profile = {
			nickname:`${item.nickname}`,
			contents:`${item.contents}`
	};
</script>
<script src="/js/modal.js"></script>
<script src="/js/profile.js"></script>
<div class="wrapper" id="profile-wrapper">
	<div class="pos-relative article-title pt-30 pb-10 border-b-1 border-color-sub">
		<h2 class="text-black">프로필</h2>
		<span class="update" id="modal-open-btn">수정</span>
	</div>
	<div>
		<table class="profile-table">
			<tr class="nickname">
				<th>닉네임</th>
				<td>${item.nickname}</td>
			</tr>
			<tr class="contents">
				<th>자기 소개</th>
				<td>${item.contents}</td>
			</tr>
			<tr class="recom">
				<th>총 추천수</th>
				<td>${item.recom}</td>
			</tr>
			<tr class="novel">
				<th>작품들</th>
				<td>
					<ul>
						<c:forEach var="novel" items="${item.novelList}">
							<li>
								<a href="/novel/detail/${novel.code}" class="title">${novel.title}</a> 
								<a href="/novel/list?keyword=${novel.genreContents}&search=3" class="genre">${novel.genreContents}</a>
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
		</table>
	</div>
</div>
<div id="modal-wrapper">
	<form method="post" class="form">
		<h1 class="title">프로필 수정</h1>
		<div class="input-wrapper">
			<input type="text" name="nickname" class="nickname" placeholder="닉네임">
		</div>
		<div class="input-wrapper">
			<textarea cols="30" rows="10" name="contents" class="contents" placeholder="자기소개"></textarea>
		</div>
		<div class="flex justify-end">
			<div class="button">
				<button class="proflie-update-btn">확인</button>
			</div>
		</div>
	</form>
</div>