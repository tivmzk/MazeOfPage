$(function() {
	$('.sort-new').click(function() {
		$('.sort-new').toggleClass('active');
		$('.sort-recom').toggleClass('active');
	});
	$('.sort-recom').click(function() {
		$('.sort-new').toggleClass('active');
		$('.sort-recom').toggleClass('active');
	});
});