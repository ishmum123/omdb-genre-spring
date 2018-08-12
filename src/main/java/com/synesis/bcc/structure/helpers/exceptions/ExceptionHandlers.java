package com.synesis.bcc.structure.helpers.exceptions;

import com.synesis.bcc.structure.config.ServiceConfiguration;
import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestControllerAdvice
public class ExceptionHandlers {

    private final ServiceConfiguration serviceConfiguration;
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlers.class);

    public ExceptionHandlers(@Autowired ServiceConfiguration serviceConfiguration) {
        this.serviceConfiguration = serviceConfiguration;
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ServiceExceptionHolder.UserNotFoundException.class)
    public ApiErrorResponse handleUserNotFoundException(final ServiceExceptionHolder.UserNotFoundException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.OK, ex.getMessage(), ex);
        LOGGER.error(apiErrorResponse.toString());
        return apiErrorResponse;
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ApiErrorResponse handleUnsupportedMediaType(final HttpMediaTypeNotSupportedException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage());
        LOGGER.error(apiErrorResponse.toString());
        return apiErrorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiErrorResponse handleValidationError(final HttpMessageNotReadableException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST, "Could not read the request body");
        LOGGER.error(apiErrorResponse.toString());
        return apiErrorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiErrorResponse handleInvalidRequestParam(final MethodArgumentTypeMismatchException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Request Parameters");
        LOGGER.error(apiErrorResponse.toString());
        return apiErrorResponse;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleValidationError(final MethodArgumentNotValidException ex) {
        Errors validtionErrors = ex.getBindingResult();
        String message = "Validation failed. " + validtionErrors.getErrorCount() + " error(s)";
        List<String> errors = new ArrayList<>();
        for (ObjectError error : validtionErrors.getAllErrors())
            errors.add(error.getDefaultMessage());

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, message, errors);
        LOGGER.error(apiErrorResponse.toString());
        return apiErrorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiErrorResponse handleNoHandlerFoundError(final NoHandlerFoundException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND, "Invalid URL");
        LOGGER.error(apiErrorResponse.toString());
        return apiErrorResponse;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiErrorResponse handleThrowable(final Exception ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error while processing request.", ex.getClass().getCanonicalName());
        LOGGER.error(apiErrorResponse.toString());
        return apiErrorResponse;
    }

    @Getter
    @ToString
    public class ApiErrorResponse {

        private final int httpStatus;
        private final String code;
        private final String message;
        private final List<String> errors;

        public ApiErrorResponse(HttpStatus httpStatus, String message, List<String> errors, ServiceExceptionHolder.ServiceException e) {
            this.httpStatus = httpStatus.value();
            this.code = serviceConfiguration.getShortCode() + e.getCode();
            this.message = message;
            this.errors = errors;
        }

        public ApiErrorResponse(HttpStatus httpStatus, String message, ServiceExceptionHolder.ServiceException e) {
            this(httpStatus, message, new ArrayList<>(), e);
        }

        public ApiErrorResponse(HttpStatus httpStatus, String message, List<String> errors) {
            this(httpStatus, message, errors, new ServiceExceptionHolder.ServiceException(httpStatus.value(), message));
        }

        public ApiErrorResponse(HttpStatus httpStatus, String message) {
            this(httpStatus, message, Collections.singletonList(message));
        }

        public ApiErrorResponse(HttpStatus httpStatus, String message, String errorMessage) {
            this(httpStatus, message, Collections.singletonList(errorMessage));
        }
    }
}