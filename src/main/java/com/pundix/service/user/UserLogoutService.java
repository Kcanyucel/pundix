package com.pundix.service.user;

import com.pundix.entity.user.session.UserSession;
import com.pundix.response.UserLogoutResponse;
import com.pundix.service.MessageResourceService;
import com.pundix.service.user.session.UserSessionService;
import org.springframework.stereotype.Service;

@Service
public class UserLogoutService {

    private final UserSessionService userSessionService;
    private final MessageResourceService messageResourceService;

    public UserLogoutService(UserSessionService userSessionService, MessageResourceService messageResourceService) {
        this.userSessionService = userSessionService;
        this.messageResourceService = messageResourceService;
    }

    public UserLogoutResponse logout(String accessToken) {
        UserSession userSessionInfo = userSessionService.logout(accessToken);

        return UserLogoutResponse.create(messageResourceService.getMessage("user.is.logout"), userSessionInfo.getLogoutDate());
    }
}
