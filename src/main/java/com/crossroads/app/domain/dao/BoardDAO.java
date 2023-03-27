package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDAO {
    private final BoardMapper boardMapper;

    /* 어드민 게시글 목록 */
    public List<BoardDTO> findAllAdmin() {
        return boardMapper.selectAllAdmin();
    }

    /* 어드민 게시글 삭제 */
    public void deleteById(Long boardId){
        boardMapper.deleteAdmin(boardId);
    };
}
