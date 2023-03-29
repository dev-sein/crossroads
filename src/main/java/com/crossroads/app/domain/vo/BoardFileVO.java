package com.crossroads.app.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardFileVO extends FileVO{
    private Long boardId;
}
