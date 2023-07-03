package org.example.carservice.dto.request;

import java.util.Set;
import javax.validation.constraints.Size;
import lombok.Data;
import org.example.carservice.lib.FieldsValueMatch;
import org.example.carservice.lib.ValidEmail;
import org.example.carservice.model.Role;

@Data
@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!")
public class UserRequestDto {
    @ValidEmail
    private String email;
    @Size(min = 4, max = 40)
    private String password;
    private String repeatPassword;
    private Set<Role> roles;
}
