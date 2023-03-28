package com.crossroads.app.controller;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.PageDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admins/*")
@Slf4j
public class AdminController {
    private final FreeBoardService freeBoardService;
    //관리자 홈
    @GetMapping("home")
    public String adminHome(){
        return "admin/admin-home";
    }

    //관리자 회원 목록
    @GetMapping("member")
    public String adminMember(){
        return "admin/admin-member";
    }

    //관리자 연수신청 목록
    @GetMapping("apply")
    public String adminApply(){
        return "admin/admin-apply";
    }

    //관리자 포인트 목록
    @GetMapping("point")
    public String adminPoint(){
        return "admin/admin-point";
    }

    //관리자 후기 목록
    @GetMapping("review")
    public String adminReview(){
        return "admin/admin-review";
    }

    //관리자 게시글 목록
    @GetMapping("board/list")
    public String adminBoard(Model model, Criteria criteria){
        if (criteria.getPage() == 0) {
            criteria = criteria.create(1, 6); // 1페이지부터 / 화면에 몇개 보일지
        }

        List<BoardDTO> boards = freeBoardService.getListAdmin(criteria);

        model.addAttribute("boards", boards);
        model.addAttribute("pagination", new PageDTO().createPageDTO(criteria, freeBoardService.getCountAdmin()));
        return "admin/admin-board";
    }

//    관리자 게시글 삭제
    @ResponseBody
    @DeleteMapping("board/delete")
    public void deleteBoard(@RequestParam("checkedIds[]") List<String> checkedIds) {
        freeBoardService.remove(checkedIds);
    }

    //관리자 댓글 목록
    @GetMapping("reply")
    public String adminReply(){ return "admin/reply"; }

}
