package com.music.suffer.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {
    private String jwtSecretKey;
    private long accessExpiration;
    private long refreshExpiration;
}
