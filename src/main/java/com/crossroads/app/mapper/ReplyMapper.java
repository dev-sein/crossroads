package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {
//    게시글 별 댓글 수 조회
    public Integer selectReplyCount(Long boardId);

    //마이페이지 댓글 전체 조회
    public List<ReplyDTO> selectAllMyReply(Long memberId, Standards standards);

    //마이페이지 게시글 페이징 - 전체 개수
    public int selectTotalMy();

//    개별 댓글 삭제
    public void delete(Long replyId);

//    게시글 별 댓글 삭제
    public void deleteByBoardId(Long boardId);

//    회원 별 댓글 삭제
    public void deleteByMemberId(Long memberId);

    //    관리자 댓글 전체 조회
    public List<ReplyDTO> selectAllAdmin(@Param("criteria") Criteria criteria, @Param("keyword") String keyword);

    //    관리자 댓글 총 개수
    public Integer selectCountAllAdmin(@Param("keyword") String keyword);

}
