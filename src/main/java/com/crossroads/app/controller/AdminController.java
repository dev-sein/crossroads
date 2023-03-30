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
@RequestMapping("/admins/*")
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


    //관리자 회원 목록
    @GetMapping("members/list")
    public String adminMember(){
//        model.addAttribute("members", memberService.getList());
        return "admin/admin-member";
    }
    //관리자 회원 목록
    @PostMapping("members/list")
    public List<MemberVO> adminMemberList(Model model){
        return memberService.getList();
    }

    //관리자 회원 삭제
    @ResponseBody
    @DeleteMapping("members/delete")
    public void deleteMember(@RequestBody List<String> checkedIds) {
        checkedIds.stream().map(checkedId -> Long.parseLong(checkedId)).forEach(memberService::remove);
    }

    //관리자 연수신청 목록
    @GetMapping("applies/list")
    public String adminApply(Model model){
//        model.addAttribute("applies", applyService.getList());
        return "admin/admin-apply";
    }

    //관리자 연수신청 목록
    @ResponseBody
    @PostMapping("applies/list")
    public List<ApplyDTO> adminApplyList(){
        return applyService.getList();
    }

    //관리자 연수신청 삭제
    @ResponseBody
    @DeleteMapping("apply/delete")
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

    //관리자 후기 목록
    @GetMapping("review/list")
    public String adminReview(Model model){
        model.addAttribute("reviews", reviewBoardService.getListReview());
        return "admin/admin-review";
    }

    //    관리자 후기 삭제
    @ResponseBody
    @DeleteMapping("review/delete")
    public void deleteReview(@RequestParam("checkedIds[]") List<String> checkedIds) {
        reviewBoardService.remove(checkedIds);
    }

    //관리자 게시글 목록
//    @GetMapping("board/list")
//    public String adminBoard(@RequestParam(value = "keyword", required = false) /*required = false를 해야 null값도 들어옴*/ String keyword, Criteria criteria, Model model){
//        if (criteria.getPage() == 0) {
//            criteria = criteria.create(1, 6); // 1페이지부터 / 화면에 몇개 보일지
//        } else {
//            criteria = criteria.create(criteria.getPage(), 6);
//        }
//
//        log.info(criteria.toString());
//
//        List<BoardDTO> boards = freeBoardService.getListAdmin(criteria, keyword);
//
//        model.addAttribute("boards", boards);
//        model.addAttribute("pagination", new PageDTO().createPageDTO(criteria, freeBoardService.getCountAdmin()));
//        return "admin/admin-board";
//    }

    // 관리자 게시글 목록 이동
    @GetMapping("boards/list")
    public String adminBoard(){
        return "admin/admin-board";
    }


    //관리자 게시글 목록
    @ResponseBody
    @PostMapping("boards/list/")
    public Map<String, Object> adminBoardList(@RequestBody Map<String, Object> requestData, Criteria criteria/*@RequestParam(value = "keyword", required = false) required = false를 해야 null값도 들어옴*/ ){
        Map<String, Object> result = new HashMap<String, Object>();
        String keyword = (String) requestData.get("keyword");
//        String keyword = null;
        Integer page = (Integer) requestData.get("page") == null ? 0 : (Integer) requestData.get("page");

        if (page == 0 || page == null) {
            criteria = criteria.create(1, 6); // 1페이지부터 / 화면에 몇개 보일지
        } else {
            criteria = criteria.create(page, 10);
        }


        List<BoardDTO> boards = freeBoardService.getListAdmin(criteria, keyword);

        result.put("boards", boards);
        result.put("pagination", new PageDTO().createPageDTO(criteria, freeBoardService.getCountAdmin()));

        return result;
    }

//    관리자 게시글 삭제
    @ResponseBody
    @DeleteMapping("boards/delete")
    public void deleteBoard(@RequestBody List<String> checkedIds /*, @RequestParam String page*/) {
        freeBoardService.remove(checkedIds);

//        return new RedirectView("/admins/board/list?page=" + page);
//        return "/admins/board/list?page=" + page;
    }

    //관리자 댓글 목록
    @GetMapping("reply/list")
    public String adminReply(){ return "admin/admin-reply"; }

    //    관리자 댓글 삭제
//    @ResponseBody
//    @DeleteMapping("reply/delete")
//    public void deleteReply(@RequestParam("checkedIds[]") List<String> checkedIds) {
//    }
}
