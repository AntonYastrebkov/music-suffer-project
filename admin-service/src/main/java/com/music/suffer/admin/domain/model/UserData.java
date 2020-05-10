package com.music.suffer.admin.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserData {
    private long sub;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isEnabled;
    private Set<String> roles;
}
