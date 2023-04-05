package com.crossroads.app.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Data
public class PointDTO {
    private Long pointId;
    private Long memberId;
    private Integer pointStatus;
    private String pointUpdateDate;
    private Integer pointPoint;
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
