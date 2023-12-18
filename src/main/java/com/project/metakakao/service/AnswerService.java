package com.project.metakakao.service;

import com.project.metakakao.entity.Answer;
import com.project.metakakao.entity.Question;
import com.project.metakakao.repository.AnswerRepository;
import com.project.metakakao.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository; // QuestionRepository 추가

    public void saveAnswer(Long questionId, String answerContent) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question 정보를 조회할 수 없습니다. QID: " + questionId));
        Answer answer = Answer.builder()
                .content(answerContent)
                .regDate(LocalDateTime.now())
                .question(question) // 질문과의 관계 설정
                .build();

        answerRepository.save(answer);
        question.setAnswer(answer);
        questionRepository.save(question);
    }
}
