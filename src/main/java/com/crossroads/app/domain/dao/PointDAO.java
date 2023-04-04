package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.dto.PointDTO;
import com.crossroads.app.mapper.ApplyMapper;
import com.crossroads.app.mapper.PointMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    //    마이페이지 포인트 내역
    public List<PointDTO> findByIdMyPoint(Long memberId){
        return pointMapper.selectAllMyPoint(memberId);
    }

    //     포인트 차감
    public void setAfterApply(Long memberId, Integer memberPoint) { pointMapper.updateAfterApply(memberId, memberPoint);}
}
