package com.crossroads.app.service;

import com.crossroads.app.domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
public class ApplyServiceTests {

    @Autowired
    ApplyService applyService;

    @Test
    public void getListTest(){
        Criteria criteria = new Criteria();
        criteria.create(3,5);
//        log.info(applyService.getList(criteria).toString());
    }

    @Test
    public void modifyStatusTest(){
        applyService.modifyStatus(2L);
    }

    @Test
    public void modifyVeteranIdTest(){
        Map<String, Object> info = new HashMap<>();
        info.put("memberId", 1L);
        info.put("applyId", 4L);
        applyService.modifyVeteranId(info);
    }

//    //    신청 상태 수정
//    public void modifyStatus(Long applyId) {
//        applyDAO.setApplyStatus(applyId);
//    }
//
//    //    담당 베테랑 id 수정
//    public void modifyVeteranId(Map<String, Object> info) {
//        applyDAO.setVeteranId(info);
//    }

}
