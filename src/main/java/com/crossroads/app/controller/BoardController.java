package com.crossroads.app.controller;


import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.service.BoardService;
import com.crossroads.app.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private BoardService boardService;

    // 게시판 목록
    @GetMapping("/board-list")
    public String boardList(){
        return "board/board-list";
    }

    // 게시판 상세 목록
    @GetMapping("/board-detail")
    public String boardDetail() {
        return "board/board-detail";
    }

    // 게시판 작성하기
    @GetMapping("/board-write")
    public String boardWrite(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("boardDTO", new BoardDTO());
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("memberId", 1L);
        return "board/board-write";
    }

    // 게시판 작성(저장하기)
    @PostMapping("/board-save")
    public String save(BoardDTO boardDTO, @RequestParam("upload1") List<MultipartFile> files) throws IOException {
        boardService.saveBoard(boardDTO, files);
        return "redirect:/board-list";
    }

}




