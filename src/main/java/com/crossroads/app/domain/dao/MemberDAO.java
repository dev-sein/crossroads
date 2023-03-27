package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;
    public void saveMember(MemberVO memberVO){ memberMapper.join(memberVO);}

    public MemberVO findById(Long memberId){ return memberMapper.select(memberId);}
}
