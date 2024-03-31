package com.leferi.notification.exception;

public class NotFoundException extends AbsBaseException {
    @Override
    public long getErrorCode() {
        return 200;
    }

    @Override
    public String getErrorMsg() {
        return "정보가 없습니다.";
    }
}
