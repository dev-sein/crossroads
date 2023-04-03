package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.dto.*;
import com.crossroads.app.domain.vo.ReviewVO;
import com.crossroads.app.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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

//    마이페이지 게시글 페이징 전체 개수
    public int findCountAllMy(){
        return reviewMapper.selectTotalMy();
    }

    
    /* 관리자 게시글 목록 */
    public List<ReviewDTO> findAllAdmin(Criteria criteria, String keyword) {
        return reviewMapper.selectAllAdmin(criteria, keyword);
    }

    /* 관리자 게시글 총 개수 */
    public Integer findCountAllAdmin(String keyword) {
        return reviewMapper.selectCountAllAdmin(keyword);
    }

    /* 관리자 게시글 삭제 */
    public void deleteByIdAdmin(Long reviewId) {
        reviewMapper.deleteAdmin(reviewId);
    }

    //    회원별 게시글 삭제
    public void deleteByMemberId(Long memberId){
        reviewMapper.deleteByMemberId(memberId);
    };

}

