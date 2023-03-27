package com.crossroads.app.dao;

import com.crossroads.app.domain.dao.ReplyDAO;
import com.crossroads.app.domain.dto.ReplyDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReplyDAOTests {
    @Autowired
    ReplyDAO replyDAO;

    @Test
    public void replyCountTest(){
//        Long test = replyDAO.findReplyCount(2L).getReplyCount();
//        Long test2 = 0L;
//        log.info(test == null ? String.valueOf(test2) : String.valueOf(test));

        int replyCount = replyDAO.findReplyCount(2L);

    }



}
