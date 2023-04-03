package com.example.pundix.repository;

import com.example.pundix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findMemberById(Long id);

    Optional<User> findMemberByUsername(String username);

    Optional<User> findMemberByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    void deleteById(Long id);
}
