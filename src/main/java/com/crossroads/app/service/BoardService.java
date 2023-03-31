package com.crossroads.app.service;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.ReviewCriteria;
import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.dto.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    //    어드민 게시글 목록
    public List<BoardDTO> getListAdmin(Criteria criteria);

//    어드민 게시글 총 개수
    public Integer getCountAdmin();

    //    마이페이지 리뷰 목록
    public List<ReviewDTO> getListMy(Long memberId);

    //    어드민 게시글 삭제
    public void remove(List<String> boardIds);

    //    자유 게시글 목록
    public List<BoardDTO> getList();

//    후기 게시글 전체 목록
    public  List<ReviewDTO> getListReview(ReviewCriteria criteria);
    public int getTotalCount();

    //후기 작성
    public void save(ReviewDTO reviewDTO);

    //    마이페이지 게시글 목록
    public List<BoardDTO> getListMyBoard(Long memberId);




}
