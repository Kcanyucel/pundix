package com.pundix.service.user;

import com.pundix.entity.user.User;
import com.pundix.exception.custom.UserNotFoundException;
import com.pundix.repository.UserRepository;
import com.pundix.response.UserInfoResponse;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    private final UserRepository userRepository;

    public UserInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfoResponse get(Long id) {
        User user = userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);

        return UserInfoResponse.create(user.getId(), user.getUsername(), user.getEmail(), user.getName(), user.getSurname());
    }
}