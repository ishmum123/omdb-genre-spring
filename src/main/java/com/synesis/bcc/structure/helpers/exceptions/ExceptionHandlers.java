package com.synesis.bcc.structure.helpers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleUserNotFoundException(final MeetingNotFoundException ex) {
        return new ApiErrorResponse(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiErrorResponse handleThrowable(final Exception ex) {
        return new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Internal server error while processing request.", ex.getClass().getCanonicalName());
    }


    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ApiErrorResponse handleUnsupportedMediaType(final HttpMediaTypeNotSupportedException ex) {
        return new ApiErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString(), ex.getLocalizedMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiErrorResponse handleValidationError(final HttpMessageNotReadableException ex) {

        return new ApiErrorResponse(HttpStatus.BAD_REQUEST.toString(), "Could not read the request body");
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleValidationError(final MethodArgumentNotValidException ex) {
        Errors validtionErrors = ex.getBindingResult();
        String message = "Validation failed. " + validtionErrors.getErrorCount() + " error(s)";
        List<String> errors = new ArrayList<>();
        for (ObjectError error : validtionErrors.getAllErrors())
            errors.add(error.getDefaultMessage());

        return new ApiErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), message, errors);
    }

    @Getter
    @AllArgsConstructor
    public static class ApiErrorResponse {
        private final String code;
        private final String message;
        private List<String> errors;

        public ApiErrorResponse(String code, String message, String error) {
            this(code, message, Collections.singletonList(error));
        }

        public ApiErrorResponse(String code, String message) {
            this(code, message, message);
        }
    }
}