package com.crossroads.app.controller;
import com.crossroads.app.domain.vo.MailTO;
import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.service.MemberFileService;
import com.crossroads.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberFileService memberFileService;

    //회원가입
    @GetMapping("join")
    public String join(){
        return "member/join";
    }


   //회원가입
    @PostMapping("join")
    public RedirectView joinfinish(MemberVO memberVO){
        memberService.save(memberVO);
        return new RedirectView("login");
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
    @GetMapping("login")
    public String login(){
        return "member/login";
    }

    //로그인
    @PostMapping("login")
    public RedirectView login(String memberIdentification, String memberPassword, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long id = memberService.login(memberIdentification, memberPassword);
        log.info(id.toString());
        if(id != null){
            session.setAttribute("memberId", id);
            if(id == 1L){
                return new RedirectView("/admin/home");
            }
            log.info(session.getAttribute("memberId").toString());
            return new RedirectView("/main");
        }
        return new RedirectView("/member/login");
    }

    @GetMapping("callback")
    public String callBack(){
        return "/member/callback";
    }

    @PostMapping("login-naver")
    public RedirectView loginNaver(){

        return new RedirectView("/main");
    }

    //로그아웃
    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        System.out.println("logout - 진입");
        //세션 끊기
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/main";
    }

    //비밀번호 찾기 1 - 이메일 인증
    @GetMapping("find-pwd")
    public String findPwd() {
        return "member/find-pwd";
    }

    @PostMapping("find-pwd")
    public RedirectView findPasswordEmail(String memberEmail, String memberIdentification, RedirectAttributes redirectAttributes) {
        if(memberService.checkEmail(memberEmail) == null) { //조회 이메일 없을 때
            return new RedirectView("/member/find-pwd?result=fail");
        }

        Long randomKey = memberService.makeRandomKey();

        //    비밀번호 변경 이메일 발송시 랜덤 키 값 컬럼에 저장
        //    비밀번호 변경 완료 시 랜덤 키 컬럼 값 삭제
        memberService.setRandomKey(memberEmail, randomKey);

        MailTO mailTO = new MailTO();
        mailTO.setAddress(memberEmail);
        mailTO.setTitle("[교차로] 새 비밀번호 설정 링크입니다.");
        mailTO.setMessage("링크: http://localhost:10000/member/change-pwd?memberEmail="+memberEmail+"&memberRandomKey="+randomKey);
        memberService.sendMail(mailTO);

        redirectAttributes.addFlashAttribute("memberEmail", memberEmail);
        System.out.print(memberEmail);
        return new RedirectView("/member/find-pwd-send");
    }

    //비밀번호 변경 이메일
    @GetMapping("find-pwd-send")
    public String findPwdSend(String memberEmail, Model model, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("memberEmail", memberEmail);
        return "member/find-pwd-send";
    }

    //비밀번호 변경
    @GetMapping("change-pwd")
    public String changePwd(String memberEmail, Long memberRandomKey, Model model){
        System.out.println(memberRandomKey);
        System.out.println(memberEmail);
        memberService.getRandomKey("getmapping" + memberEmail);
        if(!memberService.getRandomKey(memberEmail).equals(memberRandomKey)){
            return "/";
        };
        memberService.setRandomKey(memberEmail, 0L);
        model.addAttribute("memberEmail", memberEmail);
        return "member/change-pwd";
    }


    //비밀번호 변경
    @PostMapping("change-pwd")
    public RedirectView changePwdtoCompleteChange(String memberEmail, String memberPassword, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("memberEmail", memberEmail);
        log.info("이메일 출력: " + memberEmail);
        memberService.modifyPassword(memberEmail, memberPassword);
        log.info("비밀번호 변경 쿼리");
        return new RedirectView("complete-change");
    }

    //비밀번호 변경 완료
    @GetMapping("complete-change")
    public String completeChange(){
        return "member/complete-change";
    }

}
