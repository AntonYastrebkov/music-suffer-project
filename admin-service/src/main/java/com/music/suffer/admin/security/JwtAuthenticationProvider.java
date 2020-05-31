package com.music.suffer.admin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.suffer.admin.domain.model.UserData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private static final String ADMIN_ROLE = "ADMIN";
    private final ObjectMapper objectMapper;

    @Value("${jwt.jwtSecretKey}")
    private String jwtSecretKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthentication = (JwtAuthenticationToken) authentication;
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecretKey)
                    .parseClaimsJws(jwtAuthentication.getJwt())
                    .getBody();
            UserData userData = objectMapper.convertValue(claims, UserData.class);
            if (!userData.getRoles().contains(ADMIN_ROLE)) {
                return null;
            }
            jwtAuthentication.setUserData(userData);
            jwtAuthentication.setAuthenticated(true);
            return jwtAuthentication;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
