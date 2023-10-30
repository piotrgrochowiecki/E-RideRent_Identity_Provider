package com.piotrgrochowiecki.eriderent_identity_provider.remote.client;

import com.piotrgrochowiecki.eriderent_identity_provider.remote.mapper.Mapper;
import com.piotrgrochowiecki.eriderent_identity_provider.remote.dto.UserManagementResponseDto;
import com.piotrgrochowiecki.eriderent_identity_provider.domain.model.User;
import com.piotrgrochowiecki.eriderent_identity_provider.domain.client.UserManagementClient;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class UserManagementClientImpl implements UserManagementClient {

    private final RestTemplate restTemplate;
    private final Mapper mapper;

    @Override
    public User getByEmail(@Nullable String email) {
        assert email != null;
        String url = USER_MANAGEMENT_URL + "email/" + email;
        UserManagementResponseDto userManagementResponseDto = restTemplate.getForObject(url, UserManagementResponseDto.class);
        assert userManagementResponseDto != null;
        return mapper.mapToModel(userManagementResponseDto);
    }

}
