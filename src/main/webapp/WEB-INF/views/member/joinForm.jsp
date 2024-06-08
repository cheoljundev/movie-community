<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/layout/header.jsp">
    <jsp:param name="title" value="회원가입"/>
</jsp:include>

<style>
    .error-input{
        color: red;
        border: 2px solid red;
    }
    .error{
        color: red;
    }
</style>

<div class="container mt-5">
    <h2 class="mb-4">회원가입</h2>
    <form:form action="join" method="post" modelAttribute="user">
        <div class="mb-3">
            <form:label path="userId" class="form-label">아이디</form:label>
            <form:input path="userId" type="text" class="form-control" cssErrorClass="form-control error-input"/>
            <form:errors path="userId" cssClass="error"/>
        </div>
        <div class="mb-3">
            <form:label path="password" class="form-label">패스워드</form:label>
            <form:input path="password" type="password" class="form-control" cssErrorClass="form-control error-input"/>
            <form:errors path="password" cssClass="error"/>
        </div>
        <div class="mb-3">
            <form:label path="name" class="form-label">이름</form:label>
            <form:input path="name" type="text" class="form-control" cssErrorClass="form-control error-input" />
            <form:errors path="name" cssClass="error"/>
        </div>
        <button type="submit" class="btn btn-primary">가입하기</button>
    </form:form>
</div>

<%@include file="/WEB-INF/layout/footer.jsp"%>