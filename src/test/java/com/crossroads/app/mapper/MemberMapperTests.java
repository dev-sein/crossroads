package com.crossroads.app.mapper;

import com.crossroads.app.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Member;

@SpringBootTest
@Slf4j
public class MemberMapperTests {
    @Autowired
    MemberMapper memberMapper;

    //회원가입 테스트
//   @Test
//    public void insertTest(){
//        MemberVO memberVO = new MemberVO();
//        memberVO.setMemberIdentification("bcd123");
//        memberVO.setMemberPassword("1234");
//        memberVO.setMemberName("정세인");
//        memberVO.setMemberPhone("01011111111");
//        memberVO.setMemberEmail("abc@naver.com");
//
//        memberMapper.join(memberVO);
//    }

    @Test
    public void selectTest(){
       log.info(memberMapper.select(1L).toString());
    }


/*    @Test
    public void updateTest(){
        MemberVO memberVO = memberMapper.select(1L);
        memberMapper.changePassword("abc123@gmail.com", "asdf");
    }*/

    @Test
    public void selectEmail(){ log.info(memberMapper.selectEmail("test11234"));}


    @Test
    public void updateRandomKeyTest(){
        MemberVO memberVo = new MemberVO();
        memberMapper.updateRandomKey(5345534L, "membertypetest@test.com");
    }

    @Test
    public void changePassword(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("membertypetest@test.com");
        memberVO.setMemberRandomKey(5345534L);
        memberVO.setMemberPassword("passwordsettest");
        memberMapper.changePassword(memberVO);
    }

}
