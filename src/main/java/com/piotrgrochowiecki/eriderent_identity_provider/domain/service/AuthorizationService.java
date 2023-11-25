package com.piotrgrochowiecki.eriderent_identity_provider.domain.service;

import com.piotrgrochowiecki.eriderent_identity_provider.domain.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorizationService {

    private final JwtTokenService jwtTokenService;
    private final EndpointList endpointList;

    public boolean isAuthorized(String token, String url, String httpMethod) {
        Role userRole = jwtTokenService.extractRole(token);

        if (userRole.equals(Role.CUSTOMER) &&
            endpointList.getCustomerAllowedEndpoints().stream()
                .anyMatch(endpoint -> endpoint.getUri().contains(url)
                                      && endpoint.getMethod()
                        .toString()
                        .toUpperCase()
                        .equals(httpMethod))) {
            return true;
        }
        return userRole.equals(Role.ADMIN) &&
               endpointList.getAdminAllowedEndpoints().stream()
                       .anyMatch(endpoint -> url.contains(endpoint.getUri()) &&
                                             endpoint.getMethod()
                                                     .toString()
                                                     .toUpperCase()
                                                     .equals(httpMethod));
        //TODO przerzucić te sprawdzenia do jednej prywatnej metod: w parametrze przekazywać listę oraz endpoint
    }

}
