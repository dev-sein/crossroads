package com.crossroads.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardTestController {
    @GetMapping("board-list")
    public String boardList(){
        return "board/board-list";
    }

    @GetMapping("board-update")
    public String boardUpdate(){
        return "board/board-update";
    }

    @GetMapping("board-write")
    public String boardWrite(){
        return "board/board-write";
    }

}
