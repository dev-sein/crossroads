package com.crossroads.app.mapper;

import com.crossroads.app.domain.vo.MemberFileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberFileMapper {
    //파일 업로드
    public void insert(MemberFileVO memberFileVO);
}
