package org.example.carservice.service;

import org.example.carservice.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
