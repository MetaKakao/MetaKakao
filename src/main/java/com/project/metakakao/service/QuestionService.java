package com.project.metakakao.service;

import com.project.metakakao.dto.QuestionDTO;
import com.project.metakakao.entity.Question;

import java.util.List;

public interface QuestionService {
    Long register(QuestionDTO questionDTO);

    List<Question> readAll(String hostID, int status);

    void remove(String hostID, Long qno);
}
