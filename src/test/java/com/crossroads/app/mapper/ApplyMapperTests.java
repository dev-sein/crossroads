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
    public void updateStatusTest(){
        applyMapper.updateStatus(2L);
    }

    @Test
    public void updateVeteranIdTest() {
        Map<String, Object> info = new HashMap<>();
        info.put("applyId", 4L);
        info.put("memberId", 1L);
        applyMapper.updateVeteranId(info);
    }

    @Test
    public void selectCountTest(){
        log.info(applyMapper.selectCount(3L).toString());
    }

    //    나를 제외한 다른 베테랑들이 수락한 신청 개수
//    public Long selectCount(Long applyId);
}
