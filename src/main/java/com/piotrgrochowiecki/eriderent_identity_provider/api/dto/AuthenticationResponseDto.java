package com.piotrgrochowiecki.eriderent_identity_provider.api.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponseDto(String email,
                                        String accessToken) {

}
