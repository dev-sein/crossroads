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
//    @ResponseBody
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

    @GetMapping("join-mobile")
    public String joinMobile(){
        return "mobile/join-mobile";
    }

    //회원가입 post
    @PostMapping("join-mobile")
    public RedirectView joinfinishMobile(MemberVO memberVO){
        memberService.save(memberVO);
        return new RedirectView("/login-mobile");
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

    @GetMapping("login-mobile")
    public String loginMobile(){
        return "mobile/login-mobile";
    }

}
