package com.example.pundix.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public record User(

    @Id @GeneratedValue(generator = "ID_GENERATOR", strategy = GenerationType.SEQUENCE)
    Long id,

    @Column(name = "USERNAME", nullable = false)
    String username,

    @Column(name = "PASSWORD", nullable = false)
    String password,

    @Column(name = "EMAIL", nullable = false)
    String email,

    @Column(name = "NAME")
    String name,

    @Column(name = "SURNAME")
    String surname,

    @Column(name = "CREATED_DATE")
    LocalDateTime createdDate,

    @Enumerated(EnumType.STRING) @Column(name = "STATUS")
    UserStatus userStatus) {

    public User() {
        this(null, null, null, null, null, null, null, UserStatus.ACTIVE);
    }
}