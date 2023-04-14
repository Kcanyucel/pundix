package com.pundix.controller;

import com.pundix.request.UserCreateRequest;
import com.pundix.request.UserLoginRequest;
import com.pundix.request.UserUpdateRequest;
import com.pundix.response.*;
import com.pundix.service.user.*;
import com.pundix.validator.user.UserCreateRequestValidator;
import com.pundix.validator.user.UserLoginRequestValidator;
import com.pundix.validator.user.UserUpdateRequestValidator;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserRestController {

    private final UserInfoService userInfoService;
    private final UserCreateService userCreateService;
    private final UserUpdateService userUpdateService;
    private final UserDeleteService userDeleteService;
    private final UserCloseService userCloseService;
    private final UserLoginService userLoginService;
    private final UserLogoutService userLogoutService;
    private final UserCreateRequestValidator userCreateRequestValidator;
    private final UserUpdateRequestValidator userUpdateRequestValidator;
    private final UserLoginRequestValidator userLoginRequestValidator;

    public UserRestController(UserInfoService userInfoService,
                              UserCreateService userCreateService,
                              UserUpdateService userUpdateService,
                              UserDeleteService userDeleteService,
                              UserCloseService userCloseService,
                              UserLoginService userLoginService,
                              UserLogoutService userLogoutService,
                              UserCreateRequestValidator userCreateRequestValidator,
                              UserUpdateRequestValidator userUpdateRequestValidator,
                              UserLoginRequestValidator userLoginRequestValidator) {
        this.userInfoService = userInfoService;
        this.userCreateService = userCreateService;
        this.userUpdateService = userUpdateService;
        this.userDeleteService = userDeleteService;
        this.userCloseService = userCloseService;
        this.userLoginService = userLoginService;
        this.userLogoutService = userLogoutService;
        this.userCreateRequestValidator = userCreateRequestValidator;
        this.userUpdateRequestValidator = userUpdateRequestValidator;
        this.userLoginRequestValidator = userLoginRequestValidator;
    }

    @PostMapping("/create")
    public UserCreateResponse createUser(@RequestBody UserCreateRequest request) {
        userCreateRequestValidator.validate(request);
        return userCreateService.create(request);
    }

    @PutMapping("/update/{id}")
    public UserUpdateResponse updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        userUpdateRequestValidator.validate(request);
        return userUpdateService.update(id, request);
    }

    @PostMapping("/login")
    public UserLoginResponse loginUser(@RequestBody UserLoginRequest request) {
        userLoginRequestValidator.validate(request);
        return userLoginService.login(request);
    }

    @PostMapping("/logout")
    public UserLogoutResponse logoutUser(@RequestHeader(value = "Access-Token") String accessToken) {
        return userLogoutService.logout(accessToken);
    }

    @GetMapping("/info/{id}")
    public UserInfoResponse getUser(@PathVariable Long id) {
        return userInfoService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public UserDeleteResponse deleteUser(@PathVariable Long id) {
        return userDeleteService.delete(id);
    }

    @PostMapping("/close/{id}")
    public UserCloseResponse closeUser(@PathVariable Long id) {
        return userCloseService.close(id);
    }
}
