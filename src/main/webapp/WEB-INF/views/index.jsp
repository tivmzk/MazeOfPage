<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="wrapper">
	<div>
		<div>
			<h2>종합 랭킹</h2>
		</div>
		<div>
			<!-- 종합랭킹 표 -->
			<c:forEach var="item" items="${novelList}">
				<div>
					<span>${item.rank}</span>
				</div>
				<div>
					<span><a href="">${item.title}</a></span>
					<span><a href="">${item.nickname}</a></span>
					<span><a href="">${item.genre}</a></span>
				</div>
			</c:forEach>
		</div>
	</div>
	<div>
		<div>
			<div>
				<h2>장르</h2>
			</div>
			<div>
				<ul>
					<c:forEach var="genre" items="${genreList}">
						<li>${genre.contents}</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div>
			<div>
				<h2>공지사항</h2>
			</div>
			<div>
				<c:forEach var="item" items="${noticeList}">
					<div>
						<span><a href="">${item.title}</a></span>
						<span>${item.date}</span>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>