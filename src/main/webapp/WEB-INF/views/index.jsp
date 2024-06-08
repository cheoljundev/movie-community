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

안녕하세요.

<%@include file="/WEB-INF/layout/footer.jsp"%>