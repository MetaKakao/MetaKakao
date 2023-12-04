package com.project.metakakao.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quiz")
@Log4j2
@RequiredArgsConstructor
public class QuizController {
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
}
