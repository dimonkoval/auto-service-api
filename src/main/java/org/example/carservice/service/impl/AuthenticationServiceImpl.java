package org.example.carservice.service.impl;

import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.carservice.model.User;
import org.example.carservice.service.AuthenticationService;
import org.example.carservice.service.RoleService;
import org.example.carservice.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final String ROLE_USER_NAME = "USER";
    private static final Logger logger = LogManager.getLogger(AuthenticationServiceImpl.class);
    private final UserService userService;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public User register(String email, String password) {
        logger.info("register new User. Params email = {}", email);
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(Set.of(roleService.getByName(ROLE_USER_NAME)));
        userService.add(user);
        return user;
    }
}
