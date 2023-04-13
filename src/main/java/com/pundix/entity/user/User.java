package com.pundix.entity.user;

import com.pundix.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"user\"")
@SequenceGenerator(name = "id_generator", sequenceName = "seq_user")
public class User extends BaseEntity {

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private UserStatus userStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private UserRole userRole;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}

