package com.pundix.controller;

import com.pundix.request.UserRequest;
import com.pundix.response.*;
import com.pundix.service.UserService;
import com.pundix.validator.UserRequestValidator;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public record UserRestController(UserService userService, UserRequestValidator userRequestValidator) {

    @PostMapping("/create")
    public UserCreateResponse createUser(@RequestBody UserRequest userRequest) {
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
    public UserUpdateResponse updateUser(@RequestParam Long id, @RequestBody UserRequest userRequest) {
        userRequestValidator.validateForUpdate(userRequest);
        return userService.updateUser(id, userRequest);
    }
}
