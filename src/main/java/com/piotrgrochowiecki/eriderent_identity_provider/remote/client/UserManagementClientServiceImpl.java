package com.piotrgrochowiecki.eriderent_identity_provider.remote.client;

import com.piotrgrochowiecki.eriderent_identity_provider.remote.mapper.UserMapper;
import com.piotrgrochowiecki.eriderent_identity_provider.remote.dto.UserManagementResponseDto;
import com.piotrgrochowiecki.eriderent_identity_provider.domain.model.User;
import com.piotrgrochowiecki.eriderent_identity_provider.domain.client.UserManagementClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Service
public class UserManagementClientServiceImpl implements UserManagementClientService {

    @Value("${url.userManagement}")
    private String USER_MANAGEMENT_URL;

    @Value("${url.userManagement.user}")
    private String USER_MANAGEMENT_USER_ENDPOINT;

    private final UserMapper userMapper;
    private final WebClient userManagementClient;

    public UserManagementClientServiceImpl(UserMapper userMapper, WebClient userManagementClient) {
        this.userMapper = userMapper;
        this.userManagementClient = userManagementClient;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userManagementClient.get()
                .uri(USER_MANAGEMENT_URL + USER_MANAGEMENT_USER_ENDPOINT + "/email/" + email)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return clientResponse.bodyToMono(UserManagementResponseDto.class)
                                .map(userMapper::mapToModel);
                    } else {
                        return Mono.empty();
                    }
                })
                .blockOptional();
    }

}
