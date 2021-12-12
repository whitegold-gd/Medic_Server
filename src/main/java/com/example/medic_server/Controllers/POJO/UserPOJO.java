package com.example.medic_server.Controllers.POJO;

import com.example.medic_server.Models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({ "userAccountInfo" })
public class UserPOJO extends User {

}
