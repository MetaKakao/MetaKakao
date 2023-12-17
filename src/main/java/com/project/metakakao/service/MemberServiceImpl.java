package com.project.metakakao.service;

import com.project.metakakao.dto.MemberJoinDTO;
import com.project.metakakao.entity.Member;
import com.project.metakakao.entity.MemberRole;
import com.project.metakakao.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void join(MemberJoinDTO memberJoinDTO) throws MidExistException{

        String mid = memberJoinDTO.getMid();

        boolean exist = false;
        if(memberRepository.getWithRoles(mid).isPresent()){
            exist = true;
        }

        if(exist){
            throw new MidExistException();
        }
        Member member = Member.builder()
                .mid(memberJoinDTO.getMid())
                .mpw(memberJoinDTO.getMpw())
                .email(memberJoinDTO.getEmail())
                .build();
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        member.addRole(MemberRole.USER);

        log.info("=======================");
        log.info(member);
        log.info(member.getRoleSet());

        memberRepository.save(member);

    }
}
