package com.leferi.notification.exception;

public class InternalServerException extends AbsBaseException {

    public InternalServerException(Exception e) {
        super(e);
    }

    @Override
    public long getErrorCode() {
        return 404;
    }

    @Override
    public String getErrorMsg() {
        return "서버내부 에러";
    }
}
