$(function(){
	
	/*삭제 버튼 이벤트*/
	$('#genre-list').on('click', '.genre-delete-btn', function(){
		const code = $(this).parent().data('genre');
		
		$.ajax('/rest/genre?code='+code, {
			method:'DELETE',
			success:function(response){
				const {result, code, msg} = response;
				
				if(result){
					$(`#genre-list .genre-item[data-genre="${code}"]`).remove();	
				}
				console.log(msg);
			},
			error:function(xhr){
				console.log('장르 삭제 : ' + xhr.statusText);
			}
		});
	});
	
	/*등록 버튼 이벤트*/
	$('#modal-wrapper .form .genre-add-btn').click(function(){
		const genre = {};
		genre.contents = $('#modal-wrapper .form .genre-input').val();
		console.log(genre.contents);
		$.ajax('/rest/genre', {
			method:'POST',
			dataType:'json',
			contentType:'application/json',
			data:JSON.stringify(genre),
			success:function(item){
				const html = `<div class="genre-item" data-genre="${item.code}">
								<a href="/novel/list?keyword=${item.contents}&search=3">${item.contents}</a> (${item.recom})
								<span class="genre-delete-btn">X</span>
							</div>`;
				
				$('#genre-list').append(html);
				$('#modal-wrapper .form .genre-input').val('');
				modalClose();
			},
			error:function(xhr){
				console.log('장르 : ' + xhr.statusText);
			}
		});
	});
});