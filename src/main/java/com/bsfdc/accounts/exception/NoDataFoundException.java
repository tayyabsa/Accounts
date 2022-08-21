package com.bsfdc.accounts.exception;

public class NoDataFoundException extends RuntimeException {

    private String code;

    public NoDataFoundException() {
        super();
    }

    public NoDataFoundException(String code, String message) {
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
