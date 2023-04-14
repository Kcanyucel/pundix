package com.pundix.service.user;

import com.pundix.entity.user.User;
import com.pundix.exception.custom.UserNotFoundException;
import com.pundix.repository.UserRepository;
import com.pundix.response.UserDeleteResponse;
import com.pundix.service.MessageResourceService;
import com.pundix.service.user.session.UserSessionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserDeleteService {

    private final UserRepository userRepository;
    private final UserSessionService userSessionService;
    private final MessageResourceService messageResourceService;

    public UserDeleteService(UserRepository userRepository, UserSessionService userSessionService, MessageResourceService messageResourceService) {
        this.userRepository = userRepository;
        this.userSessionService = userSessionService;
        this.messageResourceService = messageResourceService;
    }

    @Transactional
    public UserDeleteResponse delete(Long id) {
        User user = userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(user.getId());
        userSessionService.delete(user.getId());

        return UserDeleteResponse.create(messageResourceService.getMessage("user.is.deleted"), LocalDateTime.now());
    }
}

