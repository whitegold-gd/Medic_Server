package com.example.medic_server.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table (name = "account_info")
public class UserAccountInfo {
    @Id
    @GeneratedValue
    @Column
    private UUID id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private Role role;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    public UserAccountInfo() {
    }

    public UserAccountInfo(String login, String password, Role role, User user) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.user = user;
    }

    public enum Role {
        Administrator,
        Moderator,
        User,
        Guest
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
