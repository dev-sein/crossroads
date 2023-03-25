package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.ApplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplyMapper {
//    신청 목록 전체 조회
    public List<ApplyDTO> selectAll();
}
