package com.music.suffer.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class RegistrationRequest {
    @Email(message = "Enter valid email")
    private String email;
    private String password;
    @NotBlank
    private String firstName;
    private String lastName;
}
