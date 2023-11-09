package com.piotrgrochowiecki.eriderent_identity_provider.domain.exception;

public class BadCredentialsRuntimeException extends RuntimeException {

    public BadCredentialsRuntimeException() {
        super("Provided credentials are invalid");
    }

}
