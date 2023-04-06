package service;

import com.pundix.entity.User;
import com.pundix.repository.UserRepository;
import com.pundix.request.UserRequest;
import com.pundix.response.UserCreateResponse;
import com.pundix.response.UserUpdateResponse;
import com.pundix.service.MessageResourceService;
import com.pundix.service.UserService;
import faker.UserFaker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void verifyCreateUserSuccessfully() {
        UserRequest userRequest = new UserRequest("Tristian43", "Romelik12", "Antony18@hotmail.com", "Tristian", "Romelik");
        User user = UserFaker.createDefaultUser();

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        UserCreateResponse response = userService.createUser(userRequest);

        assertNotNull(response);
        assertThat(response.id(), is(equalTo(1L)));
        assertThat(response.username(), is(equalTo("Tristian43")));
        assertThat(response.email(), is(equalTo("Antony18@hotmail.com")));
    }

    @Test
    public void verifyUpdateUserSuccessfully() {
        UserRequest userRequest = new UserRequest("Marcom19@hotmail.com", "Marcom", "Lucia");
        User user = UserFaker.createDefaultUser();

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        UserUpdateResponse response = userService.updateUser(user.getId(), userRequest);

        assertNotNull(response);
        assertThat(response.message(), is(equalTo("Kullanıcı Güncellenmiştir.")));
        assertThat(response.username(), is(equalTo("Tristian43")));
    }

    @Test
    public void verifyGetUserSuccessfully() {
        User user = UserFaker.createDefaultUser();

        when(userRepository.findUserById(1L)).thenReturn(Optional.of(user));
        Optional<User> foundUser = userService.getUserById(1L);

        assertNotNull(foundUser);
        assertThat(foundUser.get().getId(), is(equalTo(1L)));
        assertThat(foundUser.get().getUsername(), is(equalTo("Tristian43")));
        assertThat(foundUser.get().getPassword(), is(equalTo("Romelik12")));
        assertThat(foundUser.get().getEmail(), is(equalTo("Antony18@hotmail.com")));
        assertThat(foundUser.get().getName(), is(equalTo("Tristian")));
        assertThat(foundUser.get().getSurname(), is(equalTo("Romelik")));
    }

}


