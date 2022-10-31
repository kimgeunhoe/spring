<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />

<div class="container mt-3">
	<h1>Member Register</h1>
	<form action="/member/register" method="post">
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		<div class="mb-3 mt-3">
			<label for="email">Email:</label>
			<div class="input-group">
			<input type="email"
				class="form-control" id="email" placeholder="Enter email"
				name="email" value="tester?@tester.com">
				<button type="button" class="btn btn-success" id="dupleCheck">중복확인</button></div>
		</div>
		<div class="mb-3">
			<label for="pwd">Password:</label> <input type="password"
				class="form-control" id="pwd" placeholder="Enter password"
				name="pwd" value="1111">
		</div>
		<div class="mb-3">
			<label for="nick">Nick:</label> <input type="text"
				class="form-control" id="nick" placeholder="Enter nickname"
				name="nickName" value="Tester?">
		</div>
		<div class="mb-3 d-grid">
			<label for="profile">Profile:</label> <input type="file"
				class="form-control" style="display: none;" id="files"	name="profile" multiple>
			<button type="button" id="profileTrigger" class="btn btn-outline-primary btn-block d-block">Profile</button>
			<div class="my-3" id="fileZone"></div>
		</div>
		<button type="submit" class="btn btn-primary" id="regBtn">회원가입</button>
	</form>
</div>
<script src="/resources/js/member.register.js"></script>
<script>
document.getElementById('profileTrigger').addEventListener('click', () => {
		document.getElementById('files').click();
	});
</script>
<jsp:include page="../common/footer.jsp" />