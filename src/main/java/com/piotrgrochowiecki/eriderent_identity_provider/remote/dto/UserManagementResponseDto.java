package com.piotrgrochowiecki.eriderent_identity_provider.remote.dto;

import com.piotrgrochowiecki.eriderent_identity_provider.domain.model.Role;
import lombok.Builder;

@Builder
public record UserManagementResponseDto(String uuid,
                                        String email,
                                        String password,
                                        Role role) {

}
