package com.crossroads.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Slf4j
public class MypageControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void updateTest() throws Exception{
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/mypage/my-info").param("memberId", "1"))
                .andReturn().getModelAndView().getViewName());

        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/mypage/my-info")
                .param("memberId", "1")
                .param("memberName", "박다예")
                .param("memberPhone", "01022222222")
                .param("memberEmail", "aaa111@naver.com")
        ).andReturn().getModelAndView().getModelMap().toString());
    }



}
