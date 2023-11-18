<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page</title>
    <link rel="stylesheet" href="../../../resources/css/question.css" />
</head>
<body>
<div class="sidebar"></div>
<div class="content">
    <header>
    <div class="image"><img class="profile" src="../../../resources/assets/default.png"></div>
    <div class="nickname">홍길동</div>
    <button id="modalOpenButton">익명질문 남기기</button>
    <div id="modalContainer" class="hidden">
        <div id="modalContent">
            <form action="profile.jsp"> <!--Todo: 만들기 -->
                <textarea></textarea>
                <table>
                    <tr><td class="info"><input type="submit" id="complete" value="작성완료"></td><td class="info"><button id="modalCloseButton">취소하기</button></td></tr>
                </table>
            </form>
        </div>
    </div>
    <hr>
    </header>
    <main>
        <div id="info">
            <table>
                <tr><td><div id="done">답변 완료</div></td><td><div id="refuse">거절 질문</div></td></tr>
            </table>
        </div>
        <hr>
    </main>
    <footer>

    </footer>
</div>
<div class="sidebar"></div>
</body>
<script>
    const modalOpenButton = document.getElementById('modalOpenButton');
    const modalCloseButton = document.getElementById('modalCloseButton');
    const modal = document.getElementById('modalContainer');
    const modalSubmitButton = document.getElementById('complete');

    modalOpenButton.addEventListener('click', () => {
        modal.classList.remove('hidden');
    });

    modalCloseButton.addEventListener('click', () => {
        modal.classList.add('hidden');
    });

</script>
</html>