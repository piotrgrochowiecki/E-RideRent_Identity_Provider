package com.piotrgrochowiecki.eriderent_identity_provider.remote.controller;

import com.piotrgrochowiecki.eriderent_identity_provider.domain.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/internal/authentication/")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("authenticate")
    public ResponseEntity<?> login(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader) {
        String accessToken = authService.authenticate(authHeader);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .body("Authenticated successfully"); //TODO zwrócić token w body jako JSON wraz z czasem wygaśnięcia
    }

    //TODO poczytać o refresh tokenach.
    // Refresh token (o długim czasie życia, np. 30 dni) jest wysyłany razem z access tokenem. Użytkownik wysyła refresh token na odpowiedni
    // dedykowany endpoint. Taki refresh token powinien być przechowywany w bazie danych i być powiązany z użytkownikiem.
}
