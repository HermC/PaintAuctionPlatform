package edu.nju.ise.auction.controller;

import edu.nju.ise.auction.model.Image;
import edu.nju.ise.auction.model.ResultMap;
import edu.nju.ise.auction.service.ImageService;
import edu.nju.ise.auction.utils.Constants;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/image")
@CrossOrigin
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/list")
    public ResultMap getImageList(@RequestParam(value = "page", defaultValue = "0") final Long page,
                                  @RequestParam(value = "size", defaultValue = "10") final Long size) {
        Page<Image> images = imageService.getImageList(page, size);
        return new ResultMap()
                .code(HttpStatus.OK.value())
                .success()
                .data("images", images.getContent())
                .data("currentPage", images.getNumber())
                .data("totalPages", images.getTotalPages())
                .message("获取成功!");
    }

    @PostMapping(value = "/new")
    @RequiresRoles(value = {Constants.ROLE_ADMIN})
    public ResultMap addImage(@RequestBody final List<String> paths) {
        List<Image> images = imageService.addImages(paths);
        return new ResultMap()
                .code(HttpStatus.OK.value())
                .success()
                .data("images", images)
                .message("添加成功!");
    }

    @GetMapping(value = "/{id}/delete")
    @RequiresRoles(value = {Constants.ROLE_ADMIN})
    public ResultMap deleteImage(@PathVariable("id") final Long id) {
        imageService.deleteImage(id);
        return new ResultMap()
                .code(HttpStatus.OK.value())
                .success()
                .message("删除成功!");
    }

}
