package com.pundix.service;

import com.pundix.entity.user.User;
import com.pundix.exception.custom.UserNotFoundException;
import com.pundix.repository.UserRepository;
import com.pundix.response.UserInfoResponse;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final UserRepository userRepository;

    public UserProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfoResponse get(Long id) {
        User user = userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);

        return new UserInfoResponse(user.getId(), user.getUsername(), user.getEmail(), user.getName(), user.getSurname());
    }
}
