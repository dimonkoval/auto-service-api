package org.example.carservice.dto.response;

import java.util.Set;
import lombok.Data;
import org.example.carservice.model.Role;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private Set<Role> roles;
}
