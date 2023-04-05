package com.crossroads.app.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FileDAOTests {
    @Autowired
    MemberFileDAO memberFileDAO;
    //회원가입 테스트

/*    @Test
    public void insertTest(){
        MemberFileVO memberFileVO = new MemberFileVO();
        memberFileVO.setFileId(2L);
        memberFileVO.setFileOriginalName("filedaotest");
        memberFileVO.setFilePath("test");
        memberFileVO.setFileSize("30mb");
        memberFileVO.setFileUuid("daotest");
        memberFileVO.setMemberId(2L);

        memberFileDAO.insert(memberFileVO);
    }*/
}
