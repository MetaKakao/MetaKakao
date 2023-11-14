package com.project.metakakao.service;

import com.project.metakakao.dto.QuestionDto;
import com.project.metakakao.entity.Question;
import com.project.metakakao.entity.User;
import com.project.metakakao.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserService userService;

    public Question ask(String writerName, QuestionDto questionDto, String hostName) throws Exception {
        Optional<User> writer = userService.getUser(writerName);
        Optional<User> host = userService.getUser(hostName);
        Question question = Question.builder()
                .content(questionDto.getContent())
                .host(host.get())
                .writer(writer.get())
                .build();
        return questionRepository.save(question);
    }
}
