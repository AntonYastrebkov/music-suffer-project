package com.music.suffer.library.security;

import com.music.suffer.library.security.model.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final static String BEARER_PREFIX = "Bearer ";
    private final AuthenticationProvider authenticationProvider;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            JwtAuthentication authentication = new JwtAuthentication();
            authentication.setJwt(bearerToken.substring(BEARER_PREFIX.length()));

            Authentication authenticate = authenticationProvider.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(
                    authenticate);
        } else {
            throw new InvalidTokenException("User unauthorized!");
        }

        filterChain.doFilter(request, response);
    }
}
