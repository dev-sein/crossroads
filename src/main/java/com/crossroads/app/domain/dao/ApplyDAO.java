package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.dto.ApplyDTO;
import com.crossroads.app.domain.vo.ApplyVO;
import com.crossroads.app.mapper.ApplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplyDAO {
    private final ApplyMapper applyMapper;

//    신청목록 전체조회
    public List<ApplyDTO> findAll() {
        return applyMapper.selectAll();
    }

//    신청 상세 조회
    public ApplyVO findById(Long applyId) {return applyMapper.select(applyId);};

//    신청 상태 수정
//    public void setApplyVO(Long applyId){
//        applyMapper.updateStatus(applyId);
//    }
}
