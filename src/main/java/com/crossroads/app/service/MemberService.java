package com.crossroads.app.service;

import com.crossroads.app.domain.dao.*;
import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.domain.dto.PageDTO;
import com.crossroads.app.domain.vo.MailTO;
import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDAO memberDAO;
    private final BoardDAO boardDAO;
    private final ReviewDAO reviewDAO;
    private final ReplyDAO replyDAO;
    private final BoardFileDAO boardFileDAO;

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
    public MemberVO getMemberInfo(Long memberId) {
        return memberDAO.findByIdMy(memberId);
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

    //이메일로 랜덤키 찾기
    public Long getRandomKey(String memberEmail) { return memberDAO.findRandomKey(memberEmail); }

    //비밀번호 찾기 인증 이메일 발송 서비스
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
    public Long getPassword(Long memberId, String memberPassword) { return memberDAO.findByPasswordMy(memberId, memberPassword); }

    //마이페이지 비밀번호 변경
    public Long modifyPasswordMy(Long memberId ,String memberPassword){ return memberDAO.setPasswordMy(memberId, memberPassword); }

    //랜덤 난수 생성
    public Long makeRandomKey() {
        Random rand = new Random();
        long randomkey = rand.nextLong()+1;
        return randomkey;
    }

    //랜덤키 삽입
    public void setRandomKey(String memberEmail, Long memberRandomKey){ memberDAO.setRandomKey(memberEmail, memberRandomKey);};

    //이메일로 VO 찾기
    public MemberVO getByEmail(String memberEmail) {return memberDAO.findByEmail(memberEmail);}
    //마이페이지 프로필 업로드
    public void modifyProfile(MemberVO memberVO) {
        memberDAO.setProfile(memberVO);

    }

    //관리자 회원 목록
    public Map<String, Object> getListAdmin(Map<String, Object> requestData, Criteria criteria) {
        Map<String, Object> result = new HashMap<String, Object>();

        String keyword = (String) requestData.get("keyword");
        int page = (int) requestData.get("page");

        if (page == 0) {
            page = 1;
        }
        criteria = criteria.create(page, 6);

        List<MemberVO> members = memberDAO.findAllAdmin(criteria, keyword);

        result.put("members", members);
        result.put("pagination", new PageDTO().createPageDTO(criteria, getCountAdmin(keyword)));

        return result;
    }

    //관리자 회원 총 수
    public Integer getCountAdmin(String keyword) {
        return memberDAO.findCountAllAdmin(keyword);
    }

    //관리자 회원 삭제
    @Transactional(rollbackFor = Exception.class)
    public void removeAdmin(List<String> memberIds) {

////        리스트에서 memberId 하나씩 삭제
//        memberIds.stream().map(memberId -> Long.valueOf(memberId)).forEach(memberId -> {
//            boardDAO.findByMemberId(memberId).stream().map(boardDTO -> boardDTO.getBoardId()).forEach(boardId -> {
//                boardFileDAO.deleteByBoardId(boardId); // 맴버 별 작성한 자유 게시판 게시글에서 file삭제
//            });
//            replyDAO.deletByMemberId(memberId); // 댓글 삭제
//            boardDAO.deletByMemberId(memberId); // 자유 게시판 게시글 삭제
//            reviewDAO.deletByMemberId(memberId); // 후기 게시판 게시글 삭제
//            memberDAO.deleteById(memberId); // 회원 삭제
//        });

//        리스트의 길이 만큼
        for (String id : memberIds) {
            log.info("빠른 for문 들어옴?");
            Long memberId = Long.valueOf(id);

            List<BoardDTO> boards = boardDAO.findByMemberId(memberId);
            for (BoardDTO board : boards) { // 작성한 자유 게시글 수 만큼
                log.info("2번째 빠른 for문 들어옴?");
                Long boardId = board.getBoardId();
                boardFileDAO.deleteByBoardId(boardId); // 자유 게시글 파일 삭제

                log.info("게시글 파일 얘는 삭제됨?");

                replyDAO.deleteByBoardId(boardId); // 내가 작성한 자유 게시글에 댓글 삭제
                log.info("게시글에 댓글 얘는 삭제됨?");
            }
            log.info("안쪽 빠른 for문은 끝남?");
            replyDAO.deleteByMemberId(memberId); // 내가 작성한 댓글 삭제
            boardDAO.deleteByMemberId(memberId); // 자유 게시글 삭제
            reviewDAO.deleteByMemberId(memberId); // 후기 게시글 삭제
            memberDAO.deleteById(memberId); // 회원 삭제

            log.info("메소드 완료는 됨?");
        }
    }

}