<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="wrapper" id="notice-list-wrapper">
	<div>
		<h2>감상평</h2>		
	</div>
	<div>
		<ul>
			<c:forEach var="item" items="${list}">
				<li>
					<div>
						<h3><a href="view/${item.code}">${item.title}</a></h3>
					</div>
					<div>
						<a href="/novel/detail/${item.novel}">${item.novelTitle}</a>
					</div>
					<div>
						<span><a href="/profile/${item.member}">${item.nickname}</a></span> 
						<span><fmt:formatDate pattern="yyyy-MM-dd" value="${item.date}"/></span>
					</div>
					<c:if test="${sessionScope.user.mgr == 1}">
						<div>
							<div>
								<a href="delete/${item.code}">삭제</a>
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