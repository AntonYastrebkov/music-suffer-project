package com.music.suffer.user.service;

import com.music.suffer.user.model.AuthenticationToken;
import org.springframework.security.core.Authentication;

public interface TokenGenerator {
    AuthenticationToken generate(Authentication authentication);
}
