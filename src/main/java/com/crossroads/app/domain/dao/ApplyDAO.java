package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.dto.ApplyDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.domain.dto.Standards;
import com.crossroads.app.domain.vo.ApplyVO;
import com.crossroads.app.mapper.ApplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ApplyDAO {
    private final ApplyMapper applyMapper;

//    신청목록 전체조회
    public List<ApplyDTO> findAll(Criteria criteria) {
        return applyMapper.selectAll(criteria);
    }

//    신청목록 검색조회
    public List<ApplyDTO> findList(Criteria criteria, Map<String, Object> info) {
        return applyMapper.selectList(criteria, info);
    }

//    신청 상세 조회
    public ApplyVO findById(Long applyId) {return applyMapper.select(applyId);};

//    신청 상태 수정
    public void setApplyStatus(Long applyId){
        applyMapper.updateStatus(applyId);
    }

//    담당 베테랑 id 수정
    public void setVeteranId(Map<String, Object> info){
        applyMapper.updateVeteranId(info);
    }

//    나를 제외한 다른 베테랑들이 수락한 신청 개수
    public Long findCountById(Long memberId){return applyMapper.selectCount(memberId);}

//    신청 삭제(취소)
    public void deleteById(Long applyId){
        applyMapper.delete(applyId);
    }

//   연수 신청
    public void RegisterApply(ApplyDTO applyDTO) { applyMapper.insertApply((applyDTO));}

//  연수 코스
    public void RegisterCourse(ApplyDTO applyDTO) { applyMapper.insertCourse((applyDTO));}
//    검색 or 전체 목록 개수
    public Long findAppliesCount(Map<String, Object> info){
        return applyMapper.selectAppliesCount(info);
    }

//    검색 or 전체 목록에서 나를 제외한 다른 베테랑들이 수락한 연수내역 개수
    public Long findOthersCount(Map<String, Object> info){
        return applyMapper.selectOthersCount(info);
    }

//    연수 받는 사람의 id로 연수 내역 뽑기
    public List<ApplyVO> findByStarterMemberId(Long memeberId, Standards standards) {
        return applyMapper.selectByStarterMemberId(memeberId, standards);
    }

//    연수 받는 사람의 id로 연수 신청 개수 받기(status별로도 뽑기)
    public Long findCountByStarterIdAndStatus(Long memberId, String applyStatus) {
        return applyMapper.selectCountByStarterIdAndStatus(memberId, applyStatus);
    }

//    베테랑의 id로 연수 내역 뽑기
    public List<ApplyDTO> findByVeteranMemberId(Long veteranMemberId, Criteria criteria) {
        return applyMapper.selectByVeteranMemberId(veteranMemberId, criteria);
    }
}
