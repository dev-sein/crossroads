package com.crossroads.app.controller;
import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @GetMapping("join")
    public String join(){
        return "member/join";
    }


   //회원가입 테스트
    @PostMapping("join")
    public RedirectView joinfinish(MemberVO memberVO){
        memberService.save(memberVO);
        return new RedirectView("/login");
    }

    //아이디 중복체크
    @PostMapping("/checkId")
    @ResponseBody
    public Long checkId(@RequestParam("memberIdentification") String memberIdentification) {
        Long duplicateId = memberService.checkId(memberIdentification);
        return duplicateId;
    }

    //이메일 중복체크
    @PostMapping("/checkEmail")
    @ResponseBody
    public Long checkEmail(@RequestParam("memberEmail") String memberEmail) {
        Long duplicateEmail = memberService.checkEmail(memberEmail);
        return duplicateEmail;
    }

    //로그인
    @GetMapping("login")
    public String login(){
        return "member/login";
    }

    //   로그인
    @PostMapping("login")
    public RedirectView login(String memberIdentification, String memberPassword, HttpSession session){
        Long id = memberService.login(memberIdentification, memberPassword);
        if(id != null){
           session.setAttribute("memberId", id);
            return new RedirectView("/main");
        }
        return new RedirectView("/member/login");
    }


    //비밀번호 찾기
    @GetMapping("find-pwd")
    public String findPwd(){
        return "member/find-pwd";
    }

    //비밀번호 변경
    @GetMapping("change-pwd")
    public String changePwd(){
        return "member/change-pwd";
    }

    //비밀번호 변경 이메일
    @GetMapping("find-pwd-send")
    public String findPwdSend(){
        return "member/find-pwd-send";
    }

    //비밀번호 변경 완료
    @GetMapping("complete-change")
    public String completeChange(){
        return "member/complete-change";
    }
}
