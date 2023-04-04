package com.crossroads.app.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
@Slf4j
public class MemberServiceTests {

    @Autowired
    MemberService memberService;

    @Test
    public void selectTest(){log.info(memberService.getMemberInfo(1L).toString());}

    @Test
    public Long randomKey() {
        Random rand = new Random();
        long randomkey = rand.nextLong();
        return randomkey;
    }
}
