<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />

<div class="container mt-3">
	<h1>Product Register</h1>
	<form action="/product/register" method="post" enctype="multipart/form-data">
		<div class="mb-3 mt-3">
			<label for="category">Category:</label>
			<input type="text"
				class="form-control" id="category" placeholder="Enter category"
				name="category">
		</div>
		<div class="mb-3">
			<label for="pname">Pname:</label> <input type="text"
				class="form-control" id="pname" placeholder="Enter pname"
				name="pname">
		</div>
		<div class="mb-3">
			<label for="price">Price:</label> <input type="text"
				class="form-control" id="price" placeholder="Enter price"
				name="price">
		</div>
		<div class="mb-3">
			<label for="writer">Writer:</label> <input type="text"
				class="form-control" id="writer"
				name="writer" value="${ses.email }" readonly>
		</div>
		<div class="mb-3">
			<label for="madeBy">Made By:</label> <input type="text"
				class="form-control" id="madeBy" placeholder="Enter madeBy"
				name="madeBy">
		</div>
		<div class="mb-3">
			<label for="description">Description:</label>
			<textarea class="form-control" placeholder="Enter description" id="description" name="description"></textarea>
		</div>
		<div class="mb-3 d-grid">
			<label for="attach">File Attach:</label> <input type="file"
				class="form-control" style="display: none;" id="files"	name="fileAttached" multiple>
			<button type="button" id="attachTrigger" class="btn btn-outline-primary btn-block d-block">Attach Files</button>
			<div class="my-3" id="fileZone"></div>
		</div>
		<button type="submit" class="btn btn-primary" id="regBtn">상품 등록</button>
	</form>
</div>

<script>
	document.getElementById('attachTrigger').addEventListener('click', () => {
		document.getElementById('files').click();
	});
</script>
<script src="/resources/js/product.register.js"></script>
<jsp:include page="../common/footer.jsp" />