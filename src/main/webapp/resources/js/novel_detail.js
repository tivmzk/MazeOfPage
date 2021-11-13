let episodes = null;
const state = {
	keyword: `${novel.code}`,
	order: 1,
	search: 1
};
	
/* 추천, 북마크 정보를 AJAX로 보내주는 함수 */
function sendAJAX(uri, methodType, callback) {
	const sendData = {
		member: curr_user.id,
		novel: novel.code
	};

	$.ajax(uri, {
		method: methodType,
		dataType: 'json',
		data: JSON.stringify(sendData),
		contentType: 'application/json; charset=UTF-8',
		success: callback,
		error: function(xhr, status) {
			console.log(status);
		}
	});
}

/* 에피소드 리스트에 들어갈 요소 한개 만드는 함수 */
function createEpisode(episode) {
	let html = '';

	html += `<li data-epi="${episode.code}" class="epi-item">`;
	html += '<div class="title">';
	html += `<a href="/episode/${episode.novel}/${episode.code}">${episode.title}</a>`;
	if (!episode.contents) {
		const msg = '내용이 비어있습니다';
		html += `<span class="warn-sign" title="${msg}"></span>`;
	}
	if (episode.options) {
		for (option of episode.options) {
			if (!option.oepisode && option.mepisode) {
				const msg = `선택지 '${option.action}'의 연결이 없습니다`;
				html += `<span class="warn-sign" title="${msg}"></span>`;
			}
		}
	}

	html += '</div>';

	if (episode.member == curr_user.id) {
		html += '<div class="btn-box">';
		html += '<span class="epi-update">수정</span>';
		html += '<span class="epi-delete">삭제</span>';
		html += '</div>';
	}

	html += '</li>';

	return $(html);
}

/* 에피소드를 로드하는 함수 */
function loadEpisodes(page) {
	state.page = page;
	state.list = null;
	
	$.ajax('/rest/episode', {
		method: 'GET',
		dataType: 'json',
		contentType: 'application/json',
		data: state,
		success: result => {
			episodes = result.episodes;
			state.total = result.pager.total;
			state.perPage = result.pager.perPage;

			const list = $('.detail-list .list-contents .list');
			list.children('.epi-item').remove();
			if (episodes && episodes.length > 0) {
				$('.empty-msg').addClass('hide');
				for (episode of episodes) {
					const item = createEpisode(episode);
					list.append(item);
				}
			}
			else {
				$('.empty-msg').removeClass('hide');
			}
			
			const pagnation = $('.pagnation');
			pagnation.find('.page-prev').data('page', result.pager.prev);
			pagnation.find('.page-next').data('page', result.pager.next);
			pagnation.find('.page-end').data('page', result.pager.end);
			
			pagnation.children('.page-list').remove();
			
			const pagerList = result.pager.list;
			state.list = pagerList;
			
			for(let i = 0; i < pagerList.length; i++){
				const pageList = $('<li>').addClass('page-list');
				
				const pageItem = $('<span>');
				pageItem.addClass('page-link');
				if(result.pager.page == pagerList[i]){
					pageItem.addClass('active');
				}
				pageItem.text(pagerList[i]);
				pageItem.attr('data-page', `${pagerList[i]}`);
				
				pageList.append(pageItem);
				pagnation.find('.page-next').parent().before(pageList);
			}
		},
		error: xhr => {
			console.log(xhr.statusText);
		}
	});
}

function setStartBtn() {
	const pager = {
		keyword:1,
		keyword2:novel.code,
		search:2
	};
	$.ajax('/rest/episode/item', {
		dataType: 'json',
		contentType: 'application/json',
		data: pager,
		success: result => {
			const startBtn = $('.menubox .start-btn a');

			startBtn.attr('href', `/episode/${result.novel}/${result.code}`);
		},
		error: xhr => {
			console.log('start btn load : ' + xhr.statusText);
		}
	});
}

$(function() {
	loadEpisodes(1);
	setStartBtn();
	
	/* 추천 버튼 이벤트 설정 */
	$('.btn-box .recom').click(function() {
		if (curr_user.id) {
			let isRecom = false;
			$.ajax(`/rest/recom?member=${curr_user.id}&novel=${novel.code}`, {
				success: function(result) {
					isRecom = result;

					if (isRecom) {
						sendAJAX('/rest/recom', 'DELETE',
							function(result) {
								$('.btn-box .recom').toggleClass('active');
								$('.btn-box .recom .text').text(result);
							});
					}
					else {
						sendAJAX('/rest/recom', 'POST',
							function(result) {
								$('.btn-box .recom').toggleClass('active');
								$('.btn-box .recom .text').text(result);
							});
					}
				},
				error: function() {
					console.log("확인 에러");
				}
			});
		}
		else
			alert('로그인을 필요합니다')
	});

	/* 북마크 버튼 이벤트 설정 */
	$('.btn-box .bookmark').click(function() {
		if (curr_user.id) {
			let isBookmark = false;
			$.ajax(`/rest/bookmark?member=${curr_user.id}&novel=${novel.code}`, {
				success: function(result) {
					isBookmark = result;

					if (isBookmark) {
						sendAJAX('/rest/bookmark', 'DELETE',
							function(result) {
								$('.btn-box .bookmark').toggleClass('active');
								$('.btn-box .bookmark .text').text(result);
							});
					}
					else {
						sendAJAX('/rest/bookmark', 'POST',
							function(result) {
								$('.btn-box .bookmark').toggleClass('active');
								$('.btn-box .bookmark .text').text(result);
							});
					}
				},
				error: function() {
					console.log("확인 에러");
				}
			});
		}
		else
			alert('로그인을 필요합니다')
	});

	/* 에피소드 삭제 버튼 */
	$('.detail-list .list-contents .list').on('click', '.epi-delete', function() {
		const answer = confirm('이 에피소드를 정말로 삭제합니까?');

		if (answer) {
			const code = $(this).closest('li').data('epi');
			let isStart = 0;
			
			for(let i=0; i<episodes.length; i++){
				if(episodes[i].code == code){
					isStart = episodes[i].isStart;
					break;
				}
			}
			
			if(isStart == 1){
				alert('시작 페이지는 삭제할 수 없습니다.');
				return;
			}

			$.ajax('/rest/episode?code=' + code, {
				method: 'DELETE',
				success: result => {
					/* 
					현재 보여주고 있는 리스트를 초기화하고 받아온 episodes에 삭제한 episode를 반영해서
					다시 생성후 리스트에 추가
					*/
					/*const list = $('.detail-list .list-contents .list');
					list.empty();
					episodes = episodes.filter(episode => {
						if (episode.code == result && episode.isStart == 1) {
							$('.menubox .start-btn a').attr('href', '');
						}

						if (episode.code != result) {
							for (option of episode.options) {
								if (option.oepisode == result)
									option.oepisode = 0;
							}
							return true;
						}
						return false;
					});


					for (episode of episodes) {
						const item = createEpisode(episode);
						list.append(item);
					}*/
					if(state.list.length == 1){
						$(`.detail-list .list-contents .list li[data-epi=${result}]`).remove();
					}
					else{
						loadEpisodes(state.page);
					}
				},
				error: xhr => {
					console.log('episode delete : ' + xhr.statusText);
				}
			});
		}
	});

	/* 에피소드 수정 버튼 */
	$('.detail-list .list-contents .list').on('click', '.epi-update', function() {
		const epi = $(this).closest('li').data('epi');
		location.href = `/episode/update/${novel.code}/${epi}`;
	});

	/* 작품 삭제 버튼 */
	$('#novel-delete-btn a').click(function(e) {
		const answer = confirm("이 소설을 정말 삭제합니까?");

		if (!answer) e.preventDefault();
	});	

	/* 시작 버튼 설정 */
	$('.menubox .start-btn a').click(function(e) {
		e.preventDefault();
		const url = $(this).attr('href');
		if (!url) {
			alert('시작 에피소드가 없습니다.');
		}
		else {
			location.href = url;
		}
	});
	
	/*페이지 네이션 이벤트 등록*/
	$('.pagnation').on('click', '.page-link', function(){
		const page = $(this).data('page');
		loadEpisodes(page);
	});
});