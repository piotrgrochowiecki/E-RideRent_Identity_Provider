package com.piotrgrochowiecki.eriderent_identity_provider.domain.exception;

public class NotFoundRuntimeException extends RuntimeException {

    public NotFoundRuntimeException(String email) {
        super("User with email " + email + " has not been found");
    }
}
