package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    //마이페이지 정보 조회
    public MemberVO findById(Long memberId){ return memberMapper.select(memberId);}

    //마이페이지 정보 수정
    public void setMyInfo(MemberVO memberVO){ memberMapper.update(memberVO);}

    //회원 정보 목록
    public List<MemberVO> findAll(){
        return memberMapper.selectAll();
    }

    //회원 삭제, 탈퇴
    public void deleteById(Long memberId) {
        memberMapper.delete(memberId);
    }

    //비밀번호 변경
    public void setPassword(String memberEmail, String memberPassword){ memberMapper.changePassword(memberEmail, memberPassword);};

    //마이페이지 비밀번호 확인
    public Long findByPasswordMy(String memberPassword) { return memberMapper.checkPassword(memberPassword); }

    //아이디로 랜덤키 찾기
    public Long findRandomKey(String memberEmail){ return memberMapper.selectRandomKey(memberEmail);}

    //마이페이지 비밀번호 변경
    public Long setPasswordMy(Long memberId, String memberPassword) { return memberMapper.changeNewPassword(memberId, memberPassword); }
    
    //랜덤키 삽입
    public void setRandomKey(String memberEmail,Long memberRandomKey){ memberMapper.updateRandomKey(memberEmail,memberRandomKey);};


}
