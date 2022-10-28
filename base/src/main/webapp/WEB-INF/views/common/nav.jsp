<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
		<a class="navbar-brand" href="/">Home</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
		<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.mvo.email" var="authEmail"/>
		<sec:authentication property="principal.mvo.nickName" var="authNick"/>
		<sec:authentication property="principal.mvo.grade" var="authGrade"/>
		<sec:authentication property="principal.mvo.authList" var="auths"/>
					<c:if test="${authEmail ne null && authEmail ne ''}">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						${authNick }</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="/member/detail?email=${authEmail }">${authEmail }</a></li>
						<c:if test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get() }">
						<li><a class="dropdown-item" href="#">등급: ${authGrade }</a></li>
						<li><a class="dropdown-item" href="/member/list">회원 관리</a></li>
						</c:if>
						<li><a class="dropdown-item" href="/member/logout" id="logoutBtn">Logout</a></li>
						<form action="/member/logout" method="post" id="logoutForm" style="display: none;">
							<input type="hidden" name="email" value="${authEmail }">
							<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
						</form>
					</ul></li>
					<li class="nav-item"><a href="/product/register" class="nav-link">Product Register</a></li>
					</c:if>
					</sec:authorize>
				<sec:authorize access="isAnonymous()">
				<li class="nav-item">
							<a class="nav-link active" aria-current="page" href="/member/register">Member Register</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/member/login">Login</a>
						</li>
				</sec:authorize>
				 <li class="nav-item"><a href="/product/list" class="nav-link">Product List</a></li>
			</ul>
		</div>
	</div>
</nav>
<script>
	document.getElementById('logoutBtn').addEventListener('click', (e) => {
		e.preventDefault();
		document.getElementById('logoutForm').submit();
	});
</script>