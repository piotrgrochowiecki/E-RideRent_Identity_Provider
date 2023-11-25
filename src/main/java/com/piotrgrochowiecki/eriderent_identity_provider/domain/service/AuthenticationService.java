package com.piotrgrochowiecki.eriderent_identity_provider.domain.service;

import com.piotrgrochowiecki.eriderent_identity_provider.configuration.PasswordUtils;
import com.piotrgrochowiecki.eriderent_identity_provider.domain.client.UserManagementClientService;
import com.piotrgrochowiecki.eriderent_identity_provider.domain.exception.BadCredentialsRuntimeException;
import com.piotrgrochowiecki.eriderent_identity_provider.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final JwtTokenService jwtTokenService;
    private final UserManagementClientService userManagementClientService;

    public String authenticate(String authenticationHeader) {

        String base64Credentials = authenticationHeader.substring("Basic".length())
                .trim(); // removal of "Basic" word from header value, it comes in a form "Basic dXNlcm5hbWU6cGFzc3dvcmQ="

        byte[] credentialsDecoded = Base64.getDecoder()
                .decode(base64Credentials);

        String credentials = new String(credentialsDecoded, StandardCharsets.UTF_8); //credentials = "username:password"

        final String[] credentialsArr = credentials.split(":", 2);
        String userEmailFromHeader = credentialsArr[0];
        String passwordFromHeader = credentialsArr[1];

        User authenticatedUser = getAuthenticatedUser(userEmailFromHeader, passwordFromHeader);

        return jwtTokenService.generateAccessToken(authenticatedUser);
    }

    private User getAuthenticatedUser(String userEmailFromHeader, String passwordFromHeader) {
        User user = userManagementClientService.getByEmail(userEmailFromHeader)
                .orElseThrow(BadCredentialsRuntimeException::new);
        if (PasswordUtils.isPasswordValid(passwordFromHeader, user.password())) {
            return user;
        }
        throw new BadCredentialsRuntimeException();
    }
}
