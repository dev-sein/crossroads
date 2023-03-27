package com.crossroads.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {
    @GetMapping("/formFirst")
    public String formFirst(){
        return "form/formFirst";
    }
}
