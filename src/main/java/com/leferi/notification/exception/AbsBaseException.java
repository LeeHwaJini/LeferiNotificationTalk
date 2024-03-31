package com.leferi.notification.exception;

public abstract class AbsBaseException extends Exception{

    public AbsBaseException() {}

    public AbsBaseException(Exception e) {
        super(e);
    }

    public abstract long getErrorCode();
    public abstract String getErrorMsg();
}
