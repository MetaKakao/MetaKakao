package com.project.metakakao.controller;

import com.project.metakakao.dto.QuestionDto;
import com.project.metakakao.entity.Question;
import com.project.metakakao.service.QuestionService;
import com.project.metakakao.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/question")
@Log4j2
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping
    public String question() {
        return "/question/question";
    }
    /*
    @PostMapping("/{id}")
    public ResponseEntity<?> ask(QuestionDto questionDto) throws Exception {
        // 인증 정보로부터
        // String writerName = userService.getUser().get().getUsername();
        // String host = url에서 host id 가져오기
        Question question = questionService.ask(writerName, questionDto, hostName);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

     */

    // 수정 API는 없어도 될 것 같고 삭제 API는 질문 주인(not 작성자)가 할 수 있게?

    // 신고 API?
}
