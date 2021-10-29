<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<div class="visual">
</div>

<article id="ranking">
	<div class="wrapper px-10">
		<div class="article-title">
			<h2>랭킹</h2>
		</div>
		<div class="flex justify-between">
			<c:forEach var="item" items="${rankingList}">
				<section class="novel-wrapper">
					<div class="thumbnail-normal">
						<img alt="${item.title}" src="/thumbnail/${item.thumbnail == null ? item.preview : item.thumbnail.fullname}">
					</div>
					<div class="text-ellipsis overflow-hidden">
						<span class="text-title">${item.title}</span>
					</div>
					<div class="text-ellipsis overflow-hidden">
						<span class="text-nickname">${item.nickname}</span>
					</div>
					<div class="text-ellipsis overflow-hidden">
						<span class="text-recom">${item.recom}</span>
					</div>
				</section>
			</c:forEach>
		</div>
	</div>
</article>

<article id="recom-theme">
	<div class="wrapper px-10">
		<div>
			<div class="flex justify-between">
				<div class="article-title">
					<h2 class="text-black">추천 테마</h2>
				</div>
				<div>
					<button class="rect-button"><i class="fas fa-chevron-left"></i></button>
					<button class="rect-button"><i class="fas fa-chevron-right"></i></button>
				</div>
			</div>
			<div class="flex justify-between">
				<c:forEach var="item" items="${novelList}">
					<section class="flex theme">
						<div class="thumbnail-small">
							<img alt="${item.title}" src="/thumbnail/${item.thumbnail == null ? item.preview : item.thumbnail.fullname}">
						</div>
						<div class="px-10">
							<div class="text-ellipsis overflow-hidden">
								<span class="text-title">${item.title}</span>
							</div>
							<div class="text-ellipsis overflow-hidden">
								<span class="text-nickname">${item.nickname}</span>
							</div>
							<div class="overflow-hidden">
								<span class="text-recom">${item.info}</span>
							</div>
						</div>
					</section>
				</c:forEach>
			</div>
		</div>
	</div>
</article>

<c:forEach var="genre" items="${genreRankList}">
	<article class="genre-wrapper">
		<div class="wrapper">
			<div class="article-title">
				<h2>${genre.genreContents}</h2>
			</div>
			<div class="flex justify-between">
				<c:forEach var="item" items="${genre.list}">
					<div class="novel-wrapper">
						<div class="thumbnail-normal">
							<img alt="${item.title}" src="/thumbnail/${item.thumbnail == null ? item.preview : item.thumbnail.fullname}">
						</div>
						<div class="text-ellipsis overflow-hidden">
							<span class="text-title">${item.title}</span>
						</div>
						<div class="text-ellipsis overflow-hidden">
							<span class="text-nickname">${item.nickname}</span>
						</div>
						<div class="text-ellipsis overflow-hidden">
							<span class="text-recom">${item.recom}</span>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</article>
</c:forEach>

<article id="etc">
	<div class="wrapper flex">
		<div class="flex-50 p-10">
			<div class="article-title">
				<h2 class="text-black">공지사항</h2>
			</div>
			<ul class="list">
				<c:forEach var="item" items="${noticeList}">
					<li class="flex item-center">
						<span>${item.title}</span> 
						<span><fmt:formatDate pattern="yyyy-MM-dd" value="${item.date}" /></span>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="flex-50 p-10">
			<div class="article-title">
				<h2 class="text-black">감상평</h2>
			</div>
			<ul class="list">
				<c:forEach var="item" items="temp">
					<li class="flex item-center"><span>제목</span> <span>닉네임</span></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</article>