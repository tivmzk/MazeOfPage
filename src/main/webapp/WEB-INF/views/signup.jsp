<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="/js/signup.js"></script>
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
				<div class="button mt-10">
					<button id="signup_button" class="w-100p">회원가입</button>
				</div>
			</div>
		</form>
	</div>
</div>