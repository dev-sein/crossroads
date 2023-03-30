package com.crossroads.app.controller;

import com.crossroads.app.domain.dto.ReplyDTO;
import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.domain.vo.ReviewVO;
import com.crossroads.app.mapper.PointMapper;
import com.crossroads.app.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/mypages/*")
@RequiredArgsConstructor
@Slf4j
public class MypageController {
    private final MemberService memberService;
    private final ReviewBoardService reviewBoardService;
    private final ApplyService applyService;
    private final FreeBoardService freeBoardService;
    private final ReplyService replyService;
    private final PointService pointService;

    //마이페이지 메인
    @GetMapping("/my-main")
    public String mypageMain(Long memberId, Model model){
        model.addAttribute("members", memberService.getMember(1L));
        return "mypage/my-main";
    }

//    @GetMapping("my-info")
//    public String myInfo(Model model, HttpServletRequest request)throws Exception {
//        HttpSession session = request.getSession();
//        session.setAttribute("memberId", 1L);
//        model.addAttribute("mypages", memberService.getMember(1L));
//        return "mypage/my-info";
//    }

    //마이페이지 프로필 조회
    @GetMapping("/my-info")
    public String myInfoSelect(Long memberId, Model model){
        model.addAttribute("members", memberService.getMember(1L));
        return "mypage/my-info";
    }

    //마이페이지 프로필 수정
//    @PostMapping("/my-info")
//    public void myInfoUpdate(@RequestBody MemberVO memberVO) { memberService.modify(memberVO); }

//    @PostMapping("/my-info")
//    public RedirectView myInfoUpdate(MemberVO memberVO, RedirectAttributes redirectAttributes){
//        memberService.modify(memberVO);
//        redirectAttributes.addAttribute("member", memberVO.getMemberId());
//        return new RedirectView("/mypage/my-info");
//    }

    @PostMapping("/my-info")
    @Transactional(rollbackFor = Exception.class)
    public RedirectView myInfoUpdate(HttpServletRequest req, MemberVO memberVO){
        log.info("들어옴");
        Long memberId = 1L;
        memberVO = memberService.getMember(memberId);

        String memberName = req.getParameter("memberName");
        String memberPhone = req.getParameter("memberPhone");
        String memberEmail = req.getParameter("memberEmail");

        memberVO.setMemberName(memberName);
        memberVO.setMemberPhone(memberPhone);
        memberVO.setMemberEmail(memberEmail);

        memberService.modify(memberVO);

        return new RedirectView("my-info");
    }

    //마이페이지 비밀번호 변경
    @GetMapping("/my-password-change")
    public String changePassword(){
        return "mypage/my-password-change";
    }

    //마이페이지 비밀번호 변경 확인
    @GetMapping("/my-password-confirm")
    public String confirmPassword(){
        return "mypage/my-password-confirm";
    }

    //마이페이지 연수신청 목록
    @GetMapping("/my-apply")
    public String classList(){
        return "mypage/my-apply";
    }

    //마이페이지 포인트내역
    @GetMapping("/my-point")
    public String point(Model model, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();

//        session.setAttribute("memberId", 1L);
        model.addAttribute("members", memberService.getMember(1L));
        model.addAttribute("points", pointService.getListMyPoint(1L));
        return "mypage/my-point";
    }

    //마이페이지 후기 전체 조회
    @GetMapping("/my-review")
    public String showListMyReview(Model model, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();

//        Long memberId = 1L;
//        ReviewDTO reviewDTO = new ReviewDTO();
//        reviewDTO.setMemberId(1L);

//        session.setAttribute("memberId", 1L);
        model.addAttribute("members", memberService.getMember(1L));
        model.addAttribute("reviews", reviewBoardService.getListMy(1L));
        return "mypage/my-review";
    }

    //마이페이지 내가 쓴 게시글 목록
    @GetMapping("/my-board")
    public String showListMyBoard(Model model, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        //        session.setAttribute("memberId", 1L);
        model.addAttribute("members", memberService.getMember(1L));
        model.addAttribute("boards", freeBoardService.getListMyBoard(1L));
        return "mypage/my-board";
    }
    
    //마이페이지 내가 쓴 댓글 목록
    @GetMapping("/my-reply")
    public String showListMyReply(Model model, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        //        session.setAttribute("memberId", 1L);
        model.addAttribute("members", memberService.getMember(1L));
        model.addAttribute("replies", replyService.getListMyReply(1L));
        return "mypage/my-reply";
    }
    
    //마이페이지 회원탈퇴
    @GetMapping("/my-withdraw")
    public String withdraw(){
        return "mypage/my-withdraw";
    }
    
    //마이페이지 회원탈퇴 동의
    @GetMapping("/my-withdraw-agree")
    public String withdrawAgree(){
        return "mypage/my-withdraw-agree";
    }

    //마이페이지 회원탈퇴 확인
    @GetMapping("/my-withdraw-confirm")
    public String withdrawConfirm(){
        return "mypage/my-withdraw-confirm";
    }

    //마이페이지 로그아웃
    @GetMapping("/my-logout")
    public String myLogout(HttpServletRequest request) {
        System.out.println("logout - 진입");
        //세션 끊기
        HttpSession session = request.getSession();
        session.invalidate();
        return "main/main";
    }



}
