<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="/js/modal.js"></script>
<div class="wrapper">
	<div>
		<h2>프로필</h2>
		<span id="modal-open-btn">수정</span>
	</div>
	<div>
		<table id="profile-wrapper">
			<tr class="nickname">
				<th>닉네임</th>
				<td>${item.nickname}</td>
			</tr>
			<tr class="intro">
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
							<li>${novel.title} <span>${novel.genreContents}</span></li>
						</c:forEach>
					</ul>
				</td>
			</tr>
		</table>
	</div>
</div>
<div id="modal-wrapper">
	<div class="form">
		<h1>프로필 수정</h1>
		<div>
			<input type="text" class="nickname">
		</div>
		<div>
			<textarea name="" id="" cols="30" rows="10" class="intro"></textarea>
		</div>
		<div>
			<button class="proflie-update-btn">확인</button>
		</div>
	</div>
</div>