package com.crossroads.app.service;

import com.crossroads.app.domain.dao.*;
import com.crossroads.app.domain.dto.ApplyDTO;
import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.domain.dto.PageDTO;
import com.crossroads.app.domain.vo.ApplyVO;
import com.crossroads.app.domain.vo.MailTO;
import com.crossroads.app.domain.vo.MemberVO;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.lang.reflect.Member;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private final ApplyDAO applyDAO;

    private final PointService pointService;


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
    public Long getRandomKey(String memberEmail) {
        return memberDAO.findRandomKey(memberEmail);
    }

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
    public void modifyPassword(String memberEmail, String memberPassword) {
        memberDAO.setPassword(memberEmail, memberPassword);
    }

    //마이페이지 비밀번호 확인
    public Long getPassword(Long memberId, String memberPassword) { return memberDAO.findByPasswordMy(memberId, memberPassword); }

    //마이페이지 비밀번호 변경
    public Long modifyPasswordMy(Long memberId, String memberPassword) {
        return memberDAO.setPasswordMy(memberId, memberPassword);
    }

    //랜덤 난수 생성
    public Long makeRandomKey() {
        Random rand = new Random();
        long randomkey = rand.nextLong() + 1;
        return randomkey;
    }

    //랜덤키 삽입
    public void setRandomKey(String memberEmail, Long memberRandomKey) {
        memberDAO.setRandomKey(memberEmail, memberRandomKey);
    }

    ;

    //이메일로 VO 찾기
    public MemberVO getByEmail(String memberEmail) {
        return memberDAO.findByEmail(memberEmail);
    }

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
    public Integer getCountAdmin() {
        return memberDAO.findCountAllAdmin(null);
    }

    //관리자 회원 삭제
    @Transactional(rollbackFor = Exception.class)
    public void removeAdmin(List<Long> memberIds) {

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
        for (Long memberId : memberIds) {
            List<BoardDTO> boards = boardDAO.findByMemberId(memberId);
            MemberVO memberVO = getMemberInfo(memberId);
            List<ApplyVO> applyVOs = applyDAO.findByStarterMemberId(memberId, null); // 초보자 회원의 연수 신청 내역 목록

            for (BoardDTO board : boards) { // 작성한 자유 게시글 수 만큼
                Long boardId = board.getBoardId();

                boardFileDAO.deleteByBoardId(boardId); // 자유 게시글 파일 삭제
                replyDAO.deleteByBoardId(boardId); // 내가 작성한 자유 게시글에 댓글 삭제
            }

//            초보자일 때
            if(memberVO.getMemberType() == "0") {
                // 해당 초보자의 신청 내역 목록에서 아이디 하나씩을 뽑아 내역 삭제
                applyVOs.stream().map(ApplyVO::getApplyId).forEach(applyDAO::deleteById);
            } else { // 베테랑일 때
                for (ApplyVO applyVO: applyVOs) {
                    // String to LocalDateTime
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime strToLocalDateTime = LocalDateTime.parse(applyVO.getApplyDate(), format);
                    boolean status = strToLocalDateTime.isBefore(LocalDateTime.now());
                    // 수락을 한 상태
                    if(applyVO.getApplyStatus() == "1" && status) {
                        Map<String, Object> info = new HashMap<>();

                        info.put("memberId", null);
                        info.put("applyId", applyVO.getApplyId());

                        applyDAO.setApplyStatus(applyVO.getApplyId()); // 신청 내역 상태값 변경
                        applyDAO.setVeteranId(info); // 베테랑 아이디 변경
                        applyDAO.deleteById(applyVO.getApplyId()); // 내역 삭제
                    }
                }
            }


            replyDAO.deleteByMemberId(memberId); // 내가 작성한 댓글 삭제
            boardDAO.deleteByMemberId(memberId); // 자유 게시글 삭제
            reviewDAO.deleteByMemberId(memberId); // 후기 게시글 삭제
            memberDAO.deleteById(memberId); // 회원 삭제
        }
    }

    public String getKaKaoAccessToken(String code, String type){
        String access_Token="";
        String refresh_Token ="";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=ff10441318cc0a2c7e2aa44285fa956c"); // TODO REST_API_KEY 입력

//            회원가입에서 접근했을 때
            if(type.equals("join")) {
                sb.append("&redirect_uri=http://localhost:10000/member/kakao"); // TODO 인가코드 받은 redirect_uri 입력
            } else if (type.equals("login")) {
//            로그인에서 접근했을 때
                sb.append("&redirect_uri=http://localhost:10000/member/kakao-login"); // TODO 인가코드 받은 redirect_uri 입력
            } else if (type.equals("mobilejoin")) {
//            모바일 회원가입 접근했을 때
                sb.append("&redirect_uri=http://localhost:10000/applies/kakao"); // TODO 인가코드 받은 redirect_uri 입력
            } else if (type.equals("mobilelogin")) {
//            모바일 로그인에서 접근했을 때
                sb.append("&redirect_uri=http://localhost:10000/applies/kakao-login"); // TODO 인가코드 받은 redirect_uri 입력
            }

            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            log.info("access_token : " + access_Token);
            log.info("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }


    public MemberVO getKakaoInfo(String token) throws Exception {
        MemberVO kakaoInfo = new MemberVO();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("response body : " + result);

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            int id = element.getAsJsonObject().get("id").getAsInt();
            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            String memberEmail = "";
            if(hasEmail){
                memberEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            }

            log.info("id : " + id);
            log.info("email : " + memberEmail);
            kakaoInfo.setMemberEmail(memberEmail);

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return kakaoInfo;
    }

    public void logoutKakao(String token) {
        String reqURL = "https://kapi.kakao.com/v1/user/logout";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Authorization", "Bearer " + token);
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);

            if (responseCode == 400)
                throw new RuntimeException("카카오 로그아웃 도중 오류 발생");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String br_line = "";
            String result = "";
            while ((br_line = br.readLine()) != null) {
                result += br_line;
            }
            log.info("결과");
            log.info(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}