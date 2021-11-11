$(function(){
	modalClose();
	
	$('#modal-open-btn').click(function(){
		$('#modal-wrapper').css('display', 'block');	
	});
	
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