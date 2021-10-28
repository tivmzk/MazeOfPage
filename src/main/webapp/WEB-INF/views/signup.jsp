<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="wrapper">
	<div>
		<div>
			<h2>회원가입</h2>
		</div>
		<form method="post">
			<div>
				<div>
					<h2>닉네임</h2>
				</div>
				<div>
					<div>
						<input type="text" name="nickname"/>
					</div>
					<div>
						<button>중복확인</button>
					</div>
				</div>
				<div>
					<h2>아이디</h2>
				</div>
				<div>
					<div>
						<input type="text" name="id"/>
					</div>
					<div>
						<button>중복확인</button>
					</div>
				</div>
				<div>
					<h2>비밀번호</h2>
				</div>
				<div>
					<div>
						<input type="password" name="password"/>
					</div>
				</div>
				<div>
					<h2>비밀번호 확인</h2>
				</div>
				<div>
					<div>
						<input type="password" name="password-confirm"/>
					</div>
					<div>
						<button>확인</button>
					</div>
				</div>
				<div>
					<button>회원가입</button>
				</div>
			</div>
		</form>
	</div>
</div>