<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Quiz</title>
    <link rel="stylesheet" href="../../../resources/css/myquiz.css"
</head>
<body>
<div class="container">
<div class="left"></div>
<div class="center">
    <form action="make" method="post">
        <div id="contents">
        <div class="title">
            <label for="quizTitle">퀴즈 제목:</label>
            <input type="text" id="quizTitle" name="quizTitle" placeholder="제목을 입력하세요." required>
        </div>

        <% for (int i = 1; i <= 10; i++) { %>
        <div class="question">
        <label for="question<%=i%>">Q<%=i%>.</label>
            <br>
        <input type="text" id="question<%=i%>" name="question<%=i%>" placeholder="내용을 입력하세요."required><br>
        <!-- O/X 버튼 추가 -->
        <input type="radio" id="answer<%=i%>" name="answer<%=i%>" value="O" />O
        <input type="radio" id="answer<%=i%>" name="answer<%=i%>" value="X" />X
        </div>
        <% } %>

        <br>
        <input type="submit" class="button" value="저장하기">
        <input type="button" class="button" value="취소하기" onclick="window.location.href='/quiz';">
        </div>
    </form>
</div>
<div class="right"></div>
</div>



</body>
</html>
