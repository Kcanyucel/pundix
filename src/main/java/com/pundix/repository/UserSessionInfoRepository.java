package com.pundix.repository;

import com.pundix.entity.user.UserSessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserSessionInfoRepository extends JpaRepository<UserSessionInfo, Long> {

    Optional<UserSessionInfo> findUserSessionInfoByUserId(Long id);

    Optional<UserSessionInfo> deleteUserSessionInfoByUserId(Long userId);

    Optional<UserSessionInfo> findUserSessionInfoByAccessToken(String accessToken);

    @Modifying
    @Query("UPDATE UserSessionInfo u SET u.accessToken = null WHERE u.userId = :userId")
    void closeUserSessionByUserId(@Param("userId") Long userId);

}
