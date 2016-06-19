<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
欢迎您登陆系统:${sessionScope.current_user } <a href="${pageContext.request.contextPath }/account/logout">退出</a>
</div>