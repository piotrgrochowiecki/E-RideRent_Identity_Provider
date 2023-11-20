package com.piotrgrochowiecki.eriderent_identity_provider.remote.dto;

import lombok.Builder;

@Builder
public record AuthorizationRequestUrlDto(String url,
                                         String httpMethod) {

}
