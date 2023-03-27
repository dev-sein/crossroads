package com.crossroads.app.mapper;

import com.crossroads.app.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    //회원가입
    public void join(MemberVO memberVO);

    //아이디 중복체크
    public Long checkId(String memberIdentifcation);

    //이메일 중복체크
    public Long checkEmail(String memberEmail);

    public MemberVO select(Long memberId);
}
