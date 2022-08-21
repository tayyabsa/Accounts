package com.bsfdc.accounts.exception;

public class ApplicationException extends RuntimeException{
    private String code;

    public ApplicationException() {
        super();
    }

    public ApplicationException(String code, String message) {
        super(message);
        setCode(code);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
