package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.domain.dto.Standards;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
//    관리자 전체 조회
    public List<BoardDTO> selectAllAdmin(Criteria criteria);

//    관리자 게시글 총 개수
    public Integer selectCountAllAdmin();

//    관리자 게시글 삭제
    public void deleteAdmin(Long boardId);

//    게시글 전체 조회
    public List<BoardDTO> selectAll();

//    마이페이지 게시글 전체 조회
    public List<BoardDTO> selectAllMy(Long memberId, Standards standards);

//    마이페이지 게시글 페이징 - 전체 개수
    public int selectTotalMy();

}
