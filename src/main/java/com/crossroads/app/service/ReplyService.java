package com.crossroads.app.service;

import com.crossroads.app.domain.dao.ApplyDAO;
import com.crossroads.app.domain.dao.ReplyDAO;
import com.crossroads.app.domain.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void removeByBoardId(Long boardId){
        replyDAO.deleteByBoardId(boardId);
    };

//    회원 별 댓글 삭제
    public void removeByMemberId(Long memberId){
        replyDAO.deleteByMemberId(memberId);
    };

    /* 관리자 댓글 목록 */
    public Map<String, Object> getListAdmin(Map<String, Object> requestData, Criteria criteria) {
        Map<String, Object> result = new HashMap<String, Object>();

        String keyword = (String) requestData.get("keyword");
        int page = (int) requestData.get("page");

        log.info("===============================");
        log.info("되냐?");
        log.info(page + "");
        log.info(keyword + "");
        log.info(criteria.toString());
        log.info("===============================");
        if (page == 0) {
            page = 1;
        }
        criteria = criteria.create(page, 6);

        List<ReplyDTO> replies = replyDAO.findAllAdmin(criteria, keyword);

        log.info("===============================");
        log.info("되냐?");
        log.info(replies.toString());
        log.info("===============================");

        result.put("replies", replies);
        result.put("pagination", new PageDTO().createPageDTO(criteria, getCountAdmin(keyword)));

        return result;
    }

    /* 관리자 댓글 총 개수 */
    public Integer getCountAdmin(String keyword) {
        return replyDAO.findCountAllAdmin(keyword);
    }

    //    개별 댓글 삭제
    public void removeAdmin(List<Long> replyIds){
        replyIds.stream().forEach(replyDAO::deleteById);
    }

}
