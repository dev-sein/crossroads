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

    //모바일 마이페이지 메인
    @GetMapping("/my-mobile")
    public String myMobile(Long memberId, Model model){
        model.addAttribute("member", memberService.getMemberInfo(1L));
        return "mobile/my-mobile";
    }

    //모바일 마이페이지 비밀번호 확인
    @GetMapping("/my-mobile-password-check")
    public String myPasswordCheckMobileView(){
        return "mobile/my-mobile-password-check";
    }

    @PostMapping("/my-mobile-password-check")
    public RedirectView myPasswordCheckMobile(String memberPassword, HttpServletRequest request) {
        HttpSession session = request.getSession();
//        Long password = memberService.getPassword(memberPassword);
//        log.info(password.toString());
        session.setAttribute("memberId", 1L);
        if(((Long)session.getAttribute("memberId")) == memberService.getPassword((Long)session.getAttribute("memberId"), memberPassword)){
            log.info(session.getAttribute("memberId").toString());
            return new RedirectView("my-mobile-password-change");
        }
        return new RedirectView("my-mobile-password-check");
    }

    //모바일 마이페이지 비밀번호 변경
    @GetMapping("/my-mobile-password-change")
    public String myPasswordChangeMobileView(){
        return "mobile/my-mobile-password-change";
    }

    //모바일 마이페이지 비밀번호 변경
    @PostMapping("/my-mobile-password-change")
    public RedirectView myPasswordChangeMobile(String memberPassword, HttpSession session){
        session.setAttribute("memberId", 1L);
        memberService.modifyPasswordMy(1L, memberPassword);
        return new RedirectView("my-mobile");
    }

    @GetMapping("/my-mobile-point")
    public String myPointMobile(){
        return "/mobile/my-mobile-point";
    }

    @GetMapping("/my-mobile-apply")
    public String myApplyMobile(){
        return "/mobile/my-mobile-apply";
    }

    @GetMapping("my-mobile-account-check")
    public String myMobileAccountCheck() {
        return "/mobile/my-mobile-account-check";
    }

    @GetMapping("my-mobile-account-cancel")
    public String myMobileAccountCancel() {
        return "/mobile/my-mobile-account-cancel";
    }

    //마이페이지 회원탈퇴 확인
    @PostMapping("my-mobile-complete-cancel")
    public String myMobileAccountCancel(HttpSession session){
        Long memberId = (Long)session.getAttribute("memberId");
        session.invalidate();
        memberService.remove(memberId);
        return "/mobile/my-mobile-complete-cancel";
    }

    //모바일 카카오 회원가입
    @GetMapping("kakao")
    public RedirectView kakaoJoin(String code, HttpSession session) throws Exception {
        String token = memberService.getKaKaoAccessToken(code, "mobilejoin");
        MemberVO kakaoInfo = memberService.getKakaoInfo(token);
        kakaoInfo.setMemberStatus(1);
        //String userIdentification = null;
        MemberVO memberVO = memberService.getByEmail(kakaoInfo.getMemberEmail());

        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (memberVO == null || memberVO.getMemberStatus() != 1) {
            session.setAttribute("kakaoInfo", kakaoInfo);
            log.info("카카오 들어옴");
            return new RedirectView("/applies/join-mobile");
        }
        session.setAttribute("members", memberVO);
        return new RedirectView("/applies/list-mobile");
    }

    //카카오 로그인
    @GetMapping("kakao-login")
    public RedirectView kakaoLogin(String code, HttpSession session) throws Exception {
        String token = memberService.getKaKaoAccessToken(code, "mobilelogin");
        memberService.getKakaoInfo(token);

        MemberVO kakaoInfo = memberService.getKakaoInfo(token);
        MemberVO memberVO = memberService.getByEmail(kakaoInfo.getMemberEmail());

        if(memberVO.getMemberStatus() != 1){
            return new RedirectView("/applies/login?result=fail");
        }

        session.setAttribute("memberVO", memberVO);
        return new RedirectView("list-mobile");
    }



}
