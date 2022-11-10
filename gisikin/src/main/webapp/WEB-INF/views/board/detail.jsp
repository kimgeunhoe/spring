<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />

<c:set var="bvo" value="${bdto.bvo }" />
<div class="container mt-5">
<div>
<h1>Q ${bvo.title }</h1>
<div id="bnoVal" style="display: none;">${bvo.bno }</div>
<div>${bvo.description }</div>
<c:forEach items="${bdto.fileList }" var="fvo">
	<c:choose>
		<c:when test="${fvo.fileType==1 }">
		<div class=""><img class="img" src="/upload/${fn:replace(fvo.saveDir, '\\', '/') }/${fvo.uuid}_th_${fvo.fileName}"></div>
		</c:when>
		<c:otherwise></c:otherwise>
	</c:choose>
	</c:forEach>
<div>${bvo.writer } | ${bvo.modAt } | 조회수 ${bvo.readCount }</div>
</div>
	<ul class="nav justify-content-center">
		<li class="nav-item"><a class="nav-link" href="/board/list?pageNo=${pgvo.pageNo }&qty=${pgvo.qty}&type=${pgvo.type}&kw=${pgvo.kw}">목록</a></li>
<c:if test="${ses.email eq bvo.writer }">
		<li class="nav-item"><a class="nav-link" href="/board/modify?bno=${bvo.bno }&pageNo=${pgvo.pageNo }&qty=${pgvo.qty}&type=${pgvo.type}&kw=${pgvo.kw}">수정</a></li>
		<li class="nav-item"><a class="nav-link" id="boardRemove">삭제</a></li>
</c:if>
	</ul>
<div class="input-group mb-3">
      <span class="input-group-text" id="cmtWriter">${ses.email }</span>
      <input type="text" class="form-control" placeholder="Add Comment" id="cmtText">
       <button type="button" class="btn btn-primary" id="cmtSbmBtn">답변하기</button>
    </div>       
<h1 id="cmtQty"></h1>
<div id="cmtZone"></div>
	<form action="" method="post" id="boardRmForm" style="display: none;">
		<input type="hidden" id="bno" value="" name="bno">
		<input type="hidden" value="${pgvo.pageNo }" name="pageNo">
		<input type="hidden" value="${pgvo.qty }" name="qty">
		<input type="hidden" value="${pgvo.type }" name="type">
		<input type="hidden" value="${pgvo.kw }" name="kw">
	</form>
</div>
<button type="button" style="display:none;" id="modalBtn"
class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
  Open modal
</button>

<!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Modal Heading</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <img id="modalImg" src="">
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-success modSbmBtn" data-cno="">Submit</button>
      </div>

    </div>
  </div>
</div>
<div class="container mt-3">    
  <div class="text-center" style="visibility: hidden">
	  <button type="button" id="moreBtn" class="btn btn-outline-secondary" data-page="1">More +</button>
  </div>
</div>
<script src="/resources/js/board.detail.js"></script>
<script src="/resources/js/board.comment.js"></script>
<script>
document.addEventListener('DOMContentLoaded', function() {
	getCommentList(document.getElementById('bnoVal').innerText);
	if(document.getElementById('cmtWriter').innerText=='') {
		document.getElementById('cmtText').disabled = true;;
		document.getElementById('cmtSbmBtn').classList.add("disabled");
	}
});
</script>
<jsp:include page="../common/footer.jsp" />