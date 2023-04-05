package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.vo.BoardFileVO;
import com.crossroads.app.mapper.BoardFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardFileDAO {
    private final BoardFileMapper boardFileMapper;

    /*게시글 별 file 조회*/
    public List<BoardFileVO> findById(Long boardId){
        return boardFileMapper.select(boardId);
    };

    /*게시글 별 file 삭제*/
    public void deleteByBoardId(Long boardId) {
        boardFileMapper.deleteByBoardId(boardId);
    }

    //    파일 추가
    public void save(BoardDTO boardDTO){
        boardFileMapper.insert(boardDTO);
    }

    //    파일 전체 조회
    public List<BoardDTO> findByBoardId(Long boardId){
        return boardFileMapper.selectAll(boardId);
    }

    //    파일 삭제
    public void delete(Long boardId){
        boardFileMapper.delete(boardId);
    }

    //    전일 등록된 파일 조회
    public List<BoardDTO> findByFilePath(){
        return boardFileMapper.selectYesterday();
    }


}
