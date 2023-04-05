package com.crossroads.app.mapper;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.vo.BoardFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardFileMapper {

    /*게시글 별 file 조회*/
    public List<BoardFileVO> select(Long boardId);

    /*게시글 별 file 삭제*/
    public void deleteByBoardId(Long boardId);

    //    파일 추가
    public void insert(BoardFileVO boardFileVO);

    //    파일 전체 조회
    public List<BoardFileVO> selectAll(Long boardId);

    //    파일 삭제
    public void delete(Long boardId);

    //    전일 등록된 파일 조회
    public List<BoardDTO> selectYesterday();

}
