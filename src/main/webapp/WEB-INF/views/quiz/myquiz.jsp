<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Quiz</title>
</head>
<body>

<form action="make" method="post">
    <label for="quizTitle">퀴즈 제목:</label>
    <input type="text" id="quizTitle" name="quizTitle" required>

    <% for (int i = 1; i <= 10; i++) { %>
    <label for="question<%=i%>">문제 <%=i%>:</label>
    <textarea id="question<%=i%>" name="question<%=i%>" rows="4" cols="50" required></textarea><br>
    <% } %>

    <br>
    <input type="submit" value="퀴즈 저장">
    <input type="button" value="취소" onclick="window.location.href='/quiz';">
</form>

</body>
</html>
