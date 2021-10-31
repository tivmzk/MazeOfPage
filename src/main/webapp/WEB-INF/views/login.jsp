<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="wrapper">
	<div class="login-wrapper">
		<div class="form-title">
			<h2>로그인</h2>
		</div>
		<form method="post" class="login-form flex justify-center">
			<div class="mr-5">
				<input type="text" name="id" placeholder="아이디" class="mb-5" /> 
				<input type="password" name="password" placeholder="비밀번호" class="mt-5"/>
			</div>
			<div>
				<button>로그인</button>
			</div>
		</form>
		<div class="text-center py-20">
			<p>
				<a href="/signup" class="signup">회원가입 하러가기</a>
			</p>
		</div>
	</div>
</div>