let episodes = null;

/* 추천, 북마크 정보를 AJAX로 보내주는 함수 */
function sendAJAX(uri, methodType, callback) {
	const sendData = {
		member: user,
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

	html += `<li data-epi="${episode.code}">`;
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

	if (episode.member == user) {
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
	const pager = {
		keyword: `${novel.code}`,
		search: 1,
		order: 1,
		page
	};

	$.ajax('/rest/episode', {
		method: 'GET',
		dataType: 'json',
		contentType: 'application/json',
		data: pager,
		success: result => {
			episodes = result;
			const list = $('.detail-list .list-contents .list');

			for (episode of episodes) {
				const item = createEpisode(episode);
				list.append(item);
			}
		},
		error: xhr => {
			console.log(xhr.statusText);
		}
	});
}

$(function() {
	/* 추천 버튼 이벤트 설정 */
	$('.btn-box .recom').click(function() {
		if (user) {
			let isRecom = false;
			$.ajax(`/rest/recom?member=${user}&novel=${novel.code}`, {
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
		if (user) {
			let isBookmark = false;
			$.ajax(`/rest/bookmark?member=${user}&novel=${novel.code}`, {
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
					const list = $('.detail-list .list-contents .list');
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

	loadEpisodes(1);

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

	const pager = {
		search: 2,
		keyword: 1,
		keyword2: novel.code
	}

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
});