<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />

<div class="container mt-5">
	<div class="row">
		<div class="col-sm-12 col-md-6">
			<h1>Q&A</h1>
		</div>
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>제목</th>
				<th>분야</th>
				<th>답변</th>
				<th>작성</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<td><a
						href="/board/detail?bno=${bvo.bno }&pageNo=${pgn.pgvo.pageNo }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&kw=${pgn.pgvo.kw}">${bvo.title }</a>
					<c:if test="${bvo.fileCount > 0}"> <i class="material-icons">attachment</i></c:if>
					</td>
					<td>${bvo.category }</td>
					<td>${bvo.cmtQty }</td>
					<td>${bvo.modAt }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="pagination justify-content-center">
		<c:if test="${pgn.prev }">
			<li class="page-item"><a class="page-link"
				href="/board/list?pageNo=${pgn.startPage-1 }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&kw=${pgn.pgvo.kw}">이전페이지</a></li>
		</c:if>
		<c:forEach begin="${pgn.startPage }" end="${pgn.endPage }" var="i">
			<li class="page-item ${pgn.pgvo.pageNo==i?'active':'' }"><a
				class="page-link"
				href="/board/list?pageNo=${i }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&kw=${pgn.pgvo.kw}">${i }</a></li>
		</c:forEach>
		<c:if test="${pgn.next }">
			<li class="page-item"><a class="page-link"
				href="/board/list?pageNo=${pgn.endPage+1 }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&kw=${pgn.pgvo.kw}">다음페이지</a></li>
		</c:if>
	</ul>
	<div class="col-sm-12 col-md-6">
		<form class="d-flex" action="/board/list" method="get">
			<input type="hidden" name="pageNo" value="${pgn.pgvo.pageNo }">
			<input type="hidden" name="qty" value="${pgn.pgvo.qty }">
			<div class="input-group">
				<c:set value="${pgn.pgvo.type }" var="typed" />
				<select class="form-select" name="type">
					<option ${typed eq null ? 'selected' : '' }>선택</option>
					<option value="twc" ${typed eq 'tcd' ? 'selected' : '' }>전체검색</option>
					<option value="t" ${typed eq 't' ? 'selected' : '' }>제목</option>
					<option value="c" ${typed eq 'c' ? 'selected' : '' }>분야</option>
					<option value="d" ${typed eq 'd' ? 'selected' : '' }>내용</option>
					<option value="tc" ${typed eq 'tc' ? 'selected' : '' }>제목, 분야</option>
					<option value="td" ${typed eq 'td' ? 'selected' : '' }>제목, 내용</option>
					<option value="cd" ${typed eq 'cd' ? 'selected' : '' }>분야, 내용</option>
				</select> <input class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search" name="kw" value="${pgn.pgvo.kw }">
				<button class="btn btn-outline-success" type="submit">검색</button>
			</div>
		</form>
	</div>
</div>

<jsp:include page="../common/footer.jsp" />