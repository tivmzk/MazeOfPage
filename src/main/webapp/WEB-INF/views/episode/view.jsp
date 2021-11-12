<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper pos-relative" id="novel-view-wrapper">
	<div>
		<a class="anchor-down" href="#footer"></a>
	</div>
	<div>
		<h2 class="title">${item.title}</h2>
	</div>
	<div class="inner-wrapper">
		<p class="contents">
			${item.contents}
		</p>
	</div>
	<div class="option-wrapper">
		<c:forEach var="option" items="${item.options}">
			<div class="option">
				<a href="/episode/${novel}/${option.oepisode}">${option.action}</a>
			</div>
		</c:forEach>
	</div>
	<div>
		<a href="/novel/detail/${item.novel}" class="undo"></a>
	</div>
	<div>
		<a class="anchor-up" href="#header"></a>
	</div>
</div>

