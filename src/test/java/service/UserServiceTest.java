package service;

import com.pundix.entity.User;
import com.pundix.entity.UserStatus;
import com.pundix.repository.UserRepository;
import com.pundix.request.UserCreateRequest;
import com.pundix.request.UserUpdateRequest;
import com.pundix.response.UserCloseResponse;
import com.pundix.response.UserCreateResponse;
import com.pundix.response.UserDeleteResponse;
import com.pundix.response.UserUpdateResponse;
import com.pundix.service.MessageResourceService;
import com.pundix.service.PasswordEncoderService;
import com.pundix.service.UserService;
import faker.UserFaker;
import faker.UserRequestFaker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    private PasswordEncoderService passwordEncoderService;

    @Mock
    private MessageResourceService messageResourceService;

    @InjectMocks
    UserService userService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    public void verifyCreateUserSuccessfully() {
        UserCreateRequest request = UserRequestFaker.fromCreate();

        when(passwordEncoderService.encodePassword(anyString())).thenReturn("password");
        when(userRepository.save(Mockito.any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(1L);
            return savedUser;
        });

        UserCreateResponse response = userService.createUser(request);
        verify(userRepository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();

        assertNotNull(capturedUser);
        assertThat(capturedUser.getId(), is(equalTo(1L)));
        assertThat(response.getUsername(), is(equalTo("jameswilliam")));
        assertThat(capturedUser.getPassword(), is(equalTo("password")));
    }

    @Test
    public void verifyGetUserSuccessfully() {
        User user = UserFaker.generate();
        when(userRepository.findUserById(user.getId())).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.getUserById(user.getId());

        assertNotNull(foundUser);
        assertThat(foundUser.get().getId(), is(equalTo(1L)));
        assertThat(foundUser.get().getUsername(), is(equalTo(UserFaker.USERNAME.toLowerCase())));
        assertThat(foundUser.get().getPassword(), is(equalTo(UserFaker.PASSWORD)));
        assertThat(foundUser.get().getEmail(), is(equalTo(UserFaker.EMAIL)));
        assertThat(foundUser.get().getName(), is(equalTo(UserFaker.NAME)));
        assertThat(foundUser.get().getSurname(), is(equalTo(UserFaker.SURNAME)));
    }

    @Test
    public void verifyUpdateUserSuccessfully() {
        User user = UserFaker.generate();
        UserUpdateRequest userUpdateRequest = UserRequestFaker.fromUpdate();

        when(userRepository.findUserById(user.getId())).thenReturn(Optional.of(user));
        when(passwordEncoderService.encodePassword(anyString())).thenReturn("encodingPassword");
        when(messageResourceService.getMessage(anyString())).thenReturn("message");

        UserUpdateResponse response = userService.updateUser(user.getId(), userUpdateRequest);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        verify(userRepository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();

        assertNotNull(response);
        assertNotNull(response.getUpdatedDate());
        assertThat(response.getMessage(), is(equalTo("message")));
        assertThat(capturedUser.getPassword(), is(equalTo("encodingPassword")));
        assertThat(response.getUsername(), is(equalTo("William")));
    }

    @Test
    public void verifyCloseUserSuccessfully() {
        User user = UserFaker.generate();
        when(userRepository.findUserById(user.getId())).thenReturn(Optional.of(user));
        when(messageResourceService.getMessage(anyString())).thenReturn("message");
        UserCloseResponse response = userService.closeUser(user.getId());

        assertNotNull(response);
        assertNotNull(response.getClosedDate());
        assertThat(response.getUsername(), is(equalTo("jameswilliam")));
        assertThat(response.getMessage(), is(equalTo("message")));
        assertThat(user.getUserStatus(), is(equalTo(UserStatus.CLOSED)));
    }

    @Test
    public void verifyDeleteUserSuccessfully() {
        User user = UserFaker.generate();
        when(userRepository.findUserById(user.getId())).thenReturn(Optional.of(user));
        when(messageResourceService.getMessage(anyString())).thenReturn("message");
        UserDeleteResponse response = userService.deleteUser(user.getId());

        assertNotNull(response);
        assertNotNull(response.getDeletedDate());
        assertThat(response.getUsername(), is(equalTo("jameswilliam")));
        assertThat(response.getMessage(), is(equalTo("message")));
    }
}


