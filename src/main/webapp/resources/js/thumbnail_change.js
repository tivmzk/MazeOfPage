$(function() {
	$('.thumbnail').click(function() {
		$('#hidden-input').click();
	});
	$('#hidden-input').change(function() {
		const file = this.files[0];

		if (file) {
			const reader = new FileReader();
			reader.onload = function() {
				$('#input-image').attr('src', reader.result);
				$('.thumbnail').addClass('active');
			};
			reader.readAsDataURL(file);
		}
	});
});