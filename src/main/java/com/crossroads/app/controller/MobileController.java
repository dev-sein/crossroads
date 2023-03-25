package com.crossroads.app.controller;

import com.crossroads.app.service.ApplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/applies/*")
@RequiredArgsConstructor
@Slf4j
public class MobileController {
    private final ApplyService applyService;

    @GetMapping("join-mobile")
    public String joinMobile(){
        return "mobile/join-mobile";
    }

    @GetMapping("list-mobile")
    public String listMobile(){
        return "mobile/list-mobile";
    }

}
