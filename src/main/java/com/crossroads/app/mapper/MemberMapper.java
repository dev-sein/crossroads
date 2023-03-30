package com.crossroads.app.mapper;

import com.crossroads.app.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    //회원가입
    public void join(MemberVO memberVO);

    //아이디 중복체크
    public Long checkId(String memberIdentification);

    //이메일 중복체크
    public Long checkEmail(String memberEmail);

    //로그인
    public Long login(String memberIdentification, String memberPassword);

    //마이페이지 정보 조회
    public MemberVO select(Long memberId);

    //마이페이지 정보 수정
    public void update(MemberVO memberVO);

    //회원 정보 목록
    public List<MemberVO> selectAll();

    //회원삭제 회원탈퇴
    public void delete(Long memberId);

    //비밀번호 변경
    public void changePassword(String memberEmail, String memberPassword);

    //아이디로 이메일 찾기
    public String selectEmail(String memberIdentification);

    //마이페이지 비밀번호 확인
    public Long checkPassword(String memberPassword);

}
