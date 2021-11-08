<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script>
	let episodes = null;
	
	$(function(){
		const pager = {
			keyword:${code},
			search:1
		};
		
		$.ajax('/rest/episode/all',{
			method: 'GET',
			dataType: 'json',
			contentType: 'application/json',
			data:pager,
			success:function(result){
				episodes = result;
			},
			error:function(xhr){
				console.log(xhr.statusText);
			}
		});	
		
		$('#episode-story').summernote();
		
		/* 선택지 추가 버튼 이벤트 등록 */
		$('#episode-add-btn').click(function(e){
			e.preventDefault();
			
			let html = '';
			
			html += '<div class="episode-option mb-5">';
			
			html += '<div class="option-contents">';
			html += '<input type="text" placeholder="선택지 내용" name="action"/>';
			html += '</div>'
			
			html += '<div class="connect-episode">';
			html += '<select name="oepisode">';
			html += '<option value="-1">새로 만들기</option>';
			for(const episode of episodes){
				html += `<option value="\${episode.code}">\${episode.title}</option>`;
			}
			html += '</select>';
			html += '</div>';
			html += '<div class="option-delete">';
			html += '<button>X</button>';
			html += '</div>';
			html += '</div>';
			
			$('.option-wrapper').append(html);	
		});
		
		/* 선택지 삭제 버튼 */
		$('.option-wrapper').on('click', '.option-delete button', function(e){
			e.preventDefault();
			$(this).parent().parent().remove();
		});
		
		/* 소설 등록 버튼 유효성 확인 */
		$('#ok-btn').click(function(e){
			e.preventDefault();
			
			if(!$('#title').val()){
				alert('제목을 입력하세요');
				$('#title').focus();
				return;
			}
			
			for(item of $('.episode-option')){
				if(	$(item).find('.option-contents input').val() == "" &&
					$(item).find('.connect-episode select option:selected').val() != -2){
					console.log($(item).find('.connect-episode select option:selected').val());
					alert('선택지를 입력해주세요');
					$(item).find('.option-contents input').focus();
					
					return;
				}
			}
			
			$('.episode-form').submit();
		});
		
		
	});
</script>
<div class="wrapper">
	<div class="article-title flex border-b-1 border-color-gray justify-between pt-20">
		<h2 class="bootstrap-none-h text-black">에피소드 작성</h2>
		<c:if test="${episodeList.size() == 0}">
			<h3 class="bootstrap-none-h text-gray">이 에피소드가 시작지점입니다</h3>
		</c:if>
	</div>
	<form method="post" class="episode-form">
		<input type="hidden" value="${episodeList.size() == 0 ? 1 : 0}" name="isStart">
		<input type="hidden" value="${code}" name="novel">
		<div class="form-item">
			<div class="form-input">
				<label for="title">제목</label>
				<input type="text" name="title" id="title"/>
			</div>
		</div>
		<div class="form-item">
			<div class="form-input">
				<label for="episode-story">내용</label>
				<textarea id="episode-story" name="contents"></textarea>
			</div>
		</div>
		<div class="form-item">
			<div class="flex justify-between item-center">
				<div class="form-title">
					<h3 class="">선택지</h3>
				</div>
				<div class="episode-btn-box">
					<button id="episode-add-btn" type="button">+</button>
				</div>
			</div>
			<div class="option-wrapper">
				<div class="episode-option mb-5">
					<div class="option-contents">
						<input type="text" placeholder="선택지 내용" name="action"/>
					</div>
					<div class="connect-episode">
						<select name="oepisode">
							<option value="-2">선택지 없음</option>
							<option value="-1">새로 만들기</option>
							<c:forEach var="episode" items="${episodeList}">
								<option value="${episode.code}">${episode.title}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="flex justify-end pt-30">
			<div class="btn">
				<button id="ok-btn">확인</button>
			</div>
		</div>
	</form>
</div>