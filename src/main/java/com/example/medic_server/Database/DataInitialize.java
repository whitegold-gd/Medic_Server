package com.example.medic_server.Database;

import com.example.medic_server.Database.DAO.PostDAO;
import com.example.medic_server.Database.DAO.UserDAO;
import com.example.medic_server.Models.Post;
import com.example.medic_server.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitialize implements ApplicationRunner {
    private PostDAO postDAO;
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitialize(PostDAO postDAO, UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments arguments){
        postDAO.save(new Post("New post", "Hello, world!", "tags"));
        postDAO.save(new Post("New post1", "Hello, world! This is a first post.", "tags1"));
        postDAO.save(new Post("New post2", "Hello, world! This is a second post", "tags2"));
        postDAO.save(new Post("New post3", "Hello, world! This is a third post", "tags3"));

        userDAO.save(
                new User(
                        "Nikita",
                        "Ostapenko",
                        "bonnie.terebko@gmail.com",
                        passwordEncoder.encode("admin"),
                        User.Role.Administrator
                )
        );
        userDAO.save(
                new User(
                        "Danila",
                        "Ostapenko",
                        "taleforhelen@gmail.com",
                        passwordEncoder.encode("moder"),
                        User.Role.Moderator
                )
        );
    }
}
