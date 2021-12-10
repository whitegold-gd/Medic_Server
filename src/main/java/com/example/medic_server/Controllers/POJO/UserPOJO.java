package com.example.medic_server.Controllers.POJO;

import com.example.medic_server.Models.User;
import com.example.medic_server.Models.UserAccountInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({ "userAccountInfo" })
public class UserPOJO extends User {
    private UserAccountInfo.Role role;

    public UserPOJO(User user) {
        setId(user.getId());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setEmail(user.getEmail());
        setUserAccountInfo(user.getUserAccountInfo());
        this.role = user.getUserAccountInfo().getRole();
    }

    public UserAccountInfo.Role getRole() {
        role = getUserAccountInfo().getRole();
        return role;
    }

    public void setRole(UserAccountInfo.Role role) {
        getUserAccountInfo().setRole(role);
        this.role = role;
    }
}
