package com.crossroads.app.controller;

import com.crossroads.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/introduce/*")
@RequiredArgsConstructor
public class IntroduceController {
    private final MemberService memberService;

    //사업소개
    @GetMapping("/introduce")
    public String introduce(Model model, HttpSession session){
        model.addAttribute("member", memberService.getMemberInfo(1L));
        return "introduce/introduce";
    }

    //자주 묻는 질문
    @GetMapping("/notice")
    public String notice(Model model, HttpSession session){
        model.addAttribute("member", memberService.getMemberInfo(1L));
        return "introduce/notice";
    }


}
