package com.crossroads.app.dao;

import com.crossroads.app.domain.dao.ApplyDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ApplyDAOTests {
    @Autowired
    ApplyDAO applyDAO;

    @Test
    public void findAllTest(){
        log.info(applyDAO.findAll().toString());
    }
}
