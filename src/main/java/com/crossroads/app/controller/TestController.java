package com.crossroads.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "index";
    }

    @GetMapping("/maintest")
    public String maintest(){
        return "main/main";
    }


}
