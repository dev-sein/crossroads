package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.dto.ReviewCriteria;
import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.vo.ReviewVO;
import com.crossroads.app.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ReviewDAO {
    private final ReviewMapper reviewMapper;

    //    마이페이지 후기 전체 조회
    public List<ReviewDTO> findAllMy(Long memberId) {
        return reviewMapper.selectAll(memberId);
    }

    //    후기 목록 조회
    public List<ReviewDTO> findAllReview() {
        return reviewMapper.selectReviewAll();
    }

    public List<ReviewDTO> getListReview(ReviewCriteria criteria) {
        return reviewMapper.getListReview(criteria);
    }
    public int getTotalCount() {
        return reviewMapper.getTotalCount();
    }

    // 후기 작성
    public void save(ReviewDTO reviewDTO) {
        reviewMapper.insert(reviewDTO);
    }

    // 후기 수정
    public void updateReview(ReviewDTO reviewDTO) {
        reviewMapper.update(reviewDTO);
    }

    public ReviewVO getReview(Long reviewId) {
        return reviewMapper.select(reviewId);
    }

    //    후기 삭제
    public void deleteById(Long reviewId) {
        reviewMapper.delete(reviewId);
    }

    //  후기 조회
    public ReviewVO findById(Long reviewId){
        return reviewMapper.select(reviewId);
    }


}

