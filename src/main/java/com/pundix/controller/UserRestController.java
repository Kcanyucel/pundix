package com.pundix.controller;

import com.pundix.request.UserCreateRequest;
import com.pundix.request.UserLoginRequest;
import com.pundix.request.UserUpdateRequest;
import com.pundix.response.*;
import com.pundix.service.UserAuthService;
import com.pundix.service.UserManagementService;
import com.pundix.service.UserProfileService;
import com.pundix.validator.user.UserCreateRequestValidator;
import com.pundix.validator.user.UserLoginRequestValidator;
import com.pundix.validator.user.UserUpdateRequestValidator;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserRestController {

    private final UserAuthService userAuthService;
    private final UserProfileService userProfileService;
    private final UserManagementService userManagementService;
    private final UserCreateRequestValidator userCreateRequestValidator;
    private final UserUpdateRequestValidator userUpdateRequestValidator;
    private final UserLoginRequestValidator userLoginRequestValidator;

    public UserRestController(UserAuthService userAuthService,
                              UserProfileService userProfileService,
                              UserManagementService userManagementService,
                              UserCreateRequestValidator userCreateRequestValidator,
                              UserUpdateRequestValidator userUpdateRequestValidator,
                              UserLoginRequestValidator userLoginRequestValidator) {
        this.userAuthService = userAuthService;
        this.userProfileService = userProfileService;
        this.userManagementService = userManagementService;
        this.userCreateRequestValidator = userCreateRequestValidator;
        this.userUpdateRequestValidator = userUpdateRequestValidator;
        this.userLoginRequestValidator = userLoginRequestValidator;
    }

    @PostMapping("/create")
    public UserCreateResponse createUser(@Valid @RequestBody UserCreateRequest request) {
    //    userCreateRequestValidator.validate(request);
        return userManagementService.create(request);
    }

    @PutMapping("/update/{id}")
    public UserUpdateResponse updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        userUpdateRequestValidator.validate(request);
        return userManagementService.update(id, request);
    }

    @PostMapping("/login")
    public UserLoginResponse loginUser(@RequestBody UserLoginRequest request) {
        userLoginRequestValidator.validate(request);
        return userAuthService.login(request);
    }

    @PostMapping("/logout")
    public UserLogoutResponse logoutUser(@RequestHeader(value = "Access-Token") String accessToken) {
        return userAuthService.logout(accessToken);
    }

    @GetMapping("/info/{id}")
    public UserInfoResponse getUser(@PathVariable Long id) {
        return userProfileService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public UserDeleteResponse deleteUser(@PathVariable Long id) {
        return userManagementService.delete(id);
    }

    @PostMapping("/close/{id}")
    public UserCloseResponse closeUser(@PathVariable Long id) {
        return userManagementService.close(id);
    }
}
