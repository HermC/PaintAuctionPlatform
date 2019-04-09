package edu.nju.ise.auction.controller;

import edu.nju.ise.auction.model.ResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/upload")
@CrossOrigin
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Value("${pap.imagePath}")
    private String imagePath;

    @PostMapping(value = "/image", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResultMap uploadImages(@RequestParam("image") final MultipartFile file) {
        if (file.isEmpty()) {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .fail()
                    .message("上传文件为空!");
        }
        String fileName = file.getOriginalFilename();
        long size = file.getSize();

        System.out.println(fileName + "-->" + size);

        if (!verifyImgPostfix(fileName)) {
            return new ResultMap()
                    .fail()
                    .code(HttpStatus.OK.value())
                    .message("只支持jpg/jpeg/png格式的图片!");
        }

        fileName = UUID.randomUUID().toString() + "_" + fileName;

        File dist = new File(imagePath + "/" + fileName);
        if (!dist.getParentFile().exists()) {
            dist.getParentFile().mkdir();
        }
        try {
            file.transferTo(dist);
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .success()
                    .data("imageName", fileName)
                    .message("上传成功!");
        } catch (IOException e) {
            logger.error(e.getMessage());
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .fail()
                    .message("上传失败!");
        }
    }

    private boolean verifyImgPostfix(String filename) {
        return filename.endsWith(".jpg")
                || filename.endsWith(".jpeg")
                || filename.endsWith(".png");
    }

}
