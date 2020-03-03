package com.music.suffer.library.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TokenData {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<String> grantedAuthorities;

    @JsonProperty(value = "name", access = JsonProperty.Access.READ_ONLY)
    public String getName() {
        return (this.firstName + " " + this.lastName).trim();
    }
}
