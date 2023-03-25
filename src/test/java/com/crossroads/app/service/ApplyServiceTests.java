package com.crossroads.app.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ApplyServiceTests {

    @Autowired
    ApplyService applyService;

    @Test
    public void getListTest(){
        log.info(applyService.getList().toString());
    }

}
