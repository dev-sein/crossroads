package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDAO {
    private final BoardMapper boardMapper;

    public List<BoardDTO> findAllAdmin() {
        return boardMapper.selectAllAdmin();
    }
}
