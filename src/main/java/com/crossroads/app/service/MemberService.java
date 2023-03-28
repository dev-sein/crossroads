package com.crossroads.app.service;

import com.crossroads.app.domain.dao.MemberDAO;
import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDAO memberDAO;

    //회원가입
    public void save(MemberVO memberVO){
        memberDAO.saveMember(memberVO);
    }

    //아이디 중복확인
    public Long checkId(String memberIdentification){
        return memberDAO.checkId(memberIdentification);
    }

    //아이디 중복확인
    public Long checkEmail(String memberEmail){
        return memberDAO.checkEmail(memberEmail);
    }

    //로그인
    public Long login(String memberIdentification, String memberPassword){
        return memberDAO.login(memberIdentification, memberPassword);
    }

    //마이페이지 프로필 정보조회
    public MemberVO getMember(Long memberId){ return memberDAO.findById(memberId); }

    //마이페이지 프로필 수정
    public void modify(MemberVO memberVO){ memberDAO.setMyInfo(memberVO); }

    //회원 정보 목록
    public List<MemberVO> getList(){
        return memberDAO.findAll();
    }

}
