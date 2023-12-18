
<%@ page import="com.project.metakakao.entity.Member" %>
<%@ page import="com.project.metakakao.dto.KakaoDTO" %>
<%@ page import="java.util.Objects" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page</title>
    <link rel="stylesheet" href="../../../resources/css/question.css" />
    <script>
        function toggleAnswerForm(index) {
            var form = document.getElementById("answer-form-" + index);
            form.style.display = form.style.display === "none" ? "block" : "none";
        }
    </script>
</head>
<body>
    <div class="background">
        <div class = userInfo>
            <%
                Member user = (Member) request.getAttribute("user");
                KakaoDTO kakaoDTO = (KakaoDTO) request.getAttribute("kakaoDTO");
                boolean owner = Objects.equals(kakaoDTO.getEmail(), user.getEmail());
                request.setAttribute("owner", owner); //owner를 request 속성으로 설정, 이렇게해야 jstl 사용가능
                session = request.getSession();
                String accessToken = (String) session.getAttribute("accessToken");
            %>
            ${kakaoDTO.nickname}님 환영합니다. |
            <%
                if (accessToken != null && !accessToken.isEmpty()) {
            %>로그아웃
            <%
            } else {
            %>
            로그인
            <%
                }
            %>
        </div>
        <div class ="profile">
            <div class="image">
                <img src = "${user.profileImgUrl}"/>
            </div>
            <div class ="profile-box">
                <div class="name"> ${user.nickname}</div>
                <div class="birth"> ${user.email}</div>
                <div class="birth">짜파게티 먹고싶다.</div>
            </div>
            <button class = "ans_complete">답변완료</button>
            <button class = "new_question">새 질문</button>
        </div>
        <%
            if(owner){
        %>
        <div class="new_contexnt">
            <form id="questionForm" action="/question/ask" method="post">
                <input type="hidden" name="hostID" value="${user.id}"/>
                <textarea id="userQuestion" name="content" placeholder="지금 ${user.nickname} 님에게 질문을 남겨보세요"></textarea>
                <button type="submit">등록</button>
            </form>
        </div>
        <%
            }
        %>
        <c:forEach var="question" items="${questionList}" varStatus="status">
            <div class="question-item" id="question-item-${status.index}">
                <!-- 삭제 버튼 -->
                <form action="/question/delete" method="post">
                    <input type="hidden" name="questionId" value="${question.qno}"/>
                    <button type="submit" class="delete_btn">삭제</button>
                </form>
                <div class="question-content">${question.content}
                </div>
                <c:if test="${owner}">
                    <!-- 답변하기 버튼 -->
                    <button type="button" class="answer-btn" onclick="toggleAnswerForm(${status.index})">답변하기</button>
                    <!-- 답변 폼 (숨겨진 상태로 시작) -->
                    <form action="/question/answer" method="post" class="answer-form" id="answer-form-${status.index}" style="display: none;">
                        <input type="hidden" name="questionId" value="${question.qno}"/>
                        <textarea name="answerContent" placeholder="답변을 여기에 작성하세요"></textarea>
                        <button type="submit" class="submit-answer">답변 제출</button>
                    </form>
                </c:if>
            </div>
        </c:forEach>
    </div>
</body>

</html>