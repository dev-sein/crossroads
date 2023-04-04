package com.crossroads.app.controller;

import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/members/*")
@RequiredArgsConstructor
@Slf4j
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping("login-naver")
    public boolean loginNaver(String memberEmail, HttpSession session) {
        MemberVO memberVO = memberService.getByEmail(memberEmail);

        if(memberVO == null || memberVO.getMemberStatus() == 0 || memberVO.getMemberStatus() == 1) {
            log.info("false 들어옴");
            return false;
        }
        session.setAttribute("memberId", memberVO.getMemberId());
        log.info("true 들어옴");
        log.info(session.getAttribute("memberVO").toString());
        log.info(memberVO.getMemberId().toString()); //id 출력됨
        return true;
    }

    //카카오 회원가입
/*    @GetMapping("login-kakao")
    public String loginkakao(){
        return "/member/join";
    }*/

}
