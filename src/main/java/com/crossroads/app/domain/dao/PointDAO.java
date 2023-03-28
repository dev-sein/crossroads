package com.crossroads.app.domain.dao;

import com.crossroads.app.mapper.ApplyMapper;
import com.crossroads.app.mapper.PointMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PointDAO {
    private final PointMapper pointMapper;
    
    //    현재 포인트
    public Long findPointById(Long memberId){
        return pointMapper.selectPoint(memberId);
    }

    //    포인트 수정
    public void setPoint(Long memberId){
        pointMapper.updatePoint(memberId);
    }
}
