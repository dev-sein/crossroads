package com.crossroads.app.service;

import com.crossroads.app.domain.dao.BoardDAO;
import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("board")
@RequiredArgsConstructor
public class FreeBoardService implements BoardService {
    private final BoardDAO boardDAO;

    @Override
    public List<BoardDTO> getListAdmin() {
        return boardDAO.findAllAdmin();
    }

    @Override
    public List<ReviewDTO> getListMy() {
        return null;
    }
}
