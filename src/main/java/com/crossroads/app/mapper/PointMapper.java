package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.domain.dto.PointDTO;
import com.crossroads.app.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PointMapper {

//  현재 포인트
    public Long selectPoint(Long memberId);

//  포인트 수정
    public void updatePoint(Long memberId);

//   마이페이지 포인트 내역
    public List<PointDTO> selectAllMyPoint(Long memberId);

//  포인트 차감
    public void updateAfterApply(Long memberId, Integer memberPoint);

//    관리자 포인트 내역 조회
    public List<PointDTO> selectAllAdmin(@Param("criteria") Criteria criteria, @Param("keyword") String keyword);

//    관리자 포인트 내역 총 개수
    public Integer selectCountAllAdmin(@Param("keyword") String keyword);

//    관리자 포인트 내역 삭제
    public void delete(Long pointId);

//    포인트 상세
    public PointDTO select(Long pointId);

//    포인트 충전
    public void updatePointByMemberId(Long memberId, Long memberPoint);

}
