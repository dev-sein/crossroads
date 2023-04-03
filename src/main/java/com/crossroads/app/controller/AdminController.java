package com.crossroads.app.controller;

import com.crossroads.app.domain.dto.*;
import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.domain.vo.PointVO;
import com.crossroads.app.service.ApplyService;
import com.crossroads.app.service.FreeBoardService;
import com.crossroads.app.service.MemberService;
import com.crossroads.app.service.ReviewBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/*")
@Slf4j
public class AdminController {
    private final MemberService memberService;
    private final FreeBoardService freeBoardService;
    private final ReviewBoardService reviewBoardService;
    private final ApplyService applyService;

    //관리자 홈 및 출력
    @GetMapping("home")
    public String adminHome(){
        return "admin/admin-home";
    }


    /*====================회원 게시판 시작==============================*/
//    관리자 회원 목록
    @GetMapping("member/list")
    public String adminMemberList(){
        return "admin/admin-member";
    }

//    관리자 회원 목록
    @ResponseBody
    @PostMapping("members/list")
    public Map<String, Object> adminMemberList(@RequestBody Map<String, Object> requestData, Criteria criteria){
        return memberService.getListAdmin(requestData, criteria);
    }

//    관리자 회원 상세 보기
    @ResponseBody
    @PostMapping("members/detail")
    public MemberVO adminMemberDetail(@RequestParam("memberId") Long memberId) {
        return memberService.getMember(memberId);
    }

    //관리자 회원 삭제
    @ResponseBody
    @DeleteMapping("members/delete")
    public void deleteMember(@RequestParam("checkedIds[]") List<String> checkedIds) {
        log.info("delete 들어옴..?");
        memberService.removeAdmin(checkedIds);
        log.info("마지막 여긴 안됨.");
    }
    /*====================회원 게시판 끝==============================*/

    //관리자 연수신청 목록
    @GetMapping("apply/list")
    public String adminApply(Model model){
//        model.addAttribute("applies", applyService.getList());
        return "admin/admin-apply";
    }

    //관리자 연수신청 목록
    @ResponseBody
    @PostMapping("applies/list")
    public List<ApplyDTO> adminApplyList(){
        return null;
    }

    //관리자 연수신청 삭제
    @ResponseBody
    @DeleteMapping("applies/delete")
    public void deleteApply(@RequestBody List<String> checkedIds) {
        checkedIds.stream().map(checkedId -> Long.parseLong(checkedId)).forEach(applyService::cancel);
    }

    //관리자 포인트 목록
    @GetMapping("points/list")
    public String adminPoint(){
        return "admin/admin-point";
    }

    //관리자 포인트 목록
    @ResponseBody
    @PostMapping("points/list")
    public List<PointVO> adminPointList(){
        return null;
    }

//    관리자 포인트 내역 삭제

    /*====================후기 게시판 시작==============================*/
//    관리자 후기 게시판 목록
    @GetMapping("review/list")
    public String adminReview(){
        return "admin/admin-review";
    }

//    관리자 후기 게시판 목록
    @ResponseBody
    @PostMapping("reviews/list")
    public Map<String, Object> adminReviewList(@RequestBody Map<String, Object> requestData, Criteria criteria){
        return reviewBoardService.getListAdmin(requestData, criteria);
    }

//    관리자 후기 게시판 상세 보기
    @ResponseBody
    @PostMapping("reviews/detail")
    public Map<String, Object> adminReviewDetail(@RequestParam("reviewId") Long reviewId) {
        return reviewBoardService.getBoardAdmin(reviewId);
    }

//    관리자 후기 게시판 삭제
    @ResponseBody
    @DeleteMapping("reviews/delete")
    public void deleteReview(@RequestParam("checkedIds[]") List<String> checkedIds) {
        reviewBoardService.remove(checkedIds);
    }
    /*====================후기 게시판 끝==============================*/


    /*====================자유 게시판 시작==============================*/
//    관리자 자유 게시판 목록 이동
    @GetMapping("board/list")
    public String adminBoard(){
        return "admin/admin-board";
    }


//    관리자 자유 게시판 목록
    @ResponseBody
    @PostMapping("boards/list")
    public Map<String, Object> adminBoardList(@RequestBody Map<String, Object> requestData, Criteria criteria/*@RequestParam(value = "keyword", required = false) required = false를 해야 null값도 들어옴*/ ){
        return freeBoardService.getListAdmin(requestData, criteria);
    }

//    관리자 자유 게시판 상세 보기
    @ResponseBody
    @PostMapping("boards/detail")
    public Map<String, Object> adminBoardDetail(@RequestParam("boardId") /*@PathVariable("boardId")*/ Long boardId) {
        return freeBoardService.getBoardAdmin(boardId);
    }


//    관리자 자유 게시판 삭제
    @ResponseBody
    @DeleteMapping("boards/delete")
    public void deleteBoard(@RequestParam("checkedIds[]") List<String> checkedIds /*Map<String, List<Integer>> requestData*/ ){
        freeBoardService.remove(checkedIds);
    }
    /*====================자유 게시판 끝==============================*/

//    관리자 댓글 목록
    @GetMapping("reply/list")
    public String adminReply(){ return "admin/admin-reply"; }

    //    관리자 댓글 삭제
//    @ResponseBody
//    @DeleteMapping("reply/delete")
//    public void deleteReply(@RequestParam("checkedIds[]") List<String> checkedIds) {
//    }
}
