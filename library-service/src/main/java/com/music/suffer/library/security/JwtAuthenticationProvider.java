package com.music.suffer.library.security;

import com.music.suffer.library.security.model.JwtAuthentication;
import com.music.suffer.library.security.model.TokenData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Override
    @SuppressWarnings("unchecked")
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;
        String jwt = jwtAuthentication.getJwt();
        try {
            Claims jwtClaims = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).getBody();
            List<String> roles = jwtClaims.get("role", List.class);
            TokenData tokenData = new TokenData()
                    .setId(Long.valueOf(jwtClaims.getId()))
                    .setFirstName(jwtClaims.get("firstName", String.class))
                    .setLastName(jwtClaims.get("lastName", String.class))
                    .setEmail(jwtClaims.get("email", String.class))
                    .setGrantedAuthorities(roles);
            jwtAuthentication.setTokenData(tokenData);
            jwtAuthentication.setPrincipal(tokenData.getId()::toString);
            jwtAuthentication.setAuthenticated(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InvalidTokenException("User unauthorized");
        }

        return jwtAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}
