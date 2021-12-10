package com.example.medic_server.Database.DAO;

import com.example.medic_server.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostDAO extends JpaRepository<Post, UUID> {
    Post findByIdLike(UUID id);
}
