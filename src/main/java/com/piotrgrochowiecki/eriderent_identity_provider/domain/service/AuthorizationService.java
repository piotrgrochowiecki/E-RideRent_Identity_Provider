package com.piotrgrochowiecki.eriderent_identity_provider.domain.service;

import com.piotrgrochowiecki.eriderent_identity_provider.domain.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorizationService {

    private final JwtTokenService jwtTokenService;

    private final List<String> customerAllowedEndpoints = List.of("api/user/uuid/",
                                                                  "api/v1/internal/user/create");

    //TODO czy nie rozdzielić kontrolerów i endpointów na np. "api/v1/internal/customer/user/..." i "api/v1/internal/admin/user/..." dla prostszej autoryzacji???

    public boolean isAuthorized(String token, String url) {
        Role userRole = jwtTokenService.extractRole(token);

        return userRole.equals(Role.CUSTOMER) &&
               customerAllowedEndpoints.stream()
                       .anyMatch(url::contains);

    }


}
