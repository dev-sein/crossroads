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

    @GetMapping("formFirst")
    public String formFirst(){
        return "form/form-first-test";
    }

    @GetMapping("formSecond")
    public String applySecond(){
        return "form/formSecond";
    }

    @GetMapping("findThird")
    public String formThird() {
        return "form/formThird";
    }

}
