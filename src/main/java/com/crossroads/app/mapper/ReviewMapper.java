package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    //마이페이지 후기 전체 조회
    public List<ReviewDTO> selectAll();

    //후기 전체 조회
    public List<ReviewDTO> selectReviewAll();

}
