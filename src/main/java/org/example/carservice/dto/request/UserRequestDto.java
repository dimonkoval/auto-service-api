package org.example.carservice.dto.request;

import java.util.Set;
import lombok.Data;
import org.example.carservice.model.Role;

@Data
public class UserRequestDto {
    private String email;
    private String password;
    private Set<Role> roles;
}
