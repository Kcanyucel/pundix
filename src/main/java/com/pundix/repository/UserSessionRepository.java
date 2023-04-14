package com.pundix.repository;

import com.pundix.entity.user.session.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {

    Optional<UserSession> findUserSessionInfoByUserId(Long id);

    boolean existsUserSessionInfoByAccessToken(String accessToken);

    Optional<UserSession> findUserSessionInfoByAccessToken(String accessToken);

    List<UserSession> findUserSessionsInfoByUserId(Long userId);

    void deleteUserSessionsInfoByUserId(Long userId);

    @Modifying
    @Query("UPDATE UserSession u SET u.logoutDate = CURRENT_TIMESTAMP WHERE u.userId = :userId")
    void closeUserSessionsInfoByUserId(@Param("userId") Long userId);

}
