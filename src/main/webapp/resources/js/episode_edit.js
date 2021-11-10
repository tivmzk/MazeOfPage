let episodes = null;
const pager = {};

/* 옵션을 추가하는 함수 */
function addOption(isFirst) {
	let html = '';

	html += '<div class="episode-option mb-5">';

	html += '<div class="option-contents">';
	html += '<input type="text" placeholder="선택지 내용" name="action"/>';
	html += '</div>'
	html += `<input type="hidden" value="0" name="code"/>`;
	html += '<div class="connect-episode">';
	html += '<select name="oepisode">';
	if (isFirst) {
		html += `<option value="-2">선택지 없음</option>`;
	}
	html += '<option value="-1">새로 만들기</option>';
	for (const episode of episodes) {
		html += `<option value="${episode.code}">${episode.title}</option>`;
	}
	html += '</select>';
	html += '</div>';
	if (!isFirst) {
		html += '<div class="option-delete">';
		html += '<button>X</button>';
		html += '</div>';
	}
	html += '</div>';

	$('.option-wrapper').append(html);
}

/* DB에서 받은 option을 생성하는 함수 */
function loadOption(option, isFirst) {
	let html = '';

	html += '<div class="episode-option mb-5">';
	html += '<div class="option-contents">';
	html += `<input type="text" placeholder="선택지 내용" name="action" value="${option.action==null ? '' : option.action}"/>`;
	html += '</div>'
	html += `<input type="hidden" value="${option.code}" name="code"/>`;
	html += '<div class="connect-episode">';
	html += '<select name="oepisode">';
	if (isFirst) {
		html += `<option value="-2"  ${-2==option.mepisode ? 'selected' : ''}>선택지 없음</option>`;
	}
	html += '<option value="-1">새로 만들기</option>';
	for (const episode of episodes) {
		html += `<option value="${episode.code}" ${episode.code==option.oepisode ? 'selected' : ''}>${episode.title}</option>`;
	}
	html += '</select>';
	html += '</div>';
	if (!isFirst) {
		html += '<div class="option-delete">';
		html += '<button>X</button>';
		html += '</div>';
	}
	html += '</div>';

	$('.option-wrapper').append(html);
}

/* 현재 수정할려고 하는 episode를 받아오는 함수 */
function loadEpisode() {
	pager.keyword = epiCode;
	pager.keyword2 = "";
	pager.search = 4;

	$.ajax('/rest/episode/item', {
		method: 'GET',
		dataType: 'json',
		contentType: 'application/json',
		data: pager,
		success: function(result) {
			/* form에 값 넣기 */
			$('.episode-form #title').val(`${result.title}`);
			$('.episode-form #episode-story').text(`${result.contents}`);

			loadWebEditor();

			if (result.options.length > 0) {
				let flag = true;
				for (option of result.options) {
					loadOption(option, flag);
					flag = false;
				}
			}
			else {
				addOption(true);
			}


		},
		error: function(xhr) {
			console.log('episode load : ' + xhr.statusText);
		}
	});
}

function loadWebEditor() {
	/* 웹 에디터 */
	$('#episode-story').summernote({
		height: 500,
		maxHeight: null,
		minHeight: null
	});
}

$(function() {
	/* 이 소설과 관련된 에피소드 불러오기 */
	if (mode == 0) {
		pager.keyword = novelCode;
		pager.search = 1;
	}
	else {
		pager.keyword = novelCode;
		pager.keyword2 = epiCode;
		pager.search = 3;
	}

	$.ajax('/rest/episode/all', {
		method: 'GET',
		dataType: 'json',
		contentType: 'application/json',
		data: pager,
		success: function(result) {
			episodes = result;

			if (mode == 0) {
				/* 초기 옵션 설정 */
				addOption(true);
			}
			else {
				loadEpisode();
			}

		},
		error: function(xhr) {
			console.log(xhr.statusText);
		}
	});

	if (mode == 0) {
		/* 웹 에디터 */
		loadWebEditor();
	}


	/* 선택지 추가 버튼 이벤트 등록 */
	$('#episode-add-btn').click(function(e) {
		e.preventDefault();

		addOption(false);
	});

	/* 선택지 삭제 버튼 */
	$('.option-wrapper').on('click', '.option-delete button', function(e) {
		e.preventDefault();
		$(this).parent().parent().remove();
	});

	/* 소설 등록 버튼 유효성 확인 */
	$('#ok-btn').click(function(e) {
		e.preventDefault();

		if (!$('#title').val()) {
			alert('제목을 입력하세요');
			$('#title').focus();
			return;
		}

		for (item of $('.episode-option')) {
			if ($(item).find('.option-contents input').val() == "" &&
				$(item).find('.connect-episode select option:selected').val() != -2) {
				console.log($(item).find('.connect-episode select option:selected').val());
				alert('선택지를 입력해주세요');
				$(item).find('.option-contents input').focus();

				return;
			}
		}

		$('.episode-form').submit();
	});
});