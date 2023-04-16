import com.pundix.entity.user.User
import com.pundix.entity.user.UserSession
import com.pundix.repository.UserRepository
import com.pundix.request.UserCreateRequest
import com.pundix.service.MessageResourceService
import com.pundix.service.UserManagementService
import com.pundix.service.UserSessionService
import com.pundix.validator.UserManagementValidator
import spock.lang.Specification

class UserManagementServiceSpec extends Specification {

    private UserRepository userRepository
    private UserSessionService userSessionService
    private UserManagementValidator userManagementValidator
    private MessageResourceService messageResourceService
    private UserManagementService userManagementService

    static final String USERNAME = "Jameswilliam"
    static final String PASSWORD = "james87"
    static final String EMAIL = "James_william87@outlook.com"
    static final String NAME = "James"
    static final String SURNAME = "William"

    def "setup"() {
        userRepository = Mock(UserRepository)
        userSessionService = Mock(UserSessionService)
        userManagementValidator = Mock(UserManagementValidator)
        messageResourceService = Mock(MessageResourceService)

        userManagementService = new UserManagementService(userRepository, userSessionService, userManagementValidator, messageResourceService)
    }


    def "should create user successfully"() {
        given:
        def request = new UserCreateRequest(USERNAME, PASSWORD, EMAIL, NAME, SURNAME)
        def user = User.create(USERNAME, PASSWORD, EMAIL, NAME, SURNAME)
        def userSession = UserSession.create(1L, USERNAME)

        when:
        def response = userManagementService.create(request)

        then:
        1 * userManagementValidator.validateForCreate(_ as UserCreateRequest) > request
        1 * userRepository.existsByEmailOrUsername(request.email(), request.username()) >> false
        1 * User.create(_) >> user
        1 * userRepository.save(_ as User) >> user
        1 * userSessionService.create(_ as UserSession) >> userSession

        response.id() == userSession.userId
        response.username() == request.username().toLowerCase()
        response.email() == request.email().toLowerCase()
        response.createdDate() != null
        response.accessToken() == userSession.accessToken
    }
}






