package com.synesis.bcc.structure.helpers.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

public class ServiceExceptionHolder {

    @Getter
    @RequiredArgsConstructor
    public static class ServiceException extends RuntimeException {
        private final int code;
        private final String message;
    }

    public static class ResourceNotFoundException extends ServiceException {
        public ResourceNotFoundException(String message) {
            super(1000, message);
        }
    }

    public static class UserNotFoundException extends ResourceNotFoundException {
        public UserNotFoundException(UUID uuid) {
            super("No User found with id " + uuid);
        }
    }

}
