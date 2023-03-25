package com.crossroads.app.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ApplyVO {
    private Long applyId;
    private String applyCourse;
    private String applyLocation;
    private String applyDate;
    private String applyRegisterDate;
    private Long starterMemberId;
    private Long veteranMemberId;
    private String applyStatus;
}
