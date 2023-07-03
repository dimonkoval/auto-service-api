package org.example.carservice.controller;

import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.example.carservice.dto.mapper.DtoMapper;
import org.example.carservice.dto.request.UserRequestDto;
import org.example.carservice.dto.response.UserResponseDto;
import org.example.carservice.model.User;
import org.example.carservice.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;
    private final DtoMapper<User, UserResponseDto, UserRequestDto> dtoMapper;

    public AuthenticationController(AuthenticationService authService,
                                    DtoMapper<User, UserResponseDto, UserRequestDto> dtoMapper) {
        this.authService = authService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping("/register")
    @ApiOperation(value = "Add new User in DB")
    public UserResponseDto register(@RequestBody @Valid UserRequestDto requestDto) {
        User user = authService.register(requestDto.getEmail(), requestDto.getPassword());
        return dtoMapper.toDto(user);
    }
}
