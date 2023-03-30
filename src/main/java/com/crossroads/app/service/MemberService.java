package com.crossroads.app.service;

import com.crossroads.app.domain.dao.MemberDAO;
import com.crossroads.app.domain.vo.MailTO;
import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDAO memberDAO;

    //회원가입
    public void save(MemberVO memberVO) {
        memberDAO.saveMember(memberVO);
    }

    //아이디 중복확인
    public Long checkId(String memberIdentification) {
        return memberDAO.checkId(memberIdentification);
    }

    //아이디 중복확인
    public Long checkEmail(String memberEmail) {
        return memberDAO.checkEmail(memberEmail);
    }

    //로그인
    public Long login(String memberIdentification, String memberPassword) {
        return memberDAO.login(memberIdentification, memberPassword);
    }

    //마이페이지 프로필 정보조회
    public MemberVO getMember(Long memberId) {
        return memberDAO.findById(memberId);
    }

    //마이페이지 프로필 수정
    public void modify(MemberVO memberVO) {
        memberDAO.setMyInfo(memberVO);
    }

    //회원 정보 목록
    public List<MemberVO> getList() {
        return memberDAO.findAll();
    }

    //회원 삭제, 탈퇴
    public void remove(Long memberId) {
//        게시글 삭제, 댓글 삭제, 파일 삭제 필요
        memberDAO.deleteById(memberId);
    }

    //비밀번호 찾기 인증 이메일
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(MailTO mailTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailTO.getAddress());
//      message.setFrom(""); from 값을 설정하지 않으면 application.yml의 username값이 설정됩니다.
        message.setSubject(mailTO.getTitle());
        message.setText(mailTO.getMessage());

        mailSender.send(message);
    }

    //로그인-비밀번호 변경
    public void modifyPassword(String memberEmail, String memberPassword){
        memberDAO.setPassword(memberEmail, memberPassword);
    }

    //마이페이지 비밀번호 확인
    public Long getPassword(String memberPassword) { return memberDAO.findByPasswordMy(memberPassword); }

}