package com.music.suffer.user.repository;

import com.music.suffer.user.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, UUID> {
}
