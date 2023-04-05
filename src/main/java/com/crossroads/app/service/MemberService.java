package com.crossroads.app.service;

import com.crossroads.app.domain.dao.*;
import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.domain.dto.PageDTO;
import com.crossroads.app.domain.vo.MailTO;
import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.mapper.MemberMapper;
import com.google.gson.JsonElement;
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

    //카카오 로그인 //토큰
    public String getKaKaoAccessToken(String code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL); //자바에서 요청하는 코드
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //url 커넥션 객체 얻어옴
            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("&redirect_uri=http://localhost:10000/members/login"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=ff10441318cc0a2c7e2aa44285fa956c"); // TODO REST_API_KEY 입력
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
            //gson -> json 세션
            access_Token = element.getAsJsonObject().get("access_token").getAsString(); //세션을 사용하지 않고 db를 사용
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            log.info("access_token : " + access_Token);
            log.info("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    public void getKakaoInfo(String token) throws Exception {

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

            //            if("join"){
//                sb.append("&redirect_uri=http://localhost:10000/members/join"); // TODO 인가코드 받은 redirect_uri 입력
//            }else("login"){
//                sb.append("&redirect_uri=http://localhost:10000/members/login"); // TODO 인가코드 받은 redirect_uri 입력
//            }


            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("response body : " + result);

            //Gson 라이브러리로 JSON파싱 //내 정보 받아오기
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            int id = element.getAsJsonObject().get("id").getAsInt();
            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            String email = "";
            if (hasEmail) { //email 정보
                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            }

            log.info("id : " + id);
            log.info("email : " + email);

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
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