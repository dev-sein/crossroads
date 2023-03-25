package com.crossroads.app.controller;

import com.crossroads.app.service.ApplyService;
import com.crossroads.app.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    @GetMapping("board-test")
    public String test(){
        return "board/board-write";
    }

}
