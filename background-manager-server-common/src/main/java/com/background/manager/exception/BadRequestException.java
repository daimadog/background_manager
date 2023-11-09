package com.background.manager.exception;

import com.background.manager.exception.base.IBaseException;

public class BadRequestException extends RuntimeException  {
    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public BadRequestException() {
        super();
    }

    public BadRequestException(IBaseException iBaseException) {
        super(iBaseException.getMessage());
        this.errorCode = iBaseException.getErrorCode();
        this.errorMsg = iBaseException.getMessage();
    }

    public BadRequestException(IBaseException iBaseException, Throwable cause) {
        super(iBaseException.getMessage(), cause);
        this.errorCode = iBaseException.getErrorCode();
        this.errorMsg = iBaseException.getMessage();
    }

    public BadRequestException(IBaseException iBaseException, String errorMsg) {
        super(iBaseException.getCode());
        this.errorCode = iBaseException.getErrorCode();
        this.errorMsg = errorMsg;
    }

    public BadRequestException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BadRequestException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
