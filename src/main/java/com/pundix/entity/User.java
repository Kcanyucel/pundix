package com.pundix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR", strategy = GenerationType.SEQUENCE)
    private Long id;

   // @NotNull(message = "error.user.username.null")
   // @NotBlank(message = "error.user.username.empty")
   // @Min(value = 5, message = "error.user.username.min.length")
   // @Max(value = 20, message = "error.user.username.max.length")
   // @Column(name = "USERNAME", nullable = false)
    private String username;

    // @NotNull(message = "error.user.password.null")
    // @NotBlank(message = "error.user.password.empty")
    // @Min(value = 5, message = "error.user.password.min.length")
    //  @Max(value = 15, message = "error.user.password.max.length")
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    //  @NotNull(message = "error.user.email.null")
    //  @NotBlank(message = "error.user.email.empty")
    @Column(name = "EMAIL", nullable = false)
    private String email;

    // @Min(value = 3, message = "error.user.name.min.length")
    // @Max(value = 30, message = "error.user.name.max.length")
    @Column(name = "NAME")
    private String name;

    //@Min(value = 3, message = "error.user.surname.min.length")
    //@Max(value = 30, message = "error.user.surname.max.length")
    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private UserStatus userStatus = UserStatus.ACTIVE;

    public User(String username, String password, String email, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public User(String username, String password, String email, String name, String surname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}