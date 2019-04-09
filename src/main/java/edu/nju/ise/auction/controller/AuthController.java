package edu.nju.ise.auction.controller;

import edu.nju.ise.auction.command.LoginCommand;
import edu.nju.ise.auction.model.ResultMap;
import edu.nju.ise.auction.model.User;
import edu.nju.ise.auction.service.AuthService;
import edu.nju.ise.auction.service.UserService;
import edu.nju.ise.auction.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
@CrossOrigin
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping(value = "/token")
    public ResultMap auth(@RequestBody final LoginCommand command) {
        logger.debug(command.toString());
        if (!authService.verifyUser(command.getUsername())) {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .fail()
                    .message("用户不存在!");
        }
        if (!authService.verifyPassword(command.getUsername(), command.getPassword())) {
            return new ResultMap()
                    .code(HttpStatus.OK.value())
                    .fail()
                    .message("密码错误!");
        }
        User user = userService.getUser(command.getUsername());
        logger.debug(user.toString());
        return new ResultMap()
                .code(HttpStatus.OK.value())
                .success()
                .data("token", jwtUtils.createToken(command.getUsername()))
                .data("user", user)
                .message("认证成功!");
    }

}
