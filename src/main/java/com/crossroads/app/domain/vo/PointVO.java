package com.crossroads.app.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PointVO {
    private Long pointId;
    private Long memberId;
    private Integer pointStatus;
    private String pointUpdateDate;
    private Integer pointPoint;
}
