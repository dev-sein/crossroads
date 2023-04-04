package com.crossroads.app.controller;

import com.crossroads.app.domain.dto.ReviewCriteria;
import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.vo.ReviewVO;
import com.crossroads.app.service.ReviewBoardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.print.Pageable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
        ReviewCriteria criteria = new ReviewCriteria(1, 10);
        int totalCount = reviewBoardService.getTotalCount();
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("reviews", reviewBoardService.getListReview(criteria));

        return "review/review-list";
    }
    // 무한스크롤
    @GetMapping("/api/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviews(@RequestParam("page") int page, @RequestParam("size") int size) {
        ReviewCriteria criteria = new ReviewCriteria(page, size);
        List<ReviewDTO> reviews = reviewBoardService.getListReview(criteria);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }


    //후기 수정
    @GetMapping("/review-update")
    public String getReviewUpdatePage(@RequestParam("reviewId") Long reviewId, Model model) {
        ReviewVO reviewVO = reviewBoardService.getReview(reviewId);
        model.addAttribute("info", reviewVO);
        return "review/review-update";
    }

    @PostMapping("/review-update/{reviewId}")
    public String reviewupdate(@PathVariable("reviewId") Long reviewId,
                               @ModelAttribute @Validated ReviewDTO reviewDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, HttpSession session,
                               @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        if (bindingResult.hasErrors()) {
            // 유효성 검사 에러가 있을 경우
            redirectAttributes.addFlashAttribute("errorMessage", "입력값을 확인해주세요.");
            return "redirect:/review-update/" + reviewId;
        }
        reviewDTO.setReviewId(reviewId);
        Long memberId = Long.parseLong(session.getAttribute("memberId").toString());
        reviewDTO.setMemberId(memberId);
        if (image != null && !image.isEmpty()) {
            String originalFileName = image.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String savedFileName = UUID.randomUUID().toString() + fileExtension;
            Path savePath = Paths.get(uploadDir + savedFileName);
            Files.copy(image.getInputStream(), savePath);
            reviewDTO.setReviewFileSystemName(savedFileName);
        } else {
            reviewDTO.setReviewFileSystemName(null);
        }
        reviewBoardService.updateReview(reviewDTO);
        return "redirect:/review-list";
    }

    //후기 수정 화면 이동
    @GetMapping("/review-update/{reviewId}")
    public String reviewUpdate(@PathVariable("reviewId") Long reviewId, Model model, HttpSession session) {
        Long memberId = Long.parseLong(session.getAttribute("memberId").toString());
        ReviewVO reviewVO = reviewBoardService.getReview(reviewId);
        if (reviewVO == null) {
            return "redirect:/review-list";
        }
        model.addAttribute("info", reviewVO);
        return "/review/review-update";
    }


}
