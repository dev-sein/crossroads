package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReplyMapperTests {
    @Autowired
    ReplyMapper replyMapper;

    @Test
    public void replyListTests(){
        Criteria criteria = new Criteria();
        criteria.create(1, 6);
        log.info(replyMapper.selectAllAdmin(criteria, null).toString());
    }
}
