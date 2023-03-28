package com.crossroads.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @GetMapping("main")
    public String main(HttpServletRequest request){
        HttpSession session = request.getSession();
        return "main/main";
    }

}
