package com.crossroads.app.service;

import com.crossroads.app.domain.dao.ApplyDAO;
import com.crossroads.app.domain.dao.PointDAO;
import com.crossroads.app.domain.dto.PointDTO;
import com.crossroads.app.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.PerObjectInterfaceTypeMunger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointService {
    private final PointDAO pointDAO;

//    현재 포인트
    public Long getPoint(Long memberId) {
        return pointDAO.findPointById(memberId);
    }

//    포인트 수정
    public void modifyPoint(Long memberId){
        pointDAO.setPoint(memberId);
    }

//    마이페이지 포인트 내역
    public List<PointDTO> getListMyPoint(Long memberId) { return pointDAO.findByIdMyPoint(memberId); }

//    포인트 차감
    public void modifyAfterApply(Long memberId, Integer memberPoint) { pointDAO.setAfterApply(memberId, memberPoint);;}
}
