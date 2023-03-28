package com.crossroads.app.service;

import com.crossroads.app.domain.dao.ReviewDAO;
import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.dto.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("board")
@RequiredArgsConstructor
public class ReviewBoardService implements BoardService {
    private final ReviewDAO reviewDAO;

    @Override
    public List<BoardDTO> getListAdmin(Criteria criteria) {
        return null;
    }

    @Override
    public Integer getCountAdmin() {
        return null;
    }

    @Override
    public List<ReviewDTO> getListMy(Long memberId) {
        return reviewDAO.findAllMy(memberId);
    }

    @Override
    public void remove(List<String> boardIds) {
        boardIds.stream().map(boardId -> Long.parseLong(boardId)).forEach(reviewDAO::deleteById);
    }

    @Override
    public List<BoardDTO> getList() {
        return null;
    }

    @Override
    public List<ReviewDTO> getListReview() {
        return reviewDAO.findAllReview();
    }


}
