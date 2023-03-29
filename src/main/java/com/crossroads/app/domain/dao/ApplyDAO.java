package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.dto.ApplyDTO;
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
    public List<ApplyDTO> findAll() {
        return applyMapper.selectAll();
    }

//    신청목록 검색조회
    public List<ApplyDTO> findList(Map<String, Object> info) {
        return applyMapper.selectList(info);
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
}
