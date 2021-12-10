package com.example.medic_server.Controllers;

import com.example.medic_server.Database.DAO.PostDAO;
import com.example.medic_server.Database.DAO.UserDAO;
import com.example.medic_server.Models.Post;
import com.example.medic_server.Models.UserAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("post")
public class PostController {

    private final PostDAO postDAO;
    private final UserDAO userDAO;

    public PostController(PostDAO postDAO, UserDAO userDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
    }

    @GetMapping("/all")
    ResponseEntity<List<Post>> getAllPosts() {
        List<Post> result = postDAO.findAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    ResponseEntity<Post> addNewPostToList(@RequestBody Post post){
        postDAO.save(post);
        return ResponseEntity.ok(post);
    }

    @RequestMapping(value="/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    void deletePostById(@RequestParam UUID id){
        postDAO.deleteById(id);
    }

    @GetMapping("/byId")
    ResponseEntity<Post> getPostById(@RequestParam UUID id){
        return ResponseEntity.ok(
                postDAO.findByIdLike(id)
        );
    }

    @GetMapping("/share")
    ResponseEntity<String> getLinkToShare(@RequestParam UUID id) {
        if (postDAO.existsById(id)) {
            return ResponseEntity.ok(String.format("http://rf.medic/%s", id.toString()));
        }
        return ResponseEntity.badRequest().build();
    }
}
