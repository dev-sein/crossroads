package com.crossroads.app.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardDTO {
    /*boardVO*/
    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private Long memberId;
    private String boardRegisterDate;
    private String boardUpdateDate;
    private Integer boardReadCount;

    /*memberVO*/
//    private Long memberId;
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
