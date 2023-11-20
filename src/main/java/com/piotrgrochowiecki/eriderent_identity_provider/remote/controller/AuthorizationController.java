package com.piotrgrochowiecki.eriderent_identity_provider.remote.controller;

import com.piotrgrochowiecki.eriderent_identity_provider.domain.service.AuthorizationService;
import com.piotrgrochowiecki.eriderent_identity_provider.remote.dto.AuthorizationRequestUrlDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/internal/authorization")
@AllArgsConstructor
public class AuthorizationController {

    private AuthorizationService authorizationService;

    @PostMapping("authorize")
    public ResponseEntity<?> authorize(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authToken,
                                       @RequestBody AuthorizationRequestUrlDto authorizationRequestUrlDto) {
        boolean isAuthorized = authorizationService.isAuthorized(authToken, authorizationRequestUrlDto.url(), authorizationRequestUrlDto.httpMethod());
        if (isAuthorized) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, authToken)
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .header(HttpHeaders.AUTHORIZATION, authToken)
                    .build();
        }
    }

}
