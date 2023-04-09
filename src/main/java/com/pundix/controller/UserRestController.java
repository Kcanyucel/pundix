package com.pundix.controller;

import com.pundix.entity.user.UserSessionInfo;
import com.pundix.request.UserCreateRequest;
import com.pundix.request.UserLoginRequest;
import com.pundix.request.UserUpdateRequest;
import com.pundix.response.user.*;
import com.pundix.service.UserService;
import com.pundix.validator.user.UserValidator;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserRestController {

    private final UserService userService;

    private final UserValidator userValidator;

    public UserRestController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @PostMapping("/create")
    public UserCreateResponse createUser(@RequestBody UserCreateRequest userRequest) {
        userValidator.validateForCreate(userRequest);
        return userService.createUser(userRequest);
    }

    @PostMapping("/login")
    public UserLoginResponse loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        userValidator.validateForLogin(userLoginRequest);
        return userService.loginUser(userLoginRequest);
    }

    @PostMapping("/logout")
    public UserLogoutResponse logoutUser(@RequestHeader(value = "Access-Token") String accessToken) {
        return userService.logoutUser(accessToken);
    }

    @GetMapping("/info/{id}")
    public UserInfoResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/delete/{id}")
    public UserDeleteResponse deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/close/{id}")
    public UserCloseResponse closeUser(@PathVariable Long id) {
        return userService.closeUser(id);
    }

    @PutMapping("/update/{id}")
    public UserUpdateResponse updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        userValidator.validateForUpdate(userUpdateRequest);
        return userService.updateUser(id, userUpdateRequest);
    }
}
