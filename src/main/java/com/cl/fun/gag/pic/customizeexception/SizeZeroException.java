package com.cl.fun.gag.pic.customizeexception;

import lombok.Data;

@Data
public class SizeZeroException extends RuntimeException {
    private Integer code;

    private String errorMsg;

    public SizeZeroException(Integer code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public SizeZeroException( String errorMsg) {
        super(errorMsg);
    }

    public SizeZeroException() {
    }
}
