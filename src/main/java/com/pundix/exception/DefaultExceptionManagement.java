package com.pundix.exception;

import com.pundix.exception.custom.UserNotFoundException;
import com.pundix.exception.custom.UserSessionInfoNotFoundException;
import com.pundix.response.ErrorResponse;
import com.pundix.service.MessageResourceService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionManagement extends RuntimeException {

    private final MessageResourceService messageResourceService;

    public DefaultExceptionManagement(MessageResourceService messageResourceService) {
        this.messageResourceService = messageResourceService;
    }

    @ExceptionHandler(value = {InternalServerErrorException.class})
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalServerErrorException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BadRequestExpection.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestExpection ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {  UserSessionInfoNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(  UserSessionInfoNotFoundException ex) {
        final String message = messageResourceService.getMessage(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(message, HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        final String message = messageResourceService.getMessage(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(message, HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        final String message = messageResourceService.getMessage(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(message, HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException ex) {
        final String message = messageResourceService.getMessage(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(message, HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException ex) {
        final String message = messageResourceService.getMessage(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(message, HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ClassCastException.class})
    public ResponseEntity<ErrorResponse> handleClassCastException(ClassCastException ex) {
        final String message = messageResourceService.getMessage(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(message, HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        final String message = ex.getConstraintViolations().stream()
            .findFirst()
            .map(ConstraintViolation::getMessage)
            .orElse("Validation failed");
        ErrorResponse errorResponse = new ErrorResponse(messageResourceService.getMessage(message), HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
