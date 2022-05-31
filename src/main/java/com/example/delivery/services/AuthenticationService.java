package com.example.delivery.services;

import com.example.delivery.JwtTokenUtil;
import com.example.delivery.entities.UserEntity;
import com.example.delivery.exceptions.data_not_found.LoginException;
import com.example.delivery.forms.LoginForm;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationService {

    AuthenticationManager authenticationManager;
    JwtTokenUtil jwtTokenUtil;

    public String login(LoginForm form) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(form.username(), form.password())
        );

        Object user = authentication.getPrincipal();

        if (user instanceof UserEntity) {
            return jwtTokenUtil.generateAccessToken((UserEntity) user);
        } else {
            throw new LoginException("Invalid User");
        }
    }

    public void logout(Long userId) {
        // TODO: Implement logout
    }
}
