<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$('#nickname_confirm').click(function(e){
			e.preventDefault();
			
			const form = document.getElementById("signup_form");
			
			check('check_nickname', form.nickname, form.check_nickname, '닉네임');
		});
		
		$('#id_confirm').click(function(e){
			e.preventDefault();
			
			const form = document.getElementById("signup_form");
			
			check_duplicate('check_id', form.id, form.check_id, '아이디');
		});
		
		$('#signup').click(signup);
	});
	
	function check_duplicate(url, input_elem, check_elem, msg){
		$.ajax(url+'/'+input_elem.value, {
			success:function(result){
				if(result){
					alert(input_elem.value + '는 사용가능한 '+msg+'입니다');
					check_elem.value = input_elem.value;
				}
				else{
					alert(input_elem.value + '는 사용 불가능한 '+msg+'입니다.');
				}
			},
			error:function(request, status, error){
				console.log(error);
			}
		});
	}
		
	function signup(){
		const form = document.getElementById("signup_form");
		/* 특무문자 / 문자 / 숫자 포함 형태의 8 ~ 15자리 이내의 암호 정규식 */
		const pwRegx = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
		const nickRegx = /^([a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{1,10}$/;
		const idRegx = /^[A-za-z]{5,15}/g;
		
		if(!form.nickname.trim()){
			alert("닉네임을 입력하세요");
			form.nickname.focus();
			return;
		}
		if(form.check_nickname.value != form.nickname.value){
			alert("중복검사를 해주세요");
			return;
		}
		if(nickRegx.test(form.nickname.value)){
			alert('닉네임은 한글, 영문, 숫자만 가능하며 2-10자리까지 입니다');
			return;
		}
		
		if(!form.id.value.trim()){
			alert("ID를 입력하세요");
			form.id.focus();
			return;
		}
		
		
		if(form.password.value == ""){
			alert("비밀번호를 입력하세요");
			form.password.focus();
			return;
		}
		if(!pwRegx.test(form.password.value)){
			alert("비밀번호 형식을 확인해 주세요");
			form.password.focus();
			return;
		}
		if(form.password_confirm.value == ""){
			alert("비밀번호 확인을 입력하세요");
			form.password_confirm.focus();
			return;
		}
		if(form.password_confirm.value != form.password.value){
			alert("비밀번호 확인을 제대로 입력하세요");
			form.password_confirm.value = "";
			form.password.value = "";
			form.password.focus();
			return;
		}
		if(form.address.value == ""){
			alert("주소를 입력하세요");
			form.address.focus();
			return;
		}
		if(form.tel.value == ""){
			alert("전화번호를 입력하세요");
			form.tel.focus();
			return;
		}
		if(!telRegx.test(form.tel.value)){
			alert("전화번호 형식을 입력하세요");
			form.tel.focus();
			form.tel.value = "";
			return;
		}
		
		form.submit();
	}
</script>
<div class="wrapper">
	<div>
		<div>
			<h2>회원가입</h2>
		</div>
		<form method="post" id="signup_form">
			<input type="hidden" name="check_id">
			<input type="hidden" name="check_nickname">
			<div>
				<div>
					<h2>닉네임</h2>
				</div>
				<div>
					<div>
						<input type="text" name="nickname" id="nickname"/>
					</div>
					<div>
						<button type="button" id="nickname_confirm">중복확인</button>
					</div>
				</div>
				<div>
					<h2>아이디</h2>
				</div>
				<div>
					<div>
						<input type="text" name="id" id="id"/>
					</div>
					<div>
						<button type="button" id="id_confirm">중복확인</button>
					</div>
				</div>
				<div>
					<h2>비밀번호</h2>
				</div>
				<div>
					<div>
						<input type="password" name="password" id="password"/>
					</div>
				</div>
				<div>
					<h2>비밀번호 확인</h2>
				</div>
				<div>
					<div>
						<input type="password" name="password_confirm" id="password_confirm"/>
					</div>
				</div>
				<div>
					<button id="signup_button">회원가입</button>
				</div>
			</div>
		</form>
	</div>
</div>