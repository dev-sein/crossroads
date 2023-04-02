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

    private String boardTitle;
    private String boardContent;
    private String boardRegisterDate;
    private String boardUpdateDate;
    private Integer boardReadCount;

    private String memberIdentification;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberEmail;
    private String memberFileSystemName;
    private String memberType;
    private String memberDriveRegisterDate;
    private Integer memberPoint;
}
