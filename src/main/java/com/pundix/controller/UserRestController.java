package com.pundix.controller;

import com.pundix.request.UserCreateRequest;
import com.pundix.request.UserUpdateRequest;
import com.pundix.response.*;
import com.pundix.service.UserService;
import com.pundix.validator.UserRequestValidator;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserRestController {

    private final UserService userService;

    private final UserRequestValidator userRequestValidator;

    public UserRestController(UserService userService, UserRequestValidator userRequestValidator) {
        this.userService = userService;
        this.userRequestValidator = userRequestValidator;
    }

    @PostMapping("/create")
    public UserCreateResponse createUser(@RequestBody UserCreateRequest userRequest) {
        userRequestValidator.validateForCreate(userRequest);
        return userService.createUser(userRequest);
    }

    @GetMapping("/info")
    public UserDetailResponse getUser(@RequestParam Long id) {
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
        userRequestValidator.validateForUpdate(userUpdateRequest);
        return userService.updateUser(id, userUpdateRequest);
    }
}
