<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$('#nickname_confirm').click(function(e){
			e.preventDefault();
			
			const form = document.getElementById("signup_form");
			if(form.nickname.value.trim() == ''){
				alert('닉네임을 입력하세요.')
				return;
			}
			
			check_duplicate('check_nickname', form.nickname, form.check_nickname, '닉네임');
		});
		
		$('#id_confirm').click(function(e){
			e.preventDefault();
			
			const form = document.getElementById("signup_form");
			if(form.id.value.trim() == ''){
				alert('아이디를 입력하세요.')
				return;
			}
			
			check_duplicate('check_id', form.id, form.check_id, '아이디');
		});
		
		$('#signup_button').click(signup);
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
		
	function signup(e){
		e.preventDefault();
		const form = document.getElementById("signup_form");

		const numCheck = function(str){
			const regx = /[0-9]/;
			return regx.test(str);
		}; // 숫자 
		const charCheck = function(str){
			const regx = /[a-zA-Z]/;
			return regx.test(str);
		}; // 문자 

		const spacialCheck = function(str){
			const regx = /[~!@#$%^&*()_+|<>?:{}]/;
			return regx.test(str);
		}; // 특수문자
		const spaceCheck = function(str){
			if(str.search(' ') != -1)
				return true;

			return false;
		};
		const korCheck = function(str){
			const regx = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
			return regx.test(str);
		}
		const lengthBound = function(str, min, max){
			if(str.length < min || str.length > max)
				return false;

			return true;
		};
		
		if(form.nickname.value == ""){
			alert("닉네임을 입력하세요");
			form.nickname.focus();
			return;
		}
		if(form.check_nickname.value != form.nickname.value){
			alert("닉네임 중복검사를 해주세요");
			return;
		}
		if(
				!lengthBound(form.nickname.value, 3, 20) || 
				spacialCheck(form.nickname.value) || 
				spaceCheck(form.nickname.value)
		){
			alert('닉네임은 특수문자가 들어갈 수 없으며 3-20자리까지 입니다');
			form.nickname.focus();
			form.nickname.select();
			return;
		}
		
		if(form.id.value == ""){
			alert("아이디를 입력하세요");
			form.id.focus();
			return;
		}
		if(form.id.value != form.check_id.value){
			alert("아이디 중복검사를 해주세요.");
			return;
		}
		if(
			!lengthBound(form.id.value, 5, 20) || 
			spacialCheck(form.id.value) || 
			korCheck(form.id.value) ||
			spaceCheck(form.id.value)
			){
			alert("아이디는 특수문자와 한글이 들어갈 수 없으며 5-20자리까지 입니다");
			form.id.focus();
			form.id.select();
			return;
		}
		
		if(form.password.value == ""){
			alert("비밀번호를 입력하세요");
			form.password.focus();
			return;
		}
		if(
			!lengthBound(form.password.value, 8, 20) ||
			!spacialCheck(form.password.value) ||
			!charCheck(form.password.value) ||
			!numCheck(form.password.value) ||
			spaceCheck(form.password.value)
			){
			alert("비밀번호는 영문자, 숫자, 특수문자만 들어있어야하고 8-20자리 까지 입니다.");
			form.password.focus();
			form.password.select();
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
		
		form.submit();
	}
</script>
<div class="wrapper">
	<div class="signup-wrapper">
		<div class="form-title">
			<h2>회원가입</h2>
		</div>
		<form method="post" id="signup_form">
			<input type="hidden" name="check_id">
			<input type="hidden" name="check_nickname">
			<div>
				<div class="input-title">
					<h2>닉네임</h2>
				</div>
				<div class="flex justify-between item-center">
					<div>
						<input type="text" name="nickname" id="nickname"/>
					</div>
					<div class="vaildation-btn">
						<button type="button" id="nickname_confirm">중복확인</button>
					</div>
				</div>
				<div class="input-title">
					<h2>아이디</h2>
				</div>
				<div class="flex justify-between item-center">
					<div>
						<input type="text" name="id" id="id"/>
					</div>
					<div class="vaildation-btn">
						<button type="button" id="id_confirm">중복확인</button>
					</div>
				</div>
				<div class="input-title">
					<h2>비밀번호</h2>
				</div>
				<div>
					<div>
						<input type="password" name="password" id="password"/>
					</div>
				</div>
				<div class="input-title">
					<h2>비밀번호 확인</h2>
				</div>
				<div>
					<div>
						<input type="password" name="password_confirm" id="password_confirm"/>
					</div>
				</div>
				<div class="btn mt-10">
					<button id="signup_button" class="w-100p">회원가입</button>
				</div>
			</div>
		</form>
	</div>
</div>