package com.pundix.entity.user;

import com.pundix.entity.BaseEntity;
import com.pundix.utils.PasswordEncoder;
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

    private User() {
    }

    public static User create(String username, String password, String email, String name, String surname) {
        User user = new User();
        user.setUsername(username.toLowerCase());
        user.setPassword(PasswordEncoder.encodePassword(password));
        user.setEmail(email.toLowerCase());
        user.setName(name);
        user.setSurname(surname);
        user.setUserRole(UserRole.COSTUMER);
        user.setCreatedDate(LocalDateTime.now());
        user.setUserStatus(UserStatus.ACTIVE);

        return user;
    }

    public static User login(String username, String password) {
        User user = new User();
        user.setUsername(username.toLowerCase());
        user.setPassword(PasswordEncoder.encodePassword(password));

        return user;
    }

    public static User close(User user) {
        user.setUserStatus(UserStatus.CLOSED);
        user.setUsername("CLOSED||<-->" + LocalDateTime.now() + "<-->||" + user.getUsername());

        return user;
    }

    public static User update(User user) {
        user.setPassword(PasswordEncoder.encodePassword(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        user.setName(user.getName());
        user.setSurname(user.getSurname());

        return user;
    }

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

    private void setUsername(String username) {
        this.username = username;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setSurname(String surname) {
        this.surname = surname;
    }

    private void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    private void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    private void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}

