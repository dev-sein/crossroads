package com.crossroads.app.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReplyVO {
    private Long replyId;
    private Long boardId;
    private Long memberId;
    private String replyContents;
    private String replyRegisterDate;
    private String replyUpdateDate;
}
