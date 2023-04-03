package com.crossroads.app.service;

import com.crossroads.app.domain.dao.ReviewDAO;
import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.ReviewCriteria;
import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.domain.dto.Standards;
import com.crossroads.app.domain.vo.ReviewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Qualifier("board")
@RequiredArgsConstructor
public class ReviewBoardService implements BoardService {
    private final ReviewDAO reviewDAO;

    @Override
    public Map<String, Object> getListAdmin(Map<String, Object> requestData, Criteria criteria) {
        return null;
    }

    @Override
    public Integer getCountAdmin(String keyword) {
        return null;
    }

    //마이페이지 후기 목록
    @Override
    public List<ReviewDTO> getListMy(Long memberId, Standards standards) {
        if(standards.getPage() == 0 ) {
            standards.create(1, 5, 5, getTotalMy());
        } else {
            standards.create(standards.getPage(), 5, 5, getTotalMy());
        }
        return reviewDAO.findAllMy(memberId, standards);
    }

    @Override
    public void remove(List<String> boardIds) {
//        boardIds.stream().map(boardId -> new Long(boardId)).forEach(reviewDAO::deleteById);
        boardIds.stream().map(boardId -> Long.valueOf(boardId)).forEach(reviewDAO::deleteById);
    }

    public List<BoardDTO> getListAdmin() {
        return null;
    }

    @Override
    public List<BoardDTO> getList() {
        return null;
    }

    //후기 게시판 목록
    public List<ReviewDTO> getListReview() {
        return reviewDAO.findAllReview();
    }

    @Override
    public List<ReviewDTO> getReviewsByPage(int start, int end) {
        return null;
    }

    @Override
    public List<ReviewDTO> getListReview(ReviewCriteria criteria) {
        return reviewDAO.getListReview(criteria);
    }

    @Override
    public int getTotalCount() {
        return reviewDAO.getTotalCount();
    }

    //후기 작성
    public void save(ReviewDTO reviewDTO) {
        reviewDAO.save(reviewDTO);
    }

    //후기 수정
    @Override
    public void updateReview(ReviewDTO reviewDTO) {
        reviewDAO.updateReview(reviewDTO);
    }

    @Override
    public ReviewVO getReview(Long reviewId) {
        return null;
    }

    @Override
    public List<BoardDTO> getListMyBoard(Long memberId, Standards standards) { return null; }

    //마이페이지 후기 페이징 전체 개수
    @Override
    public int getTotalMy(){
        return reviewDAO.findCountAllMy();
    }


    //후기 조회
    public ReviewVO getReviewById(Long reviewId) {
        return reviewDAO.findById(reviewId);
    }

    //마이페이지 게시판 목록
    @Override
    public List<BoardDTO> getListMyBoard(Long memberId) {
        return null;
    }

//    상세보기
    @Override
    public Map<String, Object> getBoardAdmin(Long boardId) {
        return null;
    }


}

