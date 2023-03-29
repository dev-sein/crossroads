package com.crossroads.app.service;

import com.crossroads.app.domain.vo.MemberFileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
public interface FileService {
    //사진 등록
    public void fileRegister(MemberFileVO memberFileVO);
}
