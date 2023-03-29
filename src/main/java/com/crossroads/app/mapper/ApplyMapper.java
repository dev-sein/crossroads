package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.ApplyDTO;
import com.crossroads.app.domain.vo.ApplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ApplyMapper {
//    신청 목록 전체 조회
    public List<ApplyDTO> selectAll();

//    신청 목록 검색 조회
    public List<ApplyDTO> selectList(Map<String, Object> info);

//    신청 상세 조회
    public ApplyVO select(Long applyId);

//    신청 상태 조회
//    public String selectStatus(Long applyId);

//    신청 상태 수정
    public void updateStatus(Long applyId);

//    신청 상태 수정
    public void updateVeteranId(Map<String, Object> info);

//    나를 제외한 다른 베테랑들이 수락한 신청 개수
    public Long selectCount(Long memberId);

}
