package com.crossroads.app.service;

import com.crossroads.app.domain.dao.ApplyDAO;
import com.crossroads.app.domain.dao.ReplyDAO;
import com.crossroads.app.domain.dto.ApplyDTO;
import com.crossroads.app.domain.dto.ReplyDTO;
import com.crossroads.app.domain.dto.Standards;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyDAO replyDAO;

//    신청목록 전체조회
    public List<ReplyDTO> getListMyReply(Long memberId, Standards standards) {
        if(standards.getPage() == 0 ) {
            standards.create(1, 5, 5, getTotalMy());
        } else {
            standards.create(standards.getPage(), 5, 5, getTotalMy());
        }

        return replyDAO.findAllMyReply(memberId, standards);
    }

    public int getTotalMy(){
        return replyDAO.findCountAllMy();
    }

//    게시글 별 댓글 삭제
    public void deleteByBoardId(Long boardId){
        replyDAO.deleteByBoardId(boardId);
    };

//    회원 별 댓글 삭제
    public void deletByMemberId(Long memberId){
        replyDAO.deletByMemberId(memberId);
    };

}
