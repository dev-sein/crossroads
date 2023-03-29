package com.crossroads.app.controller;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.PageDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.service.ApplyService;
import com.crossroads.app.service.FreeBoardService;
import com.crossroads.app.service.MemberService;
import com.crossroads.app.service.ReviewBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admins/*")
@Slf4j
public class AdminController {
    private final MemberService memberService;
    private final FreeBoardService freeBoardService;
    private final ReviewBoardService reviewBoardService;
    private final ApplyService applyService;
    //관리자 홈
    @GetMapping("home")
    public String adminHome(){
        return "admin/admin-home";
    }

    //관리자 회원 목록
    @GetMapping("member/list")
    public String adminMember(Model model){
        model.addAttribute("members", memberService.getList());
        return "admin/admin-member";
    }

    //관리자 회원 삭제
    @ResponseBody
    @DeleteMapping("member/delete")
    public void deleteMember(@RequestParam("checkedIds[]") List<String> checkedIds) {
        checkedIds.stream().map(checkedId -> Long.parseLong(checkedId)).forEach(memberService::remove);
    }

    //관리자 연수신청 목록
    @GetMapping("apply/list")
    public String adminApply(Model model){
        model.addAttribute("applies", applyService.getList());
        return "admin/admin-apply";
    }

    //    관리자 연수신청 삭제
    @ResponseBody
    @DeleteMapping("apply/delete")
    public void deleteApply(@RequestParam("checkedIds[]") List<String> checkedIds) {
        checkedIds.stream().map(checkedId -> Long.parseLong(checkedId)).forEach(applyService::cancel);
    }

    //관리자 포인트 목록
    @GetMapping("point")
    public String adminPoint(){
        return "admin/admin-point";
    }

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
    @GetMapping("board/list")
    public String adminBoard(Model model, Criteria criteria){
        if (criteria.getPage() == 0) {
            criteria = criteria.create(1, 6); // 1페이지부터 / 화면에 몇개 보일지
        } else {
            criteria = criteria.create(criteria.getPage(), 6);
        }

        log.info(criteria.toString());

        List<BoardDTO> boards = freeBoardService.getListAdmin(criteria);

        model.addAttribute("boards", boards);
        model.addAttribute("pagination", new PageDTO().createPageDTO(criteria, freeBoardService.getCountAdmin()));
        return "admin/admin-board";
    }

//    관리자 게시글 삭제
    @ResponseBody
    @DeleteMapping("board/delete")
    public void deleteBoard(@RequestParam("checkedIds[]") List<String> checkedIds /*, @RequestParam String page*/) {
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
