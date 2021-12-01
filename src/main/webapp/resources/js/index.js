const state = {
	page:1,
	perPage:2
};

$(function(){
	$('.rect-button.left').click(function(){
		if(state.page == 1) return;
		state.page--;
		
		$.ajax('/rest/novel', {
			method:'GET',
			contentType:'application/json',
			dataType:'json',
			data:state,
			success:function(result){
				const {list, pager} = result;
				state.end = pager.end;
				changeTheme(list);
			},
			error:function(xhr){
				console.log('추천 테마 : ' + xhr.statusText);
				state.page++;
			}
		});
	});
	
	$('.rect-button.right').click(function(){
		if(state.page == state.end) return;
		state.page++;
		
		$.ajax('/rest/novel', {
			method:'GET',
			contentType:'application/json',
			dataType:'json',
			data:state,
			success:function(result){
				const {list, pager} = result;
				state.end = pager.end;
				
				if(!list || list.length == 0) {
					state.page--;
					return;
				}
				
				changeTheme(list);
			},
			error:function(xhr){
				console.log('추천 테마 : ' + xhr.statusText);
				state.page--;
			}
		});
	});
});

function get(callback){
	$.ajax('/rest/novel', {
			method:'GET',
			contentType:'application/json',
			dataType:'json',
			data:state,
			success:function(result){
				const {list, pager} = result;
				state.end = pager.end;
				
				if(!list || list.length == 0) {
					state.page--;
					return;
				}
				
				changeTheme(list);
			},
			error:function(xhr){
				console.log('추천 테마 : ' + xhr.statusText);
				state.page--;
			}
		});
}

function changeTheme(list){
	$('#theme-wrapper .theme').remove();
	let html = '';
	for (item of list) {
		html += '<section class="flex theme">';

		html += '<div class="thumbnail-small">';
		html += `<a href="/novel/detail/${item.code}">`;
		html += `<img alt="${item.title}" src="${item.image.fullname}">`;
		html += '</a>';
		html += '</div>';

		html += '<div class="px-10">';
		html += '<div class="text-ellipsis overflow-hidden">';
		html += `<span class="text-title"><a href="/novel/detail/${item.code}">${item.title}</a></span>`;
		html += '</div>';

		html += '<div class="text-ellipsis overflow-hidden">';
		html += `<span class="text-nickname">
				<a href="/profile/${item.member}">${item.nickname}</a> • 
				<a href="/novel/list?keyword=${item.genreContents}&search=3">${item.genreContents}</a>
				</span>`;
		html += '</div>';

		html += `<div class="overflow-hidden">
				<span class="text-info">${item.info}</span>
				</div>`;

		html += `<div class="text-ellipsis overflow-hidden">
				<span class="text-recom pr-5"><span class="icon"></span>${item.recom}</span> 
				<span class="text-bookmark"><span class="icon"></span>${item.bookmark}</span>
				</div>`;

		html += '</div>';

		html += '</section>';
	}
	
	
	$('#theme-wrapper').append(html);
}