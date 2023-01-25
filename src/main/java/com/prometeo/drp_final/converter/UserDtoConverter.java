package com.prometeo.drp_final.converter;

import com.prometeo.drp_final.model.entity.User;
import com.prometeo.drp_final.model.frontend.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter implements DtoConverter<User, UserDto> {

    private final PasswordEncoder passwordEncoder;

    public UserDtoConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto convertToDto(User user) {
        return new ModelMapper().map(user, UserDto.class);
    }

    @Override
    public void updateFromDto(User user, UserDto dto) {
        user.setCompanyName(dto.getCompanyName());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
    }

    @Override
    public void createFromDto(User user, UserDto dto) {
        DtoConverter.super.createFromDto(user, dto);
    }


}
