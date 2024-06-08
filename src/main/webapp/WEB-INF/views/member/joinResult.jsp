<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/layout/header.jsp">
  <jsp:param name="title" value="회원가입 완료"/>
</jsp:include>
<div class="container mt-5">
  <div class="row">
    <div class="col-md-6 offset-md-3">
      <div class="card">
        <div class="card-body text-center">
          <h2 class="card-title">회원가입 완료</h2>
          <p class="card-text">
            <c:out value="${user.name}" />님, 회원가입을 환영합니다!
          </p>
          <a href="/" class="btn btn-primary mt-3">메인 페이지로 돌아가기</a>
        </div>
      </div>
    </div>
  </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>
