package com.crossroads.app.controller;

import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.domain.vo.ReviewVO;
import com.crossroads.app.service.ApplyService;
import com.crossroads.app.service.MemberService;
import com.crossroads.app.service.ReviewBoardService;
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

    //마이페이지 메인
    @GetMapping("/mypage-main")
    public String mypageMain(){
        return "mypage/mypage-main";
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
        model.addAttribute("member", memberService.getMember(1L));
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
    @GetMapping("/change-password")
    public String changePassword(){
        return "mypage/change-password";
    }

    //마이페이지 비밀번호 변경 확인
    @GetMapping("/confirm-password")
    public String confirmPassword(){
        return "mypage/confirm-password";
    }

    //마이페이지 목록
    @GetMapping("/my-apply")
    public String classList(){
        return "mypage/my-apply";
    }

    //마이페이지 포인트내역
    @GetMapping("/my-point")
    public String point(){
        return "mypage/my-point";
    }

    //마이페이지 후기 전체 조회
    @GetMapping("/my-review")
    public String showListMy(Model model, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();

        Long memberId = 1L;
//        ReviewDTO reviewDTO = new ReviewDTO();
//        reviewDTO.setMemberId(1L);

//        session.setAttribute("memberId", 1L);
        model.addAttribute("reviews", reviewBoardService.getListMy(memberId));
        return "mypage/my-review";
    }

    //마이페이지 내가 쓴 게시글 목록
    @GetMapping("/my-board-list")
    public String myBoardList(){ return "mypage/my-board-list"; }
    
    //마이페이지 내가 쓴 댓글 목록
    @GetMapping("/my-reply-list")
    public String myReplyList(){
        return "mypage/my-reply-list";
    }
    
    //마이페이지 회원탈퇴
    @GetMapping("/withdraw")
    public String withdraw(){
        return "mypage/withdraw";
    }
    
    //마이페이지 회원탈퇴 동의
    @GetMapping("/withdraw-agree")
    public String withdrawAgree(){
        return "mypage/withdraw-agree";
    }

    //마이페이지 회원탈퇴 확인
    @GetMapping("/withdraw-confirm")
    public String withdrawConfirm(){
        return "mypage/withdraw-confirm";
    }





}
