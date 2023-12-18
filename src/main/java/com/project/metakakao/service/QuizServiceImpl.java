package com.project.metakakao.service;

import com.project.metakakao.dto.CreateQuizDTO;
import com.project.metakakao.dto.KakaoDTO;
import com.project.metakakao.dto.MemberDTO;
import com.project.metakakao.entity.Member;
import com.project.metakakao.entity.MemberRole;
import com.project.metakakao.entity.Quiz;
import com.project.metakakao.repository.MemberRepository;
import com.project.metakakao.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class QuizServiceImpl implements QuizService{

    private final ModelMapper modelMapper;
    private final QuizRepository quizRepository;
    private final MemberRepository memberRepository;

    private final static String KAKAO_API_URI = "https://kapi.kakao.com";
    //Access TOken 받은 후 유저 정보 받기위한 도메인

    public MemberDTO getUserInfoWithToken(String accessToken) throws Exception {
        //HttpHeader 생성
        //curl -v -X GET "https://kapi.kakao.com/v2/user/me" \
        //  -H "Authorization: Bearer ${ACCESS_TOKEN}"
        //위 양식에 따라 요청,
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpHeader 담기, 위의 과정과 동일
        RestTemplate restTem = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTem.exchange(
                KAKAO_API_URI + "/v2/user/me",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        //Response 데이터 JSON 파싱
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());
        JSONObject account = (JSONObject) jsonObj.get("kakao_account");
        JSONObject profile = (JSONObject) account.get("profile");

        long id = (long) jsonObj.get("id");
        log.info("id: " + id);
        String email = String.valueOf(account.get("email"));
        log.info("email: " + email);
        String nickname = String.valueOf(profile.get("nickname"));
        log.info("nickname: " + nickname);

        KakaoDTO kakaoUser = KakaoDTO.builder()
                .id(id)
                .email(email)
                .nickname(nickname)
                .build();

        MemberDTO memberDTO = MemberDTO.builder()
                .mid(String.valueOf(kakaoUser.getEmail()))
                .email(kakaoUser.getEmail())
                .nickname(kakaoUser.getNickname())
                .build();
        return memberDTO;
        //끝~
    }

    public Quiz findQuizById(String mid, Long quizNo) {
        Quiz quiz = quizRepository.findByMid(mid, quizNo);
        return quiz;
    }

    public List<Quiz> findAllQuizById(String mid) {
        List<Quiz> quizList = quizRepository.findAllByMid(mid);
        return quizList;
    }

    public void register(CreateQuizDTO createQuizDTO) {
        Member member = memberRepository.findByEmail(createQuizDTO.getHostId()).get();
        Quiz quiz = Quiz.builder()
                .title(createQuizDTO.getTitle())
                .q1(createQuizDTO.getQ1())
                .a1(createQuizDTO.getA1())
                .q2(createQuizDTO.getQ2())
                .a2(createQuizDTO.getA2())
                .q3(createQuizDTO.getQ3())
                .a3(createQuizDTO.getA3())
                .q4(createQuizDTO.getQ4())
                .a4(createQuizDTO.getA4())
                .q5(createQuizDTO.getQ5())
                .a5(createQuizDTO.getA5())
                .q6(createQuizDTO.getQ6())
                .a6(createQuizDTO.getA6())
                .q7(createQuizDTO.getQ7())
                .a7(createQuizDTO.getA7())
                .q8(createQuizDTO.getQ8())
                .a8(createQuizDTO.getA8())
                .q9(createQuizDTO.getQ9())
                .a9(createQuizDTO.getA9())
                .q10(createQuizDTO.getQ10())
                .a10(createQuizDTO.getA10())
                .host(member)
                .regDate(createQuizDTO.getRegDate())
                .build();
        quizRepository.save(quiz);
    }
}
