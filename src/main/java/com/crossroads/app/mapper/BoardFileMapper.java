package com.crossroads.app.mapper;

import com.crossroads.app.domain.vo.BoardFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardFileMapper {

    /*게시글 별 file 조회*/
    public List<BoardFileVO> select(Long boardId);

    /*게시글 별 file 삭제*/
    public void deleteByBoardId(Long boardId);
}
