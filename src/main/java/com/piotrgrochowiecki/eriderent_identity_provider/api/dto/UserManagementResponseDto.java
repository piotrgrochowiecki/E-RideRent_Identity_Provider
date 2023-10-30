package com.piotrgrochowiecki.eriderent_identity_provider.api.dto;

import lombok.Builder;

@Builder
public record UserManagementResponseDto(String uuid,
                                        String email,
                                        String password) {

}
