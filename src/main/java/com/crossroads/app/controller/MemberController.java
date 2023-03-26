package com.crossroads.app.controller;

import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @GetMapping("join")
    public String join(){
        return "member/join";
    }


    //회원가입 테스트
    @GetMapping("jointestpage")
    public ModelAndView jointest(){
        ModelAndView mav = new ModelAndView("member/jointests");
        return mav;
    }

   //회원가입 rest 테스트
    @PostMapping("jointestpage")
    public void joinfinish(@RequestBody MemberVO memberVO){
        memberService.save(memberVO);
    }

    //로그인
    @GetMapping("login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView("member/login");
        return mav;
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
