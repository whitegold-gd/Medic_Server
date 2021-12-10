package com.example.medic_server.Database.DAO;

import com.example.medic_server.Models.UserAccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserAccountInfoDAO extends JpaRepository<UserAccountInfo, UUID> {
    public UserAccountInfo findByIdLike(UUID id);
    public UserAccountInfo findByLoginLike(String login);
    public Boolean existsByLoginLike(String login);
}
