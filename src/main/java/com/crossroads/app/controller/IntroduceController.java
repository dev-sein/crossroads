package com.crossroads.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntroduceController {

    @GetMapping("introduce")
    public String introduce(){
        return "introduce/introduce";
    }

    @GetMapping("notice")
    public String notice(){
        return "introduce/notice";
    }
}
