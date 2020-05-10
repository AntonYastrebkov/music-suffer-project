package com.music.suffer.user.service;

import com.music.suffer.user.model.RegistrationRequest;
import com.music.suffer.user.model.User;

public interface UserService {
    User register(RegistrationRequest request);
}
