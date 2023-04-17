package service

import com.pundix.entity.user.User
import com.pundix.entity.user.UserSession
import com.pundix.exception.custom.UserLoginFailedExpection
import com.pundix.repository.UserRepository
import com.pundix.request.UserLoginRequest
import com.pundix.service.MessageResourceService
import com.pundix.service.UserAuthService
import com.pundix.service.UserSessionService
import spock.lang.Specification

class UserAuthServiceSpec extends Specification {

    private UserRepository userRepository;
    private MessageResourceService messageResourceService;
    private UserSessionService userSessionService;
    private UserAuthService userAuthService;

    static final String USERNAME = "Jameswilliam"
    static final String PASSWORD = "james87"

    def "setup"() {
        userRepository = Mock(UserRepository)
        userSessionService = Mock(UserSessionService)
        messageResourceService = Mock(MessageResourceService)
        userAuthService = new UserAuthService(userRepository, userSessionService, messageResourceService)
    }

    def "should be login user successfully"() {
        given:
        def request = new UserLoginRequest(USERNAME, PASSWORD)
        def user = User.login(request.username(), request.password())
        def userSession = UserSession.create(1L, USERNAME)
        def message = "Login Message"

        and:
        1 * userRepository.existsByUsernameAndPassword(_, _) >> true
        1 * userRepository.findUserByUsername(_) >> Optional.of(user)
        1 * userSessionService.create(_, _) >> userSession
        1 * messageResourceService.getMessage(_) >> message

        when:
        def response = userAuthService.login(request)

        then:
        response.message() == message
        response.loginDate() != null
    }

    def "should be thrown UserLoginFailedExpection on user login when username or password is not correct"() {
        given:
        def request = new UserLoginRequest(USERNAME, PASSWORD)

        and:
        1 * userRepository.existsByUsernameAndPassword(_, _) >> false

        when:
        userAuthService.login(request)

        then:
        thrown(UserLoginFailedExpection)
    }

    def "should be logout user successfully"() {
        given:
        def accessToken = "accessToken"
        def message = "Logout Message"
        def createSession = UserSession.create(1L, USERNAME)
        def logoutSession = UserSession.logout(createSession)

        and:
        1 * userSessionService.logout(_) >> logoutSession
        1 * messageResourceService.getMessage(_) >> message

        when:
        def response = userAuthService.logout(accessToken)

        then:
        response.message() == message
        response.logoutDate() != null
    }
}
