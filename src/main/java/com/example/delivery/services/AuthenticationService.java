package com.example.delivery.services;

import com.example.delivery.entities.UserEntity;
import com.example.delivery.exceptions.data_not_found.DataNotFoundException;
import com.example.delivery.exceptions.data_not_found.LoginException;
import com.example.delivery.forms.LoginForm;
import com.example.delivery.reopositories.UserRepository;
import com.example.delivery.utilities.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationService {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    JwtTokenUtil jwtTokenUtil;

    public String login(LoginForm form) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(form.username(), form.password())
        );

        Object maybeUser = authentication.getPrincipal();

        if (!(maybeUser instanceof final UserEntity user)) {
            throw new LoginException("Invalid User");
        }

        final String accessToken = jwtTokenUtil.generateAccessToken(user);
        user.setAccessToken(accessToken);
        userRepository.save(user);

        return jwtTokenUtil.generateAccessToken((UserEntity) maybeUser);
    }

    public void logout(Long userId) {
        final UserEntity user = userRepository
                .findById(userId)
                .orElseThrow(() -> new DataNotFoundException(UserEntity.class, userId));

        final String accessToken = jwtTokenUtil.generateAccessToken(user);
        user.setAccessToken(accessToken);
        userRepository.save(user);
    }
}
