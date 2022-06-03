package com.example.delivery.controllers;

import com.example.delivery.ConstantMessages;
import com.example.delivery.exceptions.data_not_found.DataNotFoundException;
import com.example.delivery.forms.LoginForm;
import com.example.delivery.presenters.LoginPresenter;
import com.example.delivery.responses.OkResponse;
import com.example.delivery.services.AuthenticationService;
import com.example.delivery.utilities.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@AllArgsConstructor
@RestController
public class AuthenticationController {

    AuthenticationService authenticationService;
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/auth/login")
    public OkResponse<LoginPresenter> login(@RequestBody LoginForm form) {
        return OkResponse.of(
                new LoginPresenter(authenticationService.login(form))
        );
    }

    @PostMapping("/auth/logout")
    public OkResponse<String> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
      final boolean hasToken = hasText(authorization)
                && authorization.startsWith("Bearer ")
                && authorization.split(" ").length > 1;

        final Optional<String> accessToken = hasToken
                ? Optional.of(authorization.split(" ")[1].trim())
                : Optional.empty();

        if (accessToken.isEmpty()) {
            throw new DataNotFoundException("Token not found.");
        }

        authenticationService.logout(jwtTokenUtil.getUserIdFromToken(accessToken.get()));
        return OkResponse.of(ConstantMessages.SUCCESS);
    }
}
