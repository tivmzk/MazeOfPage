$(function(){
	$('#modal-wrapper').css('display', 'none');
	
	$('#modal-open-btn').click(function(){
		$('#modal-wrapper').css('display', 'block');	
	});
	
	$('#modal-wrapper').click(function(){
		$(this).css('display', 'none');
	});
	
	$('#modal-wrapper').children().click(function(){
		return false;
	});
});