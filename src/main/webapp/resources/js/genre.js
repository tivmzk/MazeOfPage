$(function(){
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
				const html = `<div>
								<a href="/novel/list?keyword=${item.contents}&search=3" data-genre="${item.code}">${item.contents}</a> (${item.recom})
								<span class="genre-delete">X</span>
							</div>`;
				
				$('#genre-list').append(html);
				
			},
			error:function(xhr){
				console.log('장르 : ' + xhr.statusText);
			}
		});
	});
});