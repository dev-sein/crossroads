package com.crossroads.app.service;

import com.crossroads.app.domain.dao.ApplyDAO;
import com.crossroads.app.domain.dao.PointDAO;
import com.crossroads.app.domain.dto.ApplyDTO;
import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.domain.dto.Standards;
import com.crossroads.app.domain.dto.PageDTO;
import com.crossroads.app.domain.vo.ApplyVO;
import com.crossroads.app.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyDAO applyDAO;
    private final PointDAO pointDAO;

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
    public void cancel(Long applyId){
//        추후 포인트 추가 감소 로직 추가 필요
        applyDAO.deleteById(applyId);
    }

    //      연수 신청 - 코스
    public void saveCourse(ApplyDTO applyDTO) { applyDAO.RegisterCourse(applyDTO); }

    //      연수 신청 - 코스 신청 건을 수정
    public void saveApply(ApplyDTO applyDTO) { applyDAO.RegisterApply(applyDTO); }

//    검색 or 전체 목록 개수
    public Long getAppliesCount(Map<String, Object> info){
        return applyDAO.findAppliesCount(info);
    }

//    검색 or 전체 목록에서 나를 제외한 다른 베테랑들이 수락한 연수내역 개수
    public Long getOthersCount(Map<String, Object> info){
        return applyDAO.findOthersCount(info);
    }

//    연수 받는 사람의 id로 연수 내역 뽑기
    public List<ApplyVO> getApply(Long memberId, Standards standards) {
        if(standards.getPage() == 0 ) {
            standards.create(1, 5, 5, getApplyCount(memberId, null).intValue());
        } else {
            standards.create(standards.getPage(), 5, 5, getApplyCount(memberId, null).intValue());
        }

        return applyDAO.findByStarterMemberId(memberId, standards);
    }

//    연수 받는 사람의 id로 연수 신청 개수 받기(status별로도 뽑기)
    public Long getApplyCount(Long memberId, String applyStatus) {
        return applyDAO.findCountByStarterIdAndStatus(memberId, applyStatus);
    }

//    베테랑의 id로 연수 내역 뽑기
    public List<ApplyDTO> getApplyVeteran(Long veteranMemberId, Criteria criteria) {
        return applyDAO.findByVeteranMemberId(veteranMemberId, criteria);
    }

//    관리자 신청내역 목록
    public Map<String, Object> getListAdmin(Map<String, Object> requestData, Criteria criteria) {
        Map<String, Object> result = new HashMap<>();

        String keyword = (String) requestData.get("keyword");
        int page = (int) requestData.get("page");

        if (page == 0) {
            page = 1;
        }
        criteria = criteria.create(page, 6);

        List<ApplyVO> applies = applyDAO.findAllAdmin(criteria, keyword);


        result.put("applies", applies);
        result.put("pagination", new PageDTO().createPageDTO(criteria, applyDAO.findCountAllAdmin(keyword)));

        return result;
    }


//    관리자 신청내역 목록
    public Integer getCountAdmin(String keyword) {
        return applyDAO.findCountAllAdmin(keyword);
    }
    public Integer getCountAdmin() {
        return applyDAO.findCountAllAdmin(null);
    }

//    신청 삭제(취소)
    public void cancelAdmin(List<String> applyIds){
//        추후 포인트 추가 감소 로직을 cancel메소드에 추가 필요
        applyIds.stream().map(applyId -> Long.valueOf(applyId)).forEach(applyId -> {
            cancel(applyId);
        });
    }

//    포인트 환전 시 기록
    public void savePointDetails(Map<String, Object> pointDetail){
        pointDAO.registerPointDetail(pointDetail);
    }

    //   applyId 가져오기
    public Long getApplyIdByMemberId(Long memberId) {
        return applyDAO.getApplyIdByMemberId(memberId);
    }
}


