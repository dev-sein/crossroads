package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.vo.ReviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    //마이페이지 후기 전체 조회
    public List<ReviewDTO> selectAll(Long memberId);


    //후기 전체 조회
    public List<ReviewDTO> selectReviewAll();

    public List<ReviewDTO> selectReviewByPage(@Param("start") int start, @Param("end") int end);

    //후기 삭제
    public void delete(Long reviewId);

    //후기 작성
    public void insert(ReviewDTO reviewDTO);

    //후기 수정
    public void update(ReviewDTO reviewDTO);

    // 후기 조회
    public ReviewVO select(Long reviewId);


}
