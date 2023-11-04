package com.piotrgrochowiecki.eriderent_identity_provider.domain.service;

import com.piotrgrochowiecki.eriderent_identity_provider.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public String authenticate(String authenticationHeader) {

        String base64Credentials = authenticationHeader.substring("Basic"
                                                                          .length())
                .trim(); // removal of "Basic" word from header value, it comes in a form "Basic dXNlcm5hbWU6cGFzc3dvcmQ="

        byte[] credentialsDecoded = Base64.getDecoder()
                .decode(base64Credentials);

        String credentials = new String(credentialsDecoded, StandardCharsets.UTF_8); //credentials = "username:password"

        final String[] values = credentials.split(":", 2);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(values[0], values[1]));

        User user = (User) authentication.getPrincipal();

        return jwtTokenService.generateAccessToken(user);
    }

}
