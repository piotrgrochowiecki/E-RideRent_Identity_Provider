package com.piotrgrochowiecki.eriderent_identity_provider.api.client;

import com.piotrgrochowiecki.eriderent_identity_provider.api.mapper.Mapper;
import com.piotrgrochowiecki.eriderent_identity_provider.api.dto.UserAuthenticateResponseDto;
import com.piotrgrochowiecki.eriderent_identity_provider.domain.User;
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
        UserAuthenticateResponseDto userAuthenticateResponseDto = restTemplate.getForObject(url, UserAuthenticateResponseDto.class);
        assert userAuthenticateResponseDto != null;
        return mapper.mapToModel(userAuthenticateResponseDto);
    }

}
