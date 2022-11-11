<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
		<a class="navbar-brand" href="/">지식iN</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				 <li class="nav-item"><a href="/board/list" class="nav-link">Q&A</a></li>
				<c:choose>
					<c:when test="${ses.email eq null || ses.email eq ''}">
					<li class="nav-item"><a href="/member/login" class="nav-link">질문하기</a></li>
						<li class="nav-item">
							<a class="nav-link" href="/member/login">로그인</a>
						</li>
						<li class="nav-item">
							<a class="nav-link active" aria-current="page" href="/member/register">회원가입</a>
						</li>
					</c:when>
					<c:when test="${ses ne null && ses.email ne ''}">
					<li class="nav-item"><a href="/board/register" class="nav-link">질문하기</a></li>
						<li class="nav-item">
							<a class="nav-link" href="/member/logout">Logout</a>
						</li>
					</c:when>
				</c:choose>
			</ul>
		</div>
	</div>
</nav>