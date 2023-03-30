package com.crossroads.app.service;

import com.crossroads.app.domain.dao.BoardDAO;
import com.crossroads.app.domain.dao.ReplyDAO;
import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.dto.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("board")
@RequiredArgsConstructor
public class FreeBoardService implements BoardService {
    private final BoardDAO boardDAO;
    private final ReplyDAO replyDAO;

    @Override
    public List<BoardDTO> getListAdmin(Criteria criteria) {
        List<BoardDTO> boards = boardDAO.findAllAdmin(criteria);

//        게시글 별 댓글 수를 boards에 추가
        boards.stream().forEach(board -> board.setReplyCount(replyDAO.findReplyCount(board.getBoardId())));

        return boards;
    }


    @Override
    public Integer getCountAdmin() {
        return boardDAO.findCountAllAdmin();
    }


    @Override
    public void remove(List<String> boardIds) {
        boardIds.stream().map(boardId -> Long.parseLong(boardId)).forEach(boardDAO::deleteById);
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
    public void save(ReviewDTO reviewDTO) {

    }

    //    마이페이지 리뷰 목록
    @Override
    public List<ReviewDTO> getListMy(Long memberId) {
        return null;
    }

//    마이페이지 게시판 목록
    @Override
    public List<BoardDTO> getListMyBoard(Long memberId) {
        return boardDAO.findAllMy(memberId);
    }


}