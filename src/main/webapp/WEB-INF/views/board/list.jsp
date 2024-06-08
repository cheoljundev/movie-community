<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/layout/header.jsp">
  <jsp:param name="title" value="영화리뷰 게시판"/>
</jsp:include>

<style>
  .table-hover tbody tr:hover {
    background-color: #f1f1f1;
  }
  .table a {
    color: #007bff;
    text-decoration: none;
  }
  .table a:hover {
    color: #0056b3;
    text-decoration: underline;
  }

  .table {
    border-radius: 10px;
    overflow: hidden;
  }
  .thead-custom th {
    background-color: #6c757d;
    color: #fff;
    text-align: center;
    padding: 15px;
    font-size: 16px;
    border-top: none;
  }
  .table-bordered th, .table-bordered td {
    border: 1px solid #dee2e6;
  }
  .table-bordered tbody td {
    text-align: center;
    vertical-align: middle;
  }
  .table-responsive {
    border-radius: 10px;
    overflow: hidden;
  }
</style>

<div class="container mt-5">
  <h2 class="mb-4 text-center">영화 리뷰</h2>
  <div class="table-responsive">
    <table class="table table-hover table-bordered">
      <thead class="thead-custom">
      <tr>
        <th scope="col">번호</th>
        <th scope="col">제목</th>
        <th scope="col">작성자</th>
        <th scope="col">작성일</th>
      </tr>
      </thead>
      <c:forEach var="post" items="${posts}">
        <tr>
          <td>${post.id}</td>
          <td><a href="board/${post.id}">${post.title}</a></td>
          <td>${post.writer.name}</td>
          <td>${post.date}</td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
      <c:choose>
        <c:when test="${minPage == 1}">
          <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1">이전</a>
          </li>
        </c:when>
        <c:otherwise>
          <li class="page-item">
            <a class="page-link" href="board?page=${minPage - 1}" tabindex="-1">이전</a>
          </li>
        </c:otherwise>
      </c:choose>
      <c:forEach begin="${minPage}" end="${maxPage}" var="pageNumber">
        <c:choose>
          <c:when test="${currentPage == pageNumber}">
            <li class="page-item active">
              <a class="page-link" href="board?page=${pageNumber}">${pageNumber}</a>
            </li>
          </c:when>
          <c:otherwise>
            <li class="page-item">
              <a class="page-link" href="board?page=${pageNumber}">${pageNumber}</a>
            </li>
          </c:otherwise>
        </c:choose>
      </c:forEach>
      <c:choose>
        <c:when test="${maxPage < pages}">
          <li class="page-item">
            <a class="page-link" href="board?page=${maxPage + 1}">다음</a></li>
          </li>
        </c:when>
        <c:otherwise>
          <li class="page-item">
            <a class="page-link disabled" href="#">다음</a></li>
          </li>
        </c:otherwise>
      </c:choose>

    </ul>
  </nav>
</div>

<%@include file="/WEB-INF/layout/footer.jsp"%>