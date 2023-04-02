package com.crossroads.app.controller;

import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.vo.ReviewVO;
import com.crossroads.app.service.ReviewBoardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
    private final ReviewBoardService reviewBoardService;
    private static String uploadDir = "C:\\uploads\\";

    // 후기 작성 페이지
    @GetMapping("/review-write")
    public String reviewWrite(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("reviewDTO", new ReviewDTO());
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("memberId", 1L);
//        httpSession.getAttribute("memberId");
        return "review/review-write";
    }

    // 후기 저장
    @PostMapping("/review-save")
    public String saveReview(@ModelAttribute @Validated ReviewDTO reviewDTO, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, HttpSession session,
                             @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        log.info("들어옴@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Long memberId = Long.parseLong(session.getAttribute("memberId").toString());
        reviewDTO.setMemberId(memberId);
        if (image != null && !image.isEmpty()) {
            log.info(image.toString());
            String originalFileName = image.getOriginalFilename();
            log.info(image.getOriginalFilename());
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String savedFileName = UUID.randomUUID().toString() + fileExtension;
            Path savePath = Paths.get(uploadDir + savedFileName);
            Files.copy(image.getInputStream(), savePath);
            reviewDTO.setReviewFileSystemName(savedFileName);
        } else {
            reviewDTO.setReviewFileSystemName(null);
        }
        reviewBoardService.save(reviewDTO);
        return "redirect:/review-list";
    }

    //   후기 게시물 목록 전체 조회
    @GetMapping("/review-list")
    public String showReviewList(Model model, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        model.addAttribute("reviews", reviewBoardService.getListReview());
        return "review/review-list";
    }

    // 후기 수정
    @GetMapping("/review-update")
    public String showReview(@RequestParam(value = "reviewId") Long reviewId, Model model){
//        log.info("들어옴@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        log.info(reviewId.toString());
//        log.info(reviewBoardService.getReviewById(reviewId).toString());
        model.addAttribute("info", reviewBoardService.getReviewById(reviewId));
        return "review/review-update";
    }

    //후기 수정
    @PostMapping("/review-update")
    public String reviewupdate(ReviewVO reviewVO) {
//        reviewBoardService.
        return "review/review-update";
    }


}
