<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper flex justify-between item-center">
	<div class="logo">
		<h1 class="text-black"><a href="/">Maze of Page</a></h1>
	</div>
	<div class="flex border-3 border-color-main round">
		<div class="flex item-center">
			<select class="search-select">
				<option>제목</option>
				<option>작성자</option>
				<option>장르</option>
			</select>
		</div>
		<div class="search-box">
			<input type="text" class="input-text">
			<span class="icon"></span>
		</div>
	</div>
	<div class="btn">
		<a href="/login">로그인</a>
	</div>
</div>