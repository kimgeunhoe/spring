<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />

<div class="container mt-5">
<h1>Member detail</h1>
<table class="table table-striped">
	<tr>
		<th>Email</th>
		<td id="emVal">${mvo.email }</td>
	</tr>
	<tr>
		<th>NickName</th>
		<td>${mvo.nickName }</td>
	</tr>
	<tr>
		<th>Reg At</th>
		<td>${mvo.regAt }</td>
	</tr>
	<tr>
		<th>Last Login</th>
		<td>${mvo.lastLogin }</td>
	</tr>
	<tr>
		<th>Grade</th>
		<td>${mvo.grade }</td>
	</tr>
</table>
	<ul class="nav justify-content-center">
		<li class="nav-item"><a class="nav-link" href="/member/list?pageNo=${pgvo.pageNo }&qty=${pgvo.qty}">List</a></li>
		<li class="nav-item"><a class="nav-link" href="/member/modify?email=${mvo.email }&pageNo=${pgvo.pageNo }&qty=${pgvo.qty}">Modify</a></li>
		<li class="nav-item"><a class="nav-link" id="memberRemove">Remove</a></li>
	</ul>
	<form action="" method="post" id="memberRmForm" style="display: none;">
		<input type="hidden" id="em" value="" name="email">
		<input type="hidden" value="${pgvo.pageNo }" name="pageNo">
		<input type="hidden" value="${pgvo.qty }" name="qty">
		<input type="hidden" value="${pgvo.type }" name="type">
		<input type="hidden" value="${pgvo.kw }" name="kw">
	</form>
</div>
<script src="/resources/js/member.detail.js"></script>
<jsp:include page="../common/footer.jsp" />