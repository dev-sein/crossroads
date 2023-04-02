package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.dto.Standards;
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
    public List<ReviewDTO> findAllMy(Long memberId, Standards standards) {
        return reviewMapper.selectAll(memberId, standards);
    }

    //    후기 목록 조회
    public List<ReviewDTO> findAllReview() {
        return reviewMapper.selectReviewAll();
    }
    // 페이징처리
    public List<ReviewDTO> findReviewByPage(int start, int end) {
        return reviewMapper.selectReviewByPage(start, end);
    }

    // 후기 작성
    public void save(ReviewDTO reviewDTO) {
        reviewMapper.insert(reviewDTO);
    }

    // 후기 수정
    public void setReviewDTO(ReviewDTO reviewDTO) {
        reviewMapper.update(reviewDTO);
    }

    //    후기 삭제
    public void deleteById(Long reviewId) {
        reviewMapper.delete(reviewId);
    }

    //  후기 조회
    public ReviewVO findById(Long reviewId){
        return reviewMapper.select(reviewId);
    }

//    마이페이지 게시글 페이징 전체 개수
    public int findCountAllMy(){
        return reviewMapper.selectTotalMy();
    }


}

