package org.example.carservice.controller;

import io.swagger.annotations.ApiOperation;
import org.example.carservice.exception.DataProcessingException;
import org.example.carservice.mapper.DtoMapper;
import org.example.carservice.dto.request.UserRequestDto;
import org.example.carservice.dto.response.UserResponseDto;
import org.example.carservice.model.User;
import org.example.carservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final DtoMapper<User, UserResponseDto, UserRequestDto> dtoMapper;

    public UserController(UserService userService,
                          DtoMapper<User, UserResponseDto, UserRequestDto> dtoMapper) {
        this.userService = userService;

        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/by-email")
    @ApiOperation(value = "Get User by email")
    public UserResponseDto findByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email).orElseThrow(
                () -> new DataProcessingException("User by email " + email + " not found",
                        new IllegalArgumentException("Email not found")));
        return dtoMapper.toDto(user);
    }
}
