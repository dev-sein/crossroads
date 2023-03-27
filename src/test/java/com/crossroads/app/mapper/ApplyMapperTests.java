package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.ApplyDTO;
import com.crossroads.app.domain.vo.ApplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class ApplyMapperTests {
    @Autowired
    ApplyMapper applyMapper;

    @Test
    public void selectAllTest(){
        log.info(applyMapper.selectAll().toString());
    }

    @Test
    public void selectTest(){
        log.info(applyMapper.select(2L).toString());
    }

//    @Test
//    public void selectStatusTest(){
//        log.info((applyMapper.selectStatus(2L)));
//    }

    @Test
    public void updateVeteranIdTest() {
        Map<String, Object> info = new HashMap<>();
        info.put("memberId", 1L);
        info.put("applyId", 2L);
        applyMapper.updateVeteranId(info);
    }
}
