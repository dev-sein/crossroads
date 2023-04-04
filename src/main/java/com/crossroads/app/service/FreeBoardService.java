package com.crossroads.app.service;

import com.crossroads.app.domain.dao.BoardDAO;
import com.crossroads.app.domain.dao.BoardFileDAO;
import com.crossroads.app.domain.dao.ReplyDAO;
import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.PageDTO;
import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.domain.dto.Standards;
import com.crossroads.app.domain.dto.ReviewCriteria;
import com.crossroads.app.domain.vo.BoardFileVO;
import com.crossroads.app.domain.vo.ReviewVO;
import com.crossroads.app.mapper.BoardFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Qualifier("board")
@RequiredArgsConstructor
public class FreeBoardService implements BoardService {
    private final BoardDAO boardDAO;
    private final ReplyDAO replyDAO;
    private final BoardFileDAO boardFileDAO;

    @Override
    public Map<String, Object> getListAdmin(Map<String, Object> requestData, Criteria criteria) {
        Map<String, Object> result = new HashMap<String, Object>();

        String keyword = (String) requestData.get("keyword");
        int page = (int) requestData.get("page");

        if (page == 0) {
            page = 1;
        }
        criteria = criteria.create(page, 6);

        List<BoardDTO> boards = boardDAO.findAllAdmin(criteria, keyword);

//        게시글 별 댓글 수를 boards에 추가
        boards.stream().forEach(board -> board.setReplyCount(replyDAO.findReplyCount(board.getBoardId())));

        result.put("boards", boards);
        result.put("pagination", new PageDTO().createPageDTO(criteria, boardDAO.findCountAllAdmin(keyword)));

        return result;
    }


    @Override
    public Integer getCountAdmin(String keyword) {
        return boardDAO.findCountAllAdmin(keyword);
    }


//    게시글 삭제
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> boardIds) {
        boardIds.stream().map(boardId -> Long.valueOf(boardId)).forEach(boardId -> {
            replyDAO.deleteByBoardId(boardId); // 댓글 삭제
            boardDAO.deleteById(boardId); // 게시글 삭제
        });
    }

//    게시글 목록
    @Override
    public List<BoardDTO> getList() {
        List<BoardDTO> boards = boardDAO.findFreeAll();
        return boards;
    }

    @Override
    public List<ReviewDTO> getListReview() {
        return null;
    }

    @Override
    public List<ReviewDTO> getReviewsByPage(int start, int end) {
        return null;
    }

    @Override
    public List<ReviewDTO> getListReview(ReviewCriteria criteria) {
        return null;
    }

    @Override
    public int getTotalCount() {
        return 0;
    }

    @Override
    public void save(ReviewDTO reviewDTO) {

    }

    @Override
    public void updateReview(ReviewDTO reviewDTO) {

    }

    @Override
    public ReviewVO getReview(Long reviewId) {
        return null;
    }

    @Override
    public List<BoardDTO> getListMyBoard(Long memberId) {
        return null;
    }

//    상세 보기
    @Override
    public Map<String, Object> getBoardAdmin(Long boardId) {
        Map<String, Object> result = new HashMap<>();

        BoardDTO boardDTO = boardDAO.findById(boardId);
        List<BoardFileVO> boardFiles = boardFileDAO.findById(boardId);


        result.put("board", boardDTO);
        result.put("files", boardFiles);

        return result;
    }

    //    마이페이지 리뷰 목록
    @Override
    public List<ReviewDTO> getListMy(Long memberId, Standards standards) {
        return null;
    }

    //    마이페이지 게시글 목록
    @Override
    public List<BoardDTO> getListMyBoard(Long memberId, Standards standards) {//주소의 변동이 없음
        //getTotalMy
        standards.create(getTotalMy());
        return boardDAO.findAllMy(memberId, standards);
    }

    @Override
    public int getTotalMy() {
        return boardDAO.findCountAllMy();
    }


}