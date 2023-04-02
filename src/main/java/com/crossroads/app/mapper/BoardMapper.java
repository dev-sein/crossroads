package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.Criteria;
<<<<<<< HEAD
import com.crossroads.app.domain.dto.Standards;
=======
import com.crossroads.app.domain.dto.ReviewDTO;
>>>>>>> luks/ver6
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
<<<<<<< HEAD
//    관리자 전체 조회
    public List<BoardDTO> selectAllAdmin(@Param("criteria") Criteria criteria, @Param("keyword") String keyword);

//    관리자 게시글 총 개수
    public Integer selectCountAllAdmin(@Param("keyword") String keyword);
=======
    //    관리자 전체 조회
    public List<BoardDTO> selectAllAdmin(Criteria criteria);

    //    관리자 게시글 총 개수
    public Integer selectCountAllAdmin();
>>>>>>> luks/ver6

    //    관리자 게시글 삭제
    public void deleteAdmin(Long boardId);

    //    게시글 전체 조회
    public List<BoardDTO> selectAll();

<<<<<<< HEAD
//    마이페이지 게시글 전체 조회
    public List<BoardDTO> selectAllMy(Long memberId, Standards standards);

//    마이페이지 게시글 페이징 - 전체 개수
    public int selectTotalMy();
=======
    //    관리자 전체 조회
    public List<BoardDTO> selectAllMy(Long memberId);
>>>>>>> luks/ver6


    //    자유게시판 전체 조회
    public List<ReviewDTO> selectAllBoards();
}
