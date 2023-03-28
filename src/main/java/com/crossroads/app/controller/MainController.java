package com.crossroads.app.controller;

import com.crossroads.app.domain.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @GetMapping("main")
    public String main(){
        return "main/main";
    }

}
