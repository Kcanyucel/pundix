package com.pundix.repository;

import com.pundix.entity.user.UserSessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserSessionInfoRepository extends JpaRepository<UserSessionInfo, Long> {

    Optional<UserSessionInfo> findUserSessionInfoByUserId(Long id);

    Optional<UserSessionInfo> findUserSessionInfoByAccessToken(String accessToken);

    List<UserSessionInfo> findUserSessionsInfoByUserId(Long userId);

    void deleteUserSessionsInfoByUserId(Long userId);

    @Modifying
    @Query("UPDATE UserSessionInfo u SET u.logoutDate = CURRENT_TIMESTAMP WHERE u.userId = :userId")
    void closeUserSessionsInfoByUserId(@Param("userId") Long userId);

}
