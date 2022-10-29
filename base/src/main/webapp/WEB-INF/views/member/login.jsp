<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />

<div class="container mt-3">
  <h2>Login form</h2>
  <form action="/member/login" method="post">
  <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
    <div class="mb-3 mt-3">
      <label for="email">Email:</label>
      <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value="${email }">
    </div>
    <div class="mb-3">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
  </form>
  <c:if test="${not empty errMsg }">
  	<div class="text-dangr my-1">
  	<c:choose>
  		<c:when test="${errMsg eq 'Bad credentials' }">
  			<c:set var="errorText" value="이메일 혹은 비밀번호가 일치하지 않습니다." />
  		</c:when>
  		<c:otherwise>
  			<c:set var="errorText" value="관리자에게 문의하세요." />
  		</c:otherwise>
  	</c:choose>
  	${errorText }
  	</div>
  </c:if>
</div>

<jsp:include page="../common/footer.jsp" />