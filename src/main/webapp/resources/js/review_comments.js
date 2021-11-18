/*
	필요한 것들
	.comments-wrapper
	댓글에 관련된 걸 전부 감싸는 요소
	
	.comments-total
	댓글의 총 개수를 표시하는 요소
	
	.comments-input
	댓글 등록 시 내용을 입력한 요소
	
	.comments-button
	댓글 등록 버튼
	
	.comments-total
	댓글 총 개수
	
	.comments-sort
	댓글 정렬 버튼
	
	review_code
	review의 기본키
	
	review_member
	리뷰를 쓴 사람
*/

const state = {
	keyword: `${review_code}`,
	order: 1
};

let is_editing = false;

/*댓글 요소를 만드는 함수*/
function createComments(item) {
	const temp = new Date(item.date);
	const date = {
		year: temp.getFullYear(),
		mon: temp.getMonth() + 1,
		day: temp.getDate()
	};

	const html = $(`<div data-comments=${item.code}>`)
		.addClass('comments-item');
	const header = $(`<div>`)
		.append(`<a class="nickname" href="/profile/${item.member}">${item.nickname}</a>`)
		.append(`<span class="date">${date.year}-${date.mon}-${date.day}</span>`);

	const contents = $(`<div>`)
		.append($('<p>').text(item.contents).addClass('contents'));

	if (review_member == curr_user.id ||
		curr_user.mgr == 1) {
		const wrapper = $('<div>')
			.append($('<span class="menu-btn">'))
			.addClass('menu-wrapper hide');

		const btn_box = $('<div>')
			.addClass('btn-box hide')
			.append($('<div>').append('<button class="update">수정</button>'))
			.append($('<div>').append('<button class="delete">삭제</button>'));

		wrapper.append(btn_box);
		html.append(wrapper);
	}

	html.append(header);
	html.append(contents);

	return html;
}

/*댓글 개수 읽어오기*/
function loadCount() {
	$.ajax('/rest/comments/total?code=' + review_code, {
		success: result => {
			$('.comments-total').text(result);
		},
		error: xhr => {
			console.log('댓글 개수 : ' + xhr.statusText);
		}
	});
}

/*댓글 리스트 받아와서 초기화 하기*/
function loadComments() {
	$.ajax(`/rest/comments`, {
		contentType: 'application/json',
		dataType: 'json',
		data: state,
		success: result => {
			loadCount();
			$('.comments-wrapper .comments-list .comments-item').remove();
			for (const item of result) {
				const comments = createComments(item);
				$('.comments-wrapper .comments-list').append(comments);
			}
		},
		error: xhr => {
			console.log(xhr.statusText);
		}
	});
}

function updateComments() {
	const input = $('#modal-wrapper .input');

	const item = {
		code: `${input.data('comments')}`,
		contents: `${input.val()}`
	};

	$.ajax('/rest/comments', {
		method: 'PUT',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(item),
		success: result => {
			const comments = $(`.comments-wrapper .comments-list .comments-item[data-comments="${result.code}"]`);
			comments.find('.contents').text(result.contents);
			comments.find('.menu-wrapper').addClass('hide');
			comments.find('.btn-box').addClass('hide');
			is_editing = false;
			input.val('');
			modalClose();
		},
		error: xhr => {
			console.log("댓글 수정 : " + xhr.statusText);
		}
	});
}

function uploadComments() {
	if (!`${curr_user.id}`) {
		alert("로그인이 필요합니다");
		return;
	}

	const contents = $('.comments-wrapper .comments-input').val();
	const item = {
		member: `${curr_user.id}`,
		review: `${review_code}`,
		contents: `${contents}`
	};

	$.ajax('/rest/comments', {
		method: 'POST',
		contentType: 'application/json',
		dataType: 'json',
		data: JSON.stringify(item),
		success: result => {
			const comments = createComments(result);
			$('.comments-wrapper .comments-list').prepend(comments);
			$('.comments-wrapper .comments-input').val('');
		},
		error: xhr => {
			console.log("댓글 달기 : " + xhr.statusText);
		}
	});
}

$(function() {
	loadComments();

	/*댓글 등록 버튼 이벤트*/
	$('.comments-wrapper .comments-button').click(function() {
		uploadComments();
	});

	$('.comments-wrapper .comments-input').keypress(function(key) {
		if (key.keyCode == 13) {
			uploadComments();
		}
	});

	/*댓글 삭제 버튼*/
	$('.comments-wrapper').on('click', '.btn-box .delete', function() {
		const code = $(this).closest('.comments-item').data('comments');

		$.ajax('/rest/comments?code=' + code, {
			method: 'DELETE',
			success: result => {
				$(`.comments-wrapper .comments-list .comments-item[data-comments="${result}"]`).remove();
				is_editing = false;
			},
			error: xhr => {
				console.log("댓글 삭제 : " + xhr.statusText);
			}
		});
	});

	/*댓글 수정 모달 오픈 버튼*/
	$('.comments-wrapper').on('click', '.btn-box .update', function() {
		const item = $(this).closest('.comments-item');
		const contents = item.find('.contents').text();

		$('#modal-wrapper .input')
			.val(contents)
			.data('comments', item.data('comments'));

		modalOpen();
	});

	/*댓글 메뉴 버튼*/
	$('.comments-wrapper').on('click', '.menu-btn', function() {
		$(this).siblings('.btn-box').toggleClass('hide');
		is_editing = !is_editing;
	});

	/*메뉴 버튼 온오프*/
	$('.comments-wrapper').on('mouseover mouseout', '.comments-item', function() {
		if (!curr_user.id || is_editing) return;

		const menu = $(this).find('.menu-wrapper');
		menu.toggleClass('hide');
	});

	/*댓글 수정*/
	$('#modal-wrapper .update').click(function() {
		updateComments();
	});

	$('#modal-wrapper .input').keypress(function(key) {
		if (key.keyCode == 13) {
			updateComments();
		}
	});

	/*댓글 정렬 버튼*/
	$('.comments-wrapper .comments-sort').click(function() {
		state.order = ++state.order % 2;

		$(this).toggleClass('desc');

		loadComments();
	});
});