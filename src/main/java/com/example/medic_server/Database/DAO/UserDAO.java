package com.example.medic_server.Database.DAO;

import com.example.medic_server.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDAO extends JpaRepository<User, UUID> {
    User findByIdLike(UUID id);
    Boolean existsByEmailLike(@Param("email") String email);
    User findByFirstName(@Param("firstName") String firstName);

    User findByEmail(@Param("email") String email);
}
