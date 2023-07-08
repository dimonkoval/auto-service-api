package org.example.carservice.mapper.impl;

import org.example.carservice.dto.request.UserRequestDto;
import org.example.carservice.dto.response.UserResponseDto;
import org.example.carservice.mapper.DtoMapper;
import org.example.carservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements DtoMapper<User, UserResponseDto, UserRequestDto> {

    @Override
    public User toModel(UserRequestDto requestDto) {
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setRoles(requestDto.getRoles());
        user.setPassword(requestDto.getPassword());
        return user;
    }

    @Override
    public UserResponseDto toDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setEmail(user.getEmail());
        return responseDto;
    }
}
