package edu.nju.ise.auction.controller;

import edu.nju.ise.auction.command.ModifyPasswordCommand;
import edu.nju.ise.auction.command.UserCommand;
import edu.nju.ise.auction.model.ResultMap;
import edu.nju.ise.auction.model.User;
import edu.nju.ise.auction.service.AuthService;
import edu.nju.ise.auction.service.UserService;
import edu.nju.ise.auction.utils.Constants;
import edu.nju.ise.auction.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping(value = "")
    @RequiresRoles(logical = Logical.OR, value = {Constants.ROLE_ADMIN, Constants.ROLE_USER})
    public ResultMap getUser() {
        String username = jwtUtils.getUsername((String) SecurityUtils.getSubject().getPrincipal());
        return new ResultMap()
                .code(HttpStatus.OK.value())
                .success()
                .data("user", userService.getUser(username))
                .message("获取成功!");
    }

    @PostMapping(value = "/register")
    public ResultMap register(@RequestBody final UserCommand command) {
        if (userService.isExisted(command.getUsername())) {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .fail()
                    .message("用户已存在!");
        }
        User userNew = userService.addUser(command);
        if (userNew == null) {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .fail()
                    .message("注册失败!");
        } else {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .success()
                    .data("user", userNew)
                    .message("注册成功!");
        }
    }

    @PostMapping(value = "/password/modify")
    @RequiresRoles(logical = Logical.OR, value = {Constants.ROLE_ADMIN, Constants.ROLE_USER})
    public ResultMap modifyPassword(@RequestBody final ModifyPasswordCommand command) {
        String username = jwtUtils.getUsername((String) SecurityUtils.getSubject().getPrincipal());
        if (!authService.verifyUser(username)) {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .fail()
                    .message("用户不存在!");
        }
        if (!authService.verifyPassword(username, command.getOldPassword())) {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .fail()
                    .message("密码错误!");
        }
        User userModified = userService.modifyPassword(username, command.getOldPassword(), command.getNewPassword());
        if (userModified == null) {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .fail()
                    .message("修改失败!");
        } else {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .success()
                    .message("修改成功!");
        }
    }

}
