<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
	$(function(){
		$('.sort-new').click(function(){
			$('.sort-new').toggleClass('active');
			$('.sort-recom').toggleClass('active');
		});
		$('.sort-recom').click(function(){
			$('.sort-new').toggleClass('active');
			$('.sort-recom').toggleClass('active');
		});
	});
</script>

<div class="wrapper">
	<div class="article-title pt-30 pb-10 border-b-1 border-color-sub">
		<h2 class="text-black">작품</h2>
	</div>
	<div class="py-20 border-b-1 border-color-gray">
		<span class="sort-new active">최신</span>
		<span class="sort-recom">추천</span>
	</div>
	<div>
		<article class="flex novel-list py-30">
			<c:forEach var="item" items="${list}">
				<section class="flex novel-item">
					<div class="thumbnail-normal">
						<img alt="${item.title}" src="/thumbnail/${item.thumbnail == null ? item.preview : item.thumbnail.fullname}">
					</div>
					<div class="px-10">
						<div class="text-ellipsis overflow-hidden">
							<span class="text-title">${item.title}</span>
						</div>
						<div class="text-ellipsis overflow-hidden">
							<span class="text-nickname">${item.nickname} • ${item.genreContents}</span>
						</div>
						<div class="overflow-hidden">
							<span class="text-info">${item.info}</span>
						</div>
						<div class="text-ellipsis overflow-hidden">
							<span class="text-recom pr-5"><i class="fas fa-thumbs-up"></i> ${item.recom}</span> 
							<span class="text-bookmark"><i class="fas fa-star"></i> ${item.bookmark}</span>
						</div>
					</div>
				</section>
			</c:forEach>
		</article>
	</div>
	<div class="flex justify-center pb-60">
		<ul class="pagnation">
			<li><a href="?page=1&${pager.query}">&lt;&lt;</a></li>
			<li><a href="?page=${pager.prev}&${pager.query}">&lt;</a></li>
			<c:forEach var="page" items="${pager.list}">
				<li><a href="?page=${page}&${pager.query}" class="${pager.page==page?'active':''}">${page}</a></li>
			</c:forEach>
			<li><a href="?page=${pager.next}&${pager.query}">&gt;</a></li>
			<li><a href="?page=${pager.end}&${pager.query}">&gt;&gt;</a></li>
		</ul>
	</div>
</div>