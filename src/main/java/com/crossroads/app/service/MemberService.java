package com.crossroads.app.service;

import com.crossroads.app.domain.dao.MemberDAO;
import com.crossroads.app.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDAO memberDAO;

    //회원가입
    public void registerMember(MemberVO memberVO){
        MemberVO memberVO = new MemberVO();

    }
}
