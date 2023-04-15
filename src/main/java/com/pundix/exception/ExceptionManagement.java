package com.pundix.exception;

import com.pundix.exception.common.ValidationException;
import com.pundix.response.ErrorResponse;
import com.pundix.service.MessageResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionManagement extends RuntimeException {

    private final MessageResourceService messageResourceService;

    public ExceptionManagement(MessageResourceService messageResourceService) {
        this.messageResourceService = messageResourceService;
    }

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ErrorResponse> handleBaseExpection(BaseException ex) {
        final String message = messageResourceService.getMessage(ex.getMessageKey(), ex.getParams());
        ErrorResponse errorResponse = new ErrorResponse(message, ex.getHttpStatus().value());

        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ErrorResponse> handleValidationExpection(ValidationException ex) {
        final String message = messageResourceService.getMessage(ex.getMessageKey(), ex.getParams());
        ErrorResponse errorResponse = new ErrorResponse(message, ex.getHttpStatus().value());

        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String errorMessage = bindingResult.getFieldErrors().stream()
            .findFirst()
            .map(FieldError::getDefaultMessage)
            .orElseGet(() -> "Validation Failed!");
        ErrorResponse errorResponse = new ErrorResponse(messageResourceService.getMessage(errorMessage), HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}


