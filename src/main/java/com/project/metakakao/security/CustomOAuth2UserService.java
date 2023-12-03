package com.project.metakakao.security;

import com.project.metakakao.entity.Member;
import com.project.metakakao.entity.MemberRole;
import com.project.metakakao.repository.MemberRepository;
import com.project.metakakao.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException { // 로그인할 때 자동 호출되는 메소드

        log.info("userRequest....");
        log.info(userRequest);

        log.info("oauth2 user.....................................");

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();

        log.info("NAME: "+clientName);
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        String email = null;

        switch (clientName){
            case "kakao":
                email = getKakaoEmail(paramMap);
                break;
        }

        log.info("===============================");
        log.info(email);
        log.info("===============================");
        return generateDTO(email, paramMap);
    }

    public MemberSecurityDTO generateDTO(String email, Map<String, Object> params){ // 카카오 로그인 정보로 db에 회원 저장, 비밀번호는 전부 1111인데 일반 회원가입 창 따로 없으니까 상관없을 듯?

        //회원 추가 -- mid는 이메일 주소/ 패스워드는 1111
        Member member = Member.builder()
                .mid(email)
                .mpw(passwordEncoder.encode("1111"))
                .email(email)
                .build();
        member.addRole(MemberRole.USER);
        memberRepository.save(member);
        //MemberSecurityDTO 구성 및 반환
        MemberSecurityDTO memberSecurityDTO =
                new MemberSecurityDTO(email, "1111",email, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        memberSecurityDTO.setProps(params);
        return memberSecurityDTO;
    }

    private String getKakaoEmail(Map<String, Object> paramMap){ // loadUser 메소드에서만 사용함

        log.info("KAKAO-----------------------------------------");

        Object value = paramMap.get("kakao_account");

        log.info(value);

        LinkedHashMap accountMap = (LinkedHashMap) value;

        String email = (String)accountMap.get("email");

        log.info("email..." + email);

        return email;
    }
}
