package com.piotrgrochowiecki.eriderent_identity_provider.domain.service;

import com.piotrgrochowiecki.eriderent_identity_provider.domain.client.UserManagementClient;
import com.piotrgrochowiecki.eriderent_identity_provider.domain.exception.NotFoundRuntimeException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserManagementClient userManagementClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userManagementClient.getByEmail(email)
                .orElseThrow(() -> new NotFoundRuntimeException(email));
    }
}