$(function(){
	$('#modal-wrapper').mousedown(function(){
		modalClose();
	});
	
	$('#modal-wrapper').children().mousedown(function(e){
		e.stopPropagation();
	});
});

function modalClose(){
	$('#modal-wrapper').css('display', 'none');
}
function modalOpen(){
	$('#modal-wrapper').css('display', 'block');
}