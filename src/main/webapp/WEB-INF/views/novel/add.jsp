<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	$(function(){
		$('.thumbnail').click(function(){
			$('#hidden-input').click();
		});
		$('#hidden-input').change(function(){
			const file = this.files[0];
			
			if(file){
				const reader = new FileReader();
				reader.onload = function(){
					$('#input-image').attr('src', reader.result);
					$('.thumbnail').addClass('active');
				};
				reader.readAsDataURL(file);
			}
		});
	});
</script>
<div class="wrapper">
	<div class="article-title pt-30 pb-10 border-b-1 border-color-sub">
		<h2 class="text-black">작품</h2>
	</div>
	<div class="py-50">
		<form method="post" class="novel-form" enctype="multipart/form-data">
			<input type="text" value="${sessionScope.user.id}" hidden="hidden" name="member">
			<div class="flex justify-between">
				<div class="pr-10">
					<div class="form-input">
						<h2>제목</h2>
						<input type="text" class="w-100p" name="title">
					</div>
					<div class="form-input">
						<h2>장르</h2>
						<select name="genre">
							<c:forEach var="item" items="${list}">
								<option value="${item.code}">${item.contents}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div >
					<input type="file" hidden="hidden" id="hidden-input" name="thumbnail">
					<div class="thumbnail">
						<div class="no-input">
							<img src="/svg/add_file.svg">
							<span>썸네일을<br /> 등록하려면 클릭</span>
						</div>
						<div class="input">
							<img id="input-image">
						</div>
					</div>
				</div>
			</div>
			<div class="form-input">
				<h2>내용</h2>
				<textarea rows="20" cols="50" name="info"></textarea>
			</div>
			<div class="py-30 flex justify-end">
				<div class="btn">
					<button>확인</button>
				</div>
			</div>
		</form>
	</div>
</div>