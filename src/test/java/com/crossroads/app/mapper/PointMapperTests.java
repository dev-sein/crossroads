package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.PointDTO;
import com.crossroads.app.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

@SpringBootTest
@Slf4j
public class PointMapperTests {
    @Autowired
    PointMapper pointMapper;

    @Test
    public void insertTest(){
        PointDTO pointDTO = new PointDTO();
        pointDTO.setMemberId(2L);
        pointDTO.setPointStatus(1);
        pointDTO.setPointPoint(50000);

        pointMapper.insert(pointDTO);
    }

}
