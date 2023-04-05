package com.crossroads.app.controller;


import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.vo.BoardFileVO;
import com.crossroads.app.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards/*")
public class BoardController {
    private BoardService boardService;

    //    게시판 상세 목록
    @GetMapping("board-detail")
    public String boardDetail() {
        return "board/board-detail";
    }

    //    게시판 목록 전체조회
    @GetMapping("board-list")
    public String selectAllBoards(Model model) {
        List<BoardDTO> boards = boardService.getListBoard();
        model.addAttribute("boards", boards);
        return "board/board-list";
    }

    // 게시판 작성하기
    @GetMapping("board-write")
    public String boardWrite(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("boardDTO", new BoardDTO());
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("memberId", 1l);
        return "board/board-write";
    }

    // 게시판 작성(저장하기)
    @PostMapping("board-save")
    public String saveBoard(@ModelAttribute("boardDTO") BoardDTO boardDTO, @RequestParam("file") List<MultipartFile> multipartFiles, @RequestParam("fileUUIDs") List<String> fileUUIDs, HttpServletRequest request) {
        // 세션에서 회원 정보 가져오기 (임시로 사용)
        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute("memberId");
        boardDTO.setMemberId(memberId);

        // Set file information in BoardDTO
        List<BoardFileVO> files = new ArrayList<>();
        for (int i = 0; i < multipartFiles.size(); i++) {
            MultipartFile multipartFile = multipartFiles.get(i);
            BoardFileVO boardFileVO = new BoardFileVO();
            boardFileVO.setFileUUID(fileUUIDs.get(i));
            boardFileVO.setOriginalFileName(multipartFile.getOriginalFilename());
            boardFileVO.setFileSize(multipartFile.getSize());
            boardFileVO.setContentType(multipartFile.getContentType());
            files.add(boardFileVO);
        }
        boardDTO.setFiles(files);

        boardService.save(boardDTO);
        return "redirect:/boards/board-list";
    }




}
