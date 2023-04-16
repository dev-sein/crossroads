package com.crossroads.app.controller;

import com.crossroads.app.domain.dto.*;
import com.crossroads.app.domain.vo.ApplyVO;
import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.domain.vo.PointVO;
import com.crossroads.app.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final ReplyService replyService;
    private final PointService pointService;

    //관리자 홈 및 출력
    @GetMapping("home")
    public String adminHome(Criteria criteria, Model model){
        Map<String, Object> requestData = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        requestData.put("page", criteria.getPage());

        /* 회원 정보 가공 */
        List<MemberVO> memberVOs =  (List<MemberVO>) memberService.getListAdmin(requestData, criteria).get("members");
        Long memberId = memberVOs.get(0).getMemberId();
        Integer memberCount = memberService.getCountAdmin();
        Integer withdrawMember = memberId.intValue() - memberCount;

        int[] members = {memberId.intValue(), memberCount, withdrawMember};

        /* 연수 신청 현황 가공 */
        List<ApplyVO> applyVOs = (List<ApplyVO>) applyService.getListAdmin(requestData, criteria).get("applies");
        Long applyId = applyVOs.get(0).getApplyId();
        Integer applyCount = applyService.getCountAdmin();

        int[] applies = {applyId.intValue(), applyCount};

        /* 포인트 결제 현황 가공 */
        Integer paymentCount = pointService.getCountAdmin("결제");
        Integer exchangeCount = pointService.getCountAdmin("환전");

        int[] points = {paymentCount, exchangeCount};

        /* 후기 게시판 리스트 */
        List<ReviewDTO> reviews = (List<ReviewDTO>) reviewBoardService.getListAdmin(requestData, criteria).get("reviews");

        /* 자유 게시판 리스트 */
        List<BoardDTO> boards = (List<BoardDTO>) freeBoardService.getListAdmin(requestData, criteria).get("boards");

        /* 댓글 게시판 리스트 */
        List<ReplyDTO> replies = (List<ReplyDTO>) replyService.getListAdmin(requestData, criteria).get("replies");




        model.addAttribute("members", members);
        model.addAttribute("applies", applies);
        model.addAttribute("points", points);
        model.addAttribute("reviews", reviews);
        model.addAttribute("boards", boards);
        model.addAttribute("replies", replies);
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
        return memberService.getMemberInfo(memberId);
    }

    //관리자 회원 삭제
    @ResponseBody
    @DeleteMapping("members/delete")
    public void deleteMember(@RequestParam("checkedIds[]") List<Long> checkedIds) {
        memberService.removeAdmin(checkedIds);
    }
    /*====================회원 게시판 끝==============================*/

    /*====================연수 신청 게시판 시작==============================*/
    //관리자 연수신청 목록
    @GetMapping("apply/list")
    public String adminApply(){
//        model.addAttribute("applies", applyService.getList());
        return "admin/admin-apply";
    }

    //관리자 연수신청 목록
    @ResponseBody
    @PostMapping("applies/list")
    public Map<String, Object> adminApplyList(@RequestBody Map<String, Object> requestData, Criteria criteria){
        return applyService.getListAdmin(requestData, criteria);
    }

    //관리자 연수신청 삭제
    @ResponseBody
    @DeleteMapping("applies/delete")
    public void deleteApply(@RequestParam("checkedIds[]") List<Long> checkedIds) {
        applyService.cancelAdmin(checkedIds);
    }
    /*====================연수 신청 게시판 끝==============================*/

    /*====================포인트 내역 시작==============================*/
    //관리자 포인트 내역
    @GetMapping("point/list")
    public String adminPoint(){
        return "admin/admin-point";
    }

    //관리자 포인트 내역 목록
    @ResponseBody
    @PostMapping("points/list")
    public Map<String, Object> adminPointList(@RequestBody Map<String, Object> requestData, Criteria criteria){
        return pointService.getListAdmin(requestData, criteria);
    }
    //    관리자 포인트 내역 삭제
    @ResponseBody
    @DeleteMapping("points/delete")
    public void deletePoint(@RequestParam("checkedIds[]") List<Long> checkedIds) {
//        log.info("여긴 들어옴!");
//        log.info(checkedIds.toString()); // 이것도 잘 가져옴.
        pointService.remove(checkedIds);
    }
    /*====================포인트 내역 끝==============================*/

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
    public void deleteReview(@RequestParam("checkedIds[]") List<Long> checkedIds) {
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
    public void deleteBoard(@RequestParam("checkedIds[]") List<Long> checkedIds /*Map<String, List<Integer>> requestData*/ ){
        freeBoardService.remove(checkedIds);
    }
    /*====================자유 게시판 끝==============================*/


    /*====================댓글 시작==============================*/
//    관리자 댓글 목록
    @GetMapping("reply/list")
    public String adminReply(){ return "admin/admin-reply"; }

//    관리자 댓글 목록
    @ResponseBody
    @PostMapping("replies/list")
    public Map<String, Object> adminReplyList(@RequestBody Map<String, Object> requestData, Criteria criteria/*@RequestParam(value = "keyword", required = false) required = false를 해야 null값도 들어옴*/ ){
        return replyService.getListAdmin(requestData, criteria);
    }

    //    관리자 자유 게시판 상세 보기
//    @ResponseBody
//    @PostMapping("boards/detail")
//    public Map<String, Object> adminReplyDetail(@RequestParam("boardId") Long boardId) {
//        return replyService.getBoardAdmin(boardId);
//    }


//    관리자 댓글 삭제
    @ResponseBody
    @DeleteMapping("replies/delete")
    public void deleteReply(@RequestParam("checkedIds[]") List<Long> checkedIds){
        replyService.removeAdmin(checkedIds);
    }

    /*====================댓글 끝==============================*/
}
