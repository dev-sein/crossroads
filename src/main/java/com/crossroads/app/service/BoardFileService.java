package com.crossroads.app.service;

import com.crossroads.app.domain.dao.BoardFileDAO;
import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.vo.BoardFileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardFileService {
    private final BoardFileDAO boardFileDAO;

    /* 게시글 별 file 조회 */
    public List<BoardFileVO> getFile(Long boardId) {
        return boardFileDAO.findById(boardId);
    }


    //    파일 추가
    public void write(List<BoardDTO> files){
        files.forEach(file -> boardFileDAO.save(file));
    }

    //    파일 전체 조회
    public List<BoardDTO> getList(Long boardId){
        return boardFileDAO.findByBoardId(boardId);
    }

    //    파일 삭제
    public void remove(Long boardId){
        boardFileDAO.delete(boardId);
    }

    //    전일 등록된 파일 조회
    public List<BoardDTO> getListFromYesterday(){
        return boardFileDAO.findByFilePath();
    }


}
