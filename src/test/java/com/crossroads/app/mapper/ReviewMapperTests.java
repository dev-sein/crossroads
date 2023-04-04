package com.crossroads.app.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReviewMapperTests {
    @Autowired
    ReviewMapper reviewMapper;


    @Test
    public void selectReviewAll() {
        log.info(reviewMapper.selectReviewAll().toString());
    }

}
