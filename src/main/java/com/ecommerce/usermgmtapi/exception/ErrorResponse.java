package com.ecommerce.usermgmtapi.exception;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorResponse {

    private String message;
    private Map<String, String> fieldErrors;
    private Date timestamp;

    public ErrorResponse(String message) {
        this.timestamp = new Date();
        this.message = message;
        this.fieldErrors = new HashMap<>();
    }

    public ErrorResponse(String message, Map<String, String> errors) {
        this.timestamp = new Date();
        this.message = message;
        this.fieldErrors = errors;
    }

}
