<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/layout/header.jsp">
    <jsp:param name="title" value="로그인"/>
</jsp:include>

<style>
    .container{
        max-width: 400px;
        margin: 0 auto;
    }
</style>

<div class="container">
    <div class="login-container">
        <h2 class="text-center mb-4">로그인</h2>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <form action="/login" method="post" modelAttribute="user">
            <div class="mb-3">
                <label for="userId" class="form-label">아이디</label>
                <input type="text" class="form-control" id="userId" name="userId" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">비밀번호</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary">로그인</button>
            </div>
        </form>
    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>
