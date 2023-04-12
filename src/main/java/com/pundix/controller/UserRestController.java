package com.pundix.controller;

import com.pundix.request.UserCreateRequest;
import com.pundix.request.UserLoginRequest;
import com.pundix.request.UserUpdateRequest;
import com.pundix.response.*;
import com.pundix.service.UserService;
import com.pundix.validator.user.UserCreateRequestValidator;
import com.pundix.validator.user.UserLoginRequestValidator;
import com.pundix.validator.user.UserUpdateRequestValidator;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserRestController {

    private final UserService userService;
    private final UserCreateRequestValidator userCreateRequestValidator;
    private final UserUpdateRequestValidator userUpdateRequestValidator;
    private final UserLoginRequestValidator userLoginRequestValidator;

    public UserRestController(UserService userService,
                              UserCreateRequestValidator userCreateRequestValidator,
                              UserUpdateRequestValidator userUpdateRequestValidator,
                              UserLoginRequestValidator userLoginRequestValidator) {
        this.userService = userService;
        this.userCreateRequestValidator = userCreateRequestValidator;
        this.userUpdateRequestValidator = userUpdateRequestValidator;
        this.userLoginRequestValidator = userLoginRequestValidator;
    }

    @PostMapping("/create")
    public UserCreateResponse createUser(@RequestBody UserCreateRequest userRequest) {
        userCreateRequestValidator.validate(userRequest);
        return userService.createUser(userRequest);
    }

    @PutMapping("/update/{id}")
    public UserUpdateResponse updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        userUpdateRequestValidator.validate(userUpdateRequest);
        return userService.updateUser(id, userUpdateRequest);
    }

    @PostMapping("/login")
    public UserLoginResponse loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        userLoginRequestValidator.validate(userLoginRequest);
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
}
