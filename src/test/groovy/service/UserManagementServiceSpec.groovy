package service

import com.pundix.entity.user.User
import com.pundix.entity.user.UserSession
import com.pundix.exception.custom.UserAlreadyExistsException
import com.pundix.exception.custom.UserEmailAlreadyExistsException
import com.pundix.exception.custom.UserNotFoundException
import com.pundix.repository.UserRepository
import com.pundix.request.UserCreateRequest
import com.pundix.request.UserUpdateRequest
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

    def "should be create user successfully"() {
        given:
        def request = new UserCreateRequest(USERNAME, PASSWORD, EMAIL, NAME, SURNAME)
        def userSession = UserSession.create(1L, USERNAME)

        and:
        1 * userManagementValidator.validateForCreate(request)
        1 * userRepository.existsByEmailOrUsername(_, _) >> false
        1 * userRepository.save(_) >> { User savedUser -> savedUser.id = 1L; return savedUser }
        1 * userSessionService.create(_, _) >> userSession

        when:
        def response = userManagementService.create(request)

        then:
        response.id() == userSession.userId
        response.username() == request.username().toLowerCase()
        response.email() == request.email().toLowerCase()
        response.createdDate() != null
    }

    def "should be thrown UserAlreadyExistsException on user create when email or username already exists"() {
        given:
        def request = new UserCreateRequest(USERNAME, PASSWORD, EMAIL, NAME, SURNAME)

        and:
        1 * userManagementValidator.validateForCreate(request)
        1 * userRepository.existsByEmailOrUsername(_, _) >> true

        when:
        userManagementService.create(request)

        then:
        thrown(UserAlreadyExistsException)
    }

    def "should be update user successfully"() {
        given:
        def request = new UserUpdateRequest(PASSWORD, EMAIL, NAME, SURNAME)
        def user = User.create(USERNAME, PASSWORD, EMAIL, NAME, SURNAME)
        def updatedUser = User.update(user)
        def message = "Update Message"

        and:
        1 * userManagementValidator.validateForUpdate(request)
        1 * userRepository.existsByEmail(_) >> false
        1 * userRepository.existsByIdAndEmail(_, _) >> true
        1 * userRepository.findUserById(_) >> Optional.of(user)
        1 * userRepository.save(updatedUser) >> updatedUser
        1 * messageResourceService.getMessage(_) >> message

        when:
        def response = userManagementService.update(1L, request)

        then:
        notThrown(UserAlreadyExistsException)
        response.username() == updatedUser.username
        response.message() == message
        response.updatedDate() != null
    }

    def "should be thrown UserNotFoundException on user update when user is not found"() {
        given:
        def request = new UserUpdateRequest(PASSWORD, EMAIL, NAME, SURNAME)

        and:
        1 * userManagementValidator.validateForUpdate(request)
        1 * userRepository.existsByEmail(_) >> false
        1 * userRepository.existsByIdAndEmail(_, _) >> true
        1 * userRepository.findUserById(_) >> { throw new UserNotFoundException() }

        when:
        userManagementService.update(1L, request)

        then:
        thrown(UserNotFoundException)
    }

    def "should be thrown UserAlreadyExistsException on user update when email already exists or email is available to user who updated it before"() {
        given:
        def request = new UserUpdateRequest(PASSWORD, EMAIL, NAME, SURNAME)

        and:
        1 * userManagementValidator.validateForUpdate(_)
        1 * userRepository.existsByEmail(_) >> true
        1 * userRepository.existsByIdAndEmail(1L, _) >> false

        when:
        userManagementService.update(1L, request)

        then:
        thrown(UserEmailAlreadyExistsException)
    }

    def "should be delete user successfully!"() {
        given:
        def user = User.create(USERNAME, PASSWORD, EMAIL, NAME, SURNAME)
        def message = "Delete Message"

        and:
        1 * userRepository.findUserById(_) >> Optional.of(user)
        1 * userRepository.deleteById(_)
        1 * userSessionService.delete(_)
        1 * messageResourceService.getMessage(_) >> message

        when:
        def response = userManagementService.delete(1L)

        then:
        response.message() == message
        response.deletedDate() != null

    }

    def "should be thrown UserNotFoundException on user delete when user is not found"() {
        given:
        1 * userRepository.findUserById(_) >> { throw new UserNotFoundException() }

        when:
        userManagementService.delete(1L)

        then:
        thrown(UserNotFoundException)
    }

    def "should be close user successfully!"() {
        given:
        def id = 1L
        def user = User.create(USERNAME, PASSWORD, EMAIL, NAME, SURNAME)
        def message = "Close Message"

        and:
        1 * userRepository.findUserById(_) >> Optional.of(user)
        1 * userRepository.save(_ as User) >> user
        1 * userSessionService.close(_)
        1 * messageResourceService.getMessage(_) >> message

        when:
        def response = userManagementService.close(id)

        then:
        response.message() == message
        response.closedDate() != null
    }

    def "should be thrown UserNotFoundException on user close when user is not found"() {
        given:
        1 * userRepository.findUserById(_) >> { throw new UserNotFoundException() }

        when:
        userManagementService.close(1L)

        then:
        thrown(UserNotFoundException)
    }
}






