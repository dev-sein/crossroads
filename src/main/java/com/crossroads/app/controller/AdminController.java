package com.crossroads.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admins/*")
public class AdminController {
    //관리자 홈
    @GetMapping("admin-home")
    public String adminHome(){
        return "admin/admin-home";
    }

    //관리자 회원 목록
    @GetMapping("admin-member")
    public String adminMember(){
        return "admin/admin-member";
    }

    //관리자 연수신청 목록
    @GetMapping("admin-apply")
    public String adminApply(){
        return "admin/admin-apply";
    }

    //관리자 포인트 목록
    @GetMapping("admin-point")
    public String adminPoint(){
        return "admin/admin-point";
    }

    //관리자 후기 목록
    @GetMapping("admin-review")
    public String adminReview(){
        return "admin/admin-review";
    }

    //관리자 게시글 목록
    @GetMapping("admin-board")
    public String adminBoard(){
        return "admin/admin-board";
    }

    //관리자 댓글 목록
    @GetMapping("admin-reply")
    public String adminReply(){ return "admin/admin-reply"; }

}
