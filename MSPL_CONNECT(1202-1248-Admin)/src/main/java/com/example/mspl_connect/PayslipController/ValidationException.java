package com.example.mspl_connect.PayslipController;

import java.util.List;

public class ValidationException extends Exception {
    private List<String> validationErrors;

    public ValidationException(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }
    
    @Override
    public String getMessage() {
        return String.join("\n", validationErrors);
    }
}
