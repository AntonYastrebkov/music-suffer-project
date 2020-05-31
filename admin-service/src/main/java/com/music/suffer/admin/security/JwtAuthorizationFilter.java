package com.music.suffer.admin.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";
    private final JwtAuthenticationProvider authenticationProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException
    {
        Optional<String> tokenFromRequest = getTokenFromRequest(request);
        tokenFromRequest.ifPresent(token -> {
            JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(token);
            SecurityContextHolder.getContext().setAuthentication(
                    authenticationProvider.authenticate(jwtAuthenticationToken));
        });

        filterChain.doFilter(request, response);
    }

    private Optional<String> getTokenFromRequest(HttpServletRequest request) {
        Optional<String> header = Optional.ofNullable(request.getHeader("Authorization"));
        return header.filter(token -> StringUtils.hasText(token) && token.startsWith(BEARER_PREFIX))
                .map(token -> token.substring(BEARER_PREFIX.length()));
    }
}
