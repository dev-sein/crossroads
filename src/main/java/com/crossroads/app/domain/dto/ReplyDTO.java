package com.crossroads.app.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReplyDTO {
    private Long replyId;
    private Long boardId;
    private Long memberId;
    private String replyContent;
    private String replyRegisterDate;
    private String replyUpdateDate;

    private Long replyCount; // 댓글 수
}
