<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<jsp:include page="/WEB-INF/layout/header.jsp">
  <jsp:param name="title" value="${post.title}"/>
</jsp:include>

<div class="container mt-5">
  <div class="card">
    <div class="card-body">
      <h5 class="card-title text-black p-3 mb-4">${post.title}</h5>
      <div class="row">
        <div class="col-md-3">
          <p class="font-weight-bold">작성자:</p>
        </div>
        <div class="col-md-9">
          <p>${post.writer.name}</p>
        </div>
      </div>
      <div class="row">
        <div class="col-md-3">
          <p class="font-weight-bold">작성일:</p>
        </div>
        <div class="col-md-9">
          <p><fmt:formatDate value="${post.date}" pattern="yyyy년 MM월 dd일" /></p>
        </div>
      </div>
    </div>
  </div>
  <div class="card mt-4">
    <div class="card-body">
      <div class="row">
        <img src="/board/image/${post.storeFileName}" alt="Uploaded image"/>
        <div class="col-md-9">
          <p>${post.content}</p>
        </div>
      </div>
    </div>
  </div>
  <div class="text-right mt-4">
    <a href="/board" class="btn btn-primary">목록</a>
  </div>
</div>

<%@include file="/WEB-INF/layout/footer.jsp"%>
