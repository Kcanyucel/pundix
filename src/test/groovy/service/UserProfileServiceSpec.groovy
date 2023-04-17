package service

import com.pundix.entity.user.User
import com.pundix.exception.custom.UserNotFoundException
import com.pundix.repository.UserRepository
import com.pundix.service.UserProfileService
import spock.lang.Specification

class UserProfileServiceSpec extends Specification {

    private UserRepository userRepository
    private UserProfileService userProfileService

    def "setup"() {
        userRepository = Mock(UserRepository)
        userProfileService = new UserProfileService(userRepository)
    }

    static final String USERNAME = "Jameswilliam"
    static final String PASSWORD = "james87"
    static final String EMAIL = "James_william87@outlook.com"
    static final String NAME = "James"
    static final String SURNAME = "William"

    def "should be get user successfully"() {
        given:
        def user = User.create(USERNAME, PASSWORD, EMAIL, NAME, SURNAME)

        and:
        1 * userRepository.findUserById(_) >> Optional.of(user)

        when:
        def response = userProfileService.get(1L)

        then:
        response.id() == user.id
        response.username() == user.username
        response.email() == user.email
        response.name() == user.name
        response.surname() == user.surname
    }

    def "should be thrown UserNotFoundException on user update when user is not found"() {
        given:
        1 * userRepository.findUserById(_) >> { throw new UserNotFoundException() }

        when:
        userProfileService.get(1L)

        then:
        thrown(UserNotFoundException)
    }
}
