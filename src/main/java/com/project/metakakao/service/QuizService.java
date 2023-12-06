package com.project.metakakao.service;

import com.project.metakakao.dto.CreateQuizDTO;
import com.project.metakakao.dto.KakaoDTO;
import com.project.metakakao.dto.QuestionDTO;

public interface QuizService {

    KakaoDTO getUserInfoWithToken(String accessToken) throws Exception;

    Long register(CreateQuizDTO createQuizDTO);
}
