package com.crossroads.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/apply/*")
@RequiredArgsConstructor
@Slf4j
public class ApplyController {

    @GetMapping("applyForm")
    public String applyFirst(){
        return "form/formFirst";
    }

}
