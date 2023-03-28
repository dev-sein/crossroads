package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.dto.ReplyDTO;
import com.crossroads.app.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {
    private final ReplyMapper replyMapper;

//    게시글 별 댓글 수 조회
    public Integer findReplyCount(Long boardId) {
        return replyMapper.selectReplyCount(boardId);
    }

}
