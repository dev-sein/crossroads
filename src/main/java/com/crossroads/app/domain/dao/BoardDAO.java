package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.domain.dto.Standards;
import com.crossroads.app.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDAO {
    private final BoardMapper boardMapper;

    /* 관리자 게시글 목록 */
    public List<BoardDTO> findAllAdmin(Criteria criteria, String keyword) {
        return boardMapper.selectAllAdmin(criteria, keyword);
    }

    /* 관리자 게시글 총 개수 */
    public Integer findCountAllAdmin(String keyword) {
        return boardMapper.selectCountAllAdmin(keyword);
    }

    /* 관리자 게시글 삭제 */
    public void deleteById(Long boardId) {
        boardMapper.deleteAdmin(boardId);
    }

    public List<BoardDTO> findAll() {
        return boardMapper.selectAll();
    }

    public List<BoardDTO> findFreeAll() {
        return boardMapper.selectAll();
    }

    /* 마이페이지 게시글 목록*/
    public List<BoardDTO> findAllMy(Long memberId, Standards standards) { return boardMapper.selectAllMy(memberId, standards); }

    /* 마이페이지 게시글 페이징 전체 개수 */
    public int findCountAllMy(){
        return boardMapper.selectTotalMy();
    }

    /* 상세 보기*/
    public BoardDTO findById(Long boardId) {
        return boardMapper.select(boardId);
    };
}
