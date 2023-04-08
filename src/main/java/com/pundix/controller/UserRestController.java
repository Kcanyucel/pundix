package com.pundix.controller;

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

    @GetMapping("/info")
    public UserInfoResponse getUser(@RequestParam Long id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/delete")
    public UserDeleteResponse deleteUser(@RequestParam Long id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/close")
    public UserCloseResponse closeUser(@RequestParam Long id) {
        return userService.closeUser(id);
    }

    @PutMapping("/update")
    public UserUpdateResponse updateUser(@RequestParam Long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        userValidator.validateForUpdate(userUpdateRequest);
        return userService.updateUser(id, userUpdateRequest);
    }
}
