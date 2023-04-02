package com.crossroads.app.service;

import com.crossroads.app.domain.dao.ApplyDAO;
import com.crossroads.app.domain.dto.ApplyDTO;
import com.crossroads.app.domain.dto.Criteria;
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
    public List<ApplyDTO> getList(Criteria criteria) {return applyDAO.findAll(criteria);}

//    신청목록 검색조회
    public List<ApplyDTO> getListSearched(Criteria criteria, Map<String, Object> info){return applyDAO.findList(criteria, info);}

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
//    나를 제외한 다른 베테랑들이 수락한 신청 개수
    public Long getCount(Long memberId) {
        return applyDAO.findCountById(memberId);
    }

    //    신청 삭제(취소)
    public void cancel(Long applyId) {
//        추후 포인트 추가 감소 로직 추가 필요
        applyDAO.deleteById(applyId);
    }
//      연수 신청
    public void saveApply(ApplyDTO applyDTO) { applyDAO.addApply(applyDTO); }


}


