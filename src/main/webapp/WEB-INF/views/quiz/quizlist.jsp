<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz List</title>
</head>
<body>

<h2>퀴즈 목록</h2>

<div>
    <table border="1">
        <thead>
        <tr>
            <th>제목</th>
            <th>작성 일자</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="index" begin="0" end="${titles.size() - 1}">
            <tr>
                <td><a href="/quiz/game/${mid}/${index+1}">${titles[index]}</a></td>
                <td>${regDates[index]}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
