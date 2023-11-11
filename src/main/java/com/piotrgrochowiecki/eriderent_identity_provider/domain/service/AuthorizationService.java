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

    public boolean isAuthorized(String token, String url) {
        Role userRole = jwtTokenService.extractRole(token);

        return userRole.equals(Role.CUSTOMER) &&
               customerAllowedEndpoints.stream()
                       .anyMatch(url::contains);

    }
//TODO zastanowić się nad utworzeniem javowej klasy Endpoint z url i metodą. Powstaną dwie listy obiektów, dla customer i dla admina.
// Następnie przy żądaniu o dostęp porównać przychodzącą rolę i url. Na tej podstawie udzielić lub nie udzielić dostępu.

}
