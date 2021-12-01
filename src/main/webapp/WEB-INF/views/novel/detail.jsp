<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	const novel = {
		code:`${item.code}`,
		member:`${item.member}`,
		genre:`${item.genre}`,
		title:`${item.title}`,
		info:`${item.info}`,
		recom:`${item.recom}`,
		date:`${item.date}`,
		nickname:`${item.nickname}`,
		genreContents:`${item.genreContents}`,
		bookmark:`${item.bookmark}`
	};
</script>
<script src="/js/novel_detail.js"></script>

<div class="wrapper">
	<div class="detail-header">
		<div class="title">
			<h2>${item.title}</h2>
		</div>
		<div class="genre">
			<a href="/novel/list?search=3&keyword=${item.genreContents}">${item.genreContents}</a>
		</div>
		<div class="nickname">
			<a href="/profile/${item.member}">${item.nickname}</a>
		</div>
		<c:if test="${sessionScope.user.id == item.member}">
			<div id="novel-update-btn">
				<a href="/novel/update/${item.code}">수정</a>
			</div>
			<div id="novel-delete-btn">
				<a href="/novel/delete/${item.code}">삭제</a>
			</div>
		</c:if>
		<div class="review">
			<a href="/review/add?novel=${item.code}">감상평 쓰기</a>
		</div>
	</div>
	<article class="detail-info">
		<div>
			<p class="detail-story">${item.info}</p>
		</div>
		<div class="flex justify-between menubox">
			<div class="btn-box flex">
				<div class="recom ${recom != null && recom.member==sessionScope.user.id ? 'active' : ''}">
					<span class="icon"></span>
					<span class="text">${item.recom}</span>
				</div>
				<div class="bookmark ${bookmark != null && bookmark.member==sessionScope.user.id ? 'active' : ''}">
					<span class="icon"></span>
					<span class="text">${item.bookmark}</span>
				</div>
			</div>
			<div class="start-btn">
				<a href="">시작</a>
			</div>
		</div>
	
		<div class="thumbnail">
			<img alt="${item.title}" src="${item.image.fullname}">
		</div>
	</article>
	<div class="detail-contents">
		<article class="detail-list">
			<section class="list-contents">
				<div class="list-header">
					<h3 class="title">에피소드</h3>
					<c:if test="${sessionScope.user.id == item.member}">
						<div class="button">
							<a href="/episode/add/${item.code}">작성</a>
						</div>
					</c:if>
				</div>
				<ul class="list">
					<li class="empty-msg">에피소드가 없습니다</li>
				</ul>
			</section>
			<div class="flex justify-center pb-60">
				<ul class="pagnation">
					<%-- <c:forEach var="page" items="${pager.list}">
						<li><a href="?page=${page}&${pager.query}"
							class="${pager.page==page?'active':''}">${page}</a></li>
					</c:forEach> --%>
					
					<li><span class="page-start" data-page="1">&lt;&lt;</span></li>
					<li><span class="page-prev">&lt;</span></li>
					
					<li><span class="page-next">&gt;</span></li>
					<li><span class="page-end">&gt;&gt;</span></li>
				</ul>
			</div>
		</article>
		<article class="other">
			<div class="other-header">
				${item.nickname}님의 <span class="text-point">다른 작품</span>
			</div>
			<ul class="novel-list">
				<c:if test="${userList.size() < 1}">
					<li>다른 작품이 없습니다</li>
				</c:if>
				<c:forEach var="otherNovel" items="${userList}">
					<li class="flex">
						<div class="thumbnail">
							<a href="/novel/detail/${otherNovel.code}">
								<img alt="${otherNovel.title}" src="${otherNovel.image.fullname}">
							</a>
						</div>
						<div class="info">
							<div class="text-ellipsis overflow-hidden">
								<a href="/novel/detail/${otherNovel.code}" class="text-title">${otherNovel.title}</a>
							</div>
							<div class="text-ellipsis overflow-hidden">
								<span class="text-nickname">
									<a href="/profile/${otherNovel.member}">${otherNovel.nickname}</a> • 
									<a href="/novel/list?search=3&keyword=${otherNovel.genreContents}">${otherNovel.genreContents}</a>
								</span>
							</div>
							<div class="overflow-hidden">
								<p class="novel-story">${otherNovel.info}</p>
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