package com.piotrgrochowiecki.eriderent_identity_provider.domain.service;

import com.piotrgrochowiecki.eriderent_identity_provider.domain.client.UserManagementClient;
import com.piotrgrochowiecki.eriderent_identity_provider.domain.model.User;
import com.piotrgrochowiecki.eriderent_identity_provider.remote.dto.UserManagementResponseDto;
import com.piotrgrochowiecki.eriderent_identity_provider.remote.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService implements UserDetailsService {

    private final UserManagementClient userManagementClient;
    private final Mapper mapper;

    @Override
    public UserDetails loadUserByUsername(@Nullable String email) throws UsernameNotFoundException {
        assert email != null;
        ResponseEntity<?> responseEntity = userManagementClient.getByEmailUsingWebClient(email);
        UserManagementResponseDto userManagementResponseDto = (UserManagementResponseDto) responseEntity.getBody();
        return mapper.mapToModel(userManagementResponseDto);
    }
    //TODO dane do logowania przesyłać w hederach
}
