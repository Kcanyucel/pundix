package service;

import com.pundix.entity.user.User;
import com.pundix.entity.user.UserSessionInfo;
import com.pundix.entity.user.UserStatus;
import com.pundix.repository.UserRepository;
import com.pundix.repository.UserSessionInfoRepository;
import com.pundix.request.UserCreateRequest;
import com.pundix.request.UserLoginRequest;
import com.pundix.request.UserUpdateRequest;
import com.pundix.response.user.*;
import com.pundix.service.MessageResourceService;
import com.pundix.service.PasswordEncoderService;
import com.pundix.service.TokenService;
import com.pundix.service.UserService;
import faker.user.UserFaker;
import faker.user.UserRequestFaker;
import faker.user.UserSessionInfoFaker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserSessionInfoRepository userSessionInfoRepository;

    @Mock
    private PasswordEncoderService passwordEncoderService;

    @Mock
    private MessageResourceService messageResourceService;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    UserService userService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Captor
    private ArgumentCaptor<UserSessionInfo> userSessionInfoCaptor;

    private final String MOCK_PASSWORD = "password";
    private final String MOCK_ACCESS_TOKEN = "accessToken";
    private final String MOCK_MESSAGE = "message";

    @Test
    public void verifyCreateUserSuccessfully() {
        UserCreateRequest request = UserRequestFaker.fromCreate();

        when(passwordEncoderService.encodePassword(anyString())).thenReturn(MOCK_PASSWORD);
        when(tokenService.createAccessToken()).thenReturn(MOCK_ACCESS_TOKEN);
        when(userRepository.save(Mockito.any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(1L);
            return savedUser;
        });
        when(userSessionInfoRepository.save(Mockito.any(UserSessionInfo.class))).thenAnswer(invocation -> {
            UserSessionInfo savedUserSessionInfo = invocation.getArgument(0);
            savedUserSessionInfo.setId(1L);
            return savedUserSessionInfo;
        });
        UserCreateResponse response = userService.createUser(request);

        verify(userRepository).save(userCaptor.capture());
        verify(userSessionInfoRepository).save(userSessionInfoCaptor.capture());

        User capturedUser = userCaptor.getValue();
        UserSessionInfo capturedUserSessionInfo = userSessionInfoCaptor.getValue();

        assertNotNull(response);
        assertNotNull(capturedUser);
        assertNotNull(capturedUserSessionInfo);

        assertThat(capturedUser.getId(), is(equalTo(1L)));
        assertThat(capturedUser.getUserStatus(), is(equalTo(UserStatus.ACTIVE)));
        assertThat(capturedUser.getPassword(), is(equalTo(MOCK_PASSWORD)));

        assertThat(response.getId(), is(equalTo(1L)));
        assertThat(response.getUsername(), is(equalTo(request.getUsername().toLowerCase())));
        assertThat(response.getEmail(), is(equalTo(request.getEmail().toLowerCase())));
        assertThat(response.getAccessToken(), is(equalTo(MOCK_ACCESS_TOKEN)));
        assertNotNull(response.getCreatedDate());

        assertThat(capturedUserSessionInfo.getId(), is(equalTo(1L)));
        assertThat(capturedUserSessionInfo.getUserId(), is(equalTo(1L)));
        assertThat(capturedUserSessionInfo.getUsername(), is(equalTo(request.getUsername().toLowerCase())));
        assertThat(capturedUserSessionInfo.getAccessToken(), is(equalTo(MOCK_ACCESS_TOKEN)));
        assertNotNull(capturedUserSessionInfo.getLoginDate());
    }

    @Test
    public void verifyLoginUserSuccessfully() {
        User user = UserFaker.generate();
        UserLoginRequest request = UserRequestFaker.fromLogin();

        when(tokenService.createAccessToken()).thenReturn(MOCK_ACCESS_TOKEN);
        when(userRepository.findUserByUsername(request.getUsername())).thenReturn(Optional.of(user));
        when(messageResourceService.getMessage(anyString())).thenReturn(MOCK_MESSAGE);

        when(userSessionInfoRepository.save(Mockito.any(UserSessionInfo.class))).thenAnswer(invocation -> {
            UserSessionInfo savedUserSessionInfo = invocation.getArgument(0);
            savedUserSessionInfo.setId(1L);
            return savedUserSessionInfo;
        });
        UserLoginResponse response = userService.loginUser(request);

        verify(userSessionInfoRepository).save(userSessionInfoCaptor.capture());
        UserSessionInfo capturedUserSessionInfo = userSessionInfoCaptor.getValue();

        assertNotNull(capturedUserSessionInfo);
        assertNotNull(response);

        assertThat(response.getMessage(), is(equalTo(MOCK_MESSAGE)));
        assertThat(response.getAccessToken(), is(equalTo(MOCK_ACCESS_TOKEN)));
        assertNotNull(response.getLoginDate());

        assertThat(capturedUserSessionInfo.getId(), is(equalTo(1L)));
        assertThat(capturedUserSessionInfo.getUserId(), is(equalTo(user.getId())));
        assertThat(capturedUserSessionInfo.getUsername(), is(equalTo(user.getUsername().toLowerCase())));
        assertThat(capturedUserSessionInfo.getAccessToken(), is(equalTo(MOCK_ACCESS_TOKEN)));
        assertNotNull(capturedUserSessionInfo.getLoginDate());
    }

    @Test
    public void verifyLogoutUserSuccessfully() {
        UserSessionInfo userSessionInfo = UserSessionInfoFaker.generate();

        when(tokenService.createAccessToken()).thenReturn(MOCK_ACCESS_TOKEN);
        when(messageResourceService.getMessage(anyString())).thenReturn(MOCK_MESSAGE);
        when(userSessionInfoRepository.findUserSessionInfoByAccessToken(userSessionInfo.getAccessToken())).thenReturn(Optional.of(userSessionInfo));

        UserLogoutResponse response = userService.logoutUser(userSessionInfo.getAccessToken());

        verify(userSessionInfoRepository).save(userSessionInfoCaptor.capture());
        UserSessionInfo capturedUserSessionInfo = userSessionInfoCaptor.getValue();

        assertNotNull(capturedUserSessionInfo);
        assertNotNull(response);

        assertThat(response.getMessage(), is(equalTo(MOCK_MESSAGE)));
        assertThat(response.getUsername(), is(equalTo(userSessionInfo.getUsername())));
        assertNotNull(response.getLogoutDate());

        assertThat(capturedUserSessionInfo.getId(), is(equalTo(1L)));
        assertThat(capturedUserSessionInfo.getUserId(), is(equalTo(userSessionInfo.getId())));
        assertThat(capturedUserSessionInfo.getUsername(), is(equalTo(userSessionInfo.getUsername().toLowerCase())));
        assertThat(capturedUserSessionInfo.getAccessToken(), is(nullValue()));
        assertNotNull(capturedUserSessionInfo.getLogoutDate());
        assertNotNull(capturedUserSessionInfo.getLoginDate());
    }


    @Test
    public void verifyGetUserSuccessfully() {
        User user = UserFaker.generate();
        when(userRepository.findUserById(user.getId())).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.findUserById(user.getId());

        assertNotNull(foundUser);
        assertThat(foundUser.get().getId(), is(equalTo(UserFaker.ID)));
        assertThat(foundUser.get().getUsername(), is(equalTo(user.getUsername())));
        assertThat(foundUser.get().getPassword(), is(equalTo(user.getPassword())));
        assertThat(foundUser.get().getEmail(), is(equalTo(user.getEmail())));
        assertThat(foundUser.get().getName(), is(equalTo(user.getName())));
        assertThat(foundUser.get().getSurname(), is(equalTo(user.getSurname())));
    }

    @Test
    public void verifyUpdateUserSuccessfully() {
        User user = UserFaker.generate();
        UserUpdateRequest userUpdateRequest = UserRequestFaker.fromUpdate();

        when(userRepository.findUserById(user.getId())).thenReturn(Optional.of(user));
        when(passwordEncoderService.encodePassword(anyString())).thenReturn(MOCK_PASSWORD);
        when(messageResourceService.getMessage(anyString())).thenReturn(MOCK_MESSAGE);

        UserUpdateResponse response = userService.updateUser(user.getId(), userUpdateRequest);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        verify(userRepository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();

        assertNotNull(response);
        assertNotNull(response.getUpdatedDate());
        assertThat(capturedUser.getPassword(), is(equalTo(MOCK_PASSWORD)));
        assertThat(response.getMessage(), is(equalTo(MOCK_MESSAGE)));
        assertThat(response.getUsername(), is(equalTo(user.getUsername())));
    }

    @Test
    public void verifyCloseUserSuccessfully() {
        User user = UserFaker.generate();
        when(userRepository.findUserById(user.getId())).thenReturn(Optional.of(user));
        Mockito.doNothing().when(userSessionInfoRepository).closeSessionByUserId(user.getId());

        when(messageResourceService.getMessage(anyString())).thenReturn(MOCK_MESSAGE);
        UserCloseResponse response = userService.closeUser(user.getId());

        assertTrue(userSessionInfoRepository.findUserSessionInfoByUserId(user.getId()).isEmpty());
        assertNotNull(response);
        assertNotNull(response.getClosedDate());
        assertThat(response.getUsername(), is(equalTo(user.getUsername())));
        assertThat(response.getMessage(), is(equalTo(MOCK_MESSAGE)));
        assertThat(user.getUserStatus(), is(equalTo(UserStatus.CLOSED)));
    }

    @Test
    public void verifyDeleteUserSuccessfully() {
        User user = UserFaker.generate();
        when(userRepository.findUserById(user.getId())).thenReturn(Optional.of(user));
        when(userSessionInfoRepository.deleteUserSessionInfoByUserId(user.getId())).thenReturn(null);
        when(messageResourceService.getMessage(anyString())).thenReturn(MOCK_MESSAGE);
        UserDeleteResponse response = userService.deleteUser(user.getId());

        assertTrue(userSessionInfoRepository.findUserSessionInfoByUserId(user.getId()).isEmpty());
        assertNotNull(response);
        assertNotNull(response.getDeletedDate());
        assertThat(response.getUsername(), is(equalTo(user.getUsername())));
        assertThat(response.getMessage(), is(equalTo(MOCK_MESSAGE)));
    }
}


