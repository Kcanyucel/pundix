package faker;

import com.pundix.entity.User;

public class UserFaker {

    public static User createDefaultUser() {
        return new User(1L, "Tristian43", "Romelik12", "Antony18@hotmail.com", "Tristian", "Romelik");
    }

    public static User createUserWithCustomData(long id, String username, String password, String email, String firstName, String lastName) {
        return new User(id, username, password, email, firstName, lastName);
    }
}