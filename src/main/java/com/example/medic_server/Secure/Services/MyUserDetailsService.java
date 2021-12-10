package com.example.medic_server.Secure.Services;

import com.example.medic_server.Database.DAO.UserAccountInfoDAO;
import com.example.medic_server.Models.UserAccountInfo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserAccountInfoDAO userAccountInfoDAO;

    public MyUserDetailsService(UserAccountInfoDAO userAccountInfoDAO) {
        this.userAccountInfoDAO = userAccountInfoDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccountInfo userAccountInfo = userAccountInfoDAO.findByLoginLike(username);
        if (userAccountInfo != null){
            return new User(
                userAccountInfo.getLogin(),
                    userAccountInfo.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(userAccountInfo.getRole().toString())));
        } else {
            return null;
        }
    }
}
