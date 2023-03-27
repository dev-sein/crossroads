package com.crossroads.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

//    자유 게시판
    @GetMapping("board-list")
    public String boardList(){
        return "board/board-list";
    }

//    게시판 상세 목록
    @GetMapping("board-detail")
    public String boardDetail(){
        return "board/board-detail";
    }
}
