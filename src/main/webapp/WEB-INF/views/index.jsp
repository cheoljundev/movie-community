<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/layout/header.jsp">
    <jsp:param name="title" value="영화리뷰 커뮤니티"/>
</jsp:include>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-12 text-center">
            <h1>안녕하세요 ! 영화리뷰 커뮤니티입니다.</h1>
            <p class="lead">영화 리뷰를 나누고 공유할 수 있는 커뮤니티입니다.</p>
            <a class="btn btn-primary btn-lg" href="/board" role="button">게시판 바로가기</a>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>