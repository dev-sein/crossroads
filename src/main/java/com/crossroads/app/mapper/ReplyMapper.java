package com.crossroads.app.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {
//    게시글 별 댓글 수 조회
    public Integer selectReplyCount(Long boardId);
}
