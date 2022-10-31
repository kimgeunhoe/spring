<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />

<div class="container mt-5">
	<div class="row">
		<div class="col-sm-12 col-md-6">
			<h1>
				Produdct List
				<a href="/product/register" class="btn btn-primary">Register</a>
				<sec:authorize access="isAuthenticated()"></sec:authorize>
			</h1>
		</div>
		<div class="col-sm-12 col-md-6">
			<form class="d-flex" action="/product/list" method="get">
			<input type="hidden" name="pageNo" value="${pgn.pgvo.pageNo }">
			<input type="hidden" name="qty" value="${pgn.pgvo.qty }">
				<div class="input-group">
					<c:set value="${pgn.pgvo.type }" var="typed" />
					<select class="form-select" name="type">
						<option ${typed eq null ? 'selected' : '' }>선택</option>
						<option value="twc" ${typed eq 'twc' ? 'selected' : '' }>전체검색</option>
						<option value="t" ${typed eq 't' ? 'selected' : '' }>Title</option>
						<option value="w" ${typed eq 'w' ? 'selected' : '' }>Writer</option>
						<option value="c" ${typed eq 'c' ? 'selected' : '' }>Content</option>
						<option value="tw" ${typed eq 'tw' ? 'selected' : '' }>Title or Writer</option>
						<option value="tc" ${typed eq 'tc' ? 'selected' : '' }>Title or Content</option>
						<option value="wc" ${typed eq 'wc' ? 'selected' : '' }>Writer or Content</option>
					</select>
					<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="kw" value="${pgn.pgvo.kw }">
					<button class="btn btn-outline-success" type="submit">Search <span class="badge bg-danger">${pgn.totalCount }</span></button>
				</div>
			</form>
		</div>
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Pno</th>
				<th>Category</th>
				<th>Pname</th>
				<th>Price</th>
				<th>Writer</th>
				<th>Mod At</th>
				<th>Read Count</th>
				<th>Cmt Qty</th>
				<th>Attach</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="pvo">
				<tr>
					<td>${pvo.pno }</td>
					<td>${pvo.category }</td>
					<td><a href="/product/detail?pno=${pvo.pno }&pageNo=${pgn.pgvo.pageNo }&qty=${pgn.pgvo.qty}">${pvo.pname }</a></td>
					<td>${pvo.price }</td>
					<td>${pvo.writer }</td>
					<td>${pvo.modAt }</td>
					<td>${pvo.readCount }</td>
					<td>${pvo.cmtQty }</td>
					<td><c:if test="${pvo.fileCount > 0}"><i class="material-icons">attachment</i></c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="pagination justify-content-center">
		<c:if test="${pgn.prev }">
		<li class="page-item"><a class="page-link" href="/product/list?pageNo=${pgn.startPage-1 }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&kw=${pgn.pgvo.kw}">Prev</a></li>
		</c:if>
		<c:forEach begin="${pgn.startPage }" end="${pgn.endPage }" var="i">
		<li class="page-item ${pgn.pgvo.pageNo==i?'active':'' }"><a class="page-link" href="/product/list?pageNo=${i }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&kw=${pgn.pgvo.kw}">${i }</a></li>
		</c:forEach>
		<c:if test="${pgn.next }">
		<li class="page-item"><a class="page-link" href="/product/list?pageNo=${pgn.endPage+1 }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&kw=${pgn.pgvo.kw}">Next</a></li>
		</c:if>
	</ul>
	</div>

<jsp:include page="../common/footer.jsp" />