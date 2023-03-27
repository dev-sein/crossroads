package com.crossroads.app.controller;

import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/mypages/*")
@RequiredArgsConstructor
@Slf4j
public class MypageController {
    private final MemberService memberService;

    //마이페이지 메인
    @GetMapping("/mypage-main")
    public String mypageMain(){
        return "mypage/mypage-main";
    }

//    //마이페이지 프로필 조회, 수정
//    @GetMapping("/my-info")
//    public String profileModify(){
//        return "mypage/my-info";
//    }

//    @GetMapping("my-info")
//    public String myInfo(Model model, HttpServletRequest request)throws Exception {
//        HttpSession session = request.getSession();
//        session.setAttribute("memberId", 1L);
//        model.addAttribute("mypages", memberService.getMember(1L));
//        return "mypage/my-info";
//    }

    //마이페이지 프로필 조회
    @GetMapping("/my-info")
    public String myInfo(Long memberId, Model model){
        model.addAttribute("member", memberService.getMember(1L));
        return "mypage/my-info";
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
    @GetMapping("/classList")
    public String classList(){
        return "mypage/classList";
    }

    //마이페이지 포인트내역
    @GetMapping("/point")
    public String point(){
        return "mypage/point";
    }

    //마이페이지 후기
    @GetMapping("/review")
    public String review(){
        return "mypage/review";
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
