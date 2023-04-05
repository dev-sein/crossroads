package com.crossroads.app.controller;


import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.service.BoardService;
import com.crossroads.app.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private FreeBoardService freeboardService;
    private BoardService boardService;

    //    게시판 상세 목록
    @GetMapping("board-detail")
    public String boardDetail() {
        return "board/board-detail";
    }

    //    게시판 목록 전체조회
    @GetMapping("board-list")
    public String selectAllBoards(Model model) {
        List<BoardDTO> boards = freeboardService.getListBoard();
        model.addAttribute("boards", boards);
        return "board/board-list";
    }

    // 게시판 작성하기
    @GetMapping("board-write")
    public String boardWrite(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("boardDTO", new BoardDTO());
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("memberId", 1l);
        return "board/board-write";
    }

    //게시판 삭제
    @DeleteMapping("board-delete")
    public void boardDelete(@RequestParam("boardId") Long boardId){

    }



}
