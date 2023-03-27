package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;
    //회원가입
    public void saveMember(MemberVO memberVO){ memberMapper.join(memberVO);}

    //아이디 중복 체크
    public Long checkId(String memberIdentification){ return memberMapper.checkId(memberIdentification);}

    //이메일 중복 체크
    public Long checkEmail(String memberEmail){ return memberMapper.checkEmail(memberEmail);}

    //로그인
    public Long login(String memberIdentification, String memberPassword){return memberMapper.login(memberIdentification, memberPassword);}

    public MemberVO findById(Long memberId){ return memberMapper.select(memberId);}
}
