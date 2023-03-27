package com.crossroads.app.controller;

import com.crossroads.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class joinController {

    private final MemberService memberService;




}
