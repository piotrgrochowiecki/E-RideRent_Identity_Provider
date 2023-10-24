package com.piotrgrochowiecki.eriderent_identity_provider.api.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RuntimeExceptionDto(String message,
                                  LocalDateTime timeStamp) {

}
