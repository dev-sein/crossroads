package com.crossroads.app.controller;

import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.service.ReviewBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
    private final ReviewBoardService reviewBoardService;

   /* //후기 목록
    @GetMapping("/review-list")
    public String reviewlist() {
        return "review/review-list";
    }*/

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

    //   후기 게시물 목록 전체 조회
    @GetMapping("/review-list")
    public String showReviewList(Model model, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        model.addAttribute("reviews", reviewBoardService.getReviewList());
        return "review/review-list";
    }


}
