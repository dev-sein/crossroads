package com.crossroads.app.controller;

import com.crossroads.app.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/applies/*")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;

    @GetMapping("/apply")
    public String apply(){
        return "form/form-first-test";
    }

}
