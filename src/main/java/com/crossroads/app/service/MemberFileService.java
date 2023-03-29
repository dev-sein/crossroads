package com.crossroads.app.service;

import com.crossroads.app.domain.dao.MemberFileDAO;
import com.crossroads.app.domain.vo.MemberFileVO;
import com.crossroads.app.domain.vo.MemberVO;
import com.crossroads.app.mapper.MemberFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;

@Service
@RequiredArgsConstructor
public class MemberFileService implements FileService {
    private final MemberFileDAO memberFileDAO;

    @Override
    public void fileRegister(MemberFileVO memberFileVO)  {
        memberFileDAO.insert(memberFileVO);
    }


    }

