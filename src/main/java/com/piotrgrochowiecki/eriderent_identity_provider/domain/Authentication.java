package com.piotrgrochowiecki.eriderent_identity_provider.domain;

import lombok.Builder;

@Builder
public record Authentication(String email,
                             String accessToken) {

}
