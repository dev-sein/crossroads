package com.crossroads.app.controller;

import com.crossroads.app.domain.dto.ApplyDTO;
import com.crossroads.app.domain.dto.Criteria;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/applies/*")
@RequiredArgsConstructor
@Slf4j
public class MobileController {
    private final ApplyService applyService;
    private final MemberService memberService;


    @GetMapping("list-mobile")
    public String listMobile(HttpServletRequest request , Criteria criteria, Model model) throws Exception{
        if (criteria.getPage() == 0){
            criteria = criteria.create(1,5);
        } else {
            criteria = criteria.create(criteria.getPage(),5);
        }

        HttpSession session = request.getSession();
        session.setAttribute("memberId", 1L);   // 임의로 세션에 담아둠

        Map<String, Object> info = new HashMap<>();
        info.put("memberId", session.getAttribute("memberId"));

        model.addAttribute("applyLength", applyService.getList(criteria));
//      총 연수신청 개수 - 다른 베테랑들이 수락한 신청들 개수
        model.addAttribute("applyCount", applyService.getAppliesCount(info) - applyService.getOthersCount(info));
        model.addAttribute("others", applyService.getCount((Long)session.getAttribute("memberId")));
        return "mobile/list-mobile";
    }

    @GetMapping("list-mobiles/{page}")
    @ResponseBody
    public List<ApplyDTO> listMobiles(HttpServletRequest request, @PathVariable("page") Integer page, Criteria criteria, Model model) throws Exception{
        if (criteria.getPage() == 0){
            criteria = criteria.create(1,5);
        } else {
            log.info(page.toString());
            criteria = criteria.create(page,5);
            log.info(String.valueOf(criteria.getOffset()));
        }

        return applyService.getList(criteria);
    }


    @GetMapping("list-mobile/search")
    public String listMobileSearch(@RequestParam(value = "applyLocation")String applyLocation,
                                   @RequestParam(value = "applyDate")String applyDate,
                                   HttpServletRequest request, Criteria criteria, Model model)
    {
        HttpSession session = request.getSession();
        session.setAttribute("memberId", 1L);       // 임시로 세션에 1L 담아둠
        Map<String, Object> info = new HashMap<>();
        if(applyDate != null && applyDate != ""){
            info.put("applyDate", applyDate);
        }
        if (applyLocation != null && applyLocation != ""){
            info.put("applyLocation", applyLocation);
        }
        info.put("memberId",session.getAttribute("memberId"));

        model.addAttribute("applyLocation", applyLocation);
//      총 연수신청 개수 - 다른 베테랑들이 수락한 신청들 개수
        model.addAttribute("applyCount", applyService.getAppliesCount(info) - applyService.getOthersCount(info));
        model.addAttribute("applyDate", applyDate);
        model.addAttribute("others", applyService.getCount((Long)session.getAttribute("memberId")));
        return "mobile/list-mobiles";
    }

    @GetMapping("list-mobiles/search/{page}")
    @ResponseBody
    public Map<String, Object> listMobilesSearch(@RequestParam(value = "applyLocation", required = false) String applyLocation,
                                                 @RequestParam(value = "applyDate", required = false) String applyDate,
                                                 @PathVariable(value = "page") Integer page,
                                                 Model model){
        log.info("ajax들어옴@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Map<String, Object> result = new HashMap<>(); // return 객체
        Map<String, Object> info = new HashMap<>(); // date, location값

        Criteria criteria = Criteria.create(page, 5);

        log.info(applyDate);
        log.info(applyLocation);
        if (applyDate != null) {
            info.put("applyDate", applyDate);
        }
        if (applyLocation != null) {
            info.put("applyLocation", applyLocation);
        }
        if (criteria.getPage() == 0){
            criteria = criteria.create(1,5);
        } else {
            log.info(page.toString());
            criteria = criteria.create(page,5);
            log.info(String.valueOf(criteria.getOffset()));
        }

        result.put("info", info);
        result.put("applies", applyService.getListSearched(criteria, info));

        log.info(info.toString());
        log.info(result.toString());

        return result;
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

    //로그인
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
