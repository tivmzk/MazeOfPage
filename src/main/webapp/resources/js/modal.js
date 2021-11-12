$(function(){
	modalClose();
	
	$('#modal-wrapper').click(function(){
		modalClose();
	});
	
	$('#modal-wrapper').children().click(function(){
		return false;
	});
});

function modalClose(){
	$('#modal-wrapper').css('display', 'none');
}
function modalOpen(){
	$('#modal-wrapper').css('display', 'block');
}