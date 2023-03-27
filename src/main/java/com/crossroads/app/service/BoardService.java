package com.crossroads.app.service;

import com.crossroads.app.domain.dto.BoardDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
//    어드민 게시글 목록
    public List<BoardDTO> getListAdmin();
}
