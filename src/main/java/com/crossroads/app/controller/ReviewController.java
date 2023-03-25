package com.crossroads.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewController {
    //후기 목록
    @GetMapping("/review-list")
    public String reviewlist() {
        return "review/review-list";
    }

    //후기 작성
    @GetMapping("/review-write")
    public String reviewwrite() {
        return "review/review-write";
    }

    //후기 수정
    @GetMapping("/review-update")
    public String reviewupdate() {
        return "review/review-update";
    }
}
