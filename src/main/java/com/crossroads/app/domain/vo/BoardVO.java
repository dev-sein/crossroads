package com.crossroads.app.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardVO {
    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private Long memberId;
    private String boardRegisterDate;
    private String boardUpdateDate;
    private Integer boardReadCount;
}
