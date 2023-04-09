package com.pundix.entity.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_session_info")
public class UserSessionInfo {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "ACCESS_TOKEN")
    private String accessToken;

    @Column(name = "LOGIN_DATE")
    private LocalDateTime loginDate;

    @Column(name = "LOGOUT_DATE")
    private LocalDateTime logoutDate;
}
