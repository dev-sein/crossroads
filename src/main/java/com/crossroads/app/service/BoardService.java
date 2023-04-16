package com.crossroads.app.service;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.ReviewCriteria;
import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.domain.dto.Standards;
import com.crossroads.app.domain.vo.ReviewVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface BoardService {
    //어드민 게시글 목록
    public Map<String, Object> getListAdmin(Map<String, Object> requestData, Criteria criteria);

    //어드민 게시글 총 개수
    public Integer getCountAdmin(String keyword);

    //마이페이지 리뷰 목록
    public List<ReviewDTO> getListMy(Long memberId, Standards standards);

    //어드민 게시글 삭제
    public void remove(List<Long> boardIds);

    //자유 게시글 목록
    public List<BoardDTO> getList();

    //후기 게시글 전체 목록
    public List<ReviewDTO> getListReview();

    //페이징처리
    public List<ReviewDTO> getReviewsByPage(int start, int end);
//    후기 게시글 전체 목록
    public  List<ReviewDTO> getListReview(ReviewCriteria criteria);
    public int getTotalCount();

    //후기 작성
    public void save(ReviewDTO reviewDTO);

    //마이페이지 게시글 목록
    public List<BoardDTO> getListMyBoard(Long memberId, Standards standards);

    //마이페이지 게시글 페이징 - 전체 개수

    public int getTotalMy();
    //후기 수정
   public void updateReview(ReviewDTO reviewDTO);
   public ReviewVO getReview(Long reviewId);

    //    마이페이지 게시글 목록
    public List<BoardDTO> getListMyBoard(Long memberId);

    /* 상세 보기*/
    public Map<String, Object> getBoardAdmin(Long boardId);


    //게시판 전체 조회
   public List<BoardDTO> getListBoard();

    //게시판 작성(저장)
    public void save(BoardDTO boardDTO);
    public void saveBoard(BoardDTO boardDTO, List<MultipartFile> files) throws IOException;

}



