package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.*;
import com.crossroads.app.domain.vo.ReviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    //마이페이지 후기 전체 조회
    public List<ReviewDTO> selectAll(Long memberId, Standards standards);

    //마이페이지 게시글 페이징 - 전체 개수
    public int selectTotalMy();

    //후기 전체 조회
    public List<ReviewDTO> selectReviewAll();

    public List<ReviewDTO> getListReview(ReviewCriteria criteria);
    public int getTotalCount();

    //후기 삭제
    public void delete(Long reviewId);

    //후기 작성
    public void insert(ReviewDTO reviewDTO);

    //후기 수정
    public void update(ReviewDTO reviewDTO);

    // 후기 조회
    public ReviewVO select(Long reviewId);

    //    관리자 전체 조회
    public List<ReviewDTO> selectAllAdmin(@Param("criteria") Criteria criteria, @Param("keyword") String keyword);

    //    관리자 게시글 총 개수
    public Integer selectCountAllAdmin(@Param("keyword") String keyword);

    //    관리자 게시글 삭제
    public void deleteAdmin(Long reviewId);

    //    회원별 게시글 삭제
    public void deleteByMemberId(Long memberId);
}
