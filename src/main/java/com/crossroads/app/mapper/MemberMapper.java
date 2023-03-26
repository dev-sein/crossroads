package com.crossroads.app.mapper;

import com.crossroads.app.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public void join(MemberVO memberVO);
}
