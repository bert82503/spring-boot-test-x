package com.test.tutorial.web.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.tutorial.service.UserService;
import com.test.tutorial.web.param.UserIdParam;
import com.test.tutorial.web.param.UserInfoParam;
import com.test.tutorial.web.result.BaseResult;
import com.test.tutorial.web.result.ErrorCodeMessage;
import com.test.tutorial.web.result.UserNameResult;

/**
 * User controller.
 *
 * @author Bert Lee
 * @since  2014-8-19
 */
@Slf4j
@RestController("userController")
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
        log.info("create UserController instance");
    }

    @RequestMapping(value = "/getUserName", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserNameResult getUserName(@Valid UserIdParam userIdParam) {
        long userId = userIdParam.getUserId();
        String userName = userService.getUserName(userId);

        UserNameResult result = new UserNameResult();
        if (!StringUtils.hasLength(userName)) {
            result.setError(ErrorCodeMessage.USER_NOT_EXISTS);
        } else {
            result.setName(userName);
        }

        return result;
    }

    @RequestMapping(value = "/updateUserName", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResult updateUserName(
            @Valid @RequestBody UserInfoParam userInfoParam) { // JSON request body map
        BaseResult result = new BaseResult();

        long userId = userInfoParam.getUserId();
        String userName = userInfoParam.getName();
        boolean updateResult = userService.updateUserName(userId, userName);
        if (!updateResult) {
            result.setError(ErrorCodeMessage.OPERATION_FAIL);
        }

        return result;
    }
}
