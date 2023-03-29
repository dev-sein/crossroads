package com.crossroads.app.controller;

import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members/*")
@RequiredArgsConstructor
public class JoinController {
    @Autowired
    private final MemberService memberService;

    @GetMapping("/emailsend")
    public MemberVO sendTestMail(String email) {
        MemberVO memberVO = new MemberVO();

        memberVO.setAddress(email);
        memberVO.setTitle("교차로 님이 발송한 이메일입니다.");
        memberVO.setMessage("안녕하세요. 반가워요!");

        memberService.sendMail(memberVO);
        return memberVO;
    }

}
