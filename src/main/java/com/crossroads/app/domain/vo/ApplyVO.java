package com.crossroads.app.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ApplyVO {
    Long applyId;
    String applyCourse;
    String applyLocation;
    String applyDate;
    String applyRegisterDate;
    Long starterMemberId;
    Long veteranMemberId;
    String applyStatus;
}
