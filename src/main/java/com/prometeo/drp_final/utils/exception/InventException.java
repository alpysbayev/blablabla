package com.prometeo.drp_final.utils.exception;

public class InventException extends RuntimeException {

    String Code_error ;
    public InventException(String message) {
        super(message);
    }

    public InventException(String message, String code_) {
        super(message);
        this.Code_error = code_;
    }

    public String GetExceptionCode() {
        return this.Code_error;
    }

}
