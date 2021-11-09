<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	let episodes = null;
	/* 추천, 북마크를 위한 데이터를 만드는 함수 */
	function createUserData(){
		return {
				'member':'${sessionScope.user.id}',
				'novel':'${item.code}'
		};
	}
	
	/* 추천, 북마크 정보를 AJAX로 보내주는 함수 */
	function sendAJAX(uri, methodType, callback){
		const sendData = createUserData();
		
		$.ajax(uri,{
			method: methodType,
			dataType:'json',
			data:JSON.stringify(sendData),
			contentType:'application/json; charset=UTF-8',
			success:callback,
			error:function(xhr, status){
				console.log(status);
			}
		});
	}
	
	/* 에피소드 리스트에 들어갈 요소 한개 만드는 함수 */
	function createEpisode(episode){
		let html = '';
		
		html += `<li data-epi="\${episode.code}">`;
		html += '<div class="title">';
		html += `<a href="/episode/\${episode.novel}/\${episode.code}">\${episode.title}</a>`;
		if(!episode.contents) {
			const msg = '내용이 비어있습니다';
			html += `<span class="warn-sign" title="\${msg}"></span>`;
		}
		if(episode.options){
			for(option of episode.options){
				if(!option.oepisode && option.mepisode) {
					const msg = `선택지 '\${option.action}'의 연결이 없습니다`;
					html += `<span class="warn-sign" title="\${msg}"></span>`;
				}	
			}	
		}
		
		html += '</div>';
		
		if(episode.member == '${sessionScope.user.id}'){
			html += '<div class="btn-box">';
			html += '<span class="epi-update">수정</span>';
			html += '<span class="epi-delete">삭제</span>';
			html += '</div>';
		}
		
		html += '</li>';
		
		return $(html);
	}
	
	/* 에피소드를 로드하는 함수 */
	function loadEpisodes(page){
		const pager = {
			keyword:${item.code},
			search:1,
			order:1,
			page
		};
		
		$.ajax('/rest/episode',{
			method:'GET',
			dataType:'json',
			contentType:'application/json',
			data:pager,
			success:result => {
				episodes = result;
				const list = $('.detail-list .list-contents .list');
				
				for(episode of episodes){
					const item = createEpisode(episode);
					list.append(item);
				}
			},
			error:xhr => {
				console.log(xhr.statusText);
			}
		});
	}
	
	$(function(){
		/* 추천 버튼 이벤트 설정 */
		$('.btn-box .recom').click(function(){
			if(${sessionScope.user != null}){
				let isRecom = false;
				$.ajax(`/rest/recom?member=${sessionScope.user.id}&novel=${item.code}`, {
					success:function(result){
						isRecom = result;
						
						if(isRecom){
							sendAJAX('/rest/recom', 'DELETE', 
								function(result){
								$('.btn-box .recom').toggleClass('active');
								$('.btn-box .recom .text').text(result);
							});
						}
						else{
							sendAJAX('/rest/recom', 'POST', 
								function(result){
								$('.btn-box .recom').toggleClass('active');
								$('.btn-box .recom .text').text(result);
							});
						}
					},
					error:function(){
						console.log("확인 에러");
					}
				});
			}
			else
				alert('로그인을 필요합니다')
		});
		
		/* 북마크 버튼 이벤트 설정 */
		$('.btn-box .bookmark').click(function(){
			if(${sessionScope.user != null}){
				let isBookmark = false;
				$.ajax(`/rest/bookmark?member=${sessionScope.user.id}&novel=${item.code}`, {
					success:function(result){
						isBookmark = result;
						
						if(isBookmark){
							sendAJAX('/rest/bookmark', 'DELETE', 
								function(result){
								$('.btn-box .bookmark').toggleClass('active');
								$('.btn-box .bookmark .text').text(result);
							});
						}
						else{
							sendAJAX('/rest/bookmark', 'POST', 
								function(result){
								$('.btn-box .bookmark').toggleClass('active');
								$('.btn-box .bookmark .text').text(result);
							});
						}
					},
					error:function(){
						console.log("확인 에러");
					}
				});
			}
			else
				alert('로그인을 필요합니다')
		});
		
		/* 에피소드 삭제 버튼 */
		$('.detail-list .list-contents .list').on('click', '.epi-delete', function(){
			const answer = confirm('이 에피소드를 정말로 삭제합니까?');
			
			if(answer){
				const code = $(this).closest('li').data('epi');
				
				$.ajax('/rest/episode?code='+code, {
					method:'DELETE',
					success:result => {
						/* 
						현재 보여주고 있는 리스트를 초기화하고 받아온 episodes에 삭제한 episode를 반영해서
						다시 생성후 리스트에 추가
						*/
						const list = $('.detail-list .list-contents .list');
						list.empty();
						episodes = episodes.filter(episode => {
							if(episode.code == result && episode.isStart){
								$('.menubox .start-btn a').attr('href', '');
							}
							
							if(episode.code != result){
								for(option of episode.options){
									if(option.oepisode == result)
										option.oepisode = 0;
								}
								return true;
							}
							return false;
						});
						
						
						for(episode of episodes){
							const item = createEpisode(episode);
							list.append(item);
						}
						
						
					},
					error:xhr => {
						console.log('episode delete : ' + xhr.statusText);
					}
				});
			}
		});
		
		/* 에피소드 수정 버튼 */
		$('.detail-list .list-contents .list').on('click', '.epi-update', function(){
			const epi = $(this).closest('li').data('epi');
			location.href = `/episode/update/${code}/\${epi}`;
		});
		
		/* 작품 삭제 버튼 */
		$('#novel-delete-btn a').click(function(e){
			const answer = confirm("이 소설을 정말 삭제합니까?");
			
			if(!answer) e.preventDefault();
		});
		
		loadEpisodes(1);
		
		/* 시작 버튼 설정 */
		$('.menubox .start-btn a').click(function(e){
			e.preventDefault();
			const url = $(this).attr('href');
			if(!url){
				alert('시작 에피소드가 없습니다.');
			}
			else{
				location.href = url; 
			}
		});
		
		const pager = {
			search:2,
			keyword:1,
			keyword2:${item.code}
		}
		
		$.ajax('/rest/episode/item',{
			dataType:'json',
			contentType:'application/json',
			data:pager,
			success:result => {
				const startBtn = $('.menubox .start-btn a');
				
				startBtn.attr('href', `/episode/\${result.novel}/\${result.code}`);
			},
			error:xhr => {
				console.log('start btn load : ' + xhr.statusText);
			}
		});
	});
</script>

<div class="wrapper">
	<div class="detail-header">
		<div class="title">
			<h2>${item.title}</h2>
		</div>
		<div class="genre">
			<a href="/novel/list?search=3&keyword=${item.genreContents}">${item.genreContents}</a>
		</div>
		<div class="nickname">
			<a href="/profile/${item.member}">${item.nickname}</a>
		</div>
		<c:if test="${sessionScope.user.id == item.member}">
			<div id="novel-update-btn">
				<a href="/novel/update/${item.code}">수정</a>
			</div>
			<div id="novel-delete-btn">
				<a href="/novel/delete/${item.code}">삭제</a>
			</div>
		</c:if>
	</div>
	<article class="detail-info">
		<div>
			<p class="detail-story">${item.info}</p>
		</div>
		<div class="flex justify-between menubox">
			<div class="btn-box flex">
				<div class="recom ${recom != null && recom.member==sessionScope.user.id ? 'active' : ''}">
					<span class="icon"></span>
					<span class="text">${item.recom}</span>
				</div>
				<div class="bookmark ${bookmark != null && bookmark.member==sessionScope.user.id ? 'active' : ''}">
					<span class="icon"></span>
					<span class="text">${item.bookmark}</span>
				</div>
			</div>
			<div class="start-btn">
				<a href="">시작</a>
			</div>
		</div>
	
		<div class="thumbnail">
			<img alt="${item.title}" src="/thumbnail/${item.image == null ? item.preview : item.image.fullname}">
		</div>
	</article>
	<div class="detail-contents">
		<article class="detail-list">
			<section class="list-contents">
				<div class="list-header">
					<h3 class="title">에피소드</h3>
					<c:if test="${sessionScope.user.id == item.member}">
						<div class="btn">
							<a href="/episode/add/${item.code}">작성</a>
						</div>
					</c:if>
				</div>
				<ul class="list">
					
				</ul>
			</section>
			<div class="flex justify-center pb-60">
				<ul class="pagnation">
					<c:if test="${pager.page != 1}">
						<li><a href="?page=1&${pager.query}">&lt;&lt;</a></li>
					</c:if>
					<c:if test="${pager.page != 1}">
						<li><a href="?page=${pager.prev}&${pager.query}">&lt;</a></li>
					</c:if>
					<c:forEach var="page" items="${pager.list}">
						<li><a href="?page=${page}&${pager.query}"
							class="${pager.page==page?'active':''}">${page}</a></li>
					</c:forEach>
					<c:if test="${pager.page != pager.end}">
						<li><a href="?page=${pager.next}&${pager.query}">&gt;</a></li>
					</c:if>
					<c:if test="${pager.page != pager.end}">
						<li><a href="?page=${pager.end}&${pager.query}">&gt;&gt;</a></li>
					</c:if>
				</ul>
			</div>
		</article>
		<article class="other">
			<div class="other-header">
				${item.nickname}님의 <span class="text-point">다른 작품</span>
			</div>
			<ul class="novel-list">
				<c:if test="${userList.size() < 1}">
					<li>다른 작품이 없습니다</li>
				</c:if>
				<c:forEach var="otherNovel" items="${userList}">
					<li class="flex">
						<div class="thumbnail">
							<a href="/novel/detail/${otherNovel.code}">
								<img alt="${otherNovel.title}" src="/thumbnail/${otherNovel.image == null ? otherNovel.preview : otherNovel.image.fullname}">
							</a>
						</div>
						<div class="info">
							<div class="text-ellipsis overflow-hidden">
								<a href="/novel/detail/${otherNovel.code}" class="text-title">${otherNovel.title}</a>
							</div>
							<div class="text-ellipsis overflow-hidden">
								<span class="text-nickname">
									<a href="/profile/${otherNovel.member}">${otherNovel.nickname}</a> • 
									<a href="/novel/list?search=3&keyword=${otherNovel.genreContents}">${otherNovel.genreContents}</a>
								</span>
							</div>
							<div class="overflow-hidden">
								<p class="novel-story">줄거리</p>
							</div>
							<div>
								<span class="text-recom">
									<span class="icon"></span>${otherNovel.recom}
								</span>
								<span class="text-bookmark">
									<span class="icon"></span>${otherNovel.bookmark}
								</span>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</article>
	</div>
</div>