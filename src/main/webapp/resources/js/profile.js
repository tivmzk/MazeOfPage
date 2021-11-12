$(function() {
	if (!curr_user.id || curr_user.id != profile_user) {
		$('#modal-open-btn').remove();
	}
	else {
		/*수정 버튼 막기*/
		$('#modal-open-btn').click(function() {
			$('#modal-wrapper .form .nickname').val(profile.nickname);
			$('#modal-wrapper .form .contents').val(profile.contents);
			modalOpen();
		});
		
		$('#modal-wrapper .form .proflie-update-btn').click(function(){
			$('#modal-wrapper .form').submit();
		});
	}
});