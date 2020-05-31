package com.music.suffer.user.service;

import com.music.suffer.user.config.JwtProperties;
import com.music.suffer.user.model.AuthenticationToken;
import com.music.suffer.user.model.User;
import com.music.suffer.user.repository.AuthenticationTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtTokenGenerator implements TokenGenerator {
    private static final String TOKEN_TYPE = "Bearer";
    private final JwtProperties jwtProperties;
    private final AuthenticationTokenRepository authenticationTokenRepository;

    @Override
    public AuthenticationToken generate(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getId().toString());
        claims.put("email", user.getEmail());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("role", user.getAuthorities());

        return authenticationTokenRepository.save(new AuthenticationToken()
                .setAccessToken(generateJwt(claims, jwtProperties.getAccessExpiration()))
                .setRefreshToken(generateJwt(claims, jwtProperties.getRefreshExpiration()))
                .setTokenType(TOKEN_TYPE)
                .setExpiration(new Timestamp(jwtProperties.getAccessExpiration()))
                .setUser(user));
    }

    private String generateJwt(Map<String, Object> claims, long expiration) {
        Instant issuedAt = Instant.now();
        Date expirationDate = Date.from(issuedAt.plusSeconds(expiration));
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setExpiration(expirationDate)
                .setIssuedAt(Date.from(issuedAt))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getJwtSecretKey())
                .compact();
    }
}
