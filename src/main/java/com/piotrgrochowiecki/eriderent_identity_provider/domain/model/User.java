package com.piotrgrochowiecki.eriderent_identity_provider.domain.model;

import lombok.*;

@Builder
public record User(String uuid,
                   String email,
                   String password,
                   Role role){

}