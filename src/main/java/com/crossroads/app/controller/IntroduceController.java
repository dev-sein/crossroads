package com.crossroads.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntroduceController {
    //사업소개
    @GetMapping("introduce")
    public String introduce(){
        return "introduce/introduce";
    }
    //자주 묻는 질문
    @GetMapping("notice")
    public String notice(){
        return "introduce/notice";
    }
    //404 에러
    @GetMapping("error")
    public String error(){
        return "error/404";
    }


}
