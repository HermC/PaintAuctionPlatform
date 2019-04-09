package edu.nju.ise.auction.controller;

import edu.nju.ise.auction.command.LotCommand;
import edu.nju.ise.auction.model.Lot;
import edu.nju.ise.auction.model.ResultMap;
import edu.nju.ise.auction.service.LotService;
import edu.nju.ise.auction.utils.Constants;
import edu.nju.ise.auction.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/lot")
@CrossOrigin
public class LotController {

    @Autowired
    private LotService lotService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping(value = "/processing/list")
    @RequiresRoles(logical = Logical.OR, value = {Constants.ROLE_ADMIN, Constants.ROLE_USER})
    public ResultMap getProcessingLots(@RequestParam(value = "page", defaultValue = "0") final Long page,
                                       @RequestParam(value = "size", defaultValue = "12") final Long size) {
        Page<Lot> lots = lotService.getNotEndedLotList(page, size);
        return new ResultMap()
                .code(HttpStatus.OK.value())
                .success()
                .data("lots", lots.getContent())
                .data("currentPage", lots.getNumber())
                .data("totalPages", lots.getTotalPages())
                .message("获取成功!");
    }

    @GetMapping(value = "/{id}")
    @RequiresRoles(logical = Logical.OR, value = {Constants.ROLE_ADMIN, Constants.ROLE_USER})
    public ResultMap getLotInfo(@PathVariable("id") final Long id) {
        Lot lot = lotService.getLot(id);
        if (lot == null) {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .fail()
                    .message("该拍卖品不存在!");
        } else {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .success()
                    .data("lot", lot)
                    .message("获取成功!");
        }
    }

    @PostMapping(value = "/new")
    @RequiresRoles(logical = Logical.OR, value = {Constants.ROLE_ADMIN, Constants.ROLE_USER})
    public ResultMap addLot(@RequestBody final LotCommand command) {
        String username = jwtUtils.getUsername((String) SecurityUtils.getSubject().getPrincipal());
        Lot lot = lotService.addLot(username, command);
        if (lot == null) {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .fail()
                    .message("发布失败!");
        } else {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .success()
                    .data("lot", lot)
                    .message("发布成功!");
        }
    }

}
