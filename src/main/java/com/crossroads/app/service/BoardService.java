package com.crossroads.app.service;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.vo.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    //    어드민 게시글 목록
    public List<BoardDTO> getListAdmin(Criteria criteria);

//    어드민 게시글 총 개수
    public Integer getCountAdmin();

    //    마이페이지 리뷰 목록
    public List<ReviewDTO> getListMy();

//    어드민 게시글 삭제
    public void remove(List<String> boardIds);

    //    자유 게시글 목록
    public List<BoardDTO> getList();

}
