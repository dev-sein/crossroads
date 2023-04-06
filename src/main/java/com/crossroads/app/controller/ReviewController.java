package com.crossroads.app.controller;

import com.crossroads.app.domain.dto.ReviewCriteria;
import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.vo.ReviewVO;
import com.crossroads.app.service.ReviewBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/review/*")
public class ReviewController {
    private final ReviewBoardService reviewBoardService;
    private static String uploadDir = "C:\\upload\\";

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
    public String saveReview(@ModelAttribute @Validated ReviewDTO reviewDTO, HttpSession session,
                             @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
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
        return "redirect:/review/review-list";
    }

    //   후기 게시물 목록 전체 조회
    @GetMapping("/review-list")
    public String showReviewList(Model model, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
         session.setAttribute("memberId", 1L);   // 테스트 ( 수정해야함)*/
        ReviewCriteria criteria = new ReviewCriteria(1, 10);
        int totalCount = reviewBoardService.getTotalCount();
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("reviews", reviewBoardService.getListReview(criteria));

        return "review/review-list";
    }
    // 무한스크롤
    @GetMapping("/api/reviews")
    @ResponseBody
    public ResponseEntity<List<ReviewDTO>> getReviews(@RequestParam("page") int page, @RequestParam("size") int size) {
        log.info("reivews 들어옴");
        ReviewCriteria criteria = new ReviewCriteria(page, size);
        List<ReviewDTO> reviews = reviewBoardService.getListReview(criteria);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }


    //후기 수정
    @GetMapping("/review-update")
    public String getReviewUpdatePage(@RequestParam("reviewId") Long reviewId, Model model) {
        ReviewVO reviewVO = reviewBoardService.getReview(reviewId);

        if (reviewVO == null) {
            return "redirect:/review/review-list";
        }
        model.addAttribute("info", reviewVO);
        model.addAttribute("filename", reviewVO.getReviewFileSystemName()); // 파일 이름 추가
        return "review/review-update";
    }


    // 후기 수정 처리
    @PostMapping("/review-update/{reviewId}")
    public String updateReview(@PathVariable("reviewId") Long reviewId, @ModelAttribute @Validated ReviewDTO reviewDTO,
                               HttpSession session,
                               @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        Long memberId = Long.parseLong(session.getAttribute("memberId").toString());
        reviewDTO.setMemberId(memberId);
        reviewDTO.setReviewId(reviewId);

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
            ReviewVO currentReview = reviewBoardService.getReview(reviewId);
            reviewDTO.setReviewFileSystemName(currentReview.getReviewFileSystemName());
        }
        reviewBoardService.updateReview(reviewDTO);
        return "redirect:/review/review-list";
    }

    // 후기 삭제
    @DeleteMapping("/review-delete/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewBoardService.deleteReview(reviewId);
        return ResponseEntity.ok(reviewId);
    }




}
