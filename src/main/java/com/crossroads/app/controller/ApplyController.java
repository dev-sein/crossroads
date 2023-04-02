package com.crossroads.app.controller;

import com.crossroads.app.domain.dto.ApplyDTO;
import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.service.ApplyService;
import com.crossroads.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.print.attribute.standard.PresentationDirection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/apply/*")
@RequiredArgsConstructor
@Slf4j
public class ApplyController {
    private final ApplyService applyService;
    private final MemberService memberService;

    @GetMapping("apply-first")
    public String formFirst(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("applyDTO", new ApplyDTO());
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("memberId", 1L);
        System.out.println("apply-first getmmaping");
        return "form/apply-first";
    }

    @PostMapping("apply-first")
    public RedirectView applyFirst(ApplyDTO applyDTO, RedirectAttributes redirectAttributes, Model model, HttpSession session){
        Long memberId = Long.parseLong(session.getAttribute("memberId").toString());
        session.setAttribute("memberId", 1L);
        applyService.saveCourse(applyDTO);
        model.addAttribute("applyDTO", applyDTO);
        System.out.println("apply-first postmapping");
        return new RedirectView("/apply/apply-second");
    }

    @GetMapping("apply-second")
    public String applySecond (Model model, HttpServletRequest httpServletRequest, ApplyDTO applyDTO, @RequestParam(value="applyCourse") String applyCourse) {
        System.out.println("코스: " + applyCourse);
        model.addAttribute("applyDTO", applyDTO);
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("memberId", 1L);
        log.info("apply-second getmapping");
        return "form/apply-second";
    }

    @PostMapping("apply-second")
    public RedirectView applySecond(HttpServletRequest httpServletRequest, Model model, ApplyDTO applyDTO){
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("memberId", 1L);
        applyService.saveApply(applyDTO);
        model.addAttribute("applyDTO", applyDTO);
      /*  redirectAttributes.addAttribute("applyDTO", applyDTO);*/
        System.out.println("apply-second-postmapping");
        return new RedirectView("/apply/apply-third");
    }

    @GetMapping("apply-third")
    public String formThird(Model model, HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("memberId", 1L);
        System.out.println("third-postmapping");
        return "form/apply-third";
    }

}
