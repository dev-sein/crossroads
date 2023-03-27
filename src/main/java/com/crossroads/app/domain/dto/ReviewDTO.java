package com.crossroads.app.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReviewDTO {
    private Long reviewId;
    private Long memberId;
    private String reviewRegisterDate;
    private String reviewUpdateDate;
    private String reviewTitle;
    private String reviewContent;
    private String reviewFileSystemName;
    private String reviewGrade;
    private Long applyId;
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
