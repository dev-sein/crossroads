package com.crossroads.app.controller;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.Standards;
import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MypageController {
    private final MemberService memberService;
    private final ReviewBoardService reviewBoardService;
    private final ApplyService applyService;
    private final FreeBoardService freeBoardService;
    private final ReplyService replyService;
    private final PointService pointService;

    /*마이페이지 메인*/
    @GetMapping("/my-main")
    public String mypageMain(Long memberId, Model model) {
        model.addAttribute("member", memberService.getMemberInfo(1L));
        return "mypage/my-main";
    }

    /*마이페이지 프로필 조회*/
    @GetMapping("/my-info")
    public String myInfoSelect(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.setAttribute("memberId", 1L);
        model.addAttribute("member", memberService.getMemberInfo(1L));
        log.info("들어옴");
        log.info(model.addAttribute("member", memberService.getMemberInfo(1L)).toString());
        return "mypage/my-info";
    }

    /*마이프로필 수정*/
    @PostMapping("/my-info")
    @Transactional(rollbackFor = Exception.class)
    public RedirectView myInfoUpdate(HttpServletRequest request, MemberVO memberVO) {
        log.info("들어옴");
        Long memberId = 1L;
        memberVO = memberService.getMemberInfo(memberId);

        String memberName = request.getParameter("memberName");
        String memberPhone = request.getParameter("memberPhone");
        String memberEmail = request.getParameter("memberEmail");

        memberVO.setMemberName(memberName);
        memberVO.setMemberPhone(memberPhone);
        memberVO.setMemberEmail(memberEmail);

        memberService.modify(memberVO);

        return new RedirectView("my-info");
    }

    /*마이페이지 비밀번호 확인*/
    @GetMapping("/my-password-check")
    public String myPasswordCheckView() {
        return "mypage/my-password-check";
    }

    /*마이페이지 비밀번호 확인*/
    @PostMapping("/my-password-check")
    public RedirectView myPasswordCheck(String memberPassword, HttpServletRequest request) {
        HttpSession session = request.getSession();
//        Long password = memberService.getPassword(memberPassword);
//        log.info(password.toString());
        session.setAttribute("memberId", 2L);
        if ((Long) session.getAttribute("memberId") == memberService.getPassword((Long) session.getAttribute("memberId"), memberPassword)) {
            log.info(session.getAttribute("memberId").toString());
            return new RedirectView("my-password-change");
        }
        return new RedirectView("my-password-check");
    }

    /*마이페이지 비밀번호 변경*/
    @GetMapping("/my-password-change")
    public String myPasswordChangeView() {
        return "mypage/my-password-change";
    }

    /*마이페이지 비밀번호 변경*/
    @PostMapping("/my-password-change")
    @Transactional(rollbackFor = Exception.class)
    public RedirectView myPasswordChange(String memberPassword, HttpSession session) {
        log.info(memberPassword);
        session.setAttribute("memberId", 6L);
        memberService.modifyPasswordMy(6L, memberPassword);
        return new RedirectView("my-main");
    }

    /*마이페이지 연수신청 조회*/
    @GetMapping("my-apply")
    public String showListMyApply(Model model, HttpSession session, Standards standards) {
        session.setAttribute("memberId", 1L);

        Long memberId = (Long) session.getAttribute("memberId");

        model.addAttribute("member", memberService.getMemberInfo(memberId));
        model.addAttribute("applyVO", applyService.getApply(memberId, standards));
        model.addAttribute("applyCountAll", applyService.getApplyCount(memberId, null));
        model.addAttribute("applyCountReady", applyService.getApplyCount(memberId, "0"));
        model.addAttribute("applyCountIng", applyService.getApplyCount(memberId, "1"));
        model.addAttribute("applyCountFinish", applyService.getApplyCount(memberId, "2"));
//        session.setAttribute("memberId", 1L);
        model.addAttribute("member", memberService.getMemberInfo(1L));
        return "mypage/my-apply";
    }

    /*마이페이지 포인트 조회*/
    @GetMapping("/my-point")
    public String showListMyPoint(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

//        session.setAttribute("memberId", 1L);
        model.addAttribute("member", memberService.getMemberInfo(1L));
        model.addAttribute("point", pointService.getListMyPoint(1L));
        return "mypage/my-point";
    }

    /*마이페이지 후기 전체 조회*/
    @GetMapping("/my-review")
    public String showListMyReview(Model model, HttpServletRequest request, Standards standards) throws Exception {

        HttpSession session = request.getSession();
        session.setAttribute("memberId", 1L);

        model.addAttribute("member", memberService.getMemberInfo(1L));
        model.addAttribute("review", reviewBoardService.getListMy(1L, standards));
        log.info(model.addAttribute("review", reviewBoardService.getListMy(1L, standards)).toString());
        return "mypage/my-review";
    }

    /*마이페이지 내가 쓴 게시글 조회*/
    @GetMapping("/my-board")
    //Controller에서 Standards는 모델 객체에 안담아도 전달 가능하다. standards key값
    public String showListMyBoard(Model model, HttpServletRequest request, Standards standards, Long memberId) {
        //외부에서 standard 받음, IOC컨테이너에 기본생성자를 통해 객체화가 되어 있는 객체의 주소가 있음
        //외부에서 page를 전달받음. setter를 사용해서 standard에 저장되어 있는 page값을 전달받은 page=>3으로 변경
        //standard가 getListMyBoard로 전달됨(service로 이동)
        HttpSession session = request.getSession();

        session.setAttribute("memberId", 1L);
        model.addAttribute("member", memberService.getMemberInfo(1L));
        model.addAttribute("board", freeBoardService.getListMyBoard(1L, standards));
        log.info(model.addAttribute("member", memberService.getMemberInfo(1L)).toString());
        log.info(model.addAttribute("board", freeBoardService.getListMyBoard(1L, standards)).toString());
        log.info(standards.toString());
        return "mypage/my-board";
    }

    /*마이페이지 내가 쓴 댓글 목록*/
    @GetMapping("/my-reply")
    public String showListMyReply(Model model, HttpServletRequest request, Standards standards) {
        HttpSession session = request.getSession();
        //session.setAttribute("memberId", 1L);
        model.addAttribute("member", memberService.getMemberInfo(1L));
        model.addAttribute("reply", replyService.getListMyReply(1L, standards));
        return "mypage/my-reply";
    }

    /*마이페이지 파일 업로드*/
    @PostMapping("upload")
    @ResponseBody
    public List<String> upload(@RequestParam("file") List<MultipartFile> multipartFiles) throws IOException {
        List<String> uuids = new ArrayList<>();
        String path = "C:/upload/" + getPath();
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        for (int i = 0; i < multipartFiles.size(); i++) {
            uuids.add(UUID.randomUUID().toString());
            multipartFiles.get(i).transferTo(new File(path, uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));

            if (multipartFiles.get(i).getContentType().startsWith("image")) {
                FileOutputStream out = new FileOutputStream(new File(path, "t_" + uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));
                Thumbnailator.createThumbnail(multipartFiles.get(i).getInputStream(), out, 100, 100);
                out.close();
            }
        }
        return uuids;
    }

    /*마이페이지 파일 저장*/
    @PostMapping("save-profile")
    @ResponseBody
    public void save(@RequestBody List<MemberVO> files) {
        files.forEach(file -> memberService.modifyProfile(file));
    }

    /*마이페이지 파일 불러오기*/
    @GetMapping("/display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload", fileName));
    }

    /*현재 날짜 경로 구하기*/
    private String getPath() { return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")); }

    @GetMapping("/my-password-check-out")
    public String myPasswordCheckOut(){
        return "/mypage/my-withdraw";
    }

    /*회원탈퇴 시 비밀번호 확인*/
    @PostMapping("/my-password-check-out")
    @ResponseBody
    public boolean myPasswordCheckOut(String memberPassword, HttpSession session) {
//        Long password = memberService.getPassword(memberPassword);
        session.setAttribute("memberId", 9L);
        Long memberId = (Long) session.getAttribute("memberId");
        if (memberId == memberService.getPassword(memberId, memberPassword)) {
            return true;
        }
        return false;
    }

    /*마이페이지 회원탈퇴*/
    @GetMapping("my-withdraw")
    public String withdraw() {
        return "/mypage/my-withdraw";
    }

    /*마이페이지 회원탈퇴 동의*/
    @GetMapping("my-withdraw-agree")
    public String withdrawAgree() { return "/mypage/my-withdraw-agree"; }

    /*마이페이지 회원탈퇴 확인*/
    @PostMapping("my-withdraw-confirm")
    public String withdrawConfirm(HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        session.invalidate();
        memberService.remove(memberId);
        return "/mypage/my-withdraw-confirm";
    }

    /*마이페이지 로그아웃*/
    @GetMapping("/my-logout")
    public String myLogout(HttpServletRequest request) {
        System.out.println("logout - 진입");
        //세션 끊기
        HttpSession session = request.getSession();
        session.invalidate();
        return "main/main";
    }

    /*프로필 사진 삭제*/
    @PostMapping("delete-profile")
    @ResponseBody
    public void deleteProfile(MemberVO memberVO, HttpSession session) {
        session.setAttribute("memberId", 1L);
        Long memberId = (Long)session.getAttribute("memberId");

        memberVO.setMemberId(memberId);
        memberVO.setMemberFileOriginalName(null);
        memberVO.setMemberFileUuid(null);
        memberVO.setMemberFilePath(null);
        memberVO.setMemberFileSize(null);
        memberVO.setMemberFileType(false);
        memberService.modifyProfile(memberVO);
    }

    /*마이페이지 리뷰 삭제*/
    @PostMapping("delete-review")
    public RedirectView removeMyReview(@RequestParam("reviewId") Long reviewId){
        log.info("들어옴*******************************************************");
        reviewBoardService.deleteReview(reviewId);
        return new RedirectView("/mypage/my-review");
    }
}
