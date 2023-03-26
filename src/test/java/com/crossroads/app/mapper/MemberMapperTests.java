package com.crossroads.app.mapper;

import com.crossroads.app.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberMapperTests {
    @Autowired
    MemberMapper memberMapper;

    //회원가입 테스트
   @Test
    public void insertTest(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberIdentification("bcd123");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberName("정세인");
        memberVO.setMemberPhone("01011111111");
        memberVO.setMemberEmail("abc@naver.com");

        memberMapper.insert(memberVO);

    }
    
}
