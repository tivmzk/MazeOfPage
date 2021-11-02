<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	$(function(){
		$('.btn-box .recom').click(function(){
			$(this).toggleClass('active');
		});
		
		$('.btn-box .bookmark').click(function(){
			$(this).toggleClass('active');
		});
	});
</script>

<div class="wrapper">
	<div class="detail-header">
		<div class="title">
			<h2>${item.title}</h2>
		</div>
		<div class="genre">
			<a href="/novel/list?search=3&keyword=${item.genreContents}">${item.genreContents}</a>
		</div>
		<div class="nickname">
			<a href="/profile/${item.id}">${item.nickname}</a>
		</div>
	</div>
	<article class="detail-info">
		<div>
			<p class="detail-story">줄거리</p>
		</div>
		<div class="flex justify-between">
			<div class="btn-box flex">
				<div class="recom">
					<span class="icon"></span>
					<span class="text">추천</span>
				</div>
				<div class="bookmark">
					<span class="icon"></span>
					<span class="text">북마크</span>
				</div>
			</div>
			<div class="start-btn">
				<a href="/episode/${item.code}/1">시작</a>
			</div>
		</div>
	
		<div class="thumbnail">
			<img alt="${item.title}" src="/thumbnail/${item.image == null ? item.preview : item.image.fullname}">
		</div>
	</article>
	<div class="detail-contents">
		<article class="detail-list">
			<section class="list-contents">
				<div class="list-header">
					<h3 class="title">에피소드</h3>
					<div class="btn">
						<a href="/episode/add">작성</a>
					</div>
				</div>
				<ul class="list">
					<c:forEach var="episode" items="${episodeList}">
						<li>
							<div class="title"><a href="">${episode.title}</a></div>
							<div class="btn-box">
								<a href="/episode/update/${episode.code}" class="update">수정</a>
								<a href="/episode/delete/${episode.code}" class="delete">삭제</a>
							</div>
						</li>
					</c:forEach>
				</ul>
			</section>
			<div class="flex justify-center pb-60">
				<ul class="pagnation">
					<c:if test="${pager.page != 1}">
						<li><a href="?page=1&${pager.query}">&lt;&lt;</a></li>
					</c:if>
					<c:if test="${pager.page != 1}">
						<li><a href="?page=${pager.prev}&${pager.query}">&lt;</a></li>
					</c:if>
					<c:forEach var="page" items="${pager.list}">
						<li><a href="?page=${page}&${pager.query}"
							class="${pager.page==page?'active':''}">${page}</a></li>
					</c:forEach>
					<c:if test="${pager.page != pager.end}">
						<li><a href="?page=${pager.next}&${pager.query}">&gt;</a></li>
					</c:if>
					<c:if test="${pager.page != pager.end}">
						<li><a href="?page=${pager.end}&${pager.query}">&gt;&gt;</a></li>
					</c:if>
				</ul>
			</div>
		</article>
		<article class="other">
			<div class="other-header">
				${item.nickname}님의 <span class="text-point">다른 작품</span>
			</div>
			<ul class="novel-list">
				<c:forEach var="otherNovel" items="${userList}">
					<li class="flex">
						<div class="thumbnail">
							<a href="/novel/detail/${otherNovel.code}">
								<img alt="${otherNovel.title}" src="/thumbnail/${otherNovel.image == null ? otherNovel.preview : otherNovel.image.fullname}">
							</a>
						</div>
						<div class="info">
							<div class="text-ellipsis overflow-hidden">
								<a href="/novel/detail/${otherNovel.code}" class="text-title">${otherNovel.title}</a>
							</div>
							<div class="text-ellipsis overflow-hidden">
								<span class="text-nickname">
									<a href="/profile/${otherNovel.id}">${otherNovel.nickname}</a> • 
									<a href="/novel/list?search=3&keyword=${otherNovel.genreContents}">${otherNovel.genreContents}</a>
								</span>
							</div>
							<div class="overflow-hidden">
								<p class="novel-story">줄거리</p>
							</div>
							<div>
								<span class="text-recom">
									<span class="icon"></span>${otherNovel.recom}
								</span>
								<span class="text-bookmark">
									<span class="icon"></span>${otherNovel.bookmark}
								</span>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</article>
	</div>
</div>