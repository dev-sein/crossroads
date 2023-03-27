package com.crossroads.app.service;

import com.crossroads.app.domain.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("board")
@RequiredArgsConstructor
public class ReviewBoardService implements BoardService {

    @Override
    public List<BoardDTO> getListAdmin() {
        return null;
    }
}
