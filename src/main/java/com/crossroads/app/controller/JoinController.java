package com.crossroads.app.controller;

import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members/*")
@RequiredArgsConstructor
public class JoinController {
    @Autowired
    private final MemberService memberService;


}
