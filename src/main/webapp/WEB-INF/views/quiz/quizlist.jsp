<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz List</title>
    <link rel="stylesheet" href="../../../resources/css/quizlist.css">
</head>
<body>
<h2>${mid} 님의 퀴즈 목록</h2>
<div class="container">
    <div class="left"></div>
    <div class="center">
        <div>
            <table>
                <tbody>
                <c:forEach var="index" begin="0" end="${titles.size() - 1}" varStatus="loop">
                    <tr>
                        <td><a href="/quiz/game/${mid}/${titles.size() - 1 - loop.index}">${titles[index]}</a></td>
                        <td>생성일자: ${regDates[titles.size() - 1 - loop.index]}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="right"></div>
</div>

<!-- 홈화면 가는 버튼 넣어야될듯-->
</body>
</html>
