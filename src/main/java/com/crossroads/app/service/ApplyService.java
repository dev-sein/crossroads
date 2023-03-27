package com.crossroads.app.service;

import com.crossroads.app.domain.dao.ApplyDAO;
import com.crossroads.app.domain.dto.ApplyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyDAO applyDAO;

//    신청목록 전체조회
    public List<ApplyDTO> getList() {return applyDAO.findAll();}

//    신청 상태 조회
//    public String getStatus() {}

//    신청 상태 수정
    public void modifyStatus(Long applyId) {
        applyDAO.setApplyStatus(applyId);
    }

//    담당 베테랑 id 수정
    public void modifyVeteranId(Map<String, Object> info) {
        applyDAO.setVeteranId(info);
    }
}
