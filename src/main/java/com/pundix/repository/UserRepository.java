package com.pundix.repository;


import com.pundix.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByIdAndEmail(Long id, String email);

    boolean existsByEmailOrUsername(String email, String username);

    boolean existsByUsernameAndPassword(String username, String password);

    boolean existsByPassword(String password);

    void deleteById(Long id);
}
