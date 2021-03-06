<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="wrapper" id="post-list-wrapper">
	<div class="article-title pt-30 pb-10 border-b-1 border-color-sub header">
		<h2 class="text-black">감상평</h2>		
	</div>
	<div class="inner-wrapper">
		<ul class="post-list">
			<c:if test="${list.size() < 1}">
				<div class="noting">감상평이 없습니다</div>
			</c:if>
			<c:forEach var="item" items="${list}">
				<li>
					<div>
						<h3><a  class="title" href="view/${item.code}">${item.title}</a></h3>
					</div>
					<div>
						<a class="novel" href="/novel/detail/${item.novel}">${item.novelTitle}</a>
					</div>
					<div>
						<span><a class="nickname" href="/profile/${item.member}">${item.nickname}</a></span>  
						<span class="date"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.date}"/></span>
					</div>
					<c:if test="${sessionScope.user.mgr == 1}">
						<div class="btn-box">
							<div>
								<a class="delete" href="delete/${item.code}">삭제</a>
							</div>
						</div>
					</c:if>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class="flex justify-center pb-60">
		<ul class="pagnation">
			<c:if test="${pager.page != 1}">
				<li><a href="?page=1&${pager.query}">&lt;&lt;</a></li>
			</c:if>
			<c:if test="${pager.page != 1}">
				<li><a href="?page=${pager.prev}&${pager.query}">&lt;</a></li>
			</c:if>
			<c:forEach var="page" items="${pager.list}">
				<li><a href="?page=${page}&${pager.query}" class="${pager.page==page?'active':''}">${page}</a></li>
			</c:forEach>
			<c:if test="${pager.page != pager.end}">
				<li><a href="?page=${pager.next}&${pager.query}">&gt;</a></li>	
			</c:if>
			<c:if test="${pager.page != pager.end}">
				<li><a href="?page=${pager.end}&${pager.query}">&gt;&gt;</a></li>
			</c:if>
		</ul>
	</div>
</div>