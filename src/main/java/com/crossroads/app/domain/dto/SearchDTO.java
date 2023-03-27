package com.crossroads.app.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class SearchDTO {
    //    검색조건 타입 설정
    private List<String> types;
    private final boolean IS_SEARCH_DTO = true;
    private boolean isDesc;
    //    검색조건 필드(조건 추가시 필드 추가)
    private String keyword;
    private Long memberId;

    public SearchDTO createTypes(List<String> types){
        setTypes(types);
        return this;
    }
}
