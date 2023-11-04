package com.piotrgrochowiecki.eriderent_identity_provider.remote.mapper;

import com.piotrgrochowiecki.eriderent_identity_provider.remote.dto.UserManagementResponseDto;
import com.piotrgrochowiecki.eriderent_identity_provider.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToModel(UserManagementResponseDto userDto) {
        return User.builder()
                .uuid(userDto.uuid())
                .email(userDto.email())
                .password(userDto.password())
                .build();
    }

}
