package com.crossroads.app.mapper;

import com.crossroads.app.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PointMapperTests {
    @Autowired
    PointMapper pointMapper;

    @Test
    public void updateAfterApplyTest(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberId(2L);
        log.info(pointMapper.selectPoint(2L).toString()); //현재 포인트 조회
        pointMapper.updateAfterApply(2L, 50000);
        log.info(pointMapper.selectPoint(2L).toString()); //현재 포인트 조회
        //pointMapper.updateAfterApply(2L, 50000);
        //memberVO.setMemberPoint(50000);

    }

}
