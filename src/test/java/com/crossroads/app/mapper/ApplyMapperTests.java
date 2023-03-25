package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.ApplyDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ApplyMapperTests {
    @Autowired
    ApplyMapper applyMapper;

    @Test
    public void selectAllTest(){
        log.info(applyMapper.selectAll().toString());
    }
//    @Test
//    public List<ApplyDTO> selectAllTest(){
//        return applyMapper.selectAll();
//    }
}
