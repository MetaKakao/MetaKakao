package com.project.metakakao.controller;

import com.project.metakakao.dto.QuestionDTO;
import com.project.metakakao.entity.Member;
import com.project.metakakao.repository.MemberRepository;
import com.project.metakakao.security.CustomOAuth2UserService;
import com.project.metakakao.security.dto.MemberSecurityDTO;
import com.project.metakakao.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/question")
@Log4j2
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final MemberRepository memberRepository;
    private final CustomOAuth2UserService customOAuth2UserService;

    @GetMapping
    public String question() {
        return "/question/question";
    }

    @PostMapping
    public ResponseEntity ask(QuestionDTO questionDTO) throws Exception {
        questionService.register(questionDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 답변 완료 질문 조회
    @GetMapping("/answered/hostID")
    public ResponseEntity readAnswered(@PathVariable("hostID") String hostID) throws Exception {
        int status = 1;
        questionService.readAll(hostID, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // 미답변 질문 조회 -> 답변 완료 질문 조회에서 status = 0으로 바꿔서 호출하면 됨.
    @GetMapping("/unanswered/hostID")
    public ResponseEntity readUnAnswered(@PathVariable("hostID") String hostID) throws Exception {
        int status = 0;
        questionService.readAll(hostID, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 거절 질문은 클릭이 안됨(host와 타인 모두) 아예 db에서도 삭제할 것임.
    // 삭제는 질문 주인(not 작성자)만 할 수 있음. -> 어떻게 구현하지?
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMemory(@PathVariable("id") Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  // auth:"AnonymousAuthenticationToken"
        MemberSecurityDTO memberSecurityDTO = ((MemberSecurityDTO) auth.getPrincipal());
        String hostID = memberSecurityDTO.getMid();
        questionService.remove(hostID, id);
        Optional<Member> member = memberRepository.findById(hostID);
        member.get().addRejected(); // question 삭제하면 거절 질문 개수 +1
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
