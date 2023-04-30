package com.ecommerce.usermgmtapi.exception;

import com.ecommerce.usermgmtapi.constants.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse resourceNotFoundException(MethodArgumentNotValidException ex) {
        // Collect field fieldErrorMap in map
        Map<String, String> fieldErrorMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrorMap.put(fieldName, errorMessage);
        });

        // Build and return error response body
        return new ErrorResponse(ErrorMessages.PAYLOAD_VALIDATION_FAILED, fieldErrorMap);
    }

}
