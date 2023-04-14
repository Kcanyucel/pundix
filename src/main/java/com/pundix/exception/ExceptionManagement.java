package com.pundix.exception;

import com.pundix.exception.common.ValidationException;
import com.pundix.response.ErrorResponse;
import com.pundix.service.MessageResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionManagement extends RuntimeException {

    private final MessageResourceService messageResourceService;

    public ExceptionManagement(MessageResourceService messageResourceService) {
        this.messageResourceService = messageResourceService;
    }

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ErrorResponse> handle(BaseException ex) {
        final String message = messageResourceService.getMessage(ex.getMessageKey(), ex.getParams(), ex.getHttpStatus());
        ErrorResponse errorResponse = new ErrorResponse(message, ex.getHttpStatus());

        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ErrorResponse> handle(ValidationException ex) {
        final String message = messageResourceService.getMessage(ex.getMessageKey(), ex.getParams());
        ErrorResponse errorResponse = new ErrorResponse(message, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
