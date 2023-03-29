package com.crossroads.app.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MemberFileVO extends FileVO {
    private Long memberId;
}
