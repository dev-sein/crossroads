package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.ReplyDTO;
import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.dto.Standards;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {
//    게시글 별 댓글 수 조회
    public Integer selectReplyCount(Long boardId);

    //마이페이지 댓글 전체 조회
    public List<ReplyDTO> selectAllMyReply(Long memberId, Standards standards);

    //마이페이지 게시글 페이징 - 전체 개수
    public int selectTotalMy();

//    게시글 별 댓글 삭제
    public void deleteByBoard(Long boardId);

//    회원 별 댓글 삭제
    public void deletByMember(Long memberId);

}
