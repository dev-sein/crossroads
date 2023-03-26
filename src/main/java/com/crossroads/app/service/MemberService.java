package com.crossroads.app.service;

import com.crossroads.app.domain.dao.MemberDAO;
import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDAO memberDAO;

    //회원가입
    public void save(MemberVO memberVO){
        memberDAO.saveMember(memberVO);
    }

}
