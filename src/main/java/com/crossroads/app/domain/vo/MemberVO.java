package com.crossroads.app.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MemberVO {
    private Long memberId;
    private String memberIdentification;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberEmail;
    private String memberFileOriginalName;
    private String memberFileUuid;
    private String memberFilePath;
    private String memberFileSize;
    private boolean memberFileType;
    private String memberType;
    private String memberDriveRegisterDate;
    private Integer memberPoint;
    private Long memberRandomKey;

}
