package com.piotrgrochowiecki.eriderent_identity_provider.domain.service;

import com.piotrgrochowiecki.eriderent_identity_provider.domain.client.UserManagementClient;
import com.piotrgrochowiecki.eriderent_identity_provider.domain.exception.NotFoundRuntimeException;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService implements UserDetailsService {

    private final UserManagementClient userManagementClient;

    @Override
    public UserDetails loadUserByUsername(@Nullable String email) throws UsernameNotFoundException {
        assert email != null;
        return userManagementClient.getByEmailUsingWebClient(email)
                .orElseThrow(() -> new NotFoundRuntimeException(email));
    }
    //TODO dane do logowania przesyłać w hederach
}
