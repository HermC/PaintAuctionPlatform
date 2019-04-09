package edu.nju.ise.auction.controller;

import edu.nju.ise.auction.command.ImageOrderCommand;
import edu.nju.ise.auction.model.Image;
import edu.nju.ise.auction.model.ImageOrder;
import edu.nju.ise.auction.model.ResultMap;
import edu.nju.ise.auction.service.ImageOrderService;
import edu.nju.ise.auction.service.ImageService;
import edu.nju.ise.auction.utils.Constants;
import edu.nju.ise.auction.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "/api/image/order")
public class ImageOrderController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageOrderService imageOrderService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping(value = "")
    @RequiresRoles(logical = Logical.OR, value = {Constants.ROLE_ADMIN, Constants.ROLE_USER})
    public ResultMap orderImage(@RequestBody ImageOrderCommand command) {
        Image image = imageService.getImage(command.getImageid());
        if (image == null) {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .fail()
                    .message("商品不存在!");
        }
        if (!Objects.equals(image.getAvailable(), Image.AVAILABLE)) {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .fail()
                    .message("商品已被订购!");
        }
        String username = jwtUtils.getUsername((String) SecurityUtils.getSubject().getPrincipal());
        ImageOrder order = imageOrderService.addOrder(username, command);
        if (order == null) {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .fail()
                    .message("订购失败!");
        } else {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .success()
                    .message("订购成功!");
        }
    }

}
