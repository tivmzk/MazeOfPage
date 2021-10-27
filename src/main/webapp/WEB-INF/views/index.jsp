<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<div class="wrapper">
	<div>
		<div class="section-title">
			<h2>종합 랭킹</h2>
		</div>
		<div class="border-y-3 border-color-main flex dir-col wrap ranking-wrapper w-100p">
			<!-- 종합랭킹 표 -->
			<c:forEach var="item" items="${novelList}">
				<div class="border-1 border-color-light-gray ranking-item flex">
					<div class="p-5 text-center">
						<c:if test="${item.rank == 1}">
							<span class="first">${item.rank}</span>
						</c:if>
						<c:if test="${item.rank == 2}">
							<span class="second">${item.rank}</span>
						</c:if>
						<c:if test="${item.rank == 3}">
							<span class="third">${item.rank}</span>
						</c:if>
						<c:if test="${item.rank > 3}">
							<span>${item.rank}</span>
						</c:if>
					</div>
					<div class="p-5">
						<span class="text-title px-2"><a href="">${item.title}</a></span>
						<span class="text-nickname px-2"><a href="">${item.nickname}</a></span>
						<span class="text-genre px-2"><a href="">${item.genreContents}</a></span>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="flex justify-between mb-20">
		<div class="w-55p">
			<div class="section-title">
				<h2>장르</h2>
			</div>
			<div class="border-y-3 border-color-main">
				<ul class="tab-menu flex">
					<c:forEach var="genre" items="${genreList}">
						<li data-code="${genre.code}">${genre.contents}</li>
					</c:forEach>
				</ul>
				<div class="flex dir-col wrap novel-list">
					<c:forEach var="item" items="${genreNovelList}">
						<div class="flex-50 p-5">
							<span class="text-title px-2"><a href="">${item.title}</a></span>
							<span class="text-nickname px-2"><a href="">${item.nickname}</a></span>
							<span class="text-genre px-2"><a href="">${item.genreContents}</a></span>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="w-40p">
			<div class="section-title">
				<h2>공지사항</h2>
			</div>
			<div class="border-y-3 border-color-main">
				<div class="notice-list">
					<c:forEach var="item" items="${noticeList}">
						<div class="p-5">
							<span class="text-title px-2"><a href="">${item.title}</a></span>
							<span class="text-genre px-2"><fmt:formatDate pattern="yyyy-MM-dd" type="date" value="${item.date}" /></span>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>