package com.crossroads.app.service;

import com.crossroads.app.domain.dao.ApplyDAO;
import com.crossroads.app.domain.dao.PointDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
