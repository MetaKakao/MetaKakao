<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz List</title>
</head>
<body>

<h2>퀴즈 목록</h2>

<%
    // 이전에 저장한 퀴즈 데이터를 가져오기
    String quizTitle = request.getAttribute("quizTitle").toString();
    List<String> questions = new ArrayList<>();
    for(int i = 1; i <= 10; i++) {
        questions.add(request.getAttribute("question"+i).toString());
    }
%>
<div>
    <h3><%= quizTitle %></h3>
    <ul>
        <% for (String question : questions) { %>
        <li><%= question %></li>
        <% } %>
    </ul>
</div>
</body>
</html>
