package com.crossroads.app.service;

import com.crossroads.app.domain.dao.BoardDAO;
import com.crossroads.app.domain.dao.ReplyDAO;
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
    private final ReplyDAO replyDAO;

    @Override
    public List<BoardDTO> getListAdmin() {
        List<BoardDTO> boards = boardDAO.findAllAdmin();

//        게시글 별 댓글 수를 boards에 추가
        boards.stream().forEach(board -> board.setReplyCount(replyDAO.findReplyCount(board.getBoardId())));

        return boards;
    }

    @Override
    public List<ReviewDTO> getListMy() {
        return null;
    }
}
