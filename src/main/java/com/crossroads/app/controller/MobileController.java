package com.crossroads.app.controller;

import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.service.ApplyService;
import com.crossroads.app.service.MemberService;
import com.crossroads.app.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/applies/*")
@RequiredArgsConstructor
@Slf4j
public class MobileController {
    private final ApplyService applyService;
    private final MemberService memberService;


    @GetMapping("list-mobile")
    public String listMobile(Model model, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        session.setAttribute("memberId", 1L);
        model.addAttribute("applies", applyService.getList());
        model.addAttribute("others", applyService.getCount((Long)session.getAttribute("memberId")));
        return "mobile/list-mobile";
    }


    @GetMapping("list-mobile/search")
    public String listMobileSearch(@RequestParam(value = "applyLocation")String applyLocation,
                                   @RequestParam(value = "applyDate")String applyDate,
                                   Model model, HttpServletRequest request)
    {
        log.info(applyLocation);
        log.info(applyDate);
        Map<String, Object> info = new HashMap<>();
        if(applyDate != null && applyDate != ""){
            info.put("applyDate", applyDate);
        }
        if (applyLocation != null && applyLocation != ""){
            info.put("applyLocation", applyLocation);
        }
        HttpSession session = request.getSession();
        session.setAttribute("memberId", 1L);
        model.addAttribute("applies", applyService.getListSearched(info));
        model.addAttribute("others", applyService.getCount((Long)session.getAttribute("memberId")));
        return "mobile/list-mobile";
    }

    @PostMapping("list-mobile/change-status")
    @ResponseBody
    public void changeStatus(Long applyId, HttpServletRequest request){
        HttpSession session = request.getSession();
        applyService.modifyStatus(applyId);
        Map<String, Object> info = new HashMap<>();
        info.put("memberId", session.getAttribute("memberId"));
        info.put("applyId",applyId);

        applyService.modifyVeteranId(info);
    }

    //회원가입
    @GetMapping("join-mobile")
    public String joinMobile(){
        return "mobile/join-mobile";
    }

    //회원가입
    @PostMapping("join-mobile")
    public RedirectView joinfinishMobile(MemberVO memberVO){
        memberService.save(memberVO);
        return new RedirectView("login-mobile");
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
    @GetMapping("login-mobile")
    public String login(){
        return "mobile/login-mobile";
    }

    //   로그인
    @PostMapping("login-mobile")
    public RedirectView login(String memberIdentification, String memberPassword, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long id = memberService.login(memberIdentification, memberPassword);
        log.info(id.toString());
        if(id != null){
            session.setAttribute("memberId", id);
            log.info(session.getAttribute("memberId").toString());
            return new RedirectView("list-mobile");

        }
        return new RedirectView("/mobile/login");
    }

    //로그아웃
    @GetMapping("/logout-mobile")
    public String logout(HttpServletRequest request) {
        System.out.println("logout - 진입");
        //세션 끊기
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/mobile/list-mobile";
    }
}
