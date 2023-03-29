package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.ReplyDTO;
import com.crossroads.app.domain.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {
//    게시글 별 댓글 수 조회
    public Integer selectReplyCount(Long boardId);

    //마이페이지 댓글 전체 조회
    public List<ReplyDTO> selectAllMyReply(Long memberId);
}
