package com.crossroads.app.service;

import com.crossroads.app.domain.dao.BoardFileDAO;
import com.crossroads.app.domain.vo.BoardFileVO;
import com.crossroads.app.domain.vo.MemberFileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardFileService implements FileService{
    private final BoardFileDAO boardFileDAO;

    @Override
    public void fileRegister(MemberFileVO memberFileVO) {

    }

    /* 게시글 별 file 조회 */
    public List<BoardFileVO> getFile(Long boardId) {
        return boardFileDAO.findById(boardId);
    }



}
