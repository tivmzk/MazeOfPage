<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="wrapper">
	<div>
		<h2>${item.title}</h2>
	</div>
	<div>
		<p>
			${item.contents}
		</p>
	</div>
	<div>
		<c:forEach var="option" items="${item.options}">
			<div>
				<a href="/episode/${novel}/${option.oepisode}">${option.action}</a>
			</div>
		</c:forEach>
	</div>
</div>
