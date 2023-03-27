package com.crossroads.app.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReviewDTO {
    private Long reviewId;
    private Long memberId;
    private Long applyId;
    private String reviewRegisterDate;
    private String reviewUpdateDate;
    private String reviewTitle;
    private String reviewContent;
    private String reviewFileSystemName;
    private String reviewGrade;
    private String applyCourse;
    private String applyLocation;
    private String applyDate;
    private String applyRegisterDate;
    private Long starterMemberId;
    private Long veteranMemberId;
    private String applyStatus;
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
