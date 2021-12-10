package com.example.medic_server.Controllers;

import com.example.medic_server.Database.DAO.UserAccountInfoDAO;
import com.example.medic_server.Database.DAO.UserDAO;
import com.example.medic_server.Models.User;
import com.example.medic_server.Models.UserAccountInfo;
import com.example.medic_server.Secure.Services.JWTUtils;
import com.example.medic_server.Secure.Services.MyUserDetailsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserAccountInfoDAO userAccountInfoDAO;

    @Autowired
    private UserDAO userDAO;

    @PostMapping("auth")
    public ResponseEntity<String> authentication(@RequestBody UserAccountInfo body) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            body.getLogin(),
                            body.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            LoggerFactory.getLogger(AuthController.class).warn("Incorrect username or password");
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(body.getLogin());

        if (userDetails != null) {
            final String token = jwtUtils.generateToken(userDetails);

            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("register")
    public ResponseEntity<String> registration(@RequestBody UserAccountInfo body) throws Exception {
        if (body.getLogin() != null && body.getPassword()!= null
                && !userAccountInfoDAO.existsByLoginLike(body.getLogin()) && body.getUser() != null) {
            User user = userDAO.save(body.getUser());

            userAccountInfoDAO.save(
                    new UserAccountInfo(
                            body.getLogin(),
                            passwordEncoder.encode(body.getPassword()),
                            UserAccountInfo.Role.User,
                            user
                    )
            );
        } else {
            return ResponseEntity.badRequest().build();
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            body.getLogin(),
                            body.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            LoggerFactory.getLogger(AuthController.class).warn("Incorrect username or password");
            return ResponseEntity.badRequest().build();
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(body.getLogin());

        if (userDetails != null) {
            final String token = jwtUtils.generateToken(userDetails);

            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/auth/oauth")
    ResponseEntity<OutAuthResponse> outAuth(@RequestParam OAUTH2Provider provider) {
        return ResponseEntity.ok(
                new OutAuthResponse(
                        "path",
                        UUID.randomUUID()
                )
        );
    }

    public static final class OutAuthResponse{
        public String path;
        public UUID session_id;

        public OutAuthResponse(String path, UUID session_id) {
            this.path = path;
            this.session_id = session_id;
        }
    }

    public enum OAUTH2Provider{
        VK,
        Google,
        Meta
    }
}
