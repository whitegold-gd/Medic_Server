package com.example.medic_server.Controllers;

import com.example.medic_server.Controllers.POJO.UserPOJO;
import com.example.medic_server.Database.DAO.UserDAO;
import com.example.medic_server.Models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/byId")
    ResponseEntity<User> getInfoById(@RequestParam String id){
        User user = userDAO.findByIdLike(UUID.fromString(id));
        if (user != null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/byEmail")
    ResponseEntity<User> getInfoByEmail(@RequestParam String email){
        User user = userDAO.findByEmail(email);
        if (user != null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/byToken")
    ResponseEntity<User> getInfoByToken(Authentication authentication) {
        User user = userDAO.findByEmail(authentication.getName());
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }
}
