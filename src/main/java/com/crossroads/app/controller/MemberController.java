package com.crossroads.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    //회원가입
    @GetMapping("join")
    public String join(){
        return "member/join";
    }

    //로그인
    @GetMapping("login")
    public String login(){
        return "member/login";
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
