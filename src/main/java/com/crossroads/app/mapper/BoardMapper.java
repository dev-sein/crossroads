package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
//    관리자 전체 조회
    public List<BoardDTO> selectAllAdmin();

//    관리자 게시글 삭제
    public void deleteAdmin(Long boardId);

    //    게시글 전체 조회
    public List<BoardDTO> selectAll();
}
