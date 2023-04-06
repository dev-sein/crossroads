package com.crossroads.app.service;

import com.crossroads.app.domain.dao.ReviewDAO;
import com.crossroads.app.domain.dto.*;
import com.crossroads.app.domain.vo.BoardFileVO;
import com.crossroads.app.domain.vo.ReviewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Qualifier("board")
@RequiredArgsConstructor
public class ReviewBoardService implements BoardService {
    private final ReviewDAO reviewDAO;

//    관리자 후기 게시판 목록
    @Override
    public Map<String, Object> getListAdmin(Map<String, Object> requestData, Criteria criteria) {
        Map<String, Object> result = new HashMap<String, Object>();

        String keyword = (String) requestData.get("keyword");
        int page = (int) requestData.get("page");

        if (page == 0) {
            page = 1;
        }
        criteria = criteria.create(page, 6);

        List<ReviewDTO> reviews = reviewDAO.findAllAdmin(criteria, keyword);

        result.put("reviews", reviews);
        result.put("pagination", new PageDTO().createPageDTO(criteria, reviewDAO.findCountAllAdmin(keyword)));

        return result;
    }
    
//    관리자 후기 게시판 상세보기
    @Override
    public Map<String, Object> getBoardAdmin(Long reviewId) {
        Map<String, Object> result = new HashMap<>();

        ReviewVO reviewVO = reviewDAO.findById(reviewId);

        result.put("review", reviewVO);

        return result;
    }

    @Override
    public List<BoardDTO> getListBoard() {
        return null;
    }

    @Override
    public void save(BoardDTO boardDTO) {

    }

    @Override
    public void saveBoard(BoardDTO boardDTO, List<MultipartFile> files) throws IOException {

    }

    //    관리자 후기 게시판 삭제
    @Override
    public void remove(List<String> reviewIds) {
        reviewIds.stream().map(reviewId -> Long.valueOf(reviewId)).forEach(reviewDAO::deleteByIdAdmin);
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
        return reviewDAO.getReview(reviewId);
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

    //후기 삭제
    public void deleteReview(Long reviewId) {
        reviewDAO.deleteReviewById(reviewId);
    }




}

