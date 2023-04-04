package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.ApplyDTO;
import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.domain.vo.ApplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ApplyMapper {
//    신청 목록 전체 조회
    public List<ApplyDTO> selectAll(Criteria criteria);

//    신청 목록 검색 조회
    public List<ApplyDTO> selectList(@Param("criteria") Criteria criteria, @Param("info") Map<String, Object> info);

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

//    신청 삭제(취소)
    public void delete(Long applyId);

//    연수 신청
    public void insertApply(ApplyDTO applyDTO);

//    연수 코스
    public void insertCourse(ApplyDTO applyDTO);
//    검색 or 전체 목록 개수
    public Long selectAppliesCount(@Param("info") Map<String, Object> info);

//    검색 or 전체 목록에서 나를 제외한 다른 베테랑들이 수락한 연수내역 개수
    public Long selectOthersCount(@Param("info") Map<String, Object> info);

//    관리자 신청 내역 전체 조회
    public List<ApplyVO> selectAllAdmin(@Param("criteria") Criteria criteria, @Param("keyword") String keyword);

//    관리자 신청 내역 총 개수
    public Integer selectCountAllAdmin(@Param("keyword") String keyword);
}
