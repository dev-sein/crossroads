package com.crossroads.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forms/*")
@RequiredArgsConstructor
@Slf4j
public class FormController {
    @GetMapping("formFirst")
    public String formFirst(){
        return "form/form-first-test";
    }

    @GetMapping("formSecond")
    public String applyFirst(){
        return "form/formSecond";
    }

    @GetMapping("findThird")
    public String findPwd() {
        return "form/formThird";
    }
}
