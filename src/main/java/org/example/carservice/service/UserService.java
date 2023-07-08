package org.example.carservice.service;

import java.util.Optional;
import org.example.carservice.model.User;

public interface UserService {
    User add(User user);

    User get(Long id);

    Optional<User> findByEmail(String email);
}
