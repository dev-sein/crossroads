package com.crossroads.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("admin-home")
    public String adminHome(){
        return "admin/admin-home";
    }

    @GetMapping("admin-apply")
    public String adminApply(){
        return "admin/admin-apply";
    }

    @GetMapping("admin-review")
    public String adminReview(){
        return "admin/admin-review";
    }

    @GetMapping("admin-board")
    public String adminBoard(){
        return "admin/admin-board";
    }

}
