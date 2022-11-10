<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />

<div class="container mt-3">
	<h1>수정하기</h1>
	<form action="/board/modify" method="post" enctype="multipart/form-data">
		<input type="hidden" name="bno" value="${bdto.bvo.bno }">
		<input type="hidden" name="pageNo" value="${pgvo.pageNo }">
		<input type="hidden" name="qty" value="${pgvo.qty }">
		<input type="hidden" name="type" value="${pgvo.type }">
		<input type="hidden" name="kw" value="${pgvo.kw }">
		<div class="mb-3">
			<label for="title">Title:</label> <input type="text"
				class="form-control" id="title" placeholder="Enter title"
				name="title" value="${bdto.bvo.title }">
		</div>
		<div class="mb-3 mt-3">
			<label for="category">Category:</label>
			<input type="text"
				class="form-control" id="category" placeholder="Enter category"
				name="category" value="${bdto.bvo.category }">
		</div>
		<div class="mb-3">
			<label for="description">Description:</label>
			<textarea class="form-control" placeholder="Enter description" id="description" name="description">${bdto.bvo.description }</textarea>
		</div>
		<div class="mb-3 d-grid">
			<label for="attach">File Attach:</label> <input type="file"
				class="form-control" style="display: none;" id="files"	name="fileAttached" multiple>
			<button type="button" id="attachTrigger" class="btn btn-outline-primary btn-block d-block">Attach Files</button>
			<div class="my-3" id="fileZone"></div>
		</div>
		<button type="submit" class="btn btn-primary" id="regBtn">상품 수정</button>
	</form>
</div>
<hr>
<div class="container mt-3">
  <ul class="list-group list-group-flush">
  <c:forEach items="${bdto.fileList }" var="fvo">
    <li class="list-group-item d-flex justify-content-between align-items-center">${fvo.fileName }
    <span class="badge bg-primary rounded-pill">${fvo.fileSize }</span>
    <button data-uuid="${fvo.uuid }" class="btn btn-outline-danger btn-sm fileDelBtn">x</button>
  </li>
  </c:forEach>
  </ul>
</div>

<script>
	document.getElementById('attachTrigger').addEventListener('click', () => {
		document.getElementById('files').click();
	});
</script>
<script src="/resources/js/board.register.js"></script>
<script src="/resources/js/board.modify.js"></script>
<jsp:include page="../common/footer.jsp" />