package com.crossroads.app.service;

import com.crossroads.app.domain.dao.ApplyDAO;
import com.crossroads.app.domain.dao.ReviewDAO;
import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("board")
@RequiredArgsConstructor
public class ReviewBoardService implements BoardService {
    private final ReviewDAO reviewDAO;

    @Override
    public List<BoardDTO> getListAdmin() {
        return null;
    }

    @Override
    public List<ReviewDTO> getListMy() { return reviewDAO.findAllMy(); }

    @Override
    public void remove(List<String> boardIds) {

    }

    @Override
    public List<BoardDTO> getList() {
        return null;
    }

   /* @Override
    public List<ReviewDTO> getListReview() {
        return reviewDAO.findAllReview();
    }*/


}
