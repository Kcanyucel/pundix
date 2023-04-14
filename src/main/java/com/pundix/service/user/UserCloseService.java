package com.pundix.service.user;

import com.pundix.entity.user.User;
import com.pundix.entity.user.UserStatus;
import com.pundix.exception.custom.UserNotFoundException;
import com.pundix.repository.UserRepository;
import com.pundix.response.UserCloseResponse;
import com.pundix.service.MessageResourceService;
import com.pundix.service.user.session.UserSessionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserCloseService {

    private final UserRepository userRepository;
    private final UserSessionService userSessionService;
    private final MessageResourceService messageResourceService;

    public UserCloseService(UserRepository userRepository, UserSessionService userSessionInfoService, MessageResourceService messageResourceService) {
        this.userRepository = userRepository;
        this.userSessionService = userSessionInfoService;
        this.messageResourceService = messageResourceService;
    }

    @Transactional
    public UserCloseResponse close(Long id) {
        User user = userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);
        User closedUser = build(user);

        userRepository.save(user);
        userSessionService.close(closedUser.getId());

        return UserCloseResponse.create(messageResourceService.getMessage("user.is.closed"), LocalDateTime.now());
    }

    private User build(User user) {
        user.setUserStatus(UserStatus.CLOSED);
        user.setUsername("CLOSED||<-->" + LocalDateTime.now() + "<-->||" + user.getUsername());
        return user;
    }
}
