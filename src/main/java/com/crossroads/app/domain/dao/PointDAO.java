package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.domain.dto.PointDTO;
import com.crossroads.app.mapper.ApplyMapper;
import com.crossroads.app.mapper.PointMapper;
import com.crossroads.app.service.PointService;
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



    /* 관리자 포인트 내역 목록 */
    public List<PointDTO> findAllAdmin(Criteria criteria, String keyword) {
        return pointMapper.selectAllAdmin(criteria, keyword);
    }
    /* 관리자 포인트 내역 총 개수 */
    public Integer findCountAllAdmin(String keyword) {
        return pointMapper.selectCountAllAdmin(keyword);
    }

    /* 관리자 포인트 내역 삭제 */
    public void deleteById(Long pointId) {
        pointMapper.delete(pointId);
    }

    /* 포인트 상세*/
    public PointDTO selectById(Long pointId){
        return pointMapper.select(pointId);
    }


    //  포인트 내역 등록
    public void register(PointDTO pointDTO) { pointMapper.insert(pointDTO);}

    //    포인트 충전
    public void setPointByMemberId(Long memberId, Long memberPoint) {
        pointMapper.updatePointByMemberId(memberId, memberPoint);
    }
}
