<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="../../resources/css/main.css">
</head>
<body>

<div class="bg-image">
    <div class="login-container">
        <img src="<c:url value='../../resources/assets/logo.png'/>" alt="MetaKakao" class="logo">
        <a href="${kakaoUrl}" class="login-button" id="kakaoLoginButton_Kr">
            <img src="<c:url value='../../resources/assets/kakaoLoginButton_kr.png'/>" alt="카카오 로그인">
        </a>
        <a href="${kakaoUrl}" class="login-button" id="kakaoLoginButton_En">
            <img src="<c:url value='../../resources/assets/kakaoLoginButton_en.png'/>" alt="Login with Kakao">
        </a>
    </div>
</div>

</body>
</html>
