package com.project.metakakao.controller;


import com.project.metakakao.dto.CreateQuizDTO;
import com.project.metakakao.dto.KakaoDTO;
import com.project.metakakao.entity.Member;
import com.project.metakakao.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/quiz")
@Log4j2
@RequiredArgsConstructor
public class QuizController extends HttpServlet {
    private final QuizService quizService;
    @GetMapping
    public String newquiz() {
        return "/quiz/newquiz";
    }
    @GetMapping("/myquiz")
    public String myquiz() {
        return "quiz/myquiz";
    }
    @PostMapping("/list")
    public String list() {
        return "quiz/quizlist";
    }

    // 단건 조회

    // 전체 조회

    // 작성
    @PostMapping("make")
    public ResponseEntity makeQuiz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quizTitle = request.getParameter("quizTitle");
        log.info("quizTitle: " + quizTitle);
        List<String> questions = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            questions.add(request.getParameter("question" + i));
            log.info("questions: " + request.getParameter("question" + i));
        }
        String accessToken = (String) request.getSession().getAttribute("accessToken");
        log.info("accessToken: " + accessToken);
        try {
            KakaoDTO kakaoDTO = quizService.getUserInfoWithToken(accessToken); //null
            Member member = Member.builder()
                    .email(kakaoDTO.getEmail())
                    .nickname(kakaoDTO.getNickname())
                    .build();
            log.info(kakaoDTO.getEmail());
            log.info(kakaoDTO.getNickname());
            CreateQuizDTO createQuizDTO = CreateQuizDTO.builder()
                    .title(quizTitle)
                    .q1(questions.get(0))
                    .q2(questions.get(1))
                    .q3(questions.get(2))
                    .q4(questions.get(3))
                    .q5(questions.get(4))
                    .q6(questions.get(5))
                    .q7(questions.get(6))
                    .q8(questions.get(7))
                    .q9(questions.get(8))
                    .q10(questions.get(9))
                    .host(member)
                    .build();
            // db에 저장
            quizService.register(createQuizDTO);
            response.sendRedirect("quizList.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 수정

    // 삭제
}
