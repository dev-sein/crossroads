package com.crossroads.app.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReviewVO {
    private Long reviewId;
    private Long memberId;
    private String reviewRegisterDate;
    private String reviewUpdateDate;
    private String reviewTitle;
    private String reviewContents;
    private String reviewFileSystemName;
    private String reviewGrade;
    private Long applyId;
}
