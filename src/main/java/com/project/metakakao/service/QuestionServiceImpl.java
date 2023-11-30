package com.project.metakakao.service;

import com.project.metakakao.dto.QuestionDTO;
import com.project.metakakao.entity.Question;
import com.project.metakakao.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class QuestionServiceImpl implements QuestionService{
    private final ModelMapper modelMapper;

    private final QuestionRepository questionRepository;

    @Override
    public Long register(QuestionDTO questionDTO) {

        Question question = modelMapper.map(questionDTO, Question.class);

        Long qno = questionRepository.save(question).getQno();

        return qno;
    }

    @Override
    public List<Question> readAll(String hostID, int status) {
        List<Question> questionList = questionRepository.readAllByMid(hostID, status);
        return questionList;
    }

    @Override
    public void remove(String hostID, Long qno) {
        questionRepository.deleteByHostID(hostID, qno);
    }

}
