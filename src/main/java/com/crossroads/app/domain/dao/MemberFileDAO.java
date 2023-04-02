package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.vo.MemberFileVO;
import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.mapper.MemberFileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberFileDAO {
    private final MemberFileMapper memberFileMapper;

    //파일 업로드
    public void insert(MemberFileVO memberFileVO){ memberFileMapper.insert(memberFileVO);};

}
