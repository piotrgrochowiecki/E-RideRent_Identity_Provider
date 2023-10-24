package com.piotrgrochowiecki.eriderent_identity_provider.api.mapper;

import com.piotrgrochowiecki.eriderent_identity_provider.api.dto.UserAuthenticateResponseDto;
import com.piotrgrochowiecki.eriderent_identity_provider.domain.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public User mapToModel(UserAuthenticateResponseDto userDto) {
        return User.builder()
                .uuid(userDto.uuid())
                .email(userDto.email())
                .password(userDto.password())
                .build();
    }

}
