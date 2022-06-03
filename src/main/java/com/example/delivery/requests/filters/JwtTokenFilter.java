package com.example.delivery.requests.filters;

import com.example.delivery.entities.UserEntity;
import com.example.delivery.exceptions.data_not_found.DataNotFoundException;
import com.example.delivery.reopositories.UserRepository;
import com.example.delivery.utilities.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain chain
    ) throws ServletException, IOException {
        final Optional<String> token = findTokenFromHeader(request);

        if (token.isEmpty() || !jwtTokenUtil.validate(token.get())) {
            chain.doFilter(request, response);
            return;
        }

        final Long userId = jwtTokenUtil.getUserIdFromToken(token.get());

        final UserEntity user = userRepository
                .findById(userId)
                .orElseThrow(() -> new DataNotFoundException(UserEntity.class, userId));

        if (!user.getAccessToken().equals(token.get())) {
            chain.doFilter(request, response);
            return;
        }

        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    Optional<String> findTokenFromHeader(HttpServletRequest request) {
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        final boolean hasToken = hasText(authorization)
                && authorization.startsWith("Bearer ")
                && authorization.split(" ").length > 1;

        return hasToken
                ? Optional.of(authorization.split(" ")[1].trim())
                : Optional.empty();
    }

}
