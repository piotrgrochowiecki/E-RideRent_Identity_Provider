package com.piotrgrochowiecki.eriderent_identity_provider.api.dto;

import lombok.Builder;

@Builder
public record UserAuthenticateResponseDto(String uuid,
                                          String email,
                                          String password) {

}
