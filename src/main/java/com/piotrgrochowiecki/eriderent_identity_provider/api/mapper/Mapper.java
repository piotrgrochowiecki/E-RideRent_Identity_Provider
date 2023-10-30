package com.piotrgrochowiecki.eriderent_identity_provider.api.mapper;

import com.piotrgrochowiecki.eriderent_identity_provider.api.dto.UserManagementResponseDto;
import com.piotrgrochowiecki.eriderent_identity_provider.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public User mapToModel(UserManagementResponseDto userDto) {
        return User.builder()
                .uuid(userDto.uuid())
                .email(userDto.email())
                .password(userDto.password())
                .build();
    }

}
