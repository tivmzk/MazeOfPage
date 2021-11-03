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
					<div class="thumbnail-large">
						<a href="/novel/detail/${item.code}">
							<img alt="${item.title}" src="/thumbnail/${item.image == null ? item.preview : item.image.fullname}">
						</a>
					</div>
					<div class="text-ellipsis overflow-hidden">
						<span class="text-title"><a href="/novel/detail/${item.code}">${item.title}</a></span>
					</div>
					<div class="text-ellipsis overflow-hidden">
						<span class="text-nickname"><a href="/profile/${item.member}">${item.nickname}</a> • 
						<a href="/novel/list?keyword=${item.genreContents}&search=3">${item.genreContents}</a></span>
					</div>
					<div class="text-ellipsis overflow-hidden">
						<span class="text-recom pr-5"><span class="icon"></span>${item.recom}</span>
						<span class="text-bookmark"><span class="icon"></span>${item.bookmark}</span>
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
							<a href="/novel/detail/${item.code}">
								<img alt="${item.title}" src="/thumbnail/${item.image == null ? item.preview : item.image.fullname}">
							</a>
						</div>
						<div class="px-10">
							<div class="text-ellipsis overflow-hidden">
								<span class="text-title"><a href="/novel/detail/${item.code}">${item.title}</a></span>
							</div>
							<div class="text-ellipsis overflow-hidden">
								<span class="text-nickname"><a href="/profile/${item.member}">${item.nickname}</a> • 
								<a href="/novel/list?keyword=${item.genreContents}&search=3">${item.genreContents}</a></span>
							</div>
							<div class="overflow-hidden">
								<span class="text-info">${item.info}</span>
							</div>
							<div class="text-ellipsis overflow-hidden">
								<span class="text-recom pr-5"><span class="icon"></span>${item.recom}</span> 
								<span class="text-bookmark"><span class="icon"></span>${item.bookmark}</span>
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
						<div class="thumbnail-large">
							<a href="/novel/detail/${item.code}"> 
								<img alt="${item.title}" src="/thumbnail/${item.image == null ? item.preview : item.image.fullname}">
							</a>
						</div>
						<div class="text-ellipsis overflow-hidden">
							<span class="text-title"><a href="/novel/detail/${item.code}">${item.title}</a></span>
						</div>
						<div class="text-ellipsis overflow-hidden">
								<span class="text-nickname"><a href="/profile/${item.member}">${item.nickname}</a> • 
								<a href="/novel/list?keyword=${item.genreContents}&search=3">${item.genreContents}</a></span>
						</div>
						<div class="text-ellipsis overflow-hidden">
							<span class="text-recom pr-5"><span class="icon"></span>${item.recom}</span>
							<span class="text-bookmark"><span class="icon"></span>${item.bookmark}</span>
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
						<span><a href="/notice/view/${item.code}">${item.title}</a></span> 
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
				<c:forEach var="item" items="${reviewList}">
					<li class="flex item-center">
						<span><a href="/review/view/${item.code}">${item.title}</a></span>
						 <span><a href="/profile/${item.member}">${item.nickname}</a></span>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</article>