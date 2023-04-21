package com.crossroads.app.controller;

import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.service.MemberService;
import com.crossroads.app.service.ReviewBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final MemberService memberService;
    private final ReviewBoardService reviewBoardService;

    @GetMapping("")
    public RedirectView goToMain() {
        return new RedirectView("main");
    }

    @GetMapping("main")
    public String main(Model model, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute("memberId");
        model.addAttribute("member", memberService.getMemberInfo(memberId));
        model.addAttribute("reviews", reviewBoardService.getListReview());
        return "main/main";
    }

//    @GetMapping("introduce")
//    public String goIntroduce(){
//        return "introduce/introduce";
//    }


}
