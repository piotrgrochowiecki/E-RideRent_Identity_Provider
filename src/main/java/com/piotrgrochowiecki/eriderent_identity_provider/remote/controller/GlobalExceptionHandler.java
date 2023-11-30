package com.piotrgrochowiecki.eriderent_identity_provider.remote.controller;

import com.piotrgrochowiecki.eriderent_identity_provider.domain.exception.BadCredentialsRuntimeException;
import com.piotrgrochowiecki.eriderent_identity_provider.domain.exception.NotFoundRuntimeException;
import com.piotrgrochowiecki.eriderent_identity_provider.remote.dto.RuntimeExceptionDto;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsRuntimeException.class)
    public RuntimeExceptionDto handleBadCredentialsRuntimeException(BadCredentialsRuntimeException exception) {
        return RuntimeExceptionDto.builder()
                .message(exception.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ExpiredJwtException.class)
    public RuntimeExceptionDto handleExpiredJwtRuntimeException() {
        return RuntimeExceptionDto.builder()
                .message("Token has expired")
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundRuntimeException.class)
    public RuntimeExceptionDto handleNotFoundRuntimeException(NotFoundRuntimeException exception) {
        return RuntimeExceptionDto.builder()
                .message(exception.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }

}
