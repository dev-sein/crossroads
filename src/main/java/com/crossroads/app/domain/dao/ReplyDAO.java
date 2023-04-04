package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.dto.*;
import com.crossroads.app.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {
    private final ReplyMapper replyMapper;

//    게시글별 댓글 수 조회
    public Integer findReplyCount(Long boardId) {
        return replyMapper.selectReplyCount(boardId);
    }


//    마이페이지 후기 전체 조회
    public List<ReplyDTO> findAllMyReply(Long memberId, Standards standards){
        return replyMapper.selectAllMyReply(memberId, standards);
    }

//    마이페이지 게시글 페이징 전체 개수
    public int findCountAllMy(){
        return replyMapper.selectTotalMy();
    }

//    개별 댓글 삭제
    public void deleteById(Long replyId){
        replyMapper.delete(replyId);
    }

//    게시글별 댓글 삭제
    public void deleteByBoardId(Long boardId){
        replyMapper.deleteByBoardId(boardId);
    };

//    회원별 댓글 삭제
    public void deleteByMemberId(Long memberId){
        replyMapper.deleteByMemberId(memberId);
    };

    /* 관리자 댓글 목록 */
    public List<ReplyDTO> findAllAdmin(Criteria criteria, String keyword) {
        return replyMapper.selectAllAdmin(criteria, keyword);
    }

    /* 관리자 댓글 총 개수 */
    public Integer findCountAllAdmin(String keyword) {
        return replyMapper.selectCountAllAdmin(keyword);
    }


}
