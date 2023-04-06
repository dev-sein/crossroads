package com.crossroads.app.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class BoardFileVO {
    private Long fileId;
    private Long boardId;
    private String fileOriginalName;
    private String fileUuid;
    private String filePath;
    private String fileSize;


}
