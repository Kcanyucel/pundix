package com.pundix.service;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
/*
@ExtendWith(SpringExtension.class)
class UserCreateServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoderService passwordEncoderService;

    @Mock
    private UserSessionService userSessionInfoService;

    @InjectMocks
    private UserCreateService userCreateService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    public void shouldThrowUserAlreadyExistException() {
        //Given
        final UserCreateRequest request = UserCreateRequestBuilder.userCreateRequestBuilder()
            .username("username")
            .email("email")
            .build();

        when(userRepository.existsByEmailOrUsername(request.getEmail(), request.getUsername())).thenReturn(true);

        //When
        try {
            userCreateService.create(request);
        } catch (UserAlreadyExistsException ex) {
            verify(userRepository).existsByEmailOrUsername(request.getEmail(), request.getUsername());
            return;
        }

        fail();
    }

    public void shouldSaveUserSuccessfully() {
        //Given
        final UserCreateRequest request = UserCreateRequestBuilder.userCreateRequestBuilder()
            .username("username")
            .email("email")
            .name("name")
            .surname("surname")
            .email("email")
            .build();

        final UserSession userSessionInfo = UserSessionInfoBuilder.userSessionInfoBuilder()
                .accessToken("access_token")
            .build();

        when(userRepository.existsByEmailOrUsername(request.getEmail(), request.getUsername())).thenReturn(false);
        when(userSessionInfoService.create(anyLong(), request.getUsername())).thenReturn(userSessionInfo);

        //When
        final UserCreateResponse response = userCreateService.create(request);

        verify(userRepository).existsByEmailOrUsername(request.getEmail(), request.getUsername());
        verify(userRepository).save(userCaptor.capture());

        final User savedUser = userCaptor.getValue();
        response.getAccessToken() == userSessionInfo.getAccessToken()

    }

} */