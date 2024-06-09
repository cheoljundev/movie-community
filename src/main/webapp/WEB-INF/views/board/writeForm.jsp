<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/layout/header.jsp">
  <jsp:param name="title" value="글쓰기"/>
</jsp:include>

<div class="container mt-5">
  <h2 class="text-center mb-4">글쓰기</h2>
  <form action="write" method="post" modelAttribute="post" enctype="multipart/form-data">
    <div class="mb-3">
      <label for="title" class="form-label">제목</label>
      <input path="title" type="text" class="form-control" id="title" name="title" required />
    </div>
    <div class="mb-3">
      <label for="content" class="form-label">내용</label>
      <textarea path="content" class="form-control" id="content" name="content" rows="5" required></textarea>
    </div>
<%--    <div class="mb-3">--%>
<%--      <label for="file" class="form-label">첨부파일</label>--%>
<%--      <input type="file" class="form-control-file" id="file" name="file">--%>
<%--    </div>--%>
    <div class="d-grid gap-2">
      <button type="submit" class="btn btn-primary">글쓰기</button>
    </div>
  </form>
</div>

<%@include file="/WEB-INF/layout/footer.jsp"%>
