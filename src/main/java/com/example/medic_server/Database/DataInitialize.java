package com.example.medic_server.Database;

import com.example.medic_server.Database.DAO.PostDAO;
import com.example.medic_server.Database.DAO.UserAccountInfoDAO;
import com.example.medic_server.Database.DAO.UserDAO;
import com.example.medic_server.Models.Post;
import com.example.medic_server.Models.User;
import com.example.medic_server.Models.UserAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Commit;

@Component
public class DataInitialize implements ApplicationRunner {
    private PostDAO postDAO;
    private UserAccountInfoDAO userAccountInfoDAO;
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitialize(PostDAO postDAO, UserAccountInfoDAO userAccountInfoDAO, UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.postDAO = postDAO;
        this.userAccountInfoDAO = userAccountInfoDAO;
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments arguments){
        postDAO.save(new Post("New post", "Hello, world!", "tags"));
        postDAO.save(new Post("New post1", "Hello, world! This is a first post.", "tags1"));
        postDAO.save(new Post("New post2", "Hello, world! This is a second post", "tags2"));
        postDAO.save(new Post("New post3", "Hello, world! This is a third post", "tags3"));

        userAccountInfoDAO.save(
                new UserAccountInfo(
                        "admin",
                        passwordEncoder.encode("admin"),
                        UserAccountInfo.Role.Administrator,
                        userDAO.save(new User("admin", "admin"))
                )
        );
        userAccountInfoDAO.save(
                new UserAccountInfo(
                        "moder",
                        passwordEncoder.encode("moder"),
                        UserAccountInfo.Role.Moderator,
                        userDAO.save(new User("moder", "moder"))
                )
        );
    }
}
