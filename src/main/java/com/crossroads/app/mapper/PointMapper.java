package com.crossroads.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface PointMapper {

//  현재 포인트
    public Long selectPoint(Long memberId);

//  포인트 수정
    public void updatePoint(Map<String, Object> info);
}
