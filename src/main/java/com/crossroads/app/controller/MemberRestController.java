package com.crossroads.app.controller;

import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

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
            return false;
        }
        session.setAttribute("member", memberVO);
        return true;
    }

    @PostMapping("join-naver")
    public RedirectView joinNaver(MemberVO memberVO) {
        memberVO.setMemberStatus(3);
        memberService.save(memberVO);
        return new RedirectView("login");
    }

}
