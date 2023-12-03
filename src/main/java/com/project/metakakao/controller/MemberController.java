//package com.project.metakakao.controller;
//
//import com.project.metakakao.dto.MemberJoinDTO;
//import com.project.metakakao.service.MemberService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//@RequestMapping("/member")
//@Log4j2
//@RequiredArgsConstructor
//public class MemberController {
//    private final MemberService memberService;
//
//    //근데 회원가입 페이지는 따로 없지 않나...
//
//    @GetMapping("/join")
//    public void joinGET(){
//
//        log.info("회원 가입 페이지 요청");
//
//    }
//
//    @PostMapping("/join")
//    public String joinPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes){
//
//        log.info("join post...");
//        log.info(memberJoinDTO);
//
//        try {
//            memberService.join(memberJoinDTO);
//        } catch (MemberService.MidExistException e) {
//
//            redirectAttributes.addFlashAttribute("error", "mid");
//            return "redirect:/member/join";
//        }
//
//        redirectAttributes.addFlashAttribute("result", "success");
//
//        return "redirect:/"; //회원 가입 후 로그인
//    }
//}
