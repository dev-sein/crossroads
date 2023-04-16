//package com.crossroads.app.dao;
//
//import com.crossroads.app.domain.dao.ApplyDAO;
//import com.crossroads.app.domain.dto.Criteria;
//import com.crossroads.app.domain.vo.ApplyVO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@SpringBootTest
//@Slf4j
//public class ApplyDAOTests {
//    @Autowired
//    ApplyDAO applyDAO;
//
//    @Test
//    public void findAllTest(){
//        Criteria criteria = new Criteria();
//        criteria.create(2,5);
//        log.info(applyDAO.findAll(criteria).toString());
//    }
//
//    @Test
//    public void findByIdTest(){
//        log.info(applyDAO.findById(2L).toString());
//    }
//
//    @Test
//    public void setApplyStatusTest(){
//        applyDAO.setApplyStatus(2L);
//    }
//
//    @Test
//    public void setVeteranIdTest(){
//        Map<String, Object> info = new HashMap<>();
//        info.put("memberId", 1L);
//        info.put("applyId",4L);
//        applyDAO.setVeteranId(info);
//    }
//
//
//
////    //    담당 베테랑 id 수정
////    public void setVeteranId(Map<String, Object> info){
////        applyMapper.updateVeteranId(info);
////    }
//}
