package com.leferi.notification.dto;

import java.time.LocalDateTime;

import com.leferi.notification.exception.AbsBaseException;
import com.leferi.notification.exception.ExceptionUtils;

public class CommonRestResponse<T> {
    public static final int RESULT_CODE_OK = 200;
    public static final int RESULT_CODE_ERROR = 500;

    long resCd; // 응답 코드
    String msg; // 사용자 전달 메시지
    String desc; // 부가 정보,에러 관련?
    LocalDateTime tracTime;

    T data; // 주고 받는 데이터

    public long getResCd() {
        return resCd;
    }

    public CommonRestResponse<T> setResCd(long resCd) {
        this.resCd = resCd;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public CommonRestResponse<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public CommonRestResponse<T> setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public LocalDateTime getTracTime() {
        return tracTime;
    }

    public CommonRestResponse<T> setTracTime(LocalDateTime tracTime) {
        this.tracTime = tracTime;
        return this;
    }

    public T getData() {
        return data;
    }

    public CommonRestResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> CommonRestResponse<T> OK() {
        return CommonRestResponse.OK("OK", "", null);
    }

    public static <T> CommonRestResponse<T> OK(String msg) {
        return CommonRestResponse.OK(msg, "", null);
    }

    public static <T> CommonRestResponse<T> OK(T data) {
        return CommonRestResponse.OK("OK", "", data);
    }


    public static <T> CommonRestResponse<T> OK(String msg, String desc) {
        return CommonRestResponse.OK(msg, desc, null);
    }

    public static <T> CommonRestResponse<T> OK(String msg, String desc, T data) {
        return new CommonRestResponse<T>()
                .setTracTime(LocalDateTime.now())
                .setResCd(RESULT_CODE_OK)
                .setMsg(msg)
                .setDesc(desc)
                .setData(data)
                ;
    }

    public static <T> CommonRestResponse<T> ERROR(long errorCode, String msg) {
        return new CommonRestResponse<T>()
                .setTracTime(LocalDateTime.now())
                .setResCd(errorCode)
                .setMsg(msg)
                ;
    }

    public static <T> CommonRestResponse<T> ERROR(AbsBaseException e) {
        return ERROR(e.getErrorCode(), e.getErrorMsg(), ExceptionUtils.StackTraceToString(e));
    }

    public static <T> CommonRestResponse<T> ERROR(AbsBaseException e, T data) {
        return ERROR(e.getErrorCode(), e.getErrorMsg(), ExceptionUtils.StackTraceToString(e), data);
    }


    public static <T> CommonRestResponse<T> ERROR(long errorCode, String msg, String desc) {
        return new CommonRestResponse<T>()
                .setTracTime(LocalDateTime.now())
                .setResCd(errorCode)
                .setMsg(msg)
                .setDesc(desc)
                ;
    }

    public static <T> CommonRestResponse<T> ERROR(long errorCode, String msg, String desc, T data) {
        return new CommonRestResponse<T>()
                .setTracTime(LocalDateTime.now())
                .setResCd(errorCode)
                .setMsg(msg)
                .setDesc(desc)
                .setData(data)
                ;
    }


}
