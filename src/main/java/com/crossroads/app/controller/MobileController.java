package com.crossroads.app.controller;

import com.crossroads.app.domain.dto.ApplyDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.domain.vo.MailTO;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String loginMobile(){
        return "mobile/login-mobile";
    }

    //로그인
    @PostMapping("login-mobile")
    public RedirectView Mobile(String memberIdentification, String memberPassword, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long id = memberService.login(memberIdentification, memberPassword);
        log.info(id.toString());
        if(id != null){
            session.setAttribute("memberId", id);
            //관리자 if()
            log.info(session.getAttribute("memberId").toString());
            return new RedirectView("list-mobile");

        }
        return new RedirectView("/mobile/login");
    }

    //로그아웃
    @GetMapping("/logout-mobile")
    public String logoutMobile(HttpServletRequest request) {
        System.out.println("logout - 진입");
        //세션 끊기
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/mobile/list-mobile";
    }

    //비밀번호 찾기 1 - 이메일 인증
    @GetMapping("find-pwd-mobile")
    public String findPwdMobile() {
        return "mobile/find-pwd-mobile";
    }

    @PostMapping("find-pwd-mobile")
    public RedirectView findPasswordEmailMobile(String memberEmail, String memberIdentification, RedirectAttributes redirectAttributes) {
        if(memberService.checkEmail(memberEmail) == null) { //조회 이메일 없을 때
            return new RedirectView("applies/find-pwd-mobile?result=fail");
        }

        Long randomKey = memberService.makeRandomKey();

        //    비밀번호 변경 이메일 발송시 랜덤 키 값 컬럼에 저장
        //    비밀번호 변경 완료 시 랜덤 키 컬럼 값 삭제
        memberService.setRandomKey(memberEmail, randomKey);

        MailTO mailTO = new MailTO();
        mailTO.setAddress(memberEmail);
        mailTO.setTitle("[교차로] 새 비밀번호 설정 링크입니다.");
        //    mailTO.setMessage("링크: http://localhost:10000/user/changePassword-email?memberIdentification=" + memberIdentification + "&memberRandomKey=" + randomKey);
        mailTO.setMessage("링크: http://localhost:10000/applies/change-pwd-mobile?memberEmail="+memberEmail+"&memberRandomKey="+randomKey);
        memberService.sendMail(mailTO);

        redirectAttributes.addFlashAttribute("memberEmail", memberEmail);
        System.out.print(memberEmail);
        return new RedirectView("/applies/find-pwd-send-mobile");
    }

    //비밀번호 변경 이메일(입력받은 값 뿌려줘야 함)
    @GetMapping("find-pwd-send-mobile")
    public String findPwdSendMobile(String memberEmail, Model model, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("memberEmail", memberEmail);
        log.info(memberEmail);
        return ("/mobile/find-pwd-send-mobile");
    }

    //비밀번호 변경
    @GetMapping("change-pwd-mobile")
    public String changePwdMobile(String memberEmail, Long memberRandomKey, Model model){
        System.out.println(memberRandomKey);
        System.out.println(memberEmail);
        memberService.getRandomKey(memberEmail);
        if(!memberService.getRandomKey(memberEmail).equals(memberRandomKey)){
            return "/";
        };
        memberService.setRandomKey(memberEmail, 0L);
        model.addAttribute("memberEmail", memberEmail);
        return "mobile/change-pwd-mobile";
    }


    //비밀번호 변경
    @PostMapping("change-pwd-mobile")
    public RedirectView changePwdtoCompleteChangeMobile(String memberEmail, String memberPassword, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("memberEmail", memberEmail);
        log.info("이메일 출력: " + memberEmail);
        memberService.modifyPassword(memberEmail, memberPassword);
        log.info("비밀번호 변경 쿼리");
        return new RedirectView("complete-change-mobile");
    }

    //비밀번호 변경 완료
    @GetMapping("complete-change-mobile")
    public String completeChangeMobile(){
        return "mobile/complete-pwd-mobile";
    }


}
