package com.piotrgrochowiecki.eriderent_identity_provider.domain.service;

import com.piotrgrochowiecki.eriderent_identity_provider.domain.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthorizationService {

    private final JwtTokenService jwtTokenService;
    private final RoleAndAllowedEndpointMapProvider roleAndAllowedEndpointMapProvider;

    public boolean isAuthorized(String token, String url, String httpMethod) {
        Role userRole = jwtTokenService.extractRole(token);
        return isRoleAuthorized(userRole, url, httpMethod);
    }

    private boolean isRoleAuthorized(Role userRole, String url, String httpMethod) {
        return roleAndAllowedEndpointMapProvider.getRoleAllowedEndpointListMap()
                .entrySet()
                .stream()
                .anyMatch(roleEndpointListEntry -> roleEndpointListEntry.getKey() == userRole &&
                        roleEndpointListEntry.getValue()
                                .stream()
                                .anyMatch(endpoint -> doMethodAndUrlMatch(httpMethod, url, endpoint)));
    }

    private boolean doMethodAndUrlMatch(String httpMethod, String url, Endpoint endpoint) {
        return doesMethodOfRequestMatchOneFromAllowedList(httpMethod, endpoint) && doesUrlOfRequestMatchOneFromAllowedList(url, endpoint);
    }

    private boolean doesMethodOfRequestMatchOneFromAllowedList(String httpMethod, Endpoint endpoint) {
        return endpoint.method()
                .toString()
                .toUpperCase()
                .equals(httpMethod);
    }

    private boolean doesUrlOfRequestMatchOneFromAllowedList(String url, Endpoint endpoint) {
        return url.contains(endpoint.uri());
    }

}
