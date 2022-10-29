<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="common/header.jsp" />
<jsp:include page="common/nav.jsp" />

<div class="container mt-3">
  <h2>잘 부탁한다 스프링!</h2>
  <div class="mt-4 p-5 bg-primary text-white rounded">
    <h1>Spring Framework Example</h1> 
	<P>  The time on the server is ${serverTime}. </P>
  </div>
</div>
<script>
let isLogin = '<c:out value="${isLogin}"/>';
let isLogout = '<c:out value="${isLogout}"/>';

if(parseInt(isLogin)==1) {
	alert("로그인 성공");	
} /* else if(parseInt(isLogin)==0) {
	alert("로그인 실패");	
} */

if(parseInt(isLogout)==1) {
alert("로그아웃");	
}
</script>
<jsp:include page="common/footer.jsp" />