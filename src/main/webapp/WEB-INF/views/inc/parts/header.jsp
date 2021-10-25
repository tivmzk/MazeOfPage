<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper flex justify-between item-center">
	<div class="logo">
		<h1 class="text-black">Maze of Page</h1>
	</div>
	<div class="flex border-main-3 round">
		<div class="flex item-center">
			<select class="search-category">
				<option>제목</option>
				<option>작성자</option>
				<option>태그</option>
			</select>
		</div>
		<div class="search-box">
			<input type="text" class="input-text">
			<span class="icon"></span>
		</div>
	</div>
	<div class="btn">
		<button>로그인</button>
	</div>
</div>