package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDAO {
    private final BoardMapper boardMapper;

    /* 어드민 게시글 목록 */
    public List<BoardDTO> findAllAdmin(Criteria criteria) {
        return boardMapper.selectAllAdmin(criteria);
    }

    /* 어드민 게시글 총 개수 */
    public Integer findCountAllAdmin() {
        return boardMapper.selectCountAllAdmin();
    }

    /* 어드민 게시글 삭제 */
    public void deleteById(Long boardId) {
        boardMapper.deleteAdmin(boardId);
    }

    /* 관리자 게시글 목록*/
    public List<BoardDTO> findAll() {
        return boardMapper.selectAll();
    }

    public List<BoardDTO> findFreeAll() {
        return boardMapper.selectAll();
    }

}
