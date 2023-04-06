package com.crossroads.app.controller;

import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.vo.BoardFileVO;
import com.crossroads.app.service.BoardFileService;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/files/*")
@RequiredArgsConstructor
public class FileController {
    private final BoardFileService boardFileService;

    @GetMapping("upload")
    public String goUploadForm() {
        return "/upload";
    }

    // 파일 업로드
    @PostMapping("upload")
    @ResponseBody
    public List<String> upload(@RequestParam("file") List<MultipartFile> multipartFiles) throws IOException {
        List<String> uuids = new ArrayList<>();
        String path = "C:/upload/" + getPath();
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        List<BoardFileVO> files = new ArrayList<>();
        for (int i = 0; i < multipartFiles.size(); i++) {
            String uuid = UUID.randomUUID().toString();
            uuids.add(uuid);
            multipartFiles.get(i).transferTo(new File(path, uuid + "_" + multipartFiles.get(i).getOriginalFilename()));

            BoardFileVO boardFileVO = new BoardFileVO();
            boardFileVO.setFileOriginalName(multipartFiles.get(i).getOriginalFilename());
            boardFileVO.setFileUuid(uuid);
            boardFileVO.setFilePath(path);
            boardFileVO.setFileSize(String.valueOf(multipartFiles.get(i).getSize()));
            files.add(boardFileVO);

            if (multipartFiles.get(i).getContentType().startsWith("image")) {
                FileOutputStream out = new FileOutputStream(new File(path, "t_" + uuid + "_" + multipartFiles.get(i).getOriginalFilename()));
                Thumbnailator.createThumbnail(multipartFiles.get(i).getInputStream(), out, 100, 100);
                out.close();
            }
        }
        return uuids;
    }

    // 파일 불러오기
    @GetMapping("display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload", fileName));
    }

    // 현재 날짜 경로 구하기
    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
