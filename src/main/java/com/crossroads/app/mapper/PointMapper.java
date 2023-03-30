package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.PointDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PointMapper {

//  현재 포인트
    public Long selectPoint(Long memberId);

//  포인트 수정
    public void updatePoint(Long memberId);

//   마이페이지 포인트 내역
    public List<PointDTO> selectAllMyPoint(Long memberId);
}
