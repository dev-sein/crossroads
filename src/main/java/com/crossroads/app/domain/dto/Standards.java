package com.crossroads.app.domain.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
//페이징 처리 기준
public class Standards {
    private int page;
    private int rowCount;
    private int pageCount;
    private int startPage;
    private int endPage;
    private int realEnd;
    private boolean prev, next;
    private int total;
    private int offset;

    //total : 쿼리가 필요
    public void create(int page, int rowCount, int pageCount, int total) {
        this.page = page;
        this.rowCount = rowCount;/* 한 페이지 게시글 갯수*/
        this.pageCount = pageCount;/* 페이지 갯수 */
        this.total = total;/* 초기화 */
//        this.offset = (page - 1) * rowCount;
        this.endPage = (int)(Math.ceil(page / (double)pageCount)) * pageCount;/*마지막 페이지*/
        this.startPage = endPage - pageCount + 1;/*시작 페이지*/
        this.realEnd = (int)(Math.ceil(total / (double)rowCount));
        if(realEnd < endPage) {
            /*게시글이 한개도 없으면 0페이지가 나와서 bug 막아줘야함*/
            endPage = realEnd == 0 ? 1 : realEnd;
        }
        this.prev = startPage > 1;
        this.next = endPage < realEnd;
    }
}
