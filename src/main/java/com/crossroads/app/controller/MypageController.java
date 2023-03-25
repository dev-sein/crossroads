package com.crossroads.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {
    //마이페이지 메인
    @GetMapping("/mypage-main")
    public String mypageMain(){
        return "mypage/mypage-main";
    }

    //마이페이지 프로필 수정
    @GetMapping("/profile-modify")
    public String profileModify(){
        return "mypage/profile-modify";
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
