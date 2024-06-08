<%@ page import="com.spring.dao.post.Post" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/layout/header.jsp">
    <jsp:param name="title" value="영화리뷰 커뮤니티"/>
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
    .pagination .page-item.active .page-link {
        background-color: #007bff;
        border-color: #007bff;
    }
    .pagination .page-link {
        color: #007bff;
    }
    .pagination .page-link:hover {
        color: #0056b3;
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
            <tbody>
            <%
                List<Post> posts = (List<Post>) request.getAttribute("posts");
                int startIndex = (int) request.getAttribute("startIndex");
                int endIndex = (int) request.getAttribute("endIndex");

                for (int i = startIndex; i <= endIndex; i++) {
                    Post post = posts.get(i);
            %>
            <tr>
                <td><%=post.getId()%></td>
                <td><a href="post/<%=post.getId()%>"><%=post.getTitle()%></a></td>
                <td><%=post.getWriter().getName()%></td>
                <td><%=post.getDate()%></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1">Previous</a>
            </li>
            <c:forEach begin="1" end="${pages}" var="pageNumber">
                <li class="page-item">
                    <c:url value="." var="pageLink">
                        <c:param name="page" value="${pageNumber}"/>
                    </c:url>
                    <c:choose>
                        <c:when test="${pageNumber == currentPage}">
                            <span class="page-link">${pageNumber}</span>
                        </c:when>
                        <c:otherwise>
                            <a class="page-link" href="${pageLink}">${pageNumber}</a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </c:forEach>
            <li class="page-item">
                <a class="page-link" href="#">Next</a></li>
            </li>
        </ul>
    </nav>
</div>

<%@include file="/WEB-INF/layout/footer.jsp"%>