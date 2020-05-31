package com.music.suffer.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.suffer.user.exception.UserAlreadyExistsException;
import com.music.suffer.user.model.RegistrationRequest;
import com.music.suffer.user.model.User;
import com.music.suffer.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(RegistrationRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email " + request.getEmail() + " already exists");
        }
        User userToSave = modelMapper.map(request, User.class);
        userToSave.setPassword(passwordEncoder.encode(request.getPassword()));
        userToSave.setEnabled(true);
        userToSave.setRoles(Set.of("USER"));
        return userRepository.save(userToSave);
    }
}
